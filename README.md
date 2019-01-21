# Waht's it
An embedded server for dev purposes to use with simpler framework
# how to install
it's not on the repo engines, so just clone it in your project PARENT folder.
in your project's settings.graddle add:
```kotlin
//build.gradle
repositories {
    mavenCentral()
    maven {
        url  "https://dl.bintray.com/wavywalk/maven" //add this repo
    }
}

dependencies {
    compile 'at.wavywalk.simpler:simpler-embedded-jetty:0.1.0:sources'
    compile 'at.wavywalk.simpler:simpler-embedded-jetty:0.1.0'
}
```
# How to use with simpler
Simpler is supposed to run in servlet container. 
but for dev purposes if you don't like suff like gretty etc.:
create your `main.kt` and run from there like so:
```kotlin
fun main(applicationArguments: Array<String>) {
    SimplerEmbeddedJettyStarter().startServer(8000, ApplicationBootstrapper()) //here application bootstrapper is your class that inherits from simpler bootstrapper and has @WebListener  annotation
}
```
that's all folks!