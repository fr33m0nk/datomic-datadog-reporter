# fr33m0nk/datomic-datadog-reporter

A tiny clojure library that reports datomic metrics to statsd. This is heavily inspired by [Appcanary](https://github.com/appcanary) 's [datomic-statsd-reporter](https://github.com/appcanary/datomic-statsd-reporter) and [Tom Crayford](https://github.com/tcrayford) 's [datomic-riemann-reporter](https://github.com/yeller/datomic-riemann-reporter/).

## Installation

Download uberjar from Clojars

[![Clojars Project](https://img.shields.io/clojars/v/net.clojars.fr33m0nk/datomic-datadog-reporter.svg)](https://clojars.org/net.clojars.fr33m0nk/datomic-datadog-reporter)

- [Direct Link](https://repo.clojars.org/net/clojars/fr33m0nk/datomic-datadog-reporter) to download jars

## Usage

1. Drop an uberjar in $DATOMIC_DIR/lib
2. add below to your transactor's `properties` file:

    - ```ini
      metrics-callback=fr33m0nk.datomic-datadog-reporter/send-metrics
      ```

3. you need to set these three environment variables:

    - DATADOG_HOST=your_datadog_agent_host (default: `127.0.0.1`)
    - DATADOG_PORT=your_datadog_agent_port (default: `8125`)
    - DATADOG_PREFIX=your_datadog_metric-prefix (default: `datomic`)

4. Start/restart your transactor, and you'll see events showing up in DataDog.
5. All events will start with prefix "datomic".
6. Event names come from the metrics available [here](http://docs.datomic.com/monitoring.html).

## License

Copyright © 2022 Prashant Sinha
Distributed under the Eclipse Public License version 1.0.
