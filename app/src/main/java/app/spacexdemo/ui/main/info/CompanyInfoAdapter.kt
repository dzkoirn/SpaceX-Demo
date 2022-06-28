package app.spacexdemo.ui.main.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import spacexdemo.domain.dto.CompanyInfo

class CompanyInfoAdapter : RecyclerView.Adapter<CompanyInfoViewHolder>() {

    private val list = mutableListOf<CompanyInfo>()

    fun setCompanyInfo(info: CompanyInfo) {
        // store only one element
        if (list.isEmpty()) {
            list.add(info)
            notifyItemInserted(0)
        } else {
            list.clear()
            list.add(info)
            notifyItemChanged(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_info_item, parent, false)
        return CompanyInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyInfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}