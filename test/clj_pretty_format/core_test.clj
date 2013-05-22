(ns clj-pretty-format.core-test
  (:import java.util.Date)
  (:require [clojure.test :refer :all]
            [clj-pretty-format.core :refer :all]))

(deftest pretty-format-test
  (testing "test"
    (spit "D:/s.txt" [
    (pretty-format (Date. 100 0 1))

    (pretty-format (Date. 112 0 1))

    (pretty-format (Date. 113 0 1))

    (pretty-format (Date. 113 3 1))

    (pretty-format (Date. 113 4 1))

    (pretty-format (Date. 113 4 20))

    (pretty-format (Date. 113 4 22 0 0 ))
    (pretty-format (Date. 113 4 22 1 0 ))
    (pretty-format (Date. 113 4 22 2 0 ))
    (pretty-format (Date. 113 4 22 3 0 ))
    (pretty-format (Date. 113 4 22 4 0 ))
    (pretty-format (Date. 113 4 22 5 0 ))
    (pretty-format (Date. 113 4 22 6 0 ))
    (pretty-format (Date. 113 4 22 7 0 ))
    (pretty-format (Date. 113 4 22 8 0 ))
    (pretty-format (Date. 113 4 22 9 0 ))
    (pretty-format (Date. 113 4 22 10 0 ))
    (pretty-format (Date. 113 4 22 11 0 ))
    (pretty-format (Date. 113 4 22 12 0 ))
    (pretty-format (Date. 113 4 22 13 0 ))
    (pretty-format (Date. 113 4 22 14 0 ))
    (pretty-format (Date. 113 4 22 15 0 ))
    (pretty-format (Date. 113 4 22 16 0 ))
    (pretty-format (Date. 113 4 22 17 0 ))
    (pretty-format (Date. 113 4 22 18 0 ))
    (pretty-format (Date. 113 4 22 19 0 ))
    (pretty-format (Date. 113 4 22 22 0 ))
    (pretty-format (Date. 113 4 22 22 0 ))
    (pretty-format (Date. 113 4 22 22 0 ))
    (pretty-format (Date. 113 4 22 23 0 ))

    (pretty-format (Date. 113 4 22))

    (pretty-format (Date. 113 4 22))

    (pretty-format (Date. 113 5 22))

    (pretty-format (Date. 114 1 1))
    (pretty-format (Date. 114 1 1) :style :n)
    ])))
