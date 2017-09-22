(ns ^:figwheel-no-load angular-phonecat-re-frame.dev
  (:require
    [angular-phonecat-re-frame.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
