(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   ))

(defn make-cell [cell]
  [:div {:class "game-cell" :key (gensym "cell")}
   (first cell)])

(defn main-panel []
  (let [state (re-frame/subscribe [:next-level])]
    (fn []
      (.log js/console (keys @state))
      (let [cells (:current-board @state)
            seek (:current-level @state)
            lang (:language @state)]
        (.log js/console (str seek " from " lang))
        (.log js/console cells)
        [:div {:id "game-container"}
         [:div {:id "lang"} lang]
         [:div {:id "game-board"}
          (map make-cell cells)
          [:button
           {:on-click #(re-frame/dispatch [:new-game])}
           "reset"]
          [:button
           {:on-click #(re-frame/dispatch [:next-level])}
           "next level"]
          ]
         [:div {:id "game-level"} seek]]))))
