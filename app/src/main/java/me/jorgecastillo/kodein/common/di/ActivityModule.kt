package me.jorgecastillo.kodein.common.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * Activity scope. All activity modules can depend on this one, which will bring the activity
 * context into scope. Any dependencies that are just needed during the lifecycle of an activity
 * are prone to be here. Interactors for example, or the Router since it requires an activity
 * context.
 */
fun baseActivityModule(activity: AppCompatActivity) = Kodein.Module("baseActivityModule") {
  bind<Context>(overrides = true) with provider { activity }
}
