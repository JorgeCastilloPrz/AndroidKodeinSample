package me.jorgecastillo.viper.common.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun Bitmap.bytes(): ByteArray? {
  val stream = ByteArrayOutputStream()
  this.compress(Bitmap.CompressFormat.PNG, 100, stream)
  return stream.toByteArray()
}

fun ImageView.getBitmapUri(): Uri? {
  if (this.drawable !is BitmapDrawable) {
    return null
  }
  val bmp = (this.drawable as BitmapDrawable).bitmap
  val bmpUri: Uri? = null
  try {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png")
    val out = FileOutputStream(file)
    bmp.compress(Bitmap.CompressFormat.JPEG, 90, out)
    out.close()
    return FileProvider.getUriForFile(context, context.applicationContext.packageName + ".me.jorgecastillo.snapgram.provider", file)
  } catch (e: IOException) {
    e.printStackTrace()
    return bmpUri
  }
}
