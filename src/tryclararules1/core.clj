(ns tryclararules1.core
  (:gen-class)
  (require [tryclararules1.student :as stu]
           [clara.rules :as r]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (stu/helloworld)
  (println "Arguments passed in are:")
  (run! println (map-indexed vector args))
  ;; (stu/readfromfile (first args))
  (stu/run-examples (first args))
  )
