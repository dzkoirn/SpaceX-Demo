package app.spacexdemo.ui.main.info

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import spacexdemo.domain.dto.CompanyInfo

class CompanyInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val info = view.findViewById<TextView>(R.id.info)

    fun bind(companyInfo: CompanyInfo) {
        info.text = with(companyInfo) {
            info.context.getString(R.string.company_info, name, founder, founded, employees, launchSites, valuation)
        }
    }
}