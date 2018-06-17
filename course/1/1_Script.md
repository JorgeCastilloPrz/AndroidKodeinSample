# 1: "What's Kodein?"

Kodein is a dependency injection framework written with Kotlin in mind.

The main difference Kodein has compared to other frameworks like Dagger is that it works as a
"dependency container", also known as "service locator". That means that you'll need to tell Kodein
how to instantiate your classes, and Kodein will take care of nesting dependencies and providing
them at the right time on the right spot for you.

To do that, Kodein leverages the power of Kotlin DSL support and high order functions, so it can
store the bindings as deferred functions, like the following:

bind<Logger> with provider { LogcatLogger(ctx) }

This is a very declarative way to say: "Hey, here's how you will instantiate the logger when
anyone asks for it."

Kodein does not validate the dependency tree at compile but resolves dependencies at runtime. I must
say that losing compile time validation means a couple of bad things:
- First, you lose the fail fast factor, since you don't get errors reported at compile time.
- Your instantiation code can have lower safety also, since errors nesting dependencies are
invisible to you until you run the app and navigate to those concrete screens.
- In the other hand, you paradoxically get much faster iteration and also flexibility due to
simplicity.

So there's an obvious trade-off to think about here.

You can find Kodein codebase at https://github.com/Kodein-Framework/Kodein-DI
and very detailed documentation of all the features available at http://kodein.org/Kodein-DI/

On this course, we will build a complete application architecture step by step, abstracting out our
dependencies and injecting them using Kodein along the way. Welcome to the "Dependency Injection
with Kodein" course.
