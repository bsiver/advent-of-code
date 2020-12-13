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

(defn -main
  [& args]
  (p1 (util/read-problem-input "resources/p13-input.txt")))
