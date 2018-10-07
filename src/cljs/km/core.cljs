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
  ;; rf/dispatch-sync starts the ball rolling.
  ;; instead of :new-game, we want it to show
  ;; a language selector: language-select-panel.
  ;;
  ;; Dispatch triggers the event, which updates the world's state.
  ;;
  ;; The state is then read/extrcted in subs, which is then displayed
  ;; in views.
  ;;
  ;; The initial state should be the start screen, where a user can
  ;; choose the language/character set to study.
  (re-frame/dispatch-sync [:new-game])
  (dev-setup)
  (mount-root))
