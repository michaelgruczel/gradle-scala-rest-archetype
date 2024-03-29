gradle-scala-rest-archetype
========

#### archtype for developments with gradle, scala, restservices, swagger, yammer ####

The aim of the project is to setup a technology stack which could be usefull for some projects

### technology stack ###

build tool: [gradle](http://www.gradle.org/) -
rest json service: exposed by jetty-rs, jackson -
rest-api documentation: [swagger](http://swagger.wordnik.com/) -
monitoring: [yammer](http://metrics.codahale.com/) -
release artefact: war -
release plattform: jetty


### Requirements ###
its programmed in scala, so you need [scala](http://www.scala-lang.org/) e.g. 2.9.2 and
of course [java](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
You need nothing more, because it is build with gradle and gradle will be
automatically downloaded by a wrapper if needed, so you do not need to install it
and all other stuff is included in repository.

### Step 1: Building and starting the archetype ###

Go to the workspace location and type :"./gradlew jettyRun"
Gradle will be downloaded if not already done,
the code will be compiled and the artefact will be started in a jetty.
You can get by a list of gradle tasks by: "gradlew task".
The build logic is defined in build.gradle.
The project supports java, scala and jetty.

### Step 2: call the rest service ###

enter "http://localhost:8080/gradle-scala-rest-archetype/rest/hello.json/info"
in your browser or use wget or curl and you will get the HelloWorld as json.
The base path is defined in the web.xml as "/rest/*".

The services are searched in the main.scala.de.restapi package by annotation.
Hello.scala is annotated with @Path("hello.json") and a doGet in Hello.scala
is annotated with @Path("/info").

That means the complete path is:
"http://localhost:8080/gradle-scala-rest-archetype/rest/hello.json/info"

As return type json is given by annotation @Produces(Array("application/json")).
The conversion from object to jason is done by jersey and jackson
(see POJOMappingFeature in web.xml and HelloResponse annotations).

### Step 3: check the rest documentation ###

The restapi is documented with swagger.
"http://localhost:8080/gradle-scala-rest-archetype/rest/api-docs.json/hello"
will give yout the definition of the interface.
This is done in code by annotations (take a look to web.xml, too).

You can use the swagger-ui to make it more handy.
A version is already included and can be entered by:
"http://localhost:8080/gradle-scala-rest-archetype/swagger-ui-1.1.7/index.html"

Or you can open "http://petstore.swagger.wordnik.com/" and
replace "http://petstore.swagger.wordnik.com/api/api-docs.json" with
"http://localhost:8080/gradle-scala-rest-archetype/rest/api-docs.json/hello"
and clear the api-key field.

If you like you can download (https://github.com/wordnik/swagger-ui) and customize
the swagger-ui yourself. Its a standalone javascript package to visualize and test the interfaces.


### Step 4: check the metrics ###

Everytime you call helloworld a counter is increased (check Hello.scala).
This is done by yammer.

```html
...
val counter = metrics.counter("helloCalls")
...
counter += 1;
...
```

Yammer exposes metrics automatically by jmx and healthpages.
In this example only a counter and a very general healthpage (AdminServlet) is inserted.
web.xml:

```html
...
<servlet>
    <servlet-name>AdminServlet</servlet-name>
    <servlet-class>com.yammer.metrics.reporting.AdminServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>AdminServlet</servlet-name>
    <url-pattern>/admin/*</url-pattern>
</servlet-mapping>
...
```

Open the metrics page by entering "http://localhost:8080/gradle-scala-rest-archetype/admin".
More information you can find under "http://metrics.codahale.com/getting-started/"



