# 10: "Tagged injections"

We have learned that bindings are used to link a type and an instance provided for that given type. We have also
learned that instance() can be used as a way to link nested bindings.

But what happens when there are two bindings for the same type? Let's say you need two different runtime implementations
for the same type provided on the same scope, like two different OkHttp Interceptors for the OkHttpClient.

You'll get a runtime crash telling you that there's already an existing binding for that given type. Kodein does not
allow multiple bindings for the same type by default, since then it wouldn't be able to know which one to apply.

So what can we do for these cases? You can use tagged bindings for resolving the ambiguity. This concept is equivalent
to the concept of provider qualified names used in other frameworks like Dagger.

Let's dig a bit more on this concept with some code.

(Switch to code -> HttpClient.kt)

We got this function that builds up an OkHttpClient. Let's say we also have these two interceptors. One will log all the
http requests including parameters, headers, bodies, anything relevant. The other one will apend some headers to every
request.

Let's say we wanna inject these two on the OkHttpClient. (Switch to HttpClient.kt) First, since my client is provided by
a function, I'll add a couple of interceptors as arguments. First one will be the headers interceptor, and second one
will be the logging one.
The headers one will be appended without any conditions. The Logging one will be appended to the client just for the
Debug variant, since we don't want our logs to be enabled in production.

But we still need to bind both interceptors in the application scope, where our OkHttpClient is being provided. We could
add an instance() call to fulfill each one of them, but then we'll get a runtime error because of the ambiguity. To
solve that, we can just add a tag name on them.

Let's name them differently, like "headers" and "logging", for example.

(add the tags here `httpClient(instance(tag = "headers"), instance(tag = "logging"))`)

Now, we will need a couple of interceptor bindings qualified with the very same tags, like these two.

That means that each time an interceptor is requested it'll check the tag value, and if there's a binding available for
the Interceptor type with the same tag, it will use it to provide the dependency.

And our problem is solved!

As you can see, our app is running as expected, and looking neat. (Run the app).
