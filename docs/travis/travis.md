-----------------------

**Table of Contents**

- Travis
  - [Getting Started](https://docs.travis-ci.com/user/getting-started/)
  - Support settings for multiple languges in one travis ci file
  - Github - Integration with Travis
  - [Customizing the Build](https://docs.travis-ci.com/user/customizing-the-build)
  - [Building a Clojure project](https://docs.travis-ci.com/user/languages/clojure/)

-----------------------

#### Support settings for multiple languges in one travis ci file

Example:

```yaml
matrix:
  include:
    - language: python
      python: 2.7
      before_script:
        - cd backend/tests
      script:
        - python -m unittest discover

    - language: android
      jdk: oraclejdk8
      android:
        components:
          - tools
          - android-25
          - build-tools-25.0.3
      before_script:
        - cd android/AppName
      script:
        - ./gradlew build connectedCheck

    - language: objective-c
      os: osx
      osx_image: xcode8.3
      before_script:
        - cd ios/AppName
      script:
        - xcodebuild -workspace AppName.xcworkspace -scheme AppName
          -destination 'platform=iOS Simulator,name=iPhone 7,OS=10.3' build test

notifications:
  email:
    - yourname@gmail.com
```

-----------------------

#### Github - Integration with Travis

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
