(ns advent-of-code.day-10
  (:require [advent-of-code.util :as util]))

(defn can-connect-to?
  [j1 j2]
  (<= 0 (- j1 j2) 3))

(defn find-min-compatible
  [adapters t]
  (if (= (apply max adapters) t)
    (+ t 3)
    (apply min (filter #(can-connect-to? % t) (remove #{t} adapters)))))

(defn build-differences
  [inp]
  (loop [in inp
         diffs []]
    (let [t (first in)
          mc (find-min-compatible inp t)
          diff (- mc t)]
      (cond
        (empty? (rest in)) (conj diffs diff)
        :else (recur (rest in) (conj diffs diff))))))

(defn p1
  [in]
  (frequencies (build-differences in)))

(defn deltas
  [in]
  (->> in
       (apply max)
       (+ 3)
       (conj in 0)
       sort
       (partition 2 1)
       (mapv (fn [ [a b] ] (- b a)))))

(def p2
  (memoize
    (fn
      ([x] 1)
      ([x y & xs]
       (if (> (+ x y) 3)
         (apply p2 y xs)
         (+
           (apply p2 y xs)
           (apply p2 (+ x y) xs)))))))

(defn -main
  [& args]
  (p1 (map #(Integer/parseInt %) (util/read-problem-input "resources/p10-input.txt")))
  (apply p2 (deltas (map #(Integer/parseInt %) (util/read-problem-input "resources/p10-input.txt")))))
