(ns advent-of-code.day-3
  (:require [advent-of-code.util :as util]))

(defn p3
  [input]
  (loop [res 0
         input input
         tree-hits 0]
    (if (seq input)
      (do
        (let [this-row (first input)
              index (mod res (count this-row))
              this-char (nth this-row index)
              hit-tree (= this-char \#)]
          (println (format "res: %s %s " index this-row))
          (println (format "Character at this index %s" this-char))
          (println (format "Hit tree? %s" hit-tree))
          (println (format "Tree hits: %s" tree-hits))

          (recur (+ 3 res) (rest input) (if hit-tree (inc tree-hits) tree-hits))))
      res)))

(defn p3-part2
  [input x-dir y-dir]
  (loop [res 0
         input input
         tree-hits 0]
    (if (seq input)
      (do
        (let [this-row (first input)
              index (mod res (count this-row))
              this-char (nth this-row index)
              hit-tree (= this-char \#)
              hits (if hit-tree (inc tree-hits) tree-hits)]
          ;(println (format "res: %s %s " index this-row))
          ;(println (format "Character at this index %s" this-char))
          ;(println (format "Hit tree? %s" hit-tree))
          (recur (+ x-dir res) (drop y-dir input) hits)))
      tree-hits)))

(defn -main
  [& args]
  (println (p3-part2 (util/read-problem-input "resources/p3-input.txt") 1 1))
  (println (p3-part2 (util/read-problem-input "resources/p3-input.txt") 3 1))
  (println (p3-part2 (util/read-problem-input "resources/p3-input.txt") 5 1))
  (println (p3-part2 (util/read-problem-input "resources/p3-input.txt") 7 1))
  (println (p3-part2 (util/read-problem-input "resources/p3-input.txt") 1 2))
  )