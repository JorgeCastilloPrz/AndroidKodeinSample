# 7: "Injecting dependencies on the Activity Scope"

We are going to inject our first dependency using the activity scope. That means we will need to add our first activity,
like the one we got here. I've coded this PhotoListActivity, which is going to display a list of fancy photos from
interesting authors. There's nothing very interesting to note about how it's done, other than a simple layout with the
list and a toolbar with its own menu. But if you're curious just take a look at the same repository we're you'll find
all the code.

One key thing to note is that the activity inherits from InjectedActivity. All our activities will need to do so, in
order to inherit the activity scope Kodein instance.

We are going to inject a specific dependency just needed by this activity, so we will start by overriding the delegate
function activityModule() that we created for it. We will provide our specific bindings on it.

Let's create and import a photoListActivityModule for that.

Now we are going to create a presenter so we can inject it. Presenters wrap UI logic for the views, which are the
activities, fragments or custom views. We don't want to dive deeper on how they're encoded since this is not an MVP
course, but note that we will want them to be injected so we can replace them later on easily on tests. Again, please
feel free to have look to the implementation in the repository if you need it.

(Show BasePresenter)

Our presenters will inherit from this BasePresenter that I've created to enclose convenience methods that all presenters
will need to implement. One example is the logics to link and unlink the view implementation depending on the lifecycle.

Now, I'm creating a PhotoListPresenter that will extend from it, and provide it's own view contract.

Whenever the view resumes it's life, the presenter is linked to do the same, and we will use that time to load our
photos. Whenever photos are loaded, presenter will tell the view to render them.

And there's still something missing. The view requires an instance of the presenter to work with it, so we're
injecting it. (add private val presenter by instance<PhotoListPresenter>()). This is the way we will retrieve the
instance, but we also need to bind it on the module we created for it. (write the binding on the module).

Whenever we launch this activity, we will get it injected and we'll be ready to use it. As you can see, we can already
link the activity onResume and onPause callbacks to the presenter so it links and unlinks the view depending on the
lifecycle.

Since the presenter is provided on the activity scope and just referenced by it, whenever this activity gets destroyed
it will be able to get collected by garbage collector, and therefore the presenter will also be.

By adding the bindings on this scope, we're providing it on this given activity scope and not for other activities,
neither the application scope.

And with this, we've got our activity scope ready and depending on the application one, and also being able to provide
specific bindings per activity. That means we're ready to move on to next lesson!
