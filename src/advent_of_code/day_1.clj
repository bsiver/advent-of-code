(ns advent-of-code.day-1
  (:require [advent-of-code.util :as util]))

(defn p1
  [input]
  (let [input (map #(Integer/parseInt %) input)
        target-sum 2020]
    (doseq [x input]
      (if (some #{(- target-sum x)} input)
        (let [match (- target-sum x)]
          (println (format "Found a match! %s %s" x match))
          (* x match))))))


(defn tails [data]
  (take-while seq (iterate rest data)))

(defn p1-part2
  [input]
  (let [in (map #(Integer/parseInt %) input)]
    (for [[x & xs] (tails in)
          [y & ys] (tails xs)
          [z] (tails ys)
          :when (= 2020 (+ x y z))]
      (reduce * [x y z]))))

(defn -main
  [& args]
  (println (p1-part2 (util/read-problem-input "resources/p1-input.txt"))))