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

(re-frame/reg-event-db
 :new-game
 (fn [_ _]
   (let [n (rand-int (count db/all-language-boards))
         language (nth db/all-language-boards n)]
     (.log js/console (str "n = " n))
     (.log js/console (str "lang = " language))
     {:cells (take 30 #(generate-cells language))})))
