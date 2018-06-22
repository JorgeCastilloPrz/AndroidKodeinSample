# 17: "Injecting Mocks on your UI tests"

This time we are not testing our architecture end to end, but cutting the dependency graph on the use cases, so we
can test our view implementation along with its presenters. This is just one possible approach for writing simple UI
tests, but please feel free to cut the graph wherever it fits better for your codebase.

(Show test code)

When you write UI tests for Android, the inversion of control is strong. The Android SDK instantiates the application
and the activities for you, and you don't get any control on that process. That means you cannot perform constructor
injection, and the activity will inject itself on creation using its Kodein container. You will not have any chance to
step in the middle of that process and replace dependencies from your testing environment.

(Show PhotosApp).

As a consequence of this, you must find an approach that lets the activity lifecycle complete, so it gets launched, and
then come right after that to override dependencies for that given scope. That's why binding overrides is a key feature
for Dependency Injection frameworks when we write Android UI tests.

To achieve what we want, lets make a single upgrade to the application class. We are going to declare this
overrideBindings extension property that works over a Kodein.MainBuilder receiver. We will use this to override
bindings once the activities are loaded. Note that we're initializing it as an empty function, so there are not going to
be any binding overrides by default.

Let's go now to the injected activity, and let's apply those binding overrides defined on the application class right
at the end of the activity scope. app() is just a simple utility function created to retrieve the application class, for
better syntax.

Once we have that, we'll just need to add the binding overrides we need on our tests to the application class right
before every test runs. And also clean those afterwards, to isolate tests properly.

One of the best ways to run some code before and after any test can be a JUnit Rule. I've created this OverridesRule,
which receives the same extension property working over the Kodein.MainBuilder type, that will carry out the binding
overrides. Before every test, it takes the application class using the android Instrumentation, and passes the overrides
to it. And after every test it will clear out the bindings.

We can go back to our test suite now, and provide the bindings we want when creating the Rule. I'm going to override the
invoker to use the blocking version of it one more time, and I will also override the use case binding to provide a
mock. That will give me the chance to configure the mock as I need for every test.

There's also an activity rule to launch the photo list activity from the tests. And with this, we're ready to start
testing.

On the first test, I'm asserting that photos are loaded and rendered as expected when the activity is launched. I call
this given method to configure the use case to return the photos I have hardcoded on this collection. After that, I
launch the activity.

Once the activity is loaded I just need to assert over the items on the list looking for the corresponding author name
for each element. I'm using this smart RecyclerViewInteraction created by Karumi. It takes care of scrolling through the
list and performing assertions for each one of the elements. This should be enough to know that all the pictures are
being rendered, at least for the example. If you wanted to be more strict you would probably want to assert that are the
visible properties for each picture are being rendered as expected.

The second test asserts that the error snackbar is displayed whenever the use case to load the photos returns an error.
The given step is equivalent, we configure the use case mock to return an error. Then we launch the activity and perform
the assertion.

These are just two simple examples of how you can write tests with no fear to side effects or race conditions since
you've taken care of replacing the dangerous dependencies with mocks thanks to Kodein overrides. A Dependency Injection
framework like Kodein becomes really handy when writing apps because you can rely on it to wire up the complete
dependency tree, and just replace some given dependencies at any arbitrary level on the tree.

And with this, you are ready to inject dependencies on any app you write thanks to Kodein. Thanks for watching this
Course. If want to learn more, please follow me. My Twitter handle is JorgeCastilloPR.
