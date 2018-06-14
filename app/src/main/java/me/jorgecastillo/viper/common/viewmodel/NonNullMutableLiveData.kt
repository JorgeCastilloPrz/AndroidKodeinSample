package me.jorgecastillo.viper.common.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * NonNull extension for the MutableLiveData so the emitted values to the observer are never null.
 */
open class NonNullMutableLiveData<T>(initialValue: T) : MutableLiveData<T>() {

  init {
    value = initialValue
  }

  // Null values intentionally not supported here. We want to crash if some.
  override fun getValue(): T = super.getValue()!!

  fun observe(owner: LifecycleOwner, block: (T) -> Unit) {
    observe(owner, Observer<T> {
      it?.let(block)
    })
  }
}
