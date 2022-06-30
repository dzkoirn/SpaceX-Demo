package app.spacexdemo.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import app.spacexdemo.R
import spacexdemo.domain.dto.LaunchInfo

class LaunchListAdapter(clickListener: (LaunchInfo) -> Unit) : ListAdapter<LaunchInfo, LaunchViewHolder>(LaunchDiffCallBack) {

    private val mapper = LaunchInfoMapper()

    private val onItemClick = { position: Int -> clickListener(getItem(position)) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_list_item, parent, false)
        return LaunchViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
       holder.bind(mapper.map(holder.itemView.context, getItem(position)), position)
    }

}

private object LaunchDiffCallBack : DiffUtil.ItemCallback<LaunchInfo>() {

    override fun areItemsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean = oldItem == newItem

}