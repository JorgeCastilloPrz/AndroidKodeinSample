package me.jorgecastillo.viper.photoslist

import me.jorgecastillo.viper.photoslist.domain.model.Photo

data class PhotoListViewState(val photos: List<Photo> = emptyList())
