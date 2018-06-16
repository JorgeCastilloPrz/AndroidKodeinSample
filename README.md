Android Scoped DI using Kodein
==============================

This repo provides an end to end Android app encoded using **Clean Architecture**, and a DI approach based on scopes to
provide testability.

The repo is a sample project for the [caster.io Kodein course](https://caster.io) about the same subject.

## Lessons
1: **What's Kodein?**: What is Kodein and how is it different from other frameworks like Dagger
2: **First steps**: Fetch Kodein dependency on Build gradle. (Explain difference between kodein-generic-jvm or kodein-erased-jvm)
3: **Add basic DI support**: Adding Basic Dependency Injection to the application scope, showcasing how to write the bindings with Kodein.
4. **Your first injection**: Inject a simple Logger on the application class using the app scope to show how it works (run the app). (Perform the nested context injection "by constructor")
5. **Lazy injections**: Example of "Lazy" injections. Replace logger with a lazy binding explaining what's the benefit of doing it.
6. **Making your classes KodeinAware**: Upgrade Application class to use KodeinAware to make your class Kodein aware and simplify syntax on injecting deps.
7. **Scoped Injection**: Add a InjectedActivity as a base for injecting activity context and extend the activity module from the application one. Explain how this opens a new "injection scope" apart from the application one. Explain the concept of "DI scopes". Also explain retainedKodein() for activities as a Kodein immune to activity restarts (lifecycle).
8. **Activity Scope**: Inject dependencies on the Activity Scope: Add a presenter for the photos list activity and inject it using the activity scope.
9. **Transitive Dependencies**: Add use case to retrieve the photos and inject it. Explain how transitive dependencies work on Kodein when doing this.
10. **Injecting Singletons**: Add photo network datasource to fetch the photos and inject it. Inject it using by singleton {} on the application Scope. Good moment to explain singleton providers.
10. **Tagged injections**: Add repository and inject the network datasource and also an in-memory one on it. Use it as an example on how to use tagged injections (qualifiers for implementing the same contract with 2 different implementations)
11. **Constant Injection": Add a timestamp to saved records and a TTL so they are discarded whenever they are too old. Provide the TTL number using constant injection feature by Kodein.
12. **Adding a Detail Activity**: Once we have the architecture complete and injected by 2 scopes, let's add a detail activity, following the same pattern in terms of DI.
13. **Inject Navigation**: Inject navigator on activities relaying on activity context injected into scope.
14. **Injecting Fragments**: Add a fragment and inject it using Kodein enclosing activity scope (closestKodein()).
15. **Injecting Custom Views**: Add a customview and inject it using the closer  scope (activity or fragment).
16. **Injecting Mocks on your tests**: Write end to end test removing view implementation and service as an example on how to use DI to replace dependencies by mocks on your architecture.
17. **Injecting Mocks on your UI tests**: Write UI test using Kodein to Inject mock usecases that help us to return the data we want and assert over final view states.

## Screenshots

<img src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/list.png?raw=true" data-canonical-src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/list.png?raw=true" width="256" height="455" />
<img src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/detail.png?raw=true" data-canonical-src="https://github.com/JorgeCastilloPrz/AndroidKodeinSample/blob/master/assets/detail.png?raw=true" width="256" height="455" />
