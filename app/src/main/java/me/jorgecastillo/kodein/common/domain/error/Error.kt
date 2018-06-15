package me.jorgecastillo.kodein.common.domain.error

/**
 * Domain errors will fit with one of these types.
 */
sealed class Error {
  class NetworkConnection : Error()
  class ServerError : Error()

  abstract class FeatureError : Error()
}
