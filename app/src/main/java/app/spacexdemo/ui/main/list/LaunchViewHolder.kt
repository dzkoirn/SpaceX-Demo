package app.spacexdemo.ui.main.list

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import spacexdemo.domain.dto.Launch

class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val missionName = view.findViewById<TextView>(R.id.mission_name)

    fun bind(launch: Launch) {
        missionName.text = launch.name
    }
}