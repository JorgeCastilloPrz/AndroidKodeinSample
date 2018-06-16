package me.jorgecastillo.kodein.detail.presenter

import arrow.core.Either
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.interactor.Invoker
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.presenter.BasePresenter
import me.jorgecastillo.kodein.detail.domain.interactor.GetPhoto

class DetailPresenter(
    private val invoker: Invoker,
    private val photoId: String,
    private val getPhoto: GetPhoto) : BasePresenter<DetailPresenter.View>() {

  interface View : BasePresenter.View {
    fun renderPhoto(photo: Photo): Unit
    fun displayLoadingPhotoError(): Unit
    fun showShareChooser(): Unit
  }

  override fun resume(view: View) {
    super.resume(view)
    loadPhoto()
  }

  private fun loadPhoto() {
    view?.showLoading()
    invoker.execute(getPhoto, photoId, ::onPhotoArrived)
  }

  private fun onPhotoArrived(result: Either<Error, Photo>) {
    result.fold(ifLeft = {
      view?.hideLoading()
      view?.displayLoadingPhotoError()
    }, ifRight = {
      view?.hideLoading()
      view?.renderPhoto(it)
    })
  }

  fun onShareButtonClicked() {
    view?.showShareChooser()
  }
}
