package me.jorgecastillo.viper.photoslist.presenter

import me.jorgecastillo.viper.common.presenter.BasePresenter
import me.jorgecastillo.viper.photoslist.domain.model.Photo

class PhotoListPresenter() : BasePresenter<PhotoListPresenter.View>() {

  interface View : BasePresenter.View {
    fun renderPhotos(photos: List<Photo>)
  }

  init {
    loadPhotos()
  }

  private fun loadPhotos() {
    /*val params = RetrieveMessagesUseCase.Params(clientConversationId)
    retrieveMessagesUseCase.execute(params, object : DefaultFlowableObserver<List<Message>>() {
      override fun onNext(t: List<Message>) {
        introduceAgentIfNeeded(t)
        updateViewState { oldState ->
          oldState.copy(
              messages = t.toViewState(),
              scrollToBottom = t.isNotEmpty()
          )
        }
      }
    })*/
  }
}
