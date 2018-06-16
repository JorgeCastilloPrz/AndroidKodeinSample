package me.jorgecastillo.kodein

import android.app.Activity
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockito_kotlin.whenever
import me.jorgecastillo.kodein.common.data.network.PhotosNotFound
import me.jorgecastillo.kodein.common.domain.interactor.Invoker
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.kodein.photoslist.view.PhotoListActivity
import me.jorgecastillo.kodein.recyclerview.RecyclerViewInteraction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * UI tests for the list activity mocking out the domain layer (Use Cases) and any further pieces
 * beyond it.
 *
 * To mock use cases we use Kodein overrides on the application scope, so we override production
 * bindings with bindings providing mocks.
 */
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
    bind<Invoker>(overrides = true) with singleton { BlockingUseCaseInvoker() }
    bind<GetPhotos>(overrides = true) with provider { getPhotos }
  }

  @Test
  fun showPhotosOnSuccessfulLoading() {
    givenSuccessfulPhotosLoading(anyPhotos)

    activityRule.launch()

    RecyclerViewInteraction.onRecyclerView<Photo>(withId(R.id.photoList))
        .withItems(anyPhotos)
        .check { photo, view, e ->
          matches(hasDescendant(withText("AuthorName${photo.id}"))).check(view, e)
        }
  }

  @Test
  fun showFeedbackWhenErrorLoadingPhotos() {
    givenErrorLoadingPhotos()

    activityRule.launch()

    onView(withText("Something went wrong when loading images.")).check(matches(isDisplayed()))
  }

  private fun givenSuccessfulPhotosLoading(photos: List<Photo>) {
    whenever(getPhotos.run(anyGetPhotosParams)).thenReturn(photos.right())
  }

  private fun givenErrorLoadingPhotos() {
    whenever(getPhotos.run(anyGetPhotosParams)).thenReturn(PhotosNotFound().left())
  }

  companion object {
    val anyGetPhotosParams = GetPhotos.Params(1, "landscape")
    val anyPhotos: List<Photo> = (1..3).map {
      Photo(it.toString(),
          "http:url/$it",
          "AuthorName$it",
          "Description$it",
          "2018-06-16T08:52:46-0500")
    }
  }
}

fun <T : Activity> IntentsTestRule<T>.launch(): T {
  return launchActivity(null)
}
