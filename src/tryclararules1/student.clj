(ns tryclararules1.student
  (:require [clara.rules.accumulators :as acc]
            [clara.rules :refer :all]
            [clojure.string :as str]))

(defrecord Student [name marks])
(defrecord Promoted [name grade])

(defrule promoted-to
         "Prmote child to next grade if marks > 90"
          [Student (> marks 90)(= ?name name)]
            ;    [?student <- Student ]
         =>
         (insert! (->Promoted ?name 12)))

(defquery get-promotions
  "Query to find promotions for the students."
  []
  [?promotion <- Promoted])

(defn print-promotions!
  "Prints promotions from the given session"
  [session]

  (doseq [{{name :name grade :grade} :?promotion} (query session get-promotions)]
    (println name "got promoted to " grade))

  session)

(defn readfromfile [fileName]
  (def string1 (slurp fileName))
  (def lines (map vector (str/split-lines string1)))
   (into [] lines)
    ;;  (for x lines
  ;;      :let [y (str/split x)]
  ;;      println y  )
  )

(defn run-examples
  "Function to run the above example."
  [fileName]


  (println "list of promotions :")
  ;; prints "10 % :vip discount"
    (-> (mk-session 'tryclararules1.student :cache false) ; Load the rules.
      (insert (->Student (readfromfile fileName))

              (->Student "varshita" 100)
              (->Student "parul" 3)
              (->Student "arun" 93)
              ) ; Insert some facts.

      (fire-rules)
      (print-promotions! )
      ) )


(defn helloworld
  []
  (prn "helo world again"))