package me.jorgecastillo.viper.common.presenter

/**
 * Presenter contract. It must have a view assigned to the type T.
 */
open class BasePresenter<T : BasePresenter.View> {

  /**
   * Base view contract. All views on this app will have a loading progressbar.
   */
  interface View {
    fun showLoading()
    fun hideLoading()
  }

  var view: View? = null

  fun resume(view: View) {
    this.view = view
  }

  fun pause() {
    this.view = null
  }
}
