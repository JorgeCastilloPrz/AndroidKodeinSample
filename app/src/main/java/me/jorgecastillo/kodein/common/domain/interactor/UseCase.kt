package me.jorgecastillo.kodein.common.domain.interactor

import arrow.core.Either
import me.jorgecastillo.kodein.common.domain.error.Error

abstract class UseCase<in Params, out Type> where Type : Any {

  abstract fun run(params: Params): Either<Error, Type>
}
