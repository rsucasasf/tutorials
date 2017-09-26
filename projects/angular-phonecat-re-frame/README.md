Example taken from [Re-Frame - Functional Reactive Programming With Clojurescript](https://dhruvp.github.io/2015/03/07/re-frame/)
and [A Simple App](https://github.com/Day8/re-frame/tree/master/examples/simple)

[re-frame](https://github.com/Day8/re-frame/blob/master/README.md)

![re-frame](resources/dominoes.png)


### Create a new project

1. Download Leiningen

2. Run:

```bash
lein new reagent PROJECT_NAME
```

### Run It And Change It

1. Check out the re-frame repo

2. Get a command line

3. cd to the root of this project (PROJECT_NAME - where this README exists)

4. Run:

```bash
lein do clean, figwheel
```

to compile the app and start up figwheel hot-reloading

5. open http://localhost:3449/example.html to see the app

While step 4 is running, any changes you make to the ClojureScript source files (in src) will be re-compiled and reflected in the running page immediately.
