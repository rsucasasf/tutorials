# tutorials

Mini tutorials and links

-----------------------

**Table of Contents**

- [Maven](docs/maven/deploy.md)
  - [Deploying to Maven Central](docs/maven/deploy.md)
- [Travis](docs/travis/travis.md)
  - Support settings for multiple languges in one travis ci file
  - Github - Integration with Travis


- [Mini tutorials and links](#mini-tutorials-and-links)
  - Clojure
  - Travis
  - Continuous Integration
    - Integration with Travis
    - Integration with CODECOV
  - Markdown tips and links
  - Export to maven (local repo)

-----------------------

## Mini tutorials and links

### Clojure
[Readable Clojure](http://tonsky.me/blog/readable-clojure/) : "*This is how you can make Clojure code more pleasant to work with*"

[Deploying to Maven Central](DEPLOY.md), taken from https://github.com/technomancy/leiningen

### Travis

[Getting Started](https://docs.travis-ci.com/user/getting-started/)

[Customizing the Build](https://docs.travis-ci.com/user/customizing-the-build/)

[Building a Clojure project](https://docs.travis-ci.com/user/languages/clojure/)

### Continuous Integration

[Travis](https://travis-ci.org/profile) ==> [codecov](https://codecov.io)

#### Integration with Travis

1. Enable github project in [Travis](https://travis-ci.org/profile) (entering as the github user)

2. Add a *.travis.yml* file in the project's root folder with the following content:

```yaml
language: clojure

script:
- lein test
```

3. Use Travis to synchronize the project and execute the tests or do a *push*

4. Take the url of the image (i.e., *build | passing*) from Travis (in the project) and add it to the README.md file

```
[![Build Status](https://travis-ci.org/rsucasasf/cloj-rules-engine.svg?branch=master)](https://travis-ci.org/rsucasasf/cloj-rules-engine)
```

#### Integration with [CODECOV](https://codecov.io)

Codecov provides highly integrated tools to group, merge, archive and compare coverage reports. Whether your team is comparing changes in a pull request or reviewing a single commit, Codecov will improve the code review workflow and quality.

### Markdown tips and links

[Markdown License badges](https://gist.github.com/lukas-h/2a5d00690736b4c3a7ba)

Other badges from [shields.io](https://shields.io/)


### Export to maven local repo

- Create jar:

```bash
lein uberjar
```

- Create artifact in local repo:

```bash
cd target

cd uberjar

mvn install:install-file -Dfile=cloj-rules-engine-0.1.1-standalone.jar -DgroupId=cloj-libs -DartifactId=cloj-rules-engine -Dversion=0.1.1 -Dpackaging=jar
```

- Use in a Java project:
    - Add to maven dependencies:

```xml
<dependency>
  <groupId>cloj-libs</groupId>
  <artifactId>cloj-rules-engine</artifactId>
  <version>0.1.1</version>
</dependency>
```
