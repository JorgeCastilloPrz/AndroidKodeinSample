package me.jorgecastillo.kodein

import arrow.core.Either
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.interactor.Invoker
import me.jorgecastillo.kodein.common.domain.interactor.UseCase

/**
 * Blocking implementation of the invoker. Blocks the current thread to complete the invoked UseCase
 * by using runBlocking {}.
 */
class BlockingUseCaseInvoker(private val dispatcher: CoroutineDispatcher) : Invoker {

  override fun <Params, Type : Any> execute(
      useCase: UseCase<Params, Type>,
      params: Params,
      onResult: (Either<Error, Type>) -> Unit) {

    launch(dispatcher) {
      onResult.invoke(runBlocking { useCase.run(params) })
    }
  }
}
