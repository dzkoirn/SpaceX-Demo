package spacexdemo.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://github.com/r-spacex/SpaceX-API/blob/master/docs/company/v4/schema.md
 */
@Serializable
data class CompanyInfo(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val vehicles: Int,
    @SerialName("launch_sites") val launchSites: Int,
    @SerialName( "test_sites") val testSites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    @SerialName("cto_propulsion") val ctoPropulsion: String,
    val valuation: Long,
    val headquarters: Headquarters,
    val links: Links,
    val summary: String
) {
    @Serializable
    data class Headquarters(
        val address: String,
        val city: String,
        val state: String
    )

    @Serializable
    data class Links(
        val website: String,
        val flickr: String,
        val twitter: String,
        @SerialName("elon_twitter") val elonTwitter: String
    )
}


