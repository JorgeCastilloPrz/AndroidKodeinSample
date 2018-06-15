package me.jorgecastillo.kodein.common.router

import android.app.Activity
import me.jorgecastillo.kodein.detail.view.DetailActivity

/**
 * Android Router implementation. We're implementing an interface so we can easily switch the router
 * by a mock in tests when needed. We don't want to have android dependencies on our unit tests.
 */
class PhotoAppRouter(private val activity: Activity) : Router {
  override fun goToDetail(photoId: String) {
    activity.startActivity(DetailActivity.getIntent(activity, photoId))
  }
}
