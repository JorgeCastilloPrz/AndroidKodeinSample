package me.jorgecastillo.viper.common.domain.model

import java.util.*

data class Photo(val id: String,
                 val url: String,
                 val author: String,
                 val description: String?,
                 val created_at: String)
