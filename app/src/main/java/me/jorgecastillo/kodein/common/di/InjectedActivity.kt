package me.jorgecastillo.kodein.common.di

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import me.jorgecastillo.kodein.PhotosApp
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein

abstract class InjectedActivity : AppCompatActivity(), KodeinAware {

  // closestKodein() automatically fetches app Kodein scope.
  private val appKodein by closestKodein()

  override val kodein: Kodein by retainedKodein {
    extend(appKodein)
    import(baseActivityModule(this@InjectedActivity))
    import(activityModule())
    (app().overrideBindings)()
  }

  /**
   * Optional to override, if your activity needs specific DI.
   */
  open fun activityModule() = Kodein.Module {
  }
}

fun Activity.app() = applicationContext as PhotosApp
