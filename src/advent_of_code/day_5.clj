(ns advent-of-code.day-5
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.set :as cset]))

(defn to-bin
  [s]
  (cs/replace s #"F|B|L|R" {"F" "0"
                            "B" "1"
                            "L" "0"
                            "R" "1"}))

(defn get-seat-info
  [bp]
  (let [row (to-bin (reduce str (take 7 bp)))
        column (to-bin (reduce str (take-last 3 bp)))
        seat-id (str row column)]
    {:r (Integer/parseInt row 2)
     :c (Integer/parseInt column 2)
     :seat-id (Integer/parseInt seat-id 2)}))

(defn find-missing-seat
  [seats]
  (loop [missing-seats '()
         remaining-seats seats]
    (if (empty? remaining-seats)
      missing-seats
      (let [first-rem (first remaining-seats)
            second-rem (second remaining-seats)]
        (recur (if (not (= (inc first-rem) second-rem))
                 (cons (inc first-rem) missing-seats)
                 missing-seats)
               (rest remaining-seats))))))

(defn p1
  [in]
  (apply max (map #(-> % get-seat-info :seat-id) in)))

(defn p2
  [in]
  (find-missing-seat (sort (map #(-> % get-seat-info :seat-id) in))))

(defn -main
  [& args]
   (p1 (util/read-problem-input "resources/p5-input.txt"))
   (p2 (util/read-problem-input "resources/p5-input.txt")))