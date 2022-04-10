(ns fr33m0nk.datomic-datadog-reporter
  (:require
    [clj-statsd :as s]
    [camel-snake-kebab.core :as csk]
    [environ.core :as e]))


(defn datadog-client-config
  []
  {:host (or (e/env :datadog-host) "127.0.0.1")
   :port (-> (e/env :datadog-port) (or 8125) int)
   :prefix (-> (or (e/env :datadog-prefix) "datomic") (str "."))})


(let [{:keys [host port prefix]} (datadog-client-config)]
  (s/setup host port :prefix prefix))


(defn send-metrics [metrics]
  (doseq [[metric value] metrics]
    (let [metric-name (csk/->kebab-case-string metric)]
      (if (map? value)
        (doseq [[sub-metric sub-metric-value] value]
          (s/gauge (str metric-name "-" (csk/->kebab-case-string sub-metric)) sub-metric-value))
        (s/gauge (str metric-name) value)))))