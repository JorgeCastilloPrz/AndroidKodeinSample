autoscale: true
footer: [caster.io - Dependency Injection in Android using Kodein](https://www.caster.io)
slidenumbers: true

# "Lazy Injections"

---

* bindings work as __deferred functions__

```kotlin
bind<Logger>() with singleton { AndroidLogger(ctx) }
```
* Runs whenever `{ AndroidLogger(ctx) }` gets called.

## __Everything is lazy__ by default!

---

## Laziness ensures __memory__ integrity
### Avoid blowing your memory heap


