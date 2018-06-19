# 9: "Injecting Singletons"

We have a use case to retrieve the photos, but it's just returning a list of stubbed photos. Lest say we want to
retrieve them from a network API, like the Unsplash one.

Use cases are also a way to decouple our Presenters or ViewModels from the sources of data. So the use case will depend
on something else with that responsibility.

We will use a "data source" for that. A data source is precisely a source of data. I will add an interface declaring the
contract, and then implement it using retrofit and targeting the Unsplash API.

Data sources are usually hidden behind interfaces, since they depend directly on frameworks like android for
persistence, or the network layer ones that make real requests. We don't want to perform real http queries on our tests or
to store things on the real data base.

Can you imagine how our boss would react if he finds out that we've cleared the real production database just because
we were running a test? That's obviously not an ideal situation, so you will want to replace those real data sources by
mocks.

You should always depend on abstractions to keep frameworks out of the equation when the time for tests come.

Our UnsplashPhotoDataSource implementation will also require a Retrofit service, which is this UnsplashService. Don't
worry about the implementation, it's just a simple retrofit client.

Once we got all the pieces passed in by constructors, we need to provide them on the current scope. So lets go back to the
specific photo list activity module.

As you can see, our use case is now requiring to pass a new dependency in, which is gonna be the data source.
So we can add the instance() placeholder for it, and then write the corresponding binding.

But wait a second, should we write it here? We're creating a source of data. Data sources are usually accessed from
different screens. For example: If we add a detail screen tomorrow, we will need to fetch details for a single photo
from there, and that's gonna be part of the same data source. That's why data sources are usually provided in the
application scope, so they can live during the whole app execution.

That means we can just go to the app module, and provide our data source there. No worries, nested bindings will take
care as we're making our activity module to depend on the application one.

This could be the binding to provide it. Here, we are linking the interface to the concrete type, so anytime the
interface is requested for injection, we will provide the concrete implementation targeting the Unsplash API.

But there's still something else. Whenever you're declaring a binding you can use a provider like this one, which will
provide a new instance each time it's requested. But a data source sometimes requires to have the same instance all the
time. Maybe it's not such needed for this one, since it's querying the backend and we are not storing any state that we
need to keep, but what if it was an in memory one? We would need it to be a single instance, or in other words,
a Singleton.

If you need a binding to always provide the same instance for all the cases, you can just use singleton on it, instead
of provider. So any time it's injected, the same instance will be returned.

Singleton work per scope. That means they provide the same instance for the given Kodein container where they
are defined. So, if you added this binding into the base activity module, it would provide the same instance every time
for the same activity type. But will also provide a different instance for other activities.

Given that we're declaring it on the app module, we're safe. We will get the same instance for the whole app execution.

Besides that, note that the Unsplash service also requires a parameter that we can fulfill in the same way. It's the
retrofit service. We'll need a binding for it that we can also provide as a singleton. You'll also find
that you need another nested one for the OkHttpClient used to build up the retrofit service.

Once you have all the chained bindings in place, you'll be good to go, and start making real requests!
