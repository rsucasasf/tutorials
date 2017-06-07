-----------------------

**Table of Contents**

- Clojure
  - Requirements
  - [Readable Clojure](http://tonsky.me/blog/readable-clojure/) : "*This is how you can make Clojure code more pleasant to work with*"
  - [Deploying to Maven Central](../maven/deploy.md), taken from https://github.com/technomancy/leiningen
  - Leiningen
    - Create a web project
  - Export to maven (local repo)
  - gen-class

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
                 [cheshire "5.7.1"]                   ; MIT License ; https://github.com/dakrone/cheshire
                 [clj-http "3.5.0"]                   ; MIT License ; https://github.com/dakrone/clj-http/
                 [ring-cors/ring-cors "0.1.10"]       ; Eclipse Public License ; https://github.com/r0man/ring-cors
                 [ring/ring-defaults "0.3.0"]         ; MIT License ; https://github.com/ring-clojure/ring-defaults
                 [ring/ring-json "0.4.0"]             ; MIT License ; https://github.com/ring-clojure/ring-json
                 [org.clojure/tools.logging "0.3.1"]  ; Eclipse Public License - Version 1.0 ; https://github.com/clojure/tools.logging
                 [log4j/log4j "1.2.17"                ; Apache License, Version 2.0 ; http://logging.apache.org/log4j/1.2/
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

###### project.clj

```clojure
(defproject xxxx "0.1.12"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]   ; ==> "0.3.1"  => Eclipse Public License - Version 1.0  https://github.com/clojure/tools.logging
                 [log4j/log4j "1.2.17"                 ; ==> "1.2.17" => Apache License, Version 2.0           http://logging.apache.org/log4j/1.2/  **JAVA**
                  :exclusions [javax.mail/mail
                               javax.jms/jms
                               com.sun.jdmk/jmxtools
                               com.sun.jmx/jmxri]]
                 [proto-repl "0.3.1"]
                 [org.clojure/data.json "0.2.6"]       ; ==> "0.2.6"  => Eclipse Public License - Version 1.0  https://github.com/clojure/data.json
                 [com.cerner/clara-rules "0.13.0"]]    ; ==> "0.13.0" => Apache License, Version 2.0           https://github.com/cerner/clara-rules
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
