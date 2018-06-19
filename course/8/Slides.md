autoscale: true
footer: [caster.io - Dependency Injection in Android using Kodein](https://www.caster.io)
slidenumbers: true

# "Transitive Dependencies"

---

* PhotosListActivity bindings:

```kotlin
fun photoListActivityModule() = Kodein.Module {
  bind<PhotoListPresenter>() with provider { PhotoListPresenter() }
}
```

* Presenter can be instantiated and injected on the activity

---

* But what if presenter had __its own dependencies__?

```kotlin
fun photoListActivityModule() = Kodein.Module {
  bind<PhotoListPresenter>() with provider { PhotoListPresenter(????, ????, ????) }
}
```

* __Nested bindings__ to the rescue!
