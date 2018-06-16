package me.jorgecastillo.kodein

import me.jorgecastillo.kodein.common.di.appModule
import org.kodein.di.Kodein

class TestApp : PhotosApp() {

  /**
   * We can pass a lambda with receiver which provides the bindings over a Kodein instance, and then
   * run it over our application scoped Kodein to override the bindings.
   */
  fun overrideDependencies(bindings: Kodein.MainBuilder.() -> Unit) {
    kodein = Kodein.lazy {
      import(appModule(applicationContext))
      bindings()
    }
  }

  /**
   * After each test will need to restore it to its initial state.
   */
  fun clearOverrides() {
    kodein = Kodein.lazy {
      import(appModule(applicationContext))
    }
  }
}
