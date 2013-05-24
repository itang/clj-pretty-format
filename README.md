# clj-pretty-format

A Clojure library designed to pretty format data well, that part is up to you.

## Usage

prepare:

        $ git clone https://github.com/itang/cljtang.git && cd cljtang && lein install
        $ git clone https://github.com/itang/lein-checkall.git && cd lein-checkall && lein install

        $ git clone https://github.com/itang/clj-pretty-format.git
        $ cd clj-pretty-format
        $ lein checkall && lein test
        $ lein install

use:

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
