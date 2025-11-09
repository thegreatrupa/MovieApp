//data class MovieResponse(
//    val page: Int,
//    val results: List<Movie>,
//    val total_pages: Int,
//    val total_results: Int
//)
//
//data class Movie(
//    val adult: Boolean,
//    val backdrop_path: String?,
//    val genre_ids: List<Int>,
//    val id: Int,
//    val original_language: String,
//    val original_title: String,
//    val overview: String,
//    val popularity: Double,
//    val poster_path: String?,
//    val release_date: String,
//    val title: String,
//    val video: Boolean,
//    val vote_average: Double,
//    val vote_count: Int
//)

//data class MovieResponse (
//    val dates: Dates,
//    val page: Long,
//    val results: List<Movie>,
//    val totalPages: Long,
//    val totalResults: Long
//)
//data class Dates(
//    val maximum: String,
//    val minimum: String
//)
//
//data class Movie (
//    val adult: Boolean,
//    val backdropPath: String,
//    val genreIDS: List<Long>,
//    val id: Long,
//    val originalLanguage: String,
//    val originalTitle: String,
//    val overview: String,
//    val popularity: Double,
//    val posterPath: String,
//    val releaseDate: String,
//    val title: String,
//    val video: Boolean,
//    val voteAverage: Double,
//    val voteCount: Long
//)

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val dates: Dates?,
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

data class Movie(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)



