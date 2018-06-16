package me.jorgecastillo.kodein

import android.app.Application
import android.support.annotation.VisibleForTesting
import me.jorgecastillo.kodein.common.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

open class PhotosApp : Application(), KodeinAware {

  @VisibleForTesting
  var overrideBindings: Kodein.MainBuilder.() -> Unit = {}

  override var kodein = Kodein.lazy {
    import(appModule(applicationContext))
  }
}
