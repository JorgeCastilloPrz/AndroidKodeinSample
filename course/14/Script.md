# 14: "Injecting Fragments"

If we look one more time to our scopes graph, we'll find out that fragments scope is always in the outermost circle.
That means it will extend the activity one, which will extend the application one transitively. That means the fragment
scope is going to be the richest one, since it will have visibility on the bindings defined in the other two scopes.

Let's add a fragment to our app. We will add a very simple one just for showcasing how we can implement a fragment
Kodein scope. For didactic purposes, we are going to replace the description TextView on the DetailActivity with a
fragment which will just render the description by itself.

Here you have the DescriptionFragment. As you can see, it's very simple. In order to provide a Kodein container for it
we will need to extend the KodeinAware interface one more time. That will simplify things a bit. Afterwards, we will
need to override the kodein property and provide with it a Kodein container for this fragment.

Note that this is the same thing we did for the Activity scope in the InjectedActivity. Here, we will provide the fragment
scope dependencies, which will be the ones that we do not need to live longer than the Fragment's lifecycle, so whenever
the fragment gets destroyed, those dependencies can be released by the garbage collector.

And now, we need to use the Kodein magic just a bit. The same way we did for the activity scope, we need to use the
closestKodein() function here to automatically retrieve the activity scope Kodein container. Kodein is smart enough to
find it. If there wasn't an activity scope defined for the enclosing activity, it would automatically return the
application scope to ensure integrity on inheritance.

Now, our fragment scope would be ready to extend the activity one, the same way we extended the application one from
the activity scope on previous lessons. So we got our new onion layer in place.

Let's add a very basic presenter like this one. This presenter will get the description by constructor and just render
it when the fragment is resumed.

Now, let's add a Kodein Module to the DescriptionFragment's scope. We will provide the presenter here using a simple
binding. Since the presenter requires the description to be passed in, we will also request it as an argument of the
module constructor function. Now, on our fragment, we can just import the module and pass in the argument retrieved
from the extras.

Finally, we will be able to inject our presenter using the fragment scope the same way we've done for other
presenters before.
