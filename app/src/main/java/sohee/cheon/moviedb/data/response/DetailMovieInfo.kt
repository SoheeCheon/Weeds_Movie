package sohee.cheon.moviedb.data.response

import com.google.gson.annotations.SerializedName

data class DetailMovieInfo(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection : BelongToCollection,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genre_ids")
    val genreIds : List<Genre>,
    @SerializedName("id")
    val id : Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("overview")
    val overView : String,
    @SerializedName("popularity")
    val popularity : Float,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title : String,
    @SerializedName("video")
    val video : Boolean,
    @SerializedName("vote_average")
    val voteAverage : Float,
    @SerializedName("vote_count")
    val voteCount : Int
)

data class BelongToCollection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class ProductionCompany(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)