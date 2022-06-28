package app.spacexdemo.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import app.spacexdemo.R
import spacexdemo.domain.dto.Launch

class LaunchListAdapter : ListAdapter<Launch, LaunchViewHolder>(LaunchDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

}

private object LaunchDiffCallBack : DiffUtil.ItemCallback<Launch>() {

    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean = oldItem == newItem

}