# Easymeeting Alarm Scheduler Library

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

## Installation

### Requirements

- Java 1.8 or later

### Gradle users

Add it in your root build.gradle at the end of repositories:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add this dependency to your project's build file:

```groovy
dependencies {
    implementation 'com.github.aankur:test-abc:Tag'
}
```

### Maven users

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add this dependency to your project's POM:

```xml

<dependency>
    <groupId>com.github.aankur</groupId>
    <artifactId>test-abc</artifactId>
    <version>Tag</version>
</dependency>
```