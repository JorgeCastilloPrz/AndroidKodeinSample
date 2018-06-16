package me.jorgecastillo.kodein

import android.app.Activity
import arrow.core.right
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.experimental.runBlocking
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.kodein.photoslist.view.PhotoListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import android.support.test.espresso.intent.rule.IntentsTestRule;

@RunWith(MockitoJUnitRunner::class)
class PhotoListActivityShould {

  @Mock
  private lateinit var getPhotos: GetPhotos

  @Rule
  @JvmField
  var activityRule: IntentsTestRule<PhotoListActivity> =
      IntentsTestRule(PhotoListActivity::class.java, true, false)

  @Rule
  @JvmField
  val overridesRule: OverridesRule = OverridesRule {
    bind<GetPhotos>(overrides = true) with provider { getPhotos }
  }

  @Test
  fun showPhotosOnSuccessfulLoading() {
    runBlocking {
      givenSuccessfulPhotosLoading()

      activityRule.launch()
    }
  }

  private suspend fun givenSuccessfulPhotosLoading() {
    whenever(getPhotos.run(anyGetPhotosParams)).thenReturn(anyPhotos.right())
  }

  companion object {
    val anyGetPhotosParams = GetPhotos.Params(1, "Any query")
    val anyPhotos: List<Photo> = (1..3).map {
      Photo(it.toString(), "http:url/$it", "AuthorName$it", "Description$it", "2018-06-19")
    }
  }
}

fun <T : Activity> IntentsTestRule<T>.launch(): T {
  return launchActivity(null)
}
