# 10: "Tagged injections"

(Add repository and inject the network datasource and also an in-memory one on it. Use it as an example on how to use
tagged injections (qualifiers for implementing the same contract with 2 different implementations))

We have learned that bindings are used to link a type and an instance provided for that given type. We have also
learned that instance() can be used as a way to link nested bindings.

But what happens when there are two bindings for the same type? Let's say you need two different runtime implementations
for the same type provided on the same scope, like two different OkHttp Interceptors for the OkHttpClient.

You'll get a runtime crash telling you that there's already an existing binding for that given type. Kodein does not
allow multiple bindings for the same type by default, since then it wouldn't be able to know which one to apply.

So what can we do for these cases? You can use tagged bindings for resolving the ambiguity. This concept is equivalent
to the qualifiers used in other frameworks like Dagger.

(Switch to code -> HttpClient.kt)

We got this function that builds up an OkHttpClient. Let's say we also have these two interceptors. One will log all the
http requests including parameters, headers, bodies, anything relevant. The other one will apend some headers to every
request.

Let's say we wanna inject these two on the OkHttpClient. (Switch to HttpClient.kt) First, since my client is provided by
a function, I'll add a couple of interceptors as arguments. First one will be the headers interceptor, and second one
will be the logging one.

But we still need to bind them properly in the application scope, where our OkHttpClient is being provided. We could add
an instance() call to fulfill each one of them, but then we'll get a runtime error because of the ambiguity. To solve
that, we can just add a tag name on them.

(add the tags here `httpClient(instance(tag = "headers"), instance(tag = "logging"))`)

And then we will need a couple of interceptor bindings qualified with the same tag, like these two.

That means that each time an interceptor is requested it'll check the tag value, and if there's a binding available for
the Interceptor type with the same tag, it will use it to provide the dependency.

And our problem is solved!
