package spacexdemo.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    val name: String,
    val success: Boolean?,
    val details: String?,
    val rocket: String,
    val date_unix: Long,
    val date_utc: String,
    val links: Links? = null,
    val upcoming: Boolean
) {
    @Serializable
    data class Links(
        val patch: Patch?,
        val presskit: String?,
        val webcast: String?,
        val youtube_id: String?,
        val article: String?,
        val wikipedia: String?
    ) {
        @Serializable
        data class Patch(
            val small: String?,
            val large: String?
        )
    }
}

@Serializable
data class Rocket(
    val id: String,
    val name: String,
    val type: String
)

data class LaunchInfo(
    val launch: Launch,
    val rocket: Rocket
)