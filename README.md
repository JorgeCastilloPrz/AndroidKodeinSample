Android Scoped DI using Kodein
==============================

This repo provides an end to end Android app encoded using **Clean Architecture**, and a DI approach based on scopes to
provide testability.

The repo is a sample project for the [caster.io Kodein course](https://caster.io) about the same subject.

## Lessons
* **What's Kodein?**: What is Kodein and how is it different from other frameworks like Dagger
* **First steps**: Fetch Kodein dependency on Build gradle. (Explain difference between artifacts kodein-generic-jvm or kodein-erased-jvm)
* **Application Scope**: Start by creating an Application Kodein container to provide application scoped dependencies.
* **Your first injection**: Use the application scope to inject your first dependency (A simple Logger)
* **Lazy injections**: Explain the concept of laziness in DI frameworks and Kodein
* **Scoped Injection - Activity Scope**: Explain the concept of scoped injection by providing a second scope: The
Activity one.
* **Activity scoped dependencies**: Use the activity scope to inject a Presenter, which will just need to live under
that scope.
* **Transitive dependencies**: Inject a use case on presenter to showcase how Kodein solves the problem of nested
transitive dependencies.
* **Injecting Singletons**: Explain why singletons are often needed by providing a singleton instance for a photo data
source.
* **Tagged Injections**: Explain why two bindings for the same type collide and how to resolve the problem with tagged
bindings. Use it to provide two different OkHttp Interceptors.
* **Constant Injection**: Showcase how Kodein allows to define bindings for constants and not just entities. Use the
concept to provide a TTL for the local photos cache.
* **Adding a Detail Activity**: This lesson is a good chance to recap the process we followed for the photos list
activity Kodein scope, but over a new detail activity.
* **Injecting Navigation**: Inject a Navigator in Presenter and showcase how we can provide a different Context instance
per scope, showcasing the benefit of Kodein binding overrides.
* **Injecting Fragments**: We're adding a fragment to the detail activity and showcasing how to create a new scope:
Fragment Scope.
* **Injecting Custom Views**: Let's add a custom view and explain how Kodein allows Custom Views to auto inject, the
same way activities or fragments do.
* **Testing your architecture (mock injection)**: This lesson offers and end to end black box unit testing scenario
where thanks to DI we replace the pieces that depend on frameworks by mocks. DI helps us to replace those at any
arbitrary depth on the dependency tree.
* **Injecting Mocks on your UI tests**: Learn how to write UI tests reusing production Kodein modules for fulfilling
dependencies but replacing just the required parts in the graph thanks to Kodein module overrides.

## Screenshots

<img src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/list.png?raw=true" data-canonical-src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/list.png?raw=true" width="256" height="455" />
<img src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/detail.png?raw=true" data-canonical-src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/detail.png?raw=true" width="256" height="455" />
