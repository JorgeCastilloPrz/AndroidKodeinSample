package me.jorgecastillo.viper.common.router

import android.app.Activity
import me.jorgecastillo.viper.detail.view.DetailActivity

/**
 * Android Router implementation. We're implementing an interface so we can easily switch the router
 * by a mock in tests when needed. We don't want to have android dependencies on our unit tests.
 *
 * On an iOS VIPER project the Router would create the UIViewController or get it from a Storyboard,
 * but on Android we don’t create the Activities ourselves: we have to use Intents, and we don’t
 * have access to the newly created Activity from the previous one. But the main intention in the
 * end is to route from one given activity to another. And that's what we got here.
 */
class PhotoAppRouter(private val activity: Activity) : Router {
  override fun goToDetail(photoId: String) {
    activity.startActivity(DetailActivity.getIntent(activity, photoId))
  }
}
