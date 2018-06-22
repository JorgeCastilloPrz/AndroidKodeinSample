# 16: "Testing your architecture (mock injection)"

It's time to write some tests. You'll need to be smart about what you wanna test and how to do it. Choosing a proper
test scope is key for providing good value with your tests. Be smart about the tests you wanna write and how to add
value with them.

Let's think first about what we would need to remove for isolating tests and make them repeatable and non flaky. I would
personally remove the pieces that depend on external frameworks. Those can make tests become non deterministic, since
they are affected by external factors.

My architecture could be something like this. We have the view, then the presenter which uses navigator to
navigate to other activities and also the invoker to run the use cases. The use cases depend on the repository that
relies on a couple of data sources to fetch and store the data.

But which pieces depend on frameworks here?

The view implementation is a good one, since we don't want to depend on the android framework in our tests. There's
also the Navigator, since we don't want tests to perform real navigation either. Also the invoker, because it uses
coroutines to suspend threads and that could make our tests to complete before a background task has finished.
And finally we will need to remove data sources, since those depend on android for caching and the http client for
querying the server.

Let's remove them.

(Show code - GetPhotosEndtoEndShould)

This is an end to end test suite for the photos list screen. My intention is to cover the whole call stack in a black
box scenario, not caring about each one of the pieces separately but exercising the whole chain end to
end and then asserting over the final state in the end. Covering bigger scopes has a couple of benefits like not having
tests too close to the implementation and also reducing the amount of tests required.

Thanks to having a dependency injection system in place, we can rely on it to wire up the whole dependency tree at all
levels. To do that let's make our test class KodeinAware, and then override the kodein instance to include the same
application and activity scopes we use are using in the production code. That will bind dependencies at all levels.

But this has an obvious problem. The dependencies we wanna pull out like the navigator will still be bound to their
production implementations.

So let's add some mocks for those. Once we have the mocks in place for all those pieces, we can use the power of Kodein
binding overrides to override just those on the dependency tree.

For the invoker, we will replace it with a blocking version of it, which is just the same thing but runs the use cases
using the runBlocking coroutines function, which ensures the thread gets blocked until the task completes. That will
avoid any race conditions in tests.

For the navigator and the data sources, we will provide the mocks we have created so we can configure them later on.
The mock view will be linked in in tests whenever we start the call stack.

Let's have a look at our tests. First one ensures that photos are retrieved and displayed for the happy case. So, if
we have photos in cache, we will run the presenter passing in the mock view, and then we will just need to verify that
the presenter asks the view to render the retrieved photos, which must be same ones returned from the cache.

For the given block, we are just abstracting the mocked behavior into a function that makes the given data source to
return the photos we pass in as an argument.

The following test is quite similar. It also covers when photos are not in cache but can be retrieved from network.
So same story, we configure our local data source to return an error, and then the network data source to retrieve the
needed photos.

Then we can exercise presenter passing in the mock view and assert for the final call over the view after the whole
chain runs and the photos are retrieved.

Note that each time we run the whole call stack starting on the presenter, the production presenter, use cases, and
repository are running, and just the pieces that cause side effects are being replaced.

The last test would test the scenario where photos are not available on any of the data sources, so an error should be
rendered.

And that's it! With a few tests we have covered our whole architecture.
