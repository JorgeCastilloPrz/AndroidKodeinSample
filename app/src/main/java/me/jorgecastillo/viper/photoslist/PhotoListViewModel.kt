package me.jorgecastillo.viper.photoslist

import me.jorgecastillo.viper.common.viewmodel.BaseViewModel

class PhotoListViewModel() : BaseViewModel<PhotoListViewState>(defaultState = PhotoListViewState()) {

  val navigateToConversationList = SingleLiveEvent<Unit>()
  val updateAgentBioState = SingleLiveEvent<AgentBioState>()

  init {
    loadPhotos()
  }

  private fun loadPhotos() {
    val params = RetrieveMessagesUseCase.Params(clientConversationId)
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
    })
  }
}
