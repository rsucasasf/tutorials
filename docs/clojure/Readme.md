## Clojure

Clojure tutorials, links, help ....

-----------------------

**Table of Contents**

- Clojure
  - [Requirements](#requirements)
  - [Readable Clojure](http://tonsky.me/blog/readable-clojure/) : "*This is how you can make Clojure code more pleasant to work with*"
  - [Deploying to Maven Central](../maven/deploy.md), taken from https://github.com/technomancy/leiningen
  - [Leiningen](#leiningen)
    - [Create a web project](#create-a-web-project)
    - [Tutorial](leiningen_tutorial.md), taken from https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md
  - Export to maven (local repo)
  - [gen-class](#gen-class)
  - [Deploying to CLOJARS](#deploying-to-clojars)
    - Update *.jar*
  - [sample.project.clj](sample.project.clj), taken from https://github.com/technomancy/leiningen/blob/stable/sample.project.clj

-----------------------

#### Requirements

1. Java 7, 8

2. Install ['leiningen'](http://leiningen.org/)

-----------------------

#### Leiningen

- Download from https://leiningen.org/ and install

- Create a project:

```bash
lein new app APP_NAME
```

- Compile (from project root path):

```bash
lein compile
```

- Create jar:

```bash
lein uberjar
...
java -jar APP_NAME-standalone.jar [args]
```

##### Create a web project

```bash
lein new compojure PROJECT_NAME
```

- project.clj

```clojure
(defproject xxxxx "0.1.1"
  :description "..."
  :url "http://localhost"
  :min-lein-version "1.8.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [cheshire "5.7.1"]
                 [clj-http "3.5.0"]
                 [ring-cors/ring-cors "0.1.10"]
                 [ring/ring-defaults "0.3.0"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j/log4j "1.2.17"
                  :exclusions [javax.mail/mail
                              javax.jms/jms
                              com.sun.jdmk/jmxtools
                              com.sun.jmx/jmxri]]
                 [proto-repl "0.3.1"]]
  :plugins [[lein-ring "0.12.0"]]
  :ring {:handler xxxxx.handler/app
         :port 8082
         :open-browser? true
         :resources-war-path "WEB-INF/classes/"}
  :profiles
    {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                          [ring/ring-mock "0.3.0"]]}
  ;; jvm configuration
  :jvm-opts ["-Xmx256M"]})
```

- Start server from root path:

```bash
lein ring server
```

-----------------------

#### Export to maven local repo

- Create jar:

```bash
lein uberjar
```

- Create artifact in local repo:

```bash
cd target

cd uberjar

mvn install:install-file -Dfile=cloj-rules-engine-0.1.1-standalone.jar -DgroupId=cloj-libs -DartifactId=cloj-rules-engine -Dversion=0.1.1 -Dpackaging=jar
```

- Use in a Java project:
    - Add to maven dependencies:

```xml
<dependency>
  <groupId>cloj-libs</groupId>
  <artifactId>cloj-rules-engine</artifactId>
  <version>0.1.1</version>
</dependency>
```

-----------------------

#### gen-class

- Generate classes from Clojude code
  - project.clj
  - return types
  - objects

Clojure - Java interop:

##### Generate classes from Clojude code

###### project.clj

```clojure
(defproject xxxx "0.1.12"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j/log4j "1.2.17"
                  :exclusions [javax.mail/mail
                               javax.jms/jms
                               com.sun.jdmk/jmxtools
                               com.sun.jmx/jmxri]]
                 [proto-repl "0.3.1"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot xxxx.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:resource-paths ["resources"]}})
```

###### return types

- Return String array: `"[Ljava.lang.String;"`
- Return ArrayList: `java.util.ArrayList`
- Example:

```clojure
(ns olivia.utils.json
  (:use [clojure.walk])
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [olivia.common.common :as comm])
  (:gen-class
    :name olivia.utils.IWJson
    :methods [#^{:static true} [getArrayList [] java.util.ArrayList]
             [setLocation [String] void]
             [getLocation [] String]
             [getPathsGoal [String] "[Ljava.lang.String;"]]))
```

###### objects

```clojure
(ns cloj-rules-engine.rules-mng-java
  "Rules-engine library used by Java"
  (:use [clojure.math.numeric-tower])
  (:require [cloj-rules-engine.rules-funcs :as rules-funcs]
            [cloj-rules-engine.conds-eval :as conds-eval]
            [cloj-rules-engine.logs :as logs]
            [cloj-rules-engine.common :as common]
            [clojure.data.json :as json])
  (:gen-class
    :name cloj_rules_engine.ClojRules
    :state state
    :init init
    :prefix "-"
    :implements [clojure.lang.IDeref]
    :main false
    :methods [[initialize [String] boolean]
              [initializeFromJson [String] boolean]
              [updateMapFacts [clojure.lang.PersistentArrayMap] void]
              [getRulesActions [] java.util.ArrayList]
              [getFiredRules [] java.util.ArrayList]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; CLASS OBJECTS FUNCTIONS (JAVA INTEROP)

;; FUNCTION:set-field
;; function to safely set the field content
(defn set-field "function to safely set the field content"
  [this key value]
  (swap! (.state this) into {key value}))

;; FUNCTION:get-field
;; function to safely get the field content
(defn get-field "function to safely get the field content"
  [this key]
  (@(.state this) key))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; FUNCTION:-init
;; Set defaults
(defn -init []
  "store fields: values, conds & rules"
  [[] (atom {:values {}
             :rules {}
             :conds {}})])

;; FUNCTION: initialize
;; 'rules-file': rules file (relative/absolute path of the rules-file)
(defn -initialize "Intializes rules and conditions map"
  [this rules-file]
  (if-let [rules-map-content (common/read-content rules-file)]
    (do
      (common/set-field this :rules rules-map-content)
      (common/set-field this :conds (conds-eval/gen-conds-map (common/get-field this :rules)))
      true)
    false))

;; FUNCTION: initialize-from-json
;; (json/read-str json-map-str :key-fn keyword)
(defn -initializeFromJson "Intializes rules and conditions map from json string"
  [this json-map-str]
  (try
    (do
      (common/set-field this :rules (json/read-str json-map-str :key-fn keyword))
      (common/set-field this :conds (conds-eval/gen-conds-map (common/get-field this :rules)))
      true)
    (catch Exception e
      (do (logs/log-exception e) false))))
```


-----------------------

#### Deploying to CLOJARS

1. Create account in [CLOJARS](https://clojars.org/)

2. Install [GPG](https://gpg4win.org/download.html)

3. Execute:

```bash
lein deploy clojars
```

This command will take the values from *project.clj* file:

```clojure
(defproject clojars.org/cloj-rules-engine "0.1.2-SNAPSHOT"
  ...
```

The generated 'links' will look like that:

```
[clojars.org/cloj-rules-engine "0.1.2-SNAPSHOT"]
```

```xml
<repositories>
	<repository>
		<id>clojars</id>
		<url>http://clojars.org/repo/</url>
	</repository>
</repositories>
```

```xml
<dependency>
  <groupId>clojars.org</groupId>
  <artifactId>cloj-rules-engine</artifactId>
  <version>0.1.2-SNAPSHOT</version>
</dependency>
```

4. Use CLOJARS account username & password

###### tips, problems found, ...

Using `lein deploy clojars` creates a *.jar* file without dependencies.

I couldn't find a way to deploy a "uberjar" generated *.jar*


##### Update .jar

To update or change the clojars .jar file, do the following:

```bash
lein deploy clojars clojars.org/cloj-rules-engine 0.1.2-SNAPSHOT target/cloj-rules-engine-0.1.2-SNAPSHOT.jar target/cloj-rules-engine-0.1.2-SNAPSHOT.pom
```

In order to create the *.pom* needed to upload the *.jar*, use the one generated:

```bash
mvn install:install-file -Dfile=cloj-rules-engine-0.1.2-SNAPSHOT-standalone.jar -DgroupId=clojars.org -DartifactId=cloj-rules-engine -Dversion=0.1.2-SNAPSHOT -Dpackaging=jar
```
