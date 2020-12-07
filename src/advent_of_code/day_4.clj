(ns advent-of-code.day-4
  (:require [advent-of-code.util :as util]
            [clojure.string :as cs]
            [clojure.set :as cset]))

(def required-fields #{:ecl :pid :eyr :hcl :byr :iyr :hgt})

(defn passport-keys-are-valid
  [keys]
  (cset/subset? required-fields keys))

(defn values-are-valid
  [vals]
  (println (format "validating %s" vals))
  (let [byr-valid (<= 1920 (Integer/parseInt (get vals :byr 0)) 2002)
        iyr-valid (<= 2010 (Integer/parseInt (get vals :iyr 0)) 2020)
        eyr-valid (<= 2020 (Integer/parseInt (get vals :eyr 0)) 2030)
        hgt-unit (apply str (take-last 2 (get vals :hgt 0)))
        hgt-val (try
                   (Integer/parseInt (apply str (drop-last 2 (get vals :hgt 0))))
                   (catch NumberFormatException e 0))
        hgt-valid (if (= hgt-unit "cm")
                    (<= 150 hgt-val 193)
                    (<= 59 hgt-val 76))
        hcl-valid (re-matches #"^#[a-f0-9]{6}+$" (get vals :hcl 0))
        ecl-valid (re-matches #"amb|blu|brn|gry|grn|hzl|oth" (get vals :ecl 0))
        pid-valid (re-matches #"\d{9}\b" (get vals :pid 0))]
    (let [is-valid (not
                     (some #(or (nil? %) (false? %))
                           [byr-valid iyr-valid eyr-valid hgt-valid hcl-valid ecl-valid pid-valid]))]
      is-valid)))



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

(defn p4-part-2
  [input]
  (count
    (filter true?
            (let [passports (util/split-by #{"\n"""} input)]
              (for [p passports]
                (let [passport (filter not-empty (cs/split (cs/join " " p) #" "))
                      pairs (map #(cs/split % #":") passport)
                      passport-map (into {} (map (fn [[k v]] [(keyword k) v]) pairs))
                      passport-map-keys (set (keys passport-map))]
                  (and (passport-keys-are-valid passport-map-keys)
                       (values-are-valid passport-map))))))))

(defn -main
  [& args]
  (println (p4-part-2 (util/read-problem-input "resources/p4-input.txt"))))