# 11: "Constant Injection"

Up to now, we have been requesting our photos from a network service. Since we don't want to load the photos from
network every single time, we are adding a cache. I've defined this contract for abstracting away the implementation
details of the data source (show PhotosLocalDataSource).

On it, I have a couple of GET methods for retrieving the complete list of photos or a single one, and there's also a
couple of saving methods to save a complete list or a single one. For the sake of simplicity, I've implemented this contract
using a simple in memory cache like this one.

(Show InMemoryPhotosDataSource)

This in-memory implementation uses a simple List<Photos> to store the items in memory. It also receives a TTL by
constructor, which is the "time to live" we will give to any of the stored photos. That means if a photo has been stored
for longer it will automatically become invalid.

If you look carefully to the List of items, you will notice that the item stored is not a Photo but something called a
PersistedPhoto. This is going to be a decorator over a Photo that also includes a timestamp in milliseconds reflecting
when the photo was stored.

Since we know when each photo was stored and also how long is it allowed to be valid, we can consider the whole list
invalid if we find any invalid photo inside of it. In that case, we can just clear the list to remove the photos from
the cache, and then return an error.

In case we're requesting a single photo, if it turns out to be invalid, we can do the same. Remove it from the list and
return an error.

Now, we would expect the TTL number to be injected. To do that, we can go to the app module, where we are defining our
application scoped dependencies, and add a tagged instance (instance(tag = "TTL")) when we bind the local data source.

Now, we can provide the value with another binding, but this time we will actually not use a binding for it, but a
constant. Do not forget to tag the constant binding with the corresponding tag. Constant bindings are always tagged.

Note that it doesn't use a provider function block, but a constant value straight away. That means the value will be
computed as soon as the Kodein module gets instantiated.

Of course this can be a too simple example, since we could have directly passed in the constant value to the data
source binding instead of nesting a constant binding. But note that you could also use this feature to inject more
complex constants, which can actually be simple classes.

Still it's recommended to only use constant bindings for very simple types without inheritance or implementing an
interface. Good choices could be primitive types or data classes that do not need to vary.
