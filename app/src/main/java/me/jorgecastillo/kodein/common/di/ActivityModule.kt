package me.jorgecastillo.kodein.common.di

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

fun baseActivityModule(activity: AppCompatActivity) = Kodein.Module {
  bind<Activity>() with provider { activity }
}
