(ns angular-phonecat-re-frame.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to angular-phonecat-re-frame 123"]
   [:div [:a {:href "/about"} "go to about page"]]
   [:ul
     [:li
      [:span "Nexus S"]
      [:p "Fast just got faster with Nexus S."]]
     [:li
      [:span "Motorola XOOM with Wi-Fi 123123"]
      [:p "The Next, Next Generation tablet."]]]])

(defn about-page []
  [:div [:h2 "About angular-phonecat-re-frame 23"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(def page (atom #'home-page))

(defn current-page []
  (+ 12 1)
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
