# 9: "Injecting Singletons"

We have a use case to retrieve the photos, but it's just returning a list of stubbed photos. Let's say we want to
fetch them from a network API, like the Unsplash one.

Use cases are a way to decouple Presenters or ViewModels from the sources of data. So the use case will depend on
a different entity with that given responsibility. A "data source".

I will add an interface declaring the contract, and then implement it using retrofit and targeting the Unsplash API.
Data sources are usually hidden behind interfaces, since they depend directly on frameworks like android for
persistence, or an HTTP client to make real requests. We don't want to perform real http queries or to store things on
the real data base when we're running our tests. We need our tests to be isolated from frameworks.

Regardless how we're implementing the data source, it will require a Retrofit service targeting the Unsplash API passed
in to make the HTTP requests. So it will be another nested dependency on the tree.

Once we got all the pieces in place, we need to provide them on the corresponding Kodein scope. Since we will need to
show the pictures on the photo list activity, let's add the bindings there.

As you can see, our use case now requires to pass the data source in. We can call instance() for it, and then write the
corresponding binding.

But let's think for a second, should we write the binding on the activity scope? We're binding a data source.
Data sources are usually accessed from different screens, since they can fulfill different requests. For example: If we
add a detail screen tomorrow, we will need to fetch details for a single photo, and that's gonna be another query on the
same data source. For that reason, data sources are usually provided in the application scope, so they can live during
the whole app execution.

So let's add the binding to the app module. This binding will be visible from the activity scope, since we're making it
depend on the application one.

Here, we are linking the interface to the concrete type, so anytime the interface is requested for injection, we provide
the concrete implementation targeting the Unsplash API.

But there's still something else. Whenever you're declaring a binding you can use a provider like this one, which will
provide a new instance each time it's requested. But a data source sometimes requires to have the same instance all the
time. In other words, you need it to be a Singleton.

If you need a binding to always provide the same instance for all the cases, you can just use singleton {} for it,
instead of a plain provider. So any time it's injected, the same instance will be returned.

Singletons work per scope. That means they provide the same instance for the given Kodein container where they are
declared. If you added this binding into the base activity module, it would provide the same instance every time
for the same activity type. But if the activity type varies, it will return a different singleton instance for that one.

Note that the data source also requires a retrofit service that we can fulfill in the same way. We'll need a binding for
it that we can also provide as a singleton. And for the retrofit service, we can also bind the OkHttpClient instance
used to build it.

Once you have all the chained bindings in place, you'll be good to go, and start making real requests on your photos activity.
