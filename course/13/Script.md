# 13: "Injecting Navigation"

On the previous lesson we didn't show the logics we use for navigating from one activity to another.

We will need a click listener in the adapter for every item, which will just forward the click event to the presenter,
as you can see here. We need to pass the photo Id, so the presenter can navigate to the detail for that given photo.

But the presenter is not performing the navigation by itself, but delegating it to some other entity called Navigator.

If you think about one of the main purposes of presenters, it's to put a boundary between the view implementation, and
the rest of the app. That means presenters should never depend on any frameworks like the Android SDK.

Navigation depends on the Android framework since it requires an Android Context, so we cannot implement it here.

We will create an interface to abstract it away and inject it here. This contract will define all the navigation routes
available for the app. Since this app is quite simple, it will just provide a function to navigate to the detail,
which will require a photoId for it.

The implementation will get a context injected so it can perform navigation as expected.

But we still need to provide the navigation into the dependency injection scope. If we think about navigators, we
could think that it requires an activity context to launch another activity. But that is actually not true. You can
start activities using any context, like a Service or even the Application itself. That's because the startActivity
method is defined in the Android Context class, so any entity extending Context will be able to start activities.

That's how you can launch activities from push notification services, for example.

That means the binding for the navigator will fit better on the application scope, since it can be used from many
different parts of the app.

(Show scopes onion graph)

If we take a brief look to our dependency scopes graph, we can see the application scope is the innermost one.
That means its bindings are inherited by the activity one, and the bindings from both are inherited from the
fragment one.

So, if we declare the navigator in the application scope, it will clearly get the application context passed in, since
it's the only one visible. But what if we are in an activity scope? wouldn't we want to get the activity context
automatically passed in instead of the application one?

If we could define the binding in a way that leaves the context as a hole, and then provide a different implementation
of the context for each scope, that should be enough. The fragment scope wouldn't need to provide any context binding
since fragments are not context wrappers. It could just reuse the activity one, which will be visible since the fragment
scope depends on it.

But this has a new problem, hasn't it? We'll get a runtime error because there are two bindings defined for the Context
type on the activity scope, given that it's extending the application scope.

But there's a really cool feature from Kodein to resolve this: The Binding overrides.

What we want to do is to override our application context with the activity one, but just for the activity scope. To do
that, go to the `InjectedActivity` and set the allow overrides property to true when importing the module. Once you
have that, go to the base activity module and mark the context binding as an override like this. And you'll be done.

Any time a navigator is required, the application module will provide it fulfilling the context instance using the
application context, but if we're into an activity scope, the context will be overriden by the activity one.

In the same way, if we had a service, we could create a service scope that would extend from the application one,
and could also override the context binding to provide the service context.
