package me.jorgecastillo.kodein.common.router

import android.content.Context
import me.jorgecastillo.kodein.detail.view.DetailActivity

/**
 * Android Router implementation. We're implementing an interface so we can easily switch the router
 * by a mock in tests when needed. We don't want to have android dependencies on our unit tests.
 */
class PhotoAppNavigator(private val context: Context) : Navigator {
  override fun goToDetail(photoId: String) {
    context.startActivity(DetailActivity.getIntent(context, photoId))
  }
}
