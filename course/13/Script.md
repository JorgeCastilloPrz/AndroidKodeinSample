# 13: "Injecting Navigation"

On the previous lesson we tricked a bit, since we didn't show the logics we use for navigating from the list activity
to the details one.

We will need to attach a click listener in the adapter to every item, so whenever an item gets clicked, the
listener will be called. The listener is just forwarding the click event to the presenter, as you see here. We need to
pass the photo Id, so the presenter can easily navigate to the detail screen for that given photo.

But take a look at this. The presenter is not performing the navigation by itself, but delegating it to some other
entity called Navigator.

If you think about one of the main purposes of presenters, it's to put a boundary between the view implementation, which
uses the Android SDK, and the rest of the app. That means presenters must never depend on any frameworks like the
Android SDK. Thanks to this, we ensure that our app is testable with unit tests just by abstracting away the frameworks
and replacing them with mocks.

Navigation depends on the Android framework since it requires an Android Context, so we cannot implement it here.

To perform navigation, we will create an interface to abstract it away. This contract will define all the navigation
routes available for the app. Since this app is so simple, it will just provide a function to navigate to the detail,
which will require a photoId for it.

And here you have the Android specific navigator implementation, which gets a context injected so it can perform
navigation as expected by starting the detail activity.

But we still need to provide the navigation into the dependency injection scope. If we think about navigators, we
could think that it requires an activity context to launch another activity. But that is actually not true. You can
start activities using any context, like a Service or even the Application itself. That's because the startActivity
method is defined in the Android Context class, so any entity extending it will be able to start activities.

That's how you can launch activities from push notification related services, for example.

Once that is clear, we think the binding will fit better on the application scope, since it can be used by many
different parts of the app. But that would determine that the context passed in is going to be the application one for
all the cases, right?

(Show scopes onion graph)

If we take a brief look to our dependency scopes onion graph, we can see the application scope is the innermost one.
That means its bindings are inherited by the activity one, and the bindings from both are inherited from the
fragment one.

So, if we declare the navigator in the application scope, it will clearly get the application context passed in, since
it's the only one visible.

But what if we could define the binding to leave the context as a hole, and then provide a different implementation
of the context on each one of the scopes? That would fill up the navigator with the right context always depending on
the scope, so the if we're in the application scope, it will use the application context, and if we are in the activity
scope it will use the activity one. The fragment scope wouldn't need to provide a context since fragments are not
context wrappers. It could reuse the activity one, which will be visible since the fragment scope must extend the
activity one.

But this has a problem, doesn't it? We'll get a runtime error because there are two bindings defined for the Context
type on the activity scope, given that it's extending the application scope.

But there's a really cool feature from Kodein to resolve this: Binding overrides.

What we want to do is to override our application context with the activity one, but just for the activity scope. To do
that, go to the `InjectedActivity` and set the allow overrides property when importing the module to true. Once that's
done, go to the activity module and mark the context binding as an override like this. And you'll be done.

Any time a navigator is required, the application module will provide it fulfilling the context instance by using the
corresponding context depending on the scope we are requesting it from.

If we had a service, we could create a service scope for dependency injection, that would extend the application one,
and could also override the context binding to provide the service context.
