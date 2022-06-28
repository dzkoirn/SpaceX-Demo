package app.spacexdemo.ui.main.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R

class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val missionName = view.findViewById<TextView>(R.id.mission_name)
    private val patch = view.findViewById<ImageView>(R.id.path)
    private val success = view.findViewById<ImageView>(R.id.success_mark)
    private val date = view.findViewById<TextView>(R.id.datetime)
    private val rocket = view.findViewById<TextView>(R.id.rocket)
    private val days = view.findViewById<TextView>(R.id.days)

    fun bind(launchViewItem: LaunchViewItem) {
        missionName.text = launchViewItem.missionName
        launchViewItem.success?.let { success.setImageResource(it) }
        date.text = launchViewItem.date
        rocket.text = launchViewItem.rocket
        days.text = launchViewItem.days
    }
}