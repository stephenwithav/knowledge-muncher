(ns km.generators
    (:require [clojure.data.generators :as gen]))

(defn move-allowed-from? [dir cell]
  "Is movement in dir allowed from this cell?

   Returns a boolean."
  (not (contains? (:disallowed cell) dir)))


(defn generate-cells
  "Returns an infinite lazy-seq of randomly chosen keys from the supplied map.

   Accepts optional second argument, which specifies how many elements
   are chosen from the collection; defaults to 6."
  ([char-map] (generate-cells char-map 6))
  ([char-map n]
   (let [chars-of-interest ((comp seq (partial take n) shuffle seq) char-map)]
     (map
      (partial nth chars-of-interest)
      (repeatedly #(gen/uniform 0 n))))))
