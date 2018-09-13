(ns km.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [km.events :as events]
   [km.views :as views]
   [km.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:new-game])
  (dev-setup)
  (mount-root))
