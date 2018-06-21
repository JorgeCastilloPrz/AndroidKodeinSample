# 15: "Injecting Custom Views"

We've learned how to inject applications, activities, and also fragments. But we are still forgetting about another very
important piece of any app: The Custom Views.

If we have a new look to our graph, we could include custom views on it. Custom View Scope is also part the outermost
circle, along with the fragment one. Both scopes extend the activity one. So, even if you place your custom view into a
fragment, the closestKodein function will return the enclosing activity scope, completely ignoring the fragment one.
You must remember this.

Custom Views injection is usually resolved using their enclosing Activity or Fragment Scope, and that's completely
legit. But that usually happens because the dependency injection frameworks do not provide an easy enough way to
retrieve the closest scope. But Kodein does with the really smart closestKodein() function.

Views also use to be quite simple and dumb in terms of logics, so that's another good reason why injecting dependencies
on them is not that usual. But it's still needed from time to time.

So, if you need to code a complex custom view which requires to get its own dependencies injected, you could easily
create a scope for it.

As you probably already imagine, creating a custom view scope is exactly equal to what we've already done for the
fragment or the activity scopes.

Let's say we have a custom view like this one, which is just a dumb textview extension that I'll use as an example. I
have replaced the DescriptionFragment textview with this custom version of it. I can request the closestKodein() here
also, but as I said before, it will return the enclosing activity scope, ignoring the fragment one.

Now, I just need to extend the KodeinAware interface one more time, and then override the Kodein module property to
provide the bindings required for this scope. Let's just provide the logger here by overriding the one declared in the
application class. Of course this doesn't make a lot of sense, but it's just in purpose of showing how you can inject
dependencies at this level.

Once we have it provided, we just need to retrieve it using the instance Kodein function as we have already done for any
injected dependencies at any scope until now.

By using this technique, you will not need to manually inject the custom views when you create your enclosing scopes
like the activity or the fragment one. The custom views will be able to be auto injected, the same way fragments and
activities are.
