(ns advent-of-code.core
  (:require [clojure.string :as str]))

(defn read-problem-input
  [file-name]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (reduce conj [] (line-seq rdr))))


(defn p1
  [input]
  (let [target-sum 2020]
    (doseq [x input]
      (if (some #{(- target-sum x)} input)
        (let [match (- target-sum x)]
          (println (format "Found a match! %s %s" x match))
          (println (format "Result is %s" (* x match))))))))


(defn -main
  [& args])
  ;(p1 (read-problem-input ("resources/p1-input.txt")))
  ;(println (format "P2 answer: %s" (p2 (read-problem-input "resources/p2-input.txt"))))
  ;(println (format "P2-2 answer: %s" (p2-part2 (read-problem-input "resources/p2-input.txt")))))
  ;(println (format "P3 answer: %s" (p3 (read-problem-input "resources/p3-input.txt")))))