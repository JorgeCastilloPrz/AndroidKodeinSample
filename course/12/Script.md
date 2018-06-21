# 12: "Adding a Detail Activity"

We already have a photos list activity in place, so the user can scroll on it to enjoy some fancy photos. But we would
love to add a detail activity now, so any time the user clicks on one of the photos we can navigate to the detail.

We will use this lesson as a chance to briefly recap the different steps we need to follow to create an activity scope,
the same way we did for the photos list activity.

Detail activity is going to be quite simple. I'll not show you how to encode a simple detail screen, but this will to be
the final look for it. (show detail activity screenshot). As you can see, it's just showing the picture as a header,
also some author attributions and a basic description. There's also a share button to click and share the picture.

For injecting dependencies, we want to do the same we did for the list activity. We want to have a new activity scope
for this specific activity. To do that, we need to make sure that we are overriding the InjectedActivity, so we can
override the delegate method to provide our specific activity bindings for this one. If you can remember, base
activity provided an activity Kodein instance thanks to being KodeinAware, and it provided some generic dependencies
for all activities on it, plus delegating specific injection to each one of the child activities using this method.

So, If we go back to the detail, we will create a new module to provide specific bindings for this activity. We wanna
pass the photo Id to the module when building it, because we will need the Id to be passed in to the presenter to load
details for that given photo.

As you can see, our bindings are quite simple, and equivalent to the ones we provided for the list activity. We want to
provide here the dependencies we think will fit better into the activity scope, like the presenter or the use case to
retrieve a detail. Since the GetPhoto use case is very correlated to the details activity, it feels natural to put the
binding on this specific activity scope. Also note how we're passing the photoId to the presenter on its construction.

Then you will also notice a new requirement for the use case, which is something called PhotosRepository.

If you remember we had 2 different data sources, a network one to fetch the photos and
details and a local one to cache them. Both are going to be coordinated by an entity we'll put in the that I'm calling
PhotosRepository.

Repositories exist like a way for coordinating different sources of data, composing data coming from those different
sources, and even applying caching policies.

That's why our use case here is requiring a repository instance to be injected. Since the repository is gonna be
used from both screens to get the photos list or the photo detail, we will want to add the binding to the application
scope. (Add it). And of course it's gonna get both data sources injected as parameters.

If we take a rapid look to the repo, this is how it is (go to it). As you can see, it has a couple of functions to
get all the photos or a single one. It's playing with a bunch of caching policies defined by a sealed class, and
depending on which one we're using we will load photos from the data sources in different order.

Since we're using LocalFirst as the default policy, that means we are trying to load photos always from the local in
memory cache first, and if they're not available, then we will fetch them from network.

So, moving back to our activity, we wanna get our DetailPresenter injected on it. The detail presenter will have the
invoker to run the use case, the GetPhoto use case itself, and the photoId, all injected by constructor. We don't want
to move much further about how does it work, and remember you always have the sample repo for that. But note how it uses
the use case to request the details and whenever it gets a response from it, it will order the view to render the
details or an error, in case something was wrong.

Also, if the view sends an event indicating that the share button was clicked, the presenter will order it to show the
chooser to share the photo.

And with this, we already got all the pieces in place to run the app and navigate to the detail.

(Run the app, scroll a bit, navigate to the detail of one of the photos).
