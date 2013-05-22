(ns clj-pretty-format.core
  (:import [java.util Date Calendar]
           java.text.SimpleDateFormat)
  (:require [cljtang.core :refer [moment]]))

(defprotocol PrettyFormatable
  "data with pretty format"
  (-pretty-format [this opts] "method to pretty format"))

(defn pretty-format [this & args] (-pretty-format this args))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- format-date [date pattern]
  (.format (SimpleDateFormat. pattern) date))

(defn- date->calendar [d]
  (doto (Calendar/getInstance) (.setTime d)))

(defn- get-filed [x f]
  (let [c (if (instance? java.util.Date x)
            (date->calendar x)
            x)]
    (.get ^Calendar c f)))

(defn- year [c] (get-filed c Calendar/YEAR))
(defn- month [c] (get-filed c Calendar/MONTH))
(defn- date [c] (get-filed c Calendar/DAY_OF_MONTH))
(defn- hours [c] (get-filed c Calendar/HOUR_OF_DAY))
(defn- minutes [c] (get-filed c Calendar/MINUTE))
(defn- seconds [c] (get-filed c Calendar/SECOND))

(defn- pretty-date-simple [that]
  (let [now (moment)
        cal-now (date->calendar now)
        cal-that (date->calendar that)]

        ))

(defn- stage-of-day [that]
  (let [h (hours that)]
    ))

(defn- pretty-date-douban [that]
  (let [now (moment)
        cal-now (doto (Calendar/getInstance) (.setTime now))
        cal-that (doto (Calendar/getInstance) (.setTime that))]
    (cond 
     (= (year cal-now) (year cal-that))   ;;"今年"
     (cond 
      (= (month cal-now) (month cal-that)) ;; 当月
      (cond 
       (= (date cal-now) (date cal-that))  ;; 当天
       (let [h (- (hours cal-now) (hours cal-that))
             ah (Math/abs h)]
         (if (< ah 3)                      ;; 3小时以内
            (pretty-date-simple that)
            (stage-of-day that)))
       (> (date cal-now) (date cal-that))  ;;今天以前
       (if (= (date cal-now) (-> cal-that date inc)) 
        "昨天"
        (format-date that "M月d日"))
       :else                               ;;今天以后
       (if (= (date cal-now) (-> cal-that date dec)) 
        "明天"
        (format-date that "M月d日")))
      :else (format-date that "M月d日"))  ;; 今年非当月

     :else (format-date that "yyyy年M月d日")))) ;;非今年

(defn- pretty-format-date
  [dateable & [{:keys [style] :or {style :douban} :as opts}]]
  (let [date (condp instance? dateable
               java.util.Date dateable
               java.util.Calendar (.getTime ^Calendar dateable))]
    (case style
      :douban (pretty-date-douban date)
      (pretty-date-simple date))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend java.util.Date
  PrettyFormatable
  {:-pretty-format pretty-format-date})

(extend java.util.Calendar
  PrettyFormatable
  {:-pretty-format pretty-format-date})
