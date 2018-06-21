package me.jorgecastillo.kodein.detail.description

import me.jorgecastillo.kodein.common.presenter.BasePresenter

class DescriptionPresenter(private val description: String) : BasePresenter<DescriptionPresenter.View>() {

  interface View : BasePresenter.View {
    fun renderDescription(descriptionText: String): Unit
  }

  override fun resume(view: View) {
    super.resume(view)
    view.renderDescription(description)
  }
}
