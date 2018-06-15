package me.jorgecastillo.viper.detail.di

import me.jorgecastillo.viper.detail.domain.interactor.GetPhoto
import me.jorgecastillo.viper.detail.presenter.DetailPresenter
import me.jorgecastillo.viper.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.viper.photoslist.presenter.PhotoListPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * Specific android scoped module for the detail activity.
 */
fun detailActivityModule(photoId: String) = Kodein.Module {
  bind<GetPhoto>() with provider { GetPhoto(instance()) }
  bind<DetailPresenter>() with provider { DetailPresenter(photoId, instance()) }
}
