(ns advent-of-code.day-9
  (:require [advent-of-code.util :as util]
            [clojure.math.combinatorics :as c]))


(defn p1
  [in preamble-length]
    (for [[x i] (map-indexed vector (drop preamble-length in))]
      (let [last-5 (take preamble-length (drop x in))
            pre-sums (for [c (c/combinations last-5 2)] (reduce + c))]
        (if (some #{i} pre-sums) nil i))))


(defn p2
  [])


(defn -main
  [& args]
  (let [in (map #(Long/parseLong %) (util/read-problem-input "resources/p9-input.txt"))]
    (first (filter some? (p1 in 25)))))