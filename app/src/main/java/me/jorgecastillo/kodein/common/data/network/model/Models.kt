package me.jorgecastillo.kodein.common.data.network.model

data class GetPhotosResponse(
        val total: Int,
        val total_pages: Int,
        val results: List<PhotoDto>
)

data class PhotoDto(
    val id: String,
    val created_at: String,
    val width: Long,
    val height: Long,
    val color: String,
    val likes: Long,
    val liked_by_user: Boolean,
    val description: String?,
    val user: UserDto,
    val current_user_collections: List<String>,
    val urls: UrlsDto,
    val links: LinksDto
)

data class UserDto(
    val id: String,
    val username: String,
    val name: String,
    val first_name: String,
    val last_name: String,
    val instagram_username: String?,
    val twitter_username: String?,
    val portfolio_url: String?,
    val profile_image: ProfileImageDto,
    val links: ProfileLinksDto)

val PhotoDto.author: String
  get() =
    if (this.user.instagram_username != null)
      "@${this.user.instagram_username}"
    else
      this.user.username


data class UrlsDto(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String)

data class ProfileImageDto(
        val small: String,
        val medium: String,
        val large: String)

data class ProfileLinksDto(
        val self: String,
        val html: String,
        val photos: String,
        val likes: String)

data class LinksDto(
        val self: String,
        val html: String,
        val download: String)
