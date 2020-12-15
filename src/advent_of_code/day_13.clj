(ns advent-of-code.day-13
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]))


(defn closest-depart
  [t bus-id]
  (* (Math/ceil (/ t bus-id)) bus-id))

(defn wait-times
  [in]
  (let [departure-time (Integer/parseInt (first in))
        bus-ids (map #(Integer/parseInt %)
                     (filter #(not= % "x")
                             (-> (second in)
                                 (clojure.string/split #","))))]
    (for [bus-id bus-ids]
      (let [cd (closest-depart departure-time bus-id)
            waiting-time (- cd departure-time)]
        {:bus-id bus-id
         :wait-time waiting-time}))))

(defn p1
  [input]
  (let [wait-times (sort-by :wait-time (wait-times input))]
    (* (:bus-id (first wait-times)) (:wait-time (first wait-times)))))

(defn bus-map
  [in]
  (let [bus-ids (map #(Integer/parseInt %)
                     (filter #(not= % "x")
                             (-> (second in)
                                 (clojure.string/split #","))))]
    (println bus-ids)
    (for [[i id] (map-indexed vector bus-ids)]
        {:bus-id id
         :index i})))

(defn red
  [in]
  (fn [lst idx]
    (if (= (in idx) "x")
      lst
      (let [val (Integer/parseInt (in idx))]
        (cons (list (- val idx) val) lst)))))


(defn make-list
  [input]
  (let [in (vec (cs/split (second input) #","))]
    (reverse
      (reduce (red in) [] (range (count in))))))

 (defn solve
   [pairs]
   (let [modulo (apply * (map last pairs))]
     (reduce (fn [total [x mx]]
               (let [b (/ modulo mx)]
                 (mod (+ total (* x b (mod (Math/pow b (- mx 2)) mx)))
                      modulo)))
             0 pairs)))

 (defn p2
  [in]
  (solve (make-list in)))

(defn -main
  [& args]
  ;(p1 (util/read-problem-input "resources/p13-input.txt"))
  (p2 (util/read-problem-input "resources/p13-ex.txt")))
