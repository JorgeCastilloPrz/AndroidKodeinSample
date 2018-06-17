# 2: "First steps"

Kodein provides some different artifacts oriented for using Kodein on different platforms.

* kodein-di-generic-jvm: This is the generic JVM artifact, which is immune to type erasure.
* kodein-di-erased-jvm: This is equivalent but with the main difference that it does suffer from
type erasure. We'll know more about this later.
* kodein-di-erased-native, for giving support to other native platforms like iOS.
* kodein-di-erased-js, obviously for Javascript.
* kodein-di-framework-android, providing android syntax and utilities.

Since we're doing Android, on this course will be interested on the jvm and the android ones.

(show http://kodein.org/Kodein-DI/?5.0/core#install)
To fetch Kodein dependency, add jcenter to your list of repositories for the gradle modules where
you want to use it.

Then, add the gradle jvm generic dependency, which will give you access to the core parts of the
library targeting the jvm.

This version of the core is **immune to type erasure**. That means instances like
`instance<List<String>>()` and `instance<List<Int>>()` will yield two different types:
`List<String>` and `List<Int>`. So this will be very handy if you're using generic types on your
app.

But being immune to type erasure has a cost. We don't want to get into too deep details, but it relies
on some Kodein machinery to wire up types that is known to be a bit slow. In my opinion you
shouldn't worry too much about this fact unless your dependency graph is really complex, not like
the average android apps out there.

If still you find performance problems, it will probably be related to not using Kodein properly,
and that's the rationale of this course. But anyways, you must know that there is also a Kodein jvm erased
version which is more performant, but does suffer from type erasure. That means you will get into
trouble and will need some tricky workarounds on your code to make it work from time to time.

So I highly recommend you using the generic one.

Apart from the jvm artifact, since we're doing Android we will need to add the Kodein Android
artifact, and then we'll be good to go.
