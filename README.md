# fr33m0nk/datomic-datadog-reporter

A tiny clojure library that reports datomic metrics to statsd. This is heavily inspired by [Appcanary](https://github.com/appcanary) 's [datomic-statsd-reporter](https://github.com/appcanary/datomic-statsd-reporter) and [Tom Crayford](https://github.com/tcrayford) 's [datomic-riemann-reporter](https://github.com/yeller/datomic-riemann-reporter/).

## Installation

Download uberjar from Clojars

[![Clojars Project](https://img.shields.io/clojars/v/net.clojars.fr33m0nk/datomic-datadog-reporter.svg)](https://clojars.org/net.clojars.fr33m0nk/datomic-datadog-reporter)
-- [Direct Link](https://repo.clojars.org/net/clojars/fr33m0nk/datomic-datadog-reporter) to download jars

## Usage

Drop an [uberjar](https://repo.clojars.org/net/clojars/fr33m0nk/datomic-datadog-reporter/1.1.5/datomic-datadog-reporter-1.1.5.jar) in $DATOMIC_DIR/lib, then add below to your transactor's `properties` file:

```ini
metrics-callback=fr33m0nk.datomic-datadog-reporter/send-metrics
```

Then you need to set these two environment variables:

DATADOG_HOST=your_datadog_agent_host
DATADOG_PORT=your_datadog_agent_port
DATADOG_PREFIX=your_datadog_metric-prefix

If you don't set above environment variables, the defaults are host: `127.0.0.1`, port: `8125` and prefix: `datomic`.

Start/restart your transactor, and you'll see events showing up in DataDog.
All events will start with prefix "datomic".
Event names come from the metrics available on http://docs.datomic.com/monitoring.html.

## License

Copyright © 2022 Prashant Sinha
Distributed under the Eclipse Public License version 1.0.
