# boodle

Simple accounting SPA in Clojure and ClojureScript.

![Screenshot](https://github.com/manuel-uberti/boodle/blob/master/resources/img/screenshot.png)

boodle uses these Clojure/ClojureScript libraries:

- [bidi](https://github.com/juxt/bidi)
- [compojure](https://github.com/weavejester/compojure)
- [hikari-cp](https://github.com/tomekw/hikari-cp)
- [honeysql](https://github.com/jkk/honeysql)
- [http-kit](http://www.http-kit.org/)
- [mount](https://github.com/tolitius/mount)
- [re-frame](https://github.com/Day8/re-frame)
- [ring](https://github.com/ring-clojure/ring)
- [tongue](https://github.com/tonsky/tongue)

I used [Bulma](https://bulma.io/) for the UI, and I customised
[re-frame-modal](https://github.com/benhowell/re-frame-modal) and
[cljs-pikaday](https://github.com/timgilbert/cljs-pikaday) to play well with it.

Check the
[documentation](https://github.com/manuel-uberti/boodle/blob/master/doc/index.md)
for more details.

## Usage

To run boodle, install a Java JDK/JRE suitable for your system. I usually use
[OpenJDK](https://openjdk.java.net/), but your mileage may vary. Install Clojure
following the official [Getting
Started](https://clojure.org/guides/getting_started), and then install and
configure [PostgreSQL](https://www.postgresql.org) on your machine.

Set up the database with the necessary tables and permissions you find in
[model.sql](https://github.com/manuel-uberti/boodle/blob/master/resources/sql/model.sql).
Check also `conf/config.edn` to adjust the database connection parameters.

Install [yarn](https://yarnpkg.com/en/) and [gulp](https://gulpjs.com/), and
then run:

```console
$ yarn
$ cd resources/src
$ yarn
$ gulp
```

Install [shadow-cljs](http://shadow-cljs.org/) and compile the ClojureScript
files with:

```console
$ shadow-cljs compile boodle
```

Then fire up the server with:

```console
$ clj -A:run
```

You can now browse at `http://localhost:8080` and interact with boodle.

The included `etc/boodle.service` is a basic [systemd
unit](https://www.freedesktop.org/software/systemd/man/systemd.unit.html) that
executes `etc/deploy.sh` to update, build and run boodle automatically on my
home server.

## Tests

Unit tests are configured with [kaocha](https://github.com/lambdaisland/kaocha)
and can be run with:

```console
$ ./bin/kaocha
```

## License

Copyright © 2017-2019 Manuel Uberti

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
