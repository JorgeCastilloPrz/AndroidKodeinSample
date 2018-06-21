package me.jorgecastillo.kodein.detail.description.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import me.jorgecastillo.kodein.common.log.AndroidLogger
import me.jorgecastillo.kodein.common.log.Logger
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class CustomTextView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr), KodeinAware {

  private val fragmentKodein by closestKodein()
  private val logger by instance<Logger>()

  override val kodein = Kodein.lazy {
    extend(fragmentKodein)
    bind<Logger>(overrides = true) with provider { AndroidLogger() }
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    logger.log(tag = "CustomView", message = "Finished inflation!")
  }
}
