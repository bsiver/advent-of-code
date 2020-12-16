(ns advent-of-code.day-15
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.math.combinatorics :as c]))

(defn indexes-of-occurences
  "Returns an array of indices of the occurences of number in spoken
  (indexes-of-occurences [0 3 6 0 0 0 0] 0)
  => (0 3 4 5 6)"
  [spoken number]
  (map first
       (filter #(= (second %) number)
               (map-indexed vector spoken))))

(defn determine-v
  [spoken last-spoken]
  (cond (= (get (frequencies spoken) last-spoken) 1) 0
    (some #{last-spoken} spoken) (reduce - (reverse (take-last 2 (indexes-of-occurences spoken last-spoken))))
        :else 0))

(defn p1
  [input]
  (loop [i 0
         spoken []
         last-spoken (first input)]
    (let [v (cond (< i (count input)) (get input i)
                  :else (determine-v spoken last-spoken))]
      (cond (= i 2019) (conj spoken v)
            :else (recur (inc i) (conj spoken v) v)))))


(defn -main
  [& args]
  (p1 (mapv #(Integer/parseInt %) (cs/split (first (util/read-problem-input "resources/p15-input.txt")) #","))))
