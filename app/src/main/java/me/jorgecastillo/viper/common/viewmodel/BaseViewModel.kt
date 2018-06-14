package me.jorgecastillo.viper.common.viewmodel

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<T>(defaultState: T) : ViewModel() {

  val viewState = NonNullMutableLiveData(defaultState)

  fun updateViewState(liveDataProducer: (T) -> T) {
    viewState.value = liveDataProducer(viewState.value)
  }
}
