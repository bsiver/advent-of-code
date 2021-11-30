(ns advent-of-code.day-14
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.math.combinatorics :as c]))

(defn parse-mask
  [input]
  (let [ [match _ mask] (re-matches #"(mask = )([X|0-9]+)" (first input))]
    (cs/split mask #"")))

(defn to-padded-binary
  [line]
  (cs/replace (format "%36s" (Integer/toBinaryString line)) #" " "0"))

(defn parse-address
  [line]
  (re-matches #"^mem\[([0-9]+)\]\s=\s(\d+)" line))

(defn apply-bitmask
  [s bitmask]
  (for [[item bit] (map vector s bitmask)]
    (cond (= bit "X") item
          :else (Integer/parseInt bit))))

(defn parse-group
  [mask sequence]
  (loop [i 0
         memory {}]
    (let [instruction (nth sequence i)
          [_ mem-loc value] (parse-address instruction)
          expanded-val (cs/split (to-padded-binary (Integer/parseInt value)) #"")
          bitmasked (apply-bitmask expanded-val mask)
          new-val (Long/parseLong (cs/join bitmasked) 2)]
      (cond (= i (dec (count sequence))) (assoc memory mem-loc new-val)
            :else (recur (inc i) (assoc memory mem-loc new-val))))))

(defn p1
  [input]
  (let [groups (util/split-by #(re-matches #"^mask.*" %) input)]
    (apply merge
      (for [group groups]
        (parse-group (parse-mask group) (rest group))))))


(defn apply-bitmask-p2
  [s bitmask]
  (for [[item bit] (map vector s bitmask)]
    (cond (= bit "0") item
          (= bit "1") "1"
          (= bit "X") "X")))

(defn vals-from-float
  [bm]
  (let [x-count (count (re-seq #"X" bm))
        combinations (c/permuted-combinations (concat (take x-count (repeat 1))
                                                      (take x-count (repeat 0))) x-count)]
    (for [comb combinations]
      (reduce #(clojure.string/replace-first %1 "X" %2) bm comb))))

(defn parse-group-2
  [mask sequence]
  (loop [i 0
         memory {}]
    (let [instruction (nth sequence i)
          [_ mem-loc value] (parse-address instruction)
          expanded-val (cs/split (to-padded-binary (Integer/parseInt mem-loc)) #"")
          bitmasked (cs/join (apply-bitmask-p2 expanded-val mask))
          addresses (vals-from-float bitmasked)
          ; build a map of the permuted addresses as keys, val is the desired val to write
          locations-to-write (apply merge (for [addr addresses] {addr (Integer/parseInt value)}))]
      (cond (= i (dec (count sequence))) (merge memory locations-to-write)
            :else (recur (inc i) (merge memory locations-to-write))))))

(defn p2
  [input]
  (let [groups (util/split-by #(re-matches #"^mask.*" %) input)]
    (apply merge
           (for [group groups]
             (parse-group-2 (parse-mask group) (rest group))))))


(defn -main
  [& args]
  (p1 (util/read-problem-input "resources/p14-ex.txt")))
  ;(p1 (util/read-problem-input "resources/p14-input.txt")))
