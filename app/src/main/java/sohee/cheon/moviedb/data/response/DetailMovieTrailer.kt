package sohee.cheon.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class DetailMovieTrailer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<TrailerInfo>
)

data class TrailerInfo(
    @SerializedName("iso_639_1")
    val isoLower: String,
    @SerializedName("iso_3166_1")
    val isoUpper: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("published_at")
    val publishAt: String,
    @SerializedName("id")
    val id: String,
)
