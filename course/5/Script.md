# 5: "Lazy Injections"

(Example of "Lazy" injections. Replace logger with a lazy binding explaining what's the benefit of doing it)

As we learned before, Kodein bindings are delegated to deferred functions. On this example, the AndroidLogger lambda is
a function by itself, so it will instantiate the AndroidLogger whenever it gets called, and not before.

That means Kodein bindings are lazy by default. Therefore, dependencies are not instantiated until they're actually
needed.

Laziness is key to ensure memory integrity for all the cases. It's usual to find apps where they inject a lot of things
when the application class gets created. If you're using other frameworks like Dagger and those bindings are not defined
as lazy, you can easily blow up your memory heap.

(switch to code, show PhotosApp one more time)

So, if take the Logger we are injecting here as an example, we will get it injected right when we want to use for the
first time. Let's say our app will need to log something on start, we could write here the log (write a sample log
in onCreate). When this code runs, the logger will be provided an instantiated, and not before.

If this code was in an activity onCreate, the Logger wouldn't exist until this line runs when the activity is created.

Apart from all dependencies being lazy by default, on top of that you can also declare your Kodein instance as Lazy,
by delegating its creation on the Kodein.lazy {} function.
(switch Kodein {} with Kodein.lazy {})

If you do that, the Kodein container itself becomes lazy, so
it will not run the bindings initialization block until you get the first binding from it injected somewhere.
