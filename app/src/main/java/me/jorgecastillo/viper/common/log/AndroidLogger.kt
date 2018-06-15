package me.jorgecastillo.viper.common.log

import android.content.Context
import android.util.Log

/**
 * Android Logger implementation using Logcat.
 */
class AndroidLogger(private val context: Context) : Logger {
  override fun log(tag: String, level: Logger.Level, message: String) {
    when (level) {
      Logger.Level.Info -> Log.i(tag, message)
      Logger.Level.Debug -> Log.d(tag, message)
      Logger.Level.Warning -> Log.w(tag, message)
      Logger.Level.Error -> Log.e(tag, message)
      Logger.Level.Verbose -> Log.v(tag, message)
    }
  }

}
