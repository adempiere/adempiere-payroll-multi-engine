# ADempiere Payroll Multi-engine

This plibrary allows define a payroll multi-engine,you can implement your self payroll engine

## Requirements
- [JDK 11 or later](https://adoptium.net/)
- [Gradle 8.0.1 or later](https://gradle.org/install/)


Work in progress...

## TODO
- Create a Engine Factory
- Implement a default engine using the current payroll process
- Implement a Testing


## Binary Project

You can get all binaries from github [here](https://central.sonatype.com/artifact/io.github.adempiere/adempiere-payroll-multi-engine/1.0.0).

All contruction is from github actions


## Some XML's:

All dictionary changes are writing from XML and all XML's hare `xml/migration`


## How to add this library?

Is very easy.

- Gradle

```Java
implementation 'io.github.adempiere:adempiere-payroll-multi-engine:1.0.0'
```

- SBT

```
libraryDependencies += "io.github.adempiere" % "adempiere-payroll-multi-engine" % "1.0.0"
```

- Apache Maven

```
<dependency>
    <groupId>io.github.adempiere</groupId>
    <artifactId>adempiere-payroll-multi-engine</artifactId>
    <version>1.0.0</version>
</dependency>
```