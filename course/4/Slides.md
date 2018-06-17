autoscale: true
footer: [caster.io - Dependency Injection in Android using Kodein](https://www.caster.io)
slidenumbers: true

# "Your first injection"

---

## ~~Directly depending on external frameworks~~
## __Abstract away__ those pieces

---

```kotlin
class Logger {
    fun log(tag:String, message: String): Unit {
        Log.d(tag, message)
    }
}

// Other pieces of the architecture:
logger.log("Use Case", "Photos not found!")
```

---

## Wrong: We're strongly __depending on the Android framework__!
