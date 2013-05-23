(ns clj-pretty-format.core-test
  (:import java.util.Date)
  (:require [clojure.test :refer :all]
            [clj-pretty-format.core :refer :all]))

(deftest pretty-format-test
  (testing "test"
    (spit "D:/s.txt" [
    (pretty-format (Date.))
    (pretty-format (Date. 100 0 1))

    (pretty-format (Date. 112 0 1))

    (pretty-format (Date. 113 0 1))

    (pretty-format (Date. 113 3 1))

    (pretty-format (Date. 113 4 1))

    (pretty-format (Date. 113 4 20))

    (pretty-format (Date. 113 4 23 0 0 ))
    (pretty-format (Date. 113 4 23 1 0 ))
    (pretty-format (Date. 113 4 23 2 0 ))
    (pretty-format (Date. 113 4 23 3 0 ))
    (pretty-format (Date. 113 4 23 4 0 ))
    (pretty-format (Date. 113 4 23 5 0 ))
    (pretty-format (Date. 113 4 23 6 0 ))
    (pretty-format (Date. 113 4 23 7 0 ))
    (pretty-format (Date. 113 4 23 8 0 ))
    (pretty-format (Date. 113 4 23 9 0 ))
    (pretty-format (Date. 113 4 23 10 0 ))
    (pretty-format (Date. 113 4 23 11 0 ))
    (pretty-format (Date. 113 4 23 12 0 ))
    (pretty-format (Date. 113 4 23 13 0 ))
    (pretty-format (Date. 113 4 23 14 0 ))
    (pretty-format (Date. 113 4 23 15 0 ))
    (pretty-format (Date. 113 4 23 16 0 ))
    (pretty-format (Date. 113 4 23 17 0 ))
    (pretty-format (Date. 113 4 23 18 0 ))
    (pretty-format (Date. 113 4 23 19 0 ))
    (pretty-format (Date. 113 4 23 23 0 ))
    (pretty-format (Date. 113 4 23 23 0 ))
    (pretty-format (Date. 113 4 23 23 0 ))
    (pretty-format (Date. 113 4 23 23 0 ))

    (pretty-format (Date. 113 4 23))

    (pretty-format (Date. 113 4 23))

    (pretty-format (Date. 113 5 23))

    (pretty-format (Date. 114 1 1))
    (pretty-format (Date. 114 1 1) :style :simple)
    ])))
