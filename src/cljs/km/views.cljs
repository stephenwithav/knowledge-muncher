(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   ))

(defn make-cell [cell]
  [:div {:class "game-cell"}
   cell])

(defn main-panel []
  (let [cells (re-frame/subscribe [:new-game])]
    (fn []
      [:div {:id "game-board"}
       (map keys [{:か "ka"} {:く "ku"}])
       [:button
        {:on-click #(re-frame/dispatch [:new-game])}
        "reset"]
       ])))
