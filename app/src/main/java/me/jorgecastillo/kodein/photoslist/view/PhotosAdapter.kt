package me.jorgecastillo.kodein.photoslist.view

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_photo.view.*
import me.jorgecastillo.kodein.R
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.view.load
import java.text.SimpleDateFormat
import java.util.*

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

  private val photos: MutableList<Photo> = arrayListOf()
  var onItemClick: ((String) -> Unit)? = null

  fun addPics(photos: List<Photo>): Unit {
    this.photos.clear()
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
    fun bind(photo: Photo) {
      with(photo) {
        itemView.cell.setOnClickListener { onItemClick?.invoke(photo.id) }
        itemView.picture.load(photo.url)
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
