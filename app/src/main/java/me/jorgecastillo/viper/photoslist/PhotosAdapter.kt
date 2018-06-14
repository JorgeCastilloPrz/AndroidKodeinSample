package me.jorgecastillo.viper.photoslist

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_photo.view.*
import me.jorgecastillo.viper.R
import me.jorgecastillo.viper.common.data.network.model.PhotoDto
import me.jorgecastillo.viper.common.data.network.model.author
import me.jorgecastillo.viper.common.extensions.load
import java.text.SimpleDateFormat
import java.util.*

class PhotosAdapter(var photos: MutableList<PhotoDto> = ArrayList()) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

  var lastQueryString: String? = null
  var onItemClick: ((String) -> Unit)? = null

  fun addPics(photos: List<PhotoDto>): Unit {
    this.photos.addAll(photos)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_photo, parent, false)
    return ViewHolder(view, onItemClick)
  }

  override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
    holder.bind(photos[pos])
  }

  override fun getItemCount() = photos.size

  class ViewHolder(view: View, private val onItemClick: ((String) -> Unit)?) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(photo: PhotoDto) {
      with(photo) {
        itemView.cell.setOnClickListener { onItemClick?.invoke(photo.id) }
        itemView.picture.load(photo.urls.regular)
        itemView.title.text = photo.author
        itemView.unsplashLink.text = itemView.resources.getString(R.string.unsplash)

        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).parse(photo.created_at)
        itemView.publishedAt.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(date)
        if (photo.description.isNullOrEmpty()) {
          itemView.hashtags.visibility = View.GONE
        } else {
          itemView.hashtags.visibility = View.VISIBLE
          itemView.hashtags.text = photo.description
        }
      }
    }
  }
}
