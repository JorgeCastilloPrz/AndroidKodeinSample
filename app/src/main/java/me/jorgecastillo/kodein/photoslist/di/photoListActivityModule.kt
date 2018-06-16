package me.jorgecastillo.kodein.photoslist.di

import me.jorgecastillo.kodein.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.kodein.photoslist.presenter.PhotoListPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * Specific android scoped module for the photos list activity.
 */
fun photoListActivityModule() = Kodein.Module {
  bind<GetPhotos>() with provider { GetPhotos(instance()) }
  bind<PhotoListPresenter>() with provider { PhotoListPresenter(instance(), instance(), instance()) }
}
