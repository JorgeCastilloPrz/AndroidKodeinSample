package me.jorgecastillo.kodein.detail.description.di

import me.jorgecastillo.kodein.detail.description.DescriptionPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * Specific android scoped module for the detail activity.
 */
fun descriptionFragmentModule(description: String) = Kodein.Module("descriptionFragmentModule") {
  bind<DescriptionPresenter>() with provider { DescriptionPresenter(description) }
}
