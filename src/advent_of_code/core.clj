(ns advent-of-code.core
  (:gen-class))

(defn read-problem-input
  [file-name]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (reduce conj [] (line-seq rdr))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (let [in (map #(Integer/parseInt %) (read-problem-input "resources/p1-input.txt"))
        target-sum 2020]
    (doseq [x in]
      (if (some #{(- target-sum x)} in)
        (let [match (- target-sum x)]
          (println (format "Found a match! %s %s" x match))
          (println (format "Result is %s" (* x match))))))))