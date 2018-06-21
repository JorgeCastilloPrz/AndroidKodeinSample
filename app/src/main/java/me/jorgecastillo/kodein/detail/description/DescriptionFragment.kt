package me.jorgecastillo.kodein.detail.description

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_description.description
import me.jorgecastillo.kodein.R

class DescriptionFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_description, container, false)

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    description.text = arguments?.getString(EXTRA_TEXT)
  }

  companion object {
    private const val EXTRA_TEXT = "EXTRA_DESCRIPTION_TEXT"

    fun newInstance(description: String): DescriptionFragment =
      DescriptionFragment().apply { arguments = Bundle().apply { putString(EXTRA_TEXT, description) } }
  }
}
