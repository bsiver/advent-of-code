(ns advent-of-code.day-11
  (:require [advent-of-code.util :as util]
            [clojure.math.combinatorics :as c]
            [clojure.string :as cs]))

(defn parse-into-to-2d-array
  [in]
  (to-array-2d (mapv #(-> % str vec) in)))

(defn neighbors
  [rows r c]
  (let [deltas [[-1 -1] [-1 0] [-1 1]
                [0 -1]         [0 1]
                [1 -1]  [1 0]  [1 1]]]
    (filter some?
            (for [[x y] deltas]
              (try
                (aget rows (+ r x) (+ c y))
                (catch ArrayIndexOutOfBoundsException e))))))

(defn next-val
  [cur-val neighbors]
  (cond
    (and (= cur-val \L) (empty? (filter #(= % \#) neighbors))) \#
    (and (= cur-val \#) (>= (count (filter #(= % \#) neighbors)) 4)) \L
    :else cur-val))

(defn apply-rules
  [in]
  (let [rows (parse-into-to-2d-array in)]
    (mapv #(cs/join %)
          (for [i (range (count rows))]
            (for [j (range (count rows))]
              (next-val (aget rows i j) (neighbors rows i j)))))))


(defn p1
  [in]
  (loop [last in]
    (let [new-rules (apply-rules last)]
      (println new-rules)
      (cond
        (= new-rules last) (get (frequencies (cs/join last)) \#)
        :else (recur new-rules)))))




(defn -main
  [& args]
  (p1 (util/read-problem-input "resources/p11-input.txt")))