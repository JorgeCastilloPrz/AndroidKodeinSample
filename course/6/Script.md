# 6: "Scoped Injection: Activity Scope"

("Scoped Injection": Add a InjectedActivity as a base for injecting activity context and extend the activity module
from the application one. Explain how this opens a new "injection scope" apart from the application one. Explain the
concept of "DI scopes". Also explain retainedKodein() for activities as a Kodein immune to activity restarts
(lifecycle).)

Scopes define the lifecycle of our dependencies, or in other words, when they're instantiated and when they can be
released by garbage collector.

We want to have at least 2 different scopes. One for the application class, and another one for our activities. But
you can have many more, like another one for fragments, for example.

The scopes have an inheritance hierarchy, so it's like an onion.
* The application scope is the inner one, and provides all the application wide dependencies, like the Logger.
* The activity scope extends the application one, so it can provide all the application dependencies if needed, but
adds the activity dependencies on top of it. Activity dependencies are the ones that can just live under the lifecycle
of an activity, like a ViewModel, a Presenter, or even a use case.
* Fragment Scope is optional but if exists it would depend on the activity one, and add fragment specific bindings on
top of it.
