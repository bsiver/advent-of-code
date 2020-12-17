(ns advent-of-code.day-15
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]))

(defn indexes-of-occurences
  "Returns a seq of indices of the occurrences of number in spoken
  (indexes-of-occurences [0 3 6 0 0 0 0] 0)
  => (0 3 4 5 6)"
  [spoken number]
  (sort (get spoken number)))

(defn determine-v
  [spoken last-spoken]
  (cond (= (count (get spoken last-spoken)) 1) 0
        :else (reduce - (reverse (take-last 2 (indexes-of-occurences spoken last-spoken))))))

(defn p1
  [input]
  (loop [i 0
         spoken {}
         last-spoken (first input)]
    (let [v (cond (< i (count input)) (get input i)
                  :else (determine-v spoken last-spoken))]
      (cond (= i 30000000) (assoc spoken v (conj (get spoken v) i))
            :else (recur (inc i) (assoc spoken v (conj (get spoken v) i)) v)))))


(defn -main
  [& args]
  (p1 (mapv #(Integer/parseInt %) (cs/split (first (util/read-problem-input "resources/p15-input.txt")) #","))))
