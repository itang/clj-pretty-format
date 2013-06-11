# clj-pretty-format

A Clojure library designed to pretty format data well, that part is up to you.

## Install

To install clj-pretty-format, add the following dependency to your project.clj file:

        [clj-pretty-format "0.1.1"]

## Usage

        user=> (use 'clj-pretty-format.core)
        user=> (pretty-format (java.util.Date.) :style :douban)
        "刚刚"
        user=> (let [that (java.util.Date.)] (Thread/sleep 15000) (pretty-format that))
        "15秒钟前"
        user=> (pretty-format (java.util.Date. 100 4 1))
        "2000年5月1日"

## License

Copyright © 2013 itang

Distributed under the Eclipse Public License, the same as Clojure.
