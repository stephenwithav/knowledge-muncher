(ns km.events
  (:require
   [re-frame.core :as re-frame]
   [km.db :as db]
   ))

(def get-foreign-char (comp first first :levels-to-go))
(def get-native-char (comp second first :levels-to-go))

(re-frame/reg-event-fx
 :new-game
 (fn [_ _]
   (.log js/console "New game triggered.")
   (let [board (rand-nth (keys db/all-language-boards))
         language (board db/all-language-boards)]
     {:db {:levels-to-go (:chars language)
           :language (:language language)
           :all-levels (:chars language)
           :active-panel :game-on}
      :dispatch [:next-level]})))


(re-frame/reg-event-fx
 :next-level
 (fn [{:keys [db]} _]
   (let [native-character (get-native-char db)
         foreign-character (get-foreign-char db)
         invalid-chars (take 5 ((comp shuffle keys dissoc) (:all-levels db) foreign-character))
         ]
     (when-not (nil? native-character)
      {:db (assoc db
                  :current-level-native native-character
                  :current-level-foreign foreign-character
                  :levels-to-go (rest (:levels-to-go db))
                  :current-board (shuffle
                                  (take 30
                                        (cycle
                                         (conj invalid-chars foreign-character))))
                  )}))))

(re-frame/reg-event-fx
 :check-cell
 (fn [{:keys [db]} [_ current-cell-value clicked-cell-num]]
   (let [sought-cell-value (get-in db [:current-level-foreign])]
     (if (= sought-cell-value current-cell-value)
       {:db (assoc-in db [:current-board clicked-cell-num] nil)
        :dispatch [:is-level-complete sought-cell-value]
        }))
  ))


(re-frame/reg-event-fx
 :is-level-complete
 (fn [{:keys [db]} [_ sought-cell-value]]
   (if-not (some #{sought-cell-value} (:current-board db))
     {:dispatch [:is-game-over]})
  ))


(re-frame/reg-event-fx
 :is-game-over
 (fn [{:keys [db]} _]
   (if-not (empty? (:levels-to-go db))
     {:dispatch [:next-level]}
     {:dispatch [:game-over]})))


(re-frame/reg-event-fx
 :game-over
 (fn [{:keys [db]} _]
   (let [updated-db (assoc db
                           :active-panel :game-over)]
     (.log js/console "Game over." updated-db)
     {:db updated-db})))



(re-frame/reg-event-fx
 :change-panel
 (fn [{:keys [db]} [_ lang]]
   (.log js/console lang)
   {:dispatch [:new-game lang]}))
