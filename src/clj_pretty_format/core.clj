(ns clj-pretty-format.core
  (:import [java.util Date Calendar]
           java.text.SimpleDateFormat)
  (:require [cljtang.core :refer [abs moment format-date]]))

(defprotocol PrettyFormatable
  "data with pretty format"
  (-pretty-format [this opts] "method to pretty format"))

(defn pretty-format [this & args] (-pretty-format this args))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- date->calendar [date]
  (doto (Calendar/getInstance) (.setTime date)))

(defn- time-field [x f]
  (let [c (if (instance? Date x)
            (date->calendar x)
            x)]
    (.get ^Calendar c f)))

(defn- year [c] (time-field c Calendar/YEAR))
(defn- month [c] (time-field c Calendar/MONTH))
(defn- date [c] (time-field c Calendar/DAY_OF_MONTH))
(defn- hours [c] (time-field c Calendar/HOUR_OF_DAY))
#_(defn- minutes [c] (time-field c Calendar/MINUTE))
#_(defn- seconds [c] (time-field c Calendar/SECOND))

(defn- before-ago-str [abtw btw unit num]
  (format "%.0f%s%s" (/ abtw num) unit (if (pos? btw) "前" "后")))

(defn- pretty-format-date-simple [that]
  (let [now (moment)
        now-time (.getTime ^Date now)
        that-time (.getTime ^Date that)
        btw (/ (- now-time that-time) 1000.0)
        abtw (abs btw)]
    (cond
      (< abtw 10) "刚刚"
      (< abtw 60) (before-ago-str abtw btw "秒钟" 1)
      (< abtw 3600) (before-ago-str abtw btw "分钟" 60)
      (< abtw (* 3600 24)) (before-ago-str abtw btw "小时" 3600)
      (< abtw (* 3600 24 365)) (before-ago-str abtw btw "天" (* 24 3600))
      :else (format-date that "yyyy年M月d日"))))

(defn- stage-of-day [that]
  (let [h (hours that)]
    (cond
      (and (>= h 0) (< h 5)) "深夜"
      (and (>= h 5) (< h 7)) "今天凌晨"
      (and (>= h 7) (< h 9)) "今天早上"
      (and (>= h 9) (< h 11)) "今天上午"
      (and (>= h 11) (< h 14)) "今天中午"
      (and (>= h 14) (< h 18)) "今天下午"
      (and (>= h 18) (< h 24)) "今晚"
      :else "")))

(defn- pretty-format-date-douban [that]
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
                ah (abs h)]
            (if (< ah 3)                      ;; 3小时以内
              (pretty-format-date-simple that)
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
               Date dateable
               Calendar (.getTime ^Calendar dateable))]
    (case style
      :douban (pretty-format-date-douban date)
      (pretty-format-date-simple date))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend java.util.Date
  PrettyFormatable
  {:-pretty-format pretty-format-date})

(extend java.util.Calendar
  PrettyFormatable
  {:-pretty-format pretty-format-date})
