package app.spacexdemo.ui.main.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import com.bumptech.glide.Glide

class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val missionName = view.findViewById<TextView>(R.id.mission_name)
    private val patch = view.findViewById<ImageView>(R.id.path)
    private val success = view.findViewById<ImageView>(R.id.success_mark)
    private val date = view.findViewById<TextView>(R.id.datetime)
    private val rocket = view.findViewById<TextView>(R.id.rocket)
    private val days = view.findViewById<TextView>(R.id.days)

    fun bind(launchViewItem: LaunchViewItem) {
        missionName.text = launchViewItem.missionName
        launchViewItem.successMark?.let { success.setImageResource(it) }
        date.text = launchViewItem.date
        rocket.text = launchViewItem.rocket
        days.text = launchViewItem.days
        when(launchViewItem.patch) {
            is Patch.ResId -> patch.setImageResource(launchViewItem.patch.resId)
            is Patch.Url -> Glide.with(patch)
                .load(launchViewItem.patch.url)
                .placeholder(R.drawable.ic_download_placeholder)
                .into(patch)
        }

    }
}