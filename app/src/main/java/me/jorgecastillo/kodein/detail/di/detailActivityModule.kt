package me.jorgecastillo.kodein.detail.di

import me.jorgecastillo.kodein.detail.domain.interactor.GetPhoto
import me.jorgecastillo.kodein.detail.presenter.DetailPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * Specific android scoped module for the detail activity.
 */
fun detailActivityModule(photoId: String) = Kodein.Module("detailActivityModuleDI") {
  bind<GetPhoto>() with provider { GetPhoto(instance()) }
  bind<DetailPresenter>() with provider { DetailPresenter(instance(), photoId, instance()) }
}
