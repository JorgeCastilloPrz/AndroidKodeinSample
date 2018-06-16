# 1: "What's Kodein?"

Kodein is a dependency injection framework written for the Kotlin language. One of the main
difference it has compared to Dagger is that it works as a "dependency container", also known as
"service locator".

That means that you'll be the one in charge of instantiating your classes inside the binding modules
by yourself, and it will take care of nesting dependencies and providing them at any level for you.

Kodein does not validate the dependency tree at compile but resolves all dependencies at runtime.
That could be a downside, since you lose some iteration speed and safety when you need to wait until
runtime to know about your errors. But given how complex Dagger has grown lately and the time you
need to put on building, understanding and maintaining a code base built over it, keeping things
simple can become a big benefit. So I would recommend to keep your mind open and make your own
assumptions at the end of this course.

You can find Kodein codebase at https://github.com/Kodein-Framework/Kodein-DI
and very detailed documentation of all the features available at http://kodein.org/Kodein-DI/

On this course, we will build a complete application architecture step by step, abstracting out our
dependencies and injecting them using Kodein along the way.
