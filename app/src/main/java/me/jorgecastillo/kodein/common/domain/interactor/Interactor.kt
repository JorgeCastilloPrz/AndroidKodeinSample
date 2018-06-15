package me.jorgecastillo.kodein.common.domain.interactor

import arrow.core.Either
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import me.jorgecastillo.kodein.common.domain.error.Error

abstract class Interactor<in Params, out Type> where Type : Any {

  abstract suspend fun run(params: Params): Either<Error, Type>

  fun execute(params: Params, onResult: (Either<Error, Type>) -> Unit) {
    val job = async(CommonPool) { run(params) }
    launch(UI) { onResult.invoke(job.await()) }
  }
}
