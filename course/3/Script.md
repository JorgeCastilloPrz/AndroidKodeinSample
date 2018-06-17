# 3: "The application scope"

(Adding Basic Dependency Injection to the application scope, showcasing how to write the bindings
with Kodein.)

On this course we are building up a complete application with a decoupled architecture from scratch
so you can learn which points are key to abstract away and how to do it. The app will show a list of
fancy photos that you can click on to display their details.

First of all, lets create an application class like this one, extending the android Application
class, and let's not forget about adding it to manifest.

Once we have that ready, we'll make this class implement the KodeinAware interface. By doing this,
we're making our class able to work with Kodein seamlessly.

Each Kodein instance is like a dependency container, or a dependency graph by itself, we could say.
It's very similar to what a component could be for Dagger. By implementing KodeinAware, we are
bringing a Kodein container into scope for the application class, where we will be providing some
dependencies soon.

That means we can override that kodein instance to provide our specific dependencies at an
application level here, into this block.

We can use modules for segregating our dependencies smartly. Here, we can create an appModule()
for example to provide some dependencies. It's gonna be empty for now, but we can actually import it
into the application Kodein scope by using the import statement.

That's gonna make all dependencies provided from inside the app module and any other nested modules
inside of it provided on the application Kodein container.

As you have probably figured out already, the Kodein concept of Module is, once again, very similar
to the very same concept from Dagger.
