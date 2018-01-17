# _hello-world_

_FIXME: ...._

## Overview

_FIXME: ...._

## Setup

1. Create new project:

```
lein new figwheel hello-world -- --reagent
```

2. Add ring handler to /src/_hello_world_

```clojure
(ns example.server-handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response]))

;; If you are new to using Clojure as an HTTP server please take your
;; time and study ring and compojure. They are both very popular and
;; accessible Clojure libraries.

;; --> https://github.com/ring-clojure/ring
;; --> https://github.com/weavejester/compojure

(defroutes app-routes
  ;; NOTE: this will deliver all of your assets from the public directory
  ;; of resources i.e. resources/public
  (route/resources "/" {:root "public"})
  ;; NOTE: this will deliver your index.html
  (GET "/" [] (-> (response/resource-response "index.html" {:root "public"})
                  (response/content-type "text/html")))
  (GET "/hello" [] "Hello World there!")
  (route/not-found "Not Found"))

;; NOTE: wrap reload isn't needed when the clj sources are watched by figwheel
;; but it's very good to know about
(def app (wrap-reload (wrap-defaults #'app-routes site-defaults)))
```

3. Edit _project.clj_ (add ring references)

```clojure
(defproject example "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/core.async "0.2.395"
                  :exclusions [org.clojure/tools.reader]]

                 ;; NOTE: common clojure server side libraries
                 [ring "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [compojure "1.5.0"]]

  :plugins [[lein-figwheel "0.5.14"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-ring "0.12.0"]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "example.core/on-js-reload"
                           :open-urls ["http://localhost:3449"]}

                :compiler {:main example.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/example.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/example.js"
                           :main example.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {;; NOTE: configure figwheel to embed your ring-handler
             :ring-handler example.server-handler/dev-app
             :css-dirs ["resources/public/css"]}

  :ring {:handler example.server-handler/dev-app
         :port 8082
         :open-browser? false
         :resources-war-path "WEB-INF/classes/"}

  ;; NOTE: compile and package up your project for deployment
  ;; with `lein package`
  :aliases {"package" ["do" "clean"
                       ["cljsbuild" "once" "min"]
                       ["ring" "uberjar"]]}

  ;; setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.0"]
                                  [figwheel-sidecar "0.5.10-SNAPSHOT"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
```

4. To get an interactive development environment run:

```
lein figwheel
```

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

```
(js/alert "Am I connected?")
```

and you should see an alert in the browser window.

To clean all compiled files:

```
lein clean
```

To create a production build run:

```
lein do clean, cljsbuild once min
```

```
lein package
```

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

5. Create _.jar_ and run app:

```
lein package
```

```
java -jar target/hello-world-0.1.0-SNAPSHOT-standalone.jar
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
