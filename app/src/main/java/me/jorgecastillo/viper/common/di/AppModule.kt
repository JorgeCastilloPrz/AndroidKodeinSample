package me.jorgecastillo.viper.common.di

import android.content.Context
import me.jorgecastillo.viper.common.AndroidLogger
import me.jorgecastillo.viper.common.Logger
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Application scoped dependencies.
 */
fun appModule(appContext: Context) = Kodein.Module {
  bind<Context>() with provider { appContext }
  bind<Logger>() with singleton { AndroidLogger(instance()) }
  // bind<Router>() with provider { AndroidNavigator(instance()) }
}
