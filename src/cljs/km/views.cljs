(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   ))

(defn make-cell [cell]
  [:div {:class "game-cell" :key (gensym "cell")}
   (first cell)])

(defn main-panel []
  (let [state (re-frame/subscribe [:new-game])
        cells (:cells @state)]
    (fn []
      (.log js/console (:cells @state))
      [:div {:id "game-container"}
       [:div {:id "lang"} "Language: Japanese"]
       [:div {:id "game-board"}
       (map make-cell (:cells @state))
       [:button
        {:on-click #(re-frame/dispatch [:new-game])}
        "reset"]
       ]
       [:div {:id "game-level"} "Level: 0"]])))
