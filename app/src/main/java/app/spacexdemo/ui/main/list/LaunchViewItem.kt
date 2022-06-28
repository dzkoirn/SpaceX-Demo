package app.spacexdemo.ui.main.list

import androidx.annotation.DrawableRes

data class LaunchViewItem(
    val missionName: String,
    val patch: String,
    @DrawableRes val success: Int?,
    val date: String,
    val rocket: String,
    val days: String
)
