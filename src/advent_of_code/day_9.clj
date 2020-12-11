(ns advent-of-code.day-9
  (:require [advent-of-code.util :as util]
            [clojure.math.combinatorics :as c]))


(defn p1
  [in preamble-length]
  (first (filter some?
    (for [[x i] (map-indexed vector (drop preamble-length in))]
      (let [last-5 (take preamble-length (drop x in))
            pre-sums (for [c (c/combinations last-5 2)] (reduce + c))]
        (if (some #{i} pre-sums) nil i))))))


(defn find-continuous-subseq
  [nums target]
  (loop [nums nums,
         len 2]
    (cond
      (= target (first nums)) (list 0 0)
      :else (let [rng (take len nums)
                  sum (apply + rng)]
              (cond
                (= sum target) rng
                (< sum target) (recur nums (inc len))
                :else          (recur (rest nums) 2))))))

(defn p2
  [in preamble-length]
  (let [in (map #(Long/parseLong %) (util/read-problem-input in))
        p2-target (p1 in preamble-length)
        p2-min (apply min (find-continuous-subseq in p2-target))
        p2-max (apply max (find-continuous-subseq in p2-target))]
    (+ p2-min p2-max)))
