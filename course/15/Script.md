# 15: "Injecting Custom Views"

We've learned how to inject applications, activities, and also fragments. But we are still forgetting about another very
usual piece: The Custom Views.

Custom Views are usually injected using their enclosing Activity or Fragment Scope, and that's completely legit. But
that is usually because the dependency injection frameworks used do not provide a way of supporting dependent scopes
transparent enough like Kodein does with the closestKodein() function. And also because custom views are usually quite
simple and dumb in terms of logics.

But if you still want to encode a more complex function which requires to get its own dependencies, you could create a
scope for it.

If we have a new look to our graph, we could include custom views. Custom View Scope would be also in the outermost
layer, along with the fragment one, in case the custom view is rendered into an activity.
