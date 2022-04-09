(ns fr33m0nk.datomic-datadog-reporter
  (:require
    [com.unbounce.dogstatsd.core :as dd]
    [environ.core :as e]))

(defn create-datadog-config
  []
  {:host (or (e/env :datadog-host) "127.0.0.1")
   :port (-> (e/env :datadog-port) (or 8125) long)
   :prefix (or (e/env :datadog-prefix) "datomic")
   :once? true})

(defn send-metrics
  [metrics]
  (let [dd-config (create-datadog-config)]
    (dd/setup! dd-config)
    (doseq [[metric-type metric-value] metrics]
      (if (map? metric-value)
        (doseq [[sub-metric-name sub-metric-value] metric-value]
          (dd/gauge (str (name metric-type) "." (name sub-metric-name)) sub-metric-value))
        (dd/gauge (name metric-type) metric-value)))
    (.addShutdownHook
      (Runtime/getRuntime)
      (Thread. ^Runnable dd/shutdown!))))
