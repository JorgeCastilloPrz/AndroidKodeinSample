# 4: "Your first injection"

(Inject a simple Logger on the application class using the app scope to show how it works
(run the app). (Perform the nested context injection "by constructor"))

Once we have the application container ready, we can start thinking about injecting some
dependencies. Dependencies that we will want to abstract away are the ones that are framework
dependant, most likely.

Let's say we wanna have a Logger in our app, so we can log stuff arbitrarily from different points
in the architecture. Let's say we wanna use Logcat for it, since that's the logging API provided by
the Android SDK. All good, isn't it?

Wrong. If I used the Logger directly from those classes, those would be strongly tied to the Android
Logcat API, which is what we want to avoid.

We need to abstract away the framework, since once we start writing tests we will need our code to
be framework independent, so our tests can be easily isolated.

(Show the code -> Logger interface)

To abstract away the Logger, we will create an interface that just declares the login actions
available without any wiring to the Logcat API, like the one I got here. For a better API, I'm
declaring the different Log levels by using a `sealed class`

Then, we will create an implementation for this interface that will be using the Android Logcat. On
this point it's safe to depend on frameworks, since this class represents implementation details for
the logger, and therefore we will inject it. So it will be replaced in tests.

Once we have the interface and the implementation, we need to tell Kodein that each time a Logger
interface is requested, it should provide an AndroidLogger implementation. To do that, we need to
create a binding for linking both types.

(Write the binding `bind<Logger>() with singleton { AndroidLogger() }`)

Since the Logger is prone to be used across all app execution, it seems reasonable to provide it at
the application scope. That's why we're declaring the binding into the appModule, which is imported
on the application Kodein container.

So we can finally inject our logger anywhere, like here for example, by delegating a property with
the Logger type on the Kodein instance() method. And we'll be ready to use it.

Note that we always depend on the interface here and not directly the implementation details. That's
key to keep our code agnostic from frameworks.
