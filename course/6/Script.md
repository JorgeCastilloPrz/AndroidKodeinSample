# 6: "Scoped Injection: Activity Scope"

Scopes define the lifecycle of our dependencies, or in other words, when they're instantiated and when they can be
released by garbage collector.

We'll want to have at least 2 different scopes. One for the application class, and another one for our activities. But
you can have many more, like another one for fragments, for example.

When we define a dependency in the application scope, it means it will be able to be provided from anywhere in the app.
So it will not released by garbage collector until the app Kodein instance is released, which will happen just if you
kill the app.
In the other hand, when we define a dependency on the activity scope, just the activity Kodein instance will be able to
provide it. That implies it will be able to be released by garbage collector if the activity holding it is released.

The scopes have an inheritance hierarchy, so it's like an onion.
* The application scope would be the inner one, and provides all the application wide dependencies, like the Logger.
* The activity scope extends the application one, since it needs to be able to provide all the application dependencies
to complete the dependency graph, but it also adds the activity dependencies on top.
Activity dependencies are the ones that can just live under the lifecycle of an activity, like a ViewModel or a
Presenter for example.
* Fragment Scope is optional but if you add it, it would depend on the activity one, and it would add fragment specific
bindings on top of it.

(Show code - Application class)

To emulate this we already have one of the pieces ready: The application scope, which is defined in the application
class.

Now we would need to build the Activity scope on top of it. To do that, and since we want the scope to be available from
any activity in the app, we are conveniently creating a base activity that will provide the scope. We'll call it
InjectedActivity.

To build the activity Kodein scope we will need to make it depend on the application one, if you remember the onion. To
get access to the application Kodein scope, we can use the Kodein closestKodein() function, which will automatically
fetch it for us. This function is prepared for android. It will return the application scope when it's called from an
activity, and if you call it from a framgent or a customview, it will return the enclosing activity scope. So it's
perfect to achieve what we want.

We'll also want to implement KodeinAware on the activity, so we get an automatic instance of Kodein that we can
override. That will be our activity scope.

This is totally your choice, but you can use a retainedKodein instance instead of a normal one. That gives you a
lifecycle immune Kodein instance, which can survive to configuration changes.

First thing we do on the activity scope is to extend the application one as expected. This will bring all
the application scoped bindings into scope. Afterwards, I'm adding a base module for all activities that will provide
generic activity scoped dependencies, like the navigator or the activity context itself!

Afterwards, I can request bindings from a delegate function, so child activities will be able to define their specific
bindings. This is gonna be key. So if there's an activity that needs to bind something apart from the things we're
binding here, like its own ViewModel for example, it will be able to.
