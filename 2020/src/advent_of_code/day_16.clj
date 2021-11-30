(ns advent-of-code.day-16
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.set :as cset]))

(defn parse-fields
  [fields]
  (for [field fields]
    (let [[_ name lr hr lr2 hr2] (re-matches #"^(.*): (\d+)-(\d+) or (\d+)-(\d+)" field)]
      {:name name
       :range (concat
                (range (read-string lr) (inc (read-string hr)))
                (range (read-string lr2) (inc (read-string hr2))))})))

(defn parse-nearby
  [nearby]
  (for [n (drop 2 nearby)]
    (map read-string (cs/split n #","))))


(defn p1
  [fields nearby]
  (let [all-nearby (mapcat conj nearby)
        all-ranges (mapcat conj (for [m fields] (concat (:range m) (:range2 m))))]
    (filter #(nil? (some #{%} all-ranges)) all-nearby)))

(defn is-valid?
  "Does a given ticket have values that fall within the range of any field?"
  [fields ticket]
  (let [all-ranges (mapcat conj (for [m fields] (concat (:range m) (:range2 m))))]
    (empty? (filter #(nil? (some #{%} all-ranges)) ticket))))


(defn valid-for
  "Which fields is a given ticket valid for?"
  [fields ticket]
  (filter some?
          (for [m fields]
            (let [field-name (:name m)
                  r (:range m)]
              (when (cset/superset? (set r) (set ticket)) field-name)))))

(defn determine-fields
  [nearby fields]
  (loop [n 0
         options fields
         res {}]
    (let [ind (mod n (count (first nearby)))]
      (cond (= (count res) (count fields)) res
            :else
            (do
              (let [col (map #(nth % ind) nearby)
                    valid-keys (valid-for options col)
                    one-key (= (count valid-keys) 1)]
                (recur (inc n)
                       (if one-key
                         (remove #(= (:name %) (first valid-keys)) options)
                         options)
                       (if one-key (assoc res ind (first valid-keys))
                                   res))))))))

(defn p2
  [input]
  (let [input-groups (util/split-by #{"\n"""} input)
        fields (parse-fields (first input-groups))
        nearby (filter #(is-valid? fields %)
                       (parse-nearby (nth input-groups 2)))]
    (determine-fields nearby fields)))

(defn -main
  [& args]
  (let [input-groups (util/split-by #{"\n"""} (util/read-problem-input "resources/p16-input.txt"))
        fields (parse-fields (first input-groups))
        nearby (parse-nearby (nth input-groups 2))]
    (p1 fields nearby)))