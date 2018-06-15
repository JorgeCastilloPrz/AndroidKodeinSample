package me.jorgecastillo.kodein

import android.app.Application
import me.jorgecastillo.kodein.common.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class PhotosApp : Application(), KodeinAware {

  override val kodein = Kodein.lazy {
    import(appModule(applicationContext))
  }
}
