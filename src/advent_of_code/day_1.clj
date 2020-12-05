(ns advent-of-code.day-1
  (:require [advent-of-code.util :as util]))

(defn p1
  [input]
  (let [target-sum 2020]
    (doseq [x input]
      (if (some #{(- target-sum x)} input)
        (let [match (- target-sum x)]
          (println (format "Found a match! %s %s" x match))
          (* x match))))))

(defn -main
  [& args]
  (println (p1 (util/read-problem-input ("resources/p1-input.txt")))))