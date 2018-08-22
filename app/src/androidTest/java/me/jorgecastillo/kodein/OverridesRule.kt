package me.jorgecastillo.kodein

import android.support.test.InstrumentationRegistry
import org.junit.rules.ExternalResource
import org.kodein.di.Kodein

/**
 * Used to retrieve test application before every test and override dependencies.
 */
class OverridesRule(private val bindings: Kodein.MainBuilder.() -> Unit = {}) : ExternalResource() {

  private fun app(): PhotosApp =
    InstrumentationRegistry.getTargetContext().applicationContext as PhotosApp

  override fun before() {
    app().overrideBindings = bindings
  }

  override fun after() {
    app().overrideBindings = {}
  }
}
