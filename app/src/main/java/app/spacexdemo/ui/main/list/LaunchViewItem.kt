package app.spacexdemo.ui.main.list

import androidx.annotation.DrawableRes

data class LaunchViewItem(
    val missionName: String,
    val patch: Patch,
    @DrawableRes val successMark: Int?,
    val date: String,
    val rocket: String,
    val days: String
)

sealed interface Patch {
    @JvmInline
    value class ResId(@DrawableRes val resId: Int): Patch
    @JvmInline
    value class Url(val url: String): Patch
}
