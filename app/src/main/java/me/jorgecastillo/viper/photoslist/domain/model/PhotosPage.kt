package me.jorgecastillo.viper.photoslist.domain.model

import me.jorgecastillo.viper.common.data.network.model.PhotoDto

data class PhotosPage(val total: Long, val perPage: Int, val photos: List<PhotoDto>)
