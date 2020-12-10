(ns advent-of-code.day-8
  (:require [advent-of-code.util :as util]
             [clojure.string :as cs]))

(defn parse-line
  [acc [i text]]
  (let [ [_ instr x] (re-matches #"(\w+)\s([-+]\d+)" text)]
    (assoc acc i {:instr instr :x x})))

(defn read-instructions
  [imap]
  (loop [i 0
         seen-instructions #{}
         acc 0]

    (if (not (contains? seen-instructions i))
      (let [cur-instruction (get-in imap [i :instr])
            x (Integer/parseInt (get-in imap [i :x]))
            next-i (condp = cur-instruction
                     "acc" (inc i)
                     "jmp" (+ i x)
                     "nop" (inc i))
            new-acc (condp = cur-instruction
                  "acc" (+ x acc)
                  "jmp" acc
                  "nop" acc)]
          (recur next-i (conj seen-instructions i) new-acc))
      acc)))

(defn read-terminates?
  [imap]
  (loop [i 0
         seen-instructions #{}
         acc 0]
    (cond (= i (count imap)) acc
          (not (contains? seen-instructions i))
          (let [cur-instruction (get-in imap [i :instr])
                x (Integer/parseInt (get-in imap [i :x]))
                next-i (condp = cur-instruction
                         "acc" (inc i)
                         "jmp" (+ i x)
                         "nop" (inc i))
                new-acc (condp = cur-instruction
                          "acc" (+ x acc)
                          "jmp" acc
                          "nop" acc)]
            (recur next-i (conj seen-instructions i) new-acc))
          :else nil)))

(defn permute-instructions
  [imap]
  (let [instrs  imap
        end   (count imap)
        instr-complement {"acc" "acc", "nop" "jmp", "jmp" "nop"}]
    (loop [i 0, variants ()]
      (cond
        (= i end) (reverse variants)
        :else
        (recur (inc i) (cons (assoc instrs i {:instr (instr-complement (get-in instrs [i :instr]))
                                            :x (get-in instrs [i :x])})
                               variants))))))


(defn p1
  [input]
  (let [instruction-map (reduce parse-line {} (map-indexed vector input))]
    (read-instructions instruction-map)))


(defn p2
  [input]
  (let [permuted-instructions (permute-instructions (reduce parse-line {} (map-indexed vector input)))]
    (filter some? (map read-terminates? permuted-instructions))))

(defn -main
  [& args])