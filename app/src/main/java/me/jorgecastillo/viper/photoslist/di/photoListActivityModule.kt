package me.jorgecastillo.viper.photoslist.di

import me.jorgecastillo.viper.photoslist.presenter.PhotoListPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * Specific android scoped module for the photos list activity.
 */
fun photoListActivityModule() = Kodein.Module {
  bind<PhotoListPresenter>() with provider { PhotoListPresenter() }
}
