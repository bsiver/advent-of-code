(ns advent-of-code.day-6
  (:require [advent-of-code.util :as util]))

(defn p6
  [input]
  (reduce +
    (let [groups (util/split-by #{""} input)]
      (for [group groups]
        (let [group (filter not-empty group)]
          (count (distinct (reduce concat group))))))))

(defn p6-part2
  [input]
  (reduce +
          (let [groups (util/split-by #{""} input)]
            (for [group groups]
              (let [group (filter not-empty group)]
                (count (apply clojure.set/intersection (map set group))))))))

(defn -main
  [& args]
  (println (p6-part2 (util/read-problem-input "resources/p6-input.txt"))))