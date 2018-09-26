(ns km.events
  (:require
   [re-frame.core :as re-frame]
   [km.db :as db]
   ))

(defn move-allowed-from? [dir cell]
  "Is movement in dir allowed from this cell?

   Returns a boolean."
  (not (contains? (:disallowed cell) dir)))


(defn generate-cells
  "Returns an infinite lazy-seq of randomly chosen keys from the supplied map.

   Accepts optional second argument, which specifies how many elements
   are chosen from the collection; defaults to 6."
  ([] (generate-cells ((comp first shuffle) db/all-language-boards)))
  ([char-map] (generate-cells char-map 6))
  ([char-map n]
   (let [chars-of-interest ((comp seq (partial take n) shuffle seq) char-map)]
     (map
      (partial nth chars-of-interest)
      (repeatedly #(rand-int n))))))

(re-frame/reg-event-fx
 :new-game
 (fn [_ _]
   (.log js/console (keys db/all-language-boards))
   (let [board (rand-nth (keys db/all-language-boards))
         language (board db/all-language-boards)
         chars (:chars language)]
     {:db {:levels chars
           :language (:language language)}
      :dispatch [:next-level]})))


(re-frame/reg-event-fx
 :next-level
 (fn [{:keys [db]} _]
   (.log js/console (str "in :next-level handler: " db))
   (let [levels (:levels db)
         character-to-find (first levels)
         [kana-character romaji-character] character-to-find
         invalid-chars (take 5 (shuffle (dissoc levels character-to-find)))
         ]
   {:db (assoc db
               :current-level romaji-character
               :levels (rest levels)
               :current-board (shuffle
                               (take 30
                                     (cycle
                                      (conj invalid-chars character-to-find))))
               )})))
