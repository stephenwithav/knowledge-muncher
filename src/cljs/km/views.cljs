(ns km.views
  (:require
   [re-frame.core :as re-frame]
   [km.subs :as subs]
   [km.db :as db]
   ))


(defn make-cell
  [cell cell-num]
  [:div {:class "game-cell" :key (gensym "cell")
         :on-click #(re-frame/dispatch [:check-cell cell cell-num])}
   cell])


(defn game-level-panel
  []
  (let [state (re-frame/subscribe [:next-level])]
    (fn []
      (let [cells (:current-board @state)
            seek-native (:current-level-native @state)
            seek-foreign (:current-level-foreign @state)
            lang (:language @state)]
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


(defn game-over-panel
  []
  (fn []
    [:h1 "You win!"]))


(defn language-option
  [[language-key language-db]]
  [:option {:key (gensym "lang") :value language-key}
   (:language language-db)])


(defn language-select-panel
  []
  (fn []
    [:div
     [:div {:class "header"} "Knowledge Munchers!"]
     [:select {:on-change #(re-frame/dispatch [:change-panel %])}
      (map language-option db/all-language-boards)]
     ]))


(defn main-panel
  []
  (fn []
    (let [activep (re-frame/subscribe [:active-panel])
          active (:active-panel @activep)]
      (.log js/console "Active: " active)
      (condp = active
        :game-on game-level-panel
        :game-over game-over-panel
        language-select-panel
        ))))
