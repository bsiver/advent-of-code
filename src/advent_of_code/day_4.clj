(ns advent-of-code.day-4
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.set :as cset]))

(def required-fields #{:ecl :pid :eyr :hcl :byr :iyr :hgt})

(defn passport-keys-are-valid
  [keys]
  (cset/subset? required-fields keys))

(defn p4
  [input]
  (count
    (filter true?
            (let [passports (util/split-by #{"\n"""} input)]
              (for [p passports]
                (let [passport (filter not-empty (cs/split (cs/join " " p) #" "))
                      pairs (map #(cs/split % #":") passport)
                      passport-map (into {} (map (fn [[k v]] [(keyword k) v]) pairs))
                      passport-map-keys (set (keys passport-map))]
                  (passport-keys-are-valid passport-map-keys)))))))

(defn -main
  [& args]
  (println (p4 (util/read-problem-input "resources/p4-input.txt"))))