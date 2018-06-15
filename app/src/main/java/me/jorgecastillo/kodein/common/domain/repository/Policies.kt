package me.jorgecastillo.kodein.common.domain.repository

/**
 * Any repo is prone to apply different cache policies per query method.
 */
sealed class CachePolicy {
  object NetworkFirst : CachePolicy()
  object LocalFirst : CachePolicy()
  object LocalOnly : CachePolicy()
  object NetworkOnly : CachePolicy()
}
