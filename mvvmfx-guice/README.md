# MvvmFX Guice

This module is an extension for the [MvvmFX](https://github.com/sialcasa/mvvmFX) framework that adds support for 
[Guice](https://github.com/google/guice) as dependency injection framework.


```xml
<dependency>
		<groupId>de.saxsys</groupId>
		<artifactId>mvvmfx-guice</artifactId>
		<version>${mvvmfx-version}</version>
</dependency>
```


It is based on [fx-guice](https://github.com/cathive/fx-guice).

To create an application that is powered by Guice you have to extend `MvvmfxGuiceApplication`:

```java
public class Starter extends MvvmfxGuiceApplication {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void startMvvmfx(final Stage stage) throws Exception {
        // your code to initialize the view
    }
}
```

A simple example for this is available at [welcome-example](/examples/mini-examples/welcome-example).

* [CDI](/mvvmfx-cdi)
* [Spring-Boot](/mvvmfx-spring-boot)
* [EasyDI](/mvvmfx-easydi)