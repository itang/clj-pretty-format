(defproject clj-pretty-format "0.1.0-SNAPSHOT"
  :description "pretty format data"
  :url "http://github.com/itang/clj-pretty-format"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cljtang "0.1.0-SNAPSHOT"]]
  :profiles {:dev {:plugins [[codox "0.6.4"]
                             [lein-pprint "1.1.1"]
                             [lein-checkall "0.1.0-SNAPSHOT"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}}
  :aliases {"run-tests" ["with-profile" "1.4:1.5" "test"]}
  :warn-on-reflection true
  :injections [(require 'clojure.pprint)]
  :pom-addition [:developers [:developer [:name "itang"]]]
  :min-lein-version "2.0.0")
