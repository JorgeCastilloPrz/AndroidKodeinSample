autoscale: true
footer: [caster.io - Dependency Injection in Android using Kodein](https://www.caster.io)
slidenumbers: true

# "Tagged injections"

---

## Bindings to __wire a type and an instance__
```kotlin
bind<Interceptor>() with singleton { loggingInterceptor() }
```

## instance() to __nest bindings__
```kotlin
bind<OkHttpClient>() with singleton { client(interceptor = instance()) }
```

---

## __Problem:__
## Single type for __two different implementations__?
```kotlin
fun httpModule(): Kodein.Module {
  bind<Interceptor>() with singleton { AuthHeadersInterceptor() }
  bind<Interceptor>() with singleton { LoggingInterceptor() }

  bind<OkHttpClient>() with singleton { httpClient(instance(), instance()) }
}
```

---

## __Runtime exception!__ :boom:
## "There's a binding already defined for the type Interceptor"


### __Not allowed:__ Binding two different implementations to one type.

---

## __Tagged bindings__ to the rescue! :muscle:
