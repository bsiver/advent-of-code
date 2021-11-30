(ns advent-of-code.day-2
  (:require [clojure.string :as str]
            [advent-of-code.util :as util]))

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

(defn p2-part2-is-valid
  [line]
  (let [components (str/split line #" ")
        indices (str/split (first components) #"-")
        index-one  (dec (Integer/parseInt (first indices)))
        index-two (dec (Integer/parseInt (second indices)))
        rule-char (first (second components))
        password (nth components 2)
        first-char-to-check (nth password index-one 0)
        second-char-to-check (nth password index-two 0)]
    (do
      (println (format "Checking pw %s | indices %s | i1 %s i2 %s | first-char %s | second-char %s | rule-char %s"
                       password indices index-one index-two first-char-to-check second-char-to-check rule-char)))
    (Boolean/logicalXor (= first-char-to-check rule-char) (= second-char-to-check rule-char))))

(defn p2
  [input]
  (count (filter p2-is-valid input)))

(defn p2-part2
  [input]
  (count (filter p2-part2-is-valid input)))

(defn -main
  [& args]
  (println (p2 (util/read-problem-input ("resources/p2-input.txt")))))
  (println (p2-part2 (util/read-problem-input ("resources/p2-input.txt")))))