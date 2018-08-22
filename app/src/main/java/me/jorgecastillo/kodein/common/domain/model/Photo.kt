package me.jorgecastillo.kodein.common.domain.model

data class Photo(
  val id: String,
  val url: String,
  val author: String,
  val description: String?,
  val created_at: String
)
