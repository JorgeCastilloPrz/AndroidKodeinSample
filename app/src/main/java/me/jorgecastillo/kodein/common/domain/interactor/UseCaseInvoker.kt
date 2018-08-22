package me.jorgecastillo.kodein.common.domain.interactor

import arrow.core.Either
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import me.jorgecastillo.kodein.common.domain.error.Error

class UseCaseInvoker : Invoker {

  override fun <Params, Type : Any> execute(
    useCase: UseCase<Params, Type>,
    params: Params,
    onResult: (Either<Error, Type>) -> Unit
  ) {

    val job = async(CommonPool) { useCase.run(params) }
    launch(UI) { onResult.invoke(job.await()) }
  }
}
