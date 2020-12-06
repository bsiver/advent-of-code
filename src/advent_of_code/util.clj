(ns advent-of-code.util)

(defn read-problem-input
  [file-name]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (reduce conj [] (line-seq rdr))))

(defn split-by [pred coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [!pred (complement pred)
            [xs ys] (split-with !pred s)]
        (if (seq xs)
          (cons xs (split-by pred ys))
          (let [skip (take-while pred s)
                others (drop-while pred s)
                [xs ys] (split-with !pred others)]
            (cons (concat skip xs)
                  (split-by pred ys))))))))