package me.jorgecastillo.kodein.common.router

/**
 * Navigation contract used by the app. It can be implemented for different frameworks like Android.
 */
interface Router {

  fun goToDetail(photoId: String)
}
