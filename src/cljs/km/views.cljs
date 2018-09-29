(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   ))

(defn make-cell [cell cell-num]
  [:div {:class "game-cell" :key (gensym "cell")
         :on-click #(re-frame/dispatch [:check-cell cell cell-num])}
   cell])

(defn main-panel []
  (let [state (re-frame/subscribe [:next-level])]
    (fn []
      (.log js/console @state)
      (let [cells (:current-board @state)
            seek-native (:current-level-native @state)
            seek-foreign (:current-level-foreign @state)
            lang (:language @state)]
        (.log js/console (str seek-native " from " lang))
        (.log js/console cells)
        [:div {:id "game-container"}
         [:div {:id "lang"} lang]
         [:div {:id "game-board"}
          (map make-cell cells (range))
          [:button
           {:on-click #(re-frame/dispatch [:new-game])}
           "reset"]
          [:button
           {:on-click #(re-frame/dispatch [:next-level])}
           "next level"]
          ]
         [:div {:id "game-level"} seek-native]]))))
