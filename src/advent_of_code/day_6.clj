(ns advent-of-code.day-6
  (:require [advent-of-code.util :as util]))

(defn split-by [pred coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [!pred (complement pred)
            [xs ys] (split-with !pred s)]
        (if (seq xs)
          (cons xs (split-by pred ys))
          (let [skip (take-while pred s)
                others (drop-while pred s)
                [xs ys] (split-with !pred others)]
            (cons (concat skip xs)
                  (split-by pred ys))))))))

(defn p6
  [input]
  (reduce +
    (let [groups (split-by #{""} input)]
      (for [group groups]
        (let [group (filter not-empty group)]
          (count (distinct (reduce concat group))))))))


(defn -main
  [& args]
  (println (p6 (util/read-problem-input "resources/p6-input.txt"))))