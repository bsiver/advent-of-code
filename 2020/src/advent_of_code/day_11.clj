(ns advent-of-code.day-11
  (:require [advent-of-code.util :as util]
            [clojure.math.combinatorics :as c]
            [clojure.string :as cs]))

(def deltas [[-1 -1] [-1 0] [-1 1]
             [0 -1]         [0 1]
             [1 -1]  [1 0]  [1 1]])

(defn parse-into-to-2d-array
  [in]
  (to-array-2d (mapv #(-> % str vec) in)))

(defn safe-aget
  [rows r c]
  (try
    (aget rows r c)
    (catch ArrayIndexOutOfBoundsException e)))

(defn neighbors
  [rows r c]
  (filter some?
          (for [[x y] deltas]
            (safe-aget rows (+ r x) (+ c y)))))


(defn next-val
  [cur-val neighbors empty-seat-threshold]
  (cond
    (and (= cur-val \L) (empty? (filter #(= % \#) neighbors))) \#
    (and (= cur-val \#) (>= (count (filter #(= % \#) neighbors)) empty-seat-threshold)) \L
    :else cur-val))

(defn apply-rules
  [in empty-seat-threshold neighbors-fn]
  (let [rows (parse-into-to-2d-array in)]
    (mapv #(cs/join %)
          (for [i (range (count rows))]
            (for [j (range (count rows))]
              (next-val (aget rows i j) (neighbors-fn rows i j) empty-seat-threshold))))))

(defn visible-seat?
  [rows dir y x]
  (let [start (mapv + [y x] dir)]
    (loop [pos start]
      (let [ch (safe-aget rows (first pos) (second pos))]
        (cond
          (nil? ch) nil
          (= ch \.) (recur (mapv + pos dir))
          (= ch \#) ch
          :else     nil)))))

(defn valid
  [[y x] maxy maxx]
  (and (< -1 y maxy) (< -1 x maxx)))

(defn
  neighbors-2
  [rows y x]
  (let [maxy (alength rows)
        maxx (alength rows)
        dirs (filter #(valid (mapv + [y x] %) maxy maxx) deltas)]
    (for [dir dirs]
      (visible-seat? rows dir y x))))

(defn p1
  [in]
  (loop [last in]
    (let [new-rules (apply-rules last 4 neighbors)]
      (cond
        (= new-rules last) (get (frequencies (cs/join last)) \#)
        :else (recur new-rules)))))

(defn p2
  [in]
  (loop [last in]
    (let [new-rules (apply-rules last 5 neighbors-2)]
      (cond
        (= new-rules last) (get (frequencies (cs/join last)) \#)
        :else (recur new-rules)))))

(defn -main
  [& args]
  (p2 (util/read-problem-input "resources/p11-input.txt")))