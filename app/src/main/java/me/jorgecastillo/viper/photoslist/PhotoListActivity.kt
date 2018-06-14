package me.jorgecastillo.viper.photoslist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_photo_list.*
import me.jorgecastillo.viper.R
import me.jorgecastillo.viper.common.di.InjectedActivity
import me.jorgecastillo.viper.photoslist.di.photoListActivityModule
import me.jorgecastillo.viper.photoslist.domain.model.Photo
import me.jorgecastillo.viper.photoslist.presenter.PhotoListPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class PhotoListActivity : InjectedActivity(), PhotoListPresenter.View {

  private val presenter by instance<PhotoListPresenter>()

  override fun activityModule() = Kodein.Module {
    import(photoListActivityModule())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_photo_list)
    setSupportActionBar(toolbar)
    setFabListener()
  }

  override fun onResume() {
    super.onResume()
    presenter.resume(this)
  }

  override fun onPause() {
    super.onPause()
    presenter.pause()
  }

  private fun setFabListener() {
    fab.setOnClickListener {
      presenter.onAddButtonClicked()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_photo_list, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.action_settings -> true
    else -> super.onOptionsItemSelected(item)
  }

  override fun showLoading() {

  }

  override fun hideLoading() {

  }

  override fun renderPhotos(photos: List<Photo>) {

  }

  override fun displayLoadingPhotosError() {
    Snackbar.make(toolbar, R.string.loading_photos_error, Snackbar.LENGTH_SHORT).show()
  }
}
