-----------------------

**Table of Contents**

- Maven
  - [Deploying to Maven Central](deploy.md)
  - Export to maven (local repo)

-----------------------

#### Export to maven local repo

- Create artifact in local repo:

```bash
cd target

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
