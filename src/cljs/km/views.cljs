(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   ))

(defn make-cell [cell]
  [:div {:class "game-cell" :key (gensym "cell")}
   (first cell)])

(defn main-panel []
  (let [state (re-frame/subscribe [:new-game])]
    (fn []
      (let [cells (:cells @state)
            seek (:seek @state)
            lang (:language @state)]
        [:div {:id "game-container"}
         [:div {:id "lang"} lang]
         [:div {:id "game-board"}
          (map make-cell cells)
          [:button
           {:on-click #(re-frame/dispatch [:new-game])}
           "reset"]
          ]
         [:div {:id "game-level"} seek]]))))
