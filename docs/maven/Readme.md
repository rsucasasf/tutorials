-----------------------

**Table of Contents**

- Maven
  - [Deploying to Maven Central](deploy.md)
  - Export to maven (local repo)
  - Check code - PMD reports
  - Code coverage - jacoco

-----------------------

#### Export to maven local repo

- Compile application (java, clojure ...)

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

-----------------------

#### Check code - PMD reports

- Configuration (pom.xml):

```xml
<build>
		<pluginManagement>
			<plugins>
        ...
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-pmd-plugin</artifactId>
					<version>3.8</version>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
	...
	<reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jxr-plugin</artifactId>
        <version>2.5</version>
      </plugin>
    </plugins>
	</reporting>
  ...
```

- Usage:

```bash
mvn site
mvn pmd:pmd
```

-----------------------

#### Code coverage - jacoco

- Configuration (pom.xml):

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <configuration>
        <skipMain>true</skipMain>
        <skip>true</skip>
        <source>1.8</source>
        <target>1.8</target>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.7.5.201505241946</version>
      <executions>
        <execution>
          <id>prepare-agent</id>
          <goals>
            <goal>prepare-agent</goal>
          </goals>
        </execution>
        <execution>
          <id>report</id>
          <phase>prepare-package</phase>
          <goals>
            <goal>report</goal>
          </goals>
        </execution>
        <execution>
          <id>post-unit-test</id>
          <phase>test</phase>
          <goals>
            <goal>report</goal>
          </goals>
          <configuration>
            <!-- Sets the path to the file which contains the execution data. -->

            <dataFile>target/jacoco.exec</dataFile>
            <!-- Sets the output directory for the code coverage report. -->
            <outputDirectory>target/jacoco-ut</outputDirectory>
          </configuration>
        </execution>
      </executions>
      <configuration>
        <systemPropertyVariables>
          <jacoco-agent.destfile>target/jacoco.exec</jacoco-agent.destfile>
        </systemPropertyVariables>
      </configuration>
    </plugin>
  </plugins>
</build>
```

- Usage:

```bash
mvn test
```
