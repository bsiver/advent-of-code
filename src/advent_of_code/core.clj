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


(defn p2-is-valid
  [line]
  (let [components (str/split line #" ")
        range (str/split (first components) #"-")
        range-min (Integer/parseInt (first range))
        range-max (Integer/parseInt (second range))
        rule-char (first (second components))
        password (nth components 2)
        occurrence-count (get (frequencies password) rule-char 0)]
        (<= range-min occurrence-count range-max)))

(defn p2
  [input]
  (count (filter p2-is-valid input)))


(defn -main
  [& args]
  ;(p1 (read-problem-input ("resources/p1-input.txt")))
  (println (format "P2 answer: %s" (p2 (read-problem-input "resources/p2-input.txt")))))
