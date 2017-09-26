(defproject snake-game "0.1.1-SNAPSHOT"
  ;; Description
  :description "snake-game! from... http://lambdax.io/blog/posts/2016-01-19-snake-game-part-1.html"
  ;; URL
  :url "http://example.com/FIXME"
  ;; License
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;; Dependencies
  :dependencies [[org.clojure/clojure "1.8.0"]            ;; https://clojure.org/community/downloads
                 [org.clojure/clojurescript "1.9.908"]    ;; https://github.com/clojure/clojurescript
                 [org.clojure/core.async "0.3.443"]       ;; https://github.com/clojure/core.async
                 [reagent "0.7.0"]                        ;; https://github.com/reagent-project/reagent
                 [re-frame "0.10.1"]]                     ;; https://github.com/Day8/re-frame
  ;; Plugins
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.13"]]                     ;; https://github.com/bhauman/lein-figwheel
  ;;
  :source-paths ["src"]
  ;;
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  ;;
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "snake-game.core/on-js-reload"}

                :compiler {:main snake-game.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/snake_game.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/snake_game.js"
                           :main snake-game.core
                           :optimizations :advanced
                           :pretty-print false}}]}
  ;;
  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server 
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })
