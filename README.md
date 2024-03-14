# RedDB
This project is a simple clone of a well-known redis, powered
by the Java programming language

Redis is a complex key-value database.
It involves many complex cases and requires a solid system architecture.
This project is just a demo, showing only a minor percentage of what real Redis can do.

There are two primary reasons why I do this.
First of all, it's for understanding how to work with asynchronous programming in Java.
And second of all, I do it just for fun :)

# Implemented commands
* ECHO
* PING
* EXISTS
* GET
* SET
* QUIT
* DEL

# Design
RedDB, as mentioned, is powered by Java 21. It runs in a single thread, thanks to a Java ExecutorService.
The server is implemented with the default Java.net package. The RESP protocol parser is very basic.
only supports simple data structures, like strings and integers.

# Technologies
- redis-cli
- Java 21
- Gradle
- Docker
- Junit
- logback

# Build
Clone the repo:
~~~
    git clone https://github.com/MasoNord/redDB
~~~
Build an executable jar file. If you don't have Gradle installed on your PC, just build it in the IDE. Other programs do this:
~~~
    gradle build
~~~
Run the executable jar file from the root directory.
~~~
    java -jar build/libs/redDB-1.0-SNAPSHOT.jar
~~~
# Docker
You can also run a Docker image of RedDB using the following commands:
~~~
    docker build -t <you-image-name> .
~~~
And then run the image
~~~
    docker run -p 6379:6379 <you-image-name> 
~~~

# Usage
After you completed the previous steps, you can now run redis-cli in your terminal to connect to redDB server