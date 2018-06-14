package me.jorgecastillo.viper.common.viewmodel

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<T>(initialValue: T) : ViewModel() {

  val viewState = NonNullMutableLiveData(initialValue)

  fun updateViewState(liveDataProducer: (T) -> T) {
    viewState.value = liveDataProducer(viewState.value)
  }
}
