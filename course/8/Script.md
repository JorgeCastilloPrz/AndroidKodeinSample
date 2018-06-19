# 8: "Transitive Dependencies"

We have our bindings in place for PhotoListActivity. Whenever it gets launched we'll get our instance of the presenter
injected. We can do that thanks to the binding we have defined on the photoListActivityModule(). But what if we needed
to go further and provide a dependency to the Presenter on construction?

We'd need a way to also instantiate and provide it. And of course we can do that by declaring another binding.

We'll showcase that by injecting what we call a UseCase into the presenter. Use cases, also called "interactors", are
part of an architecture called Clean Architecture. Just keep in mind that they exist to pull business logic out from
Presenters or ViewModels so they don't grow massively, and also as a way to make our presenter or ViewModel agnostic
from any data retrieval logics.

We will be using a base class UseCase to provide a contract for all of them. We will make Parameters a generic type so
each use case can specify it, and the same for the return type.

Whenever we run the use case, we will perform some business logic or maybe retrieve data from somewhere, and then return
a result.

Since any use case is an execution unit that can fail or succeed, we're returning Either a domain error or the
successful return type, as you can see here. Either is just a wrapper class to provide that duality into the same type,
so we can avoid using callbacks.

I'm taking it from a library called Arrow, but you can just write your own, since at the end of the day it's just a
sealed class with two different implementations, one for the error and another one for the success type.

Note that the error represents a domain error type, which is defined as a sealed class that can take a bunch of expected
errors handled by our domain.

(Show GetPhotos)

But lets get to the point. We want to create our use case to get the photos and return them to the presenter. To do
that, we will inherit from the base one. The parameters for it are defined as a data class, as you can see. I'm
providing the page to load and a query string that could be used for searching photos by category or something.

Then there is the successful return type, which is going to be a list of photos (List<Photo>) in case everything goes
alright. For now we will return just a bunch of hardcoded photos so it's not gonna fail.

Once we have our use case ready, we need to inject it on the presenter! To do that, we need to add it to the presenter
constructor. Now let's go back to the photo list activity module. As you can see, this code is not compiling anymore
since we need to pass an instance for the use case to the presenter.

To do that, we can write a binding for the use case itself, since it's a dependency, and it seems reasonable to make it
live under the activity scope, since we will run the use case from this activity and we probably don't need it to be
available for other unrelated activities. After all, we're retrieving a list of photos that will be rendered on a single
activity. But it's your choice where to put it, and it will always depend on your app.

Now, we just need a way to wire both bindings. For that, we just need to use the instance() Kodein function, et voilá.

We will get the use case instance automatically instantiated and passed in when the presenter gets bound. That's how
Kodein works for nested bindings.

Note that just by using the instance() function you're kind of filling a hole with something that requires to have a
binding declared in scope. The use case binding could even be defined outside of this module, like on the application
scope for example.

But since we made the activity scope depend on the application one, it will be visible from this scope and the nested
bindings will work as expected. This is key since it gives you a very big flexibility on how to organize your scopes and
your binding modules.

Once we get our use case we can just use it to request the pictures and render results or errors back on the view,
depending on the case, as you can see here.
