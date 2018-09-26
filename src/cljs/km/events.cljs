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
   (let [board (rand-nth (keys db/all-language-boards))
         language (board db/all-language-boards)]
     (.log js/console (str "board:" board))
     (.log js/console (str "chars:" (:chars language)))
     {:db {:levels-to-go (:chars language)
           :language (:language language)
           :all-levels (:chars language)}
      :dispatch [:next-level]})))


(re-frame/reg-event-fx
 :next-level
 (fn [{:keys [db]} _]
   (let [native-character (get-native-char db)
         foreign-character (get-foreign-char db)
         invalid-chars (take 5 ((comp shuffle keys dissoc) (:all-levels db) foreign-character))
         ]
     (when-not (nil? native-character)
      (.log js/console "kana:" foreign-character "=" native-character)
      (.log js/console "invalid:" invalid-chars)
      {:db (assoc db
                  :current-level native-character
                  :levels-to-go (rest (:levels-to-go db))
                  :current-board (shuffle
                                  (take 30
                                        (cycle
                                         (conj invalid-chars foreign-character))))
                  )}))))
