package app.spacexdemo.ui.main.list

import android.content.Context
import app.spacexdemo.R
import spacexdemo.domain.dto.LaunchInfo
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LaunchInfoMapper {

    fun map(context: Context, info: LaunchInfo): LaunchViewItem {
        val instantDate = Instant.ofEpochSecond(info.launch.date_unix)
        val zonedDateTime = instantDate.atZone(ZoneId.systemDefault())

        val duration = Duration.between(Instant.now(), instantDate)

        return with(context) {
            LaunchViewItem(
                missionName = getString(R.string.mission, info.launch.name),
                date = getString(R.string.date, zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE), zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME)),
                patch = "",
                success = info.launch.success?.let { if (it) { R.drawable.ic_successfull } else { R.drawable.ic_failed } },
                rocket = getString(R.string.rocket, info.rocket.name, info.rocket.type),
                days = if (info.launch.upcoming) {
                    getString(R.string.days_from, duration.toDays())
                } else {
                    getString(R.string.days_since, duration.toDays())
                }
            )
        }
    }
}