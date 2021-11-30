(ns advent-of-code.day_8-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-8 :refer [p1 p2]]
            [advent-of-code.util :refer [read-problem-input]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [answer 5]
    (is (= answer (p1 (read-problem-input "resources/p8-ex.txt"))))))

;(deftest part2
;  (let [answer 8]
;    (is (= answer (p2 (slurp (resource "p8-ex.txt")))))))