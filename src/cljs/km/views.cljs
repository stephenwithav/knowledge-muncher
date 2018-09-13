(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   [km.events :as e]
   ))

(defn make-cell [cell]
  [:div {:class "game-cell"}
   cell])

(defn main-panel []
  (let [cells (re-frame/subscribe [:new-game])]
    [:div {:id "game-board"}
     [:button
      {:on-click #(re-frame/dispatch [:new-game])}
      "reset"]
     ]))
