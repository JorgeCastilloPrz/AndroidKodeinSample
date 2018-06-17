autoscale: true
footer: [caster.io - Dependency Injection in Android using Kodein](https://www.caster.io)
slidenumbers: true

# "What's Kodein?"

---

## __Dependency Injection__ framework written with __Kotlin__ in mind.

---

It's a __dependency container__ (__service locator__)

* You tell Kodein how to instantiate classes.
* Kodein nests and provides them for you.

---

Leverages the power of __Kotlin DSL support__ + __high order functions__

* Bindings stored as __deferred functions__

```kotlin
bind<Logger> with provider { LogcatLogger(ctx) }
```

---

## ~~Compile time dependency validation~~

## Dependencies resolved __at runtime__

* No fail fast (no compile time errors)
* Lower safety (no compile time errors)
* Faster iteration & flexibility due to simplicity

# Choose your __Trade-offs__ smartly :point_left:

---

* __Codebase__: https://github.com/Kodein-Framework/Kodein-DI
* __Docs__: http://kodein.org/Kodein-DI/

---

## "What will I learn?"
* Build a __complete application architecture__ step by step.
* Learn to __abstract and inject__ out our dependencies using Kodein.

# Welcome!
