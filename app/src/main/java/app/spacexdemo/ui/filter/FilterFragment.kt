package app.spacexdemo.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.spacexdemo.R
import app.spacexdemo.SpacexDemoViewModelFactory
import app.spacexdemo.di.DI
import com.google.android.material.slider.RangeSlider
import spacexdemo.domain.dto.LaunchSuccess
import spacexdemo.domain.dto.SortOrder

class FilterFragment : DialogFragment() {

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var slider: RangeSlider
    private lateinit var viewModel: FilterViewModel
    private lateinit var launchSuccess: Spinner
    private lateinit var sortingOrder: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_filter_setting, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, SpacexDemoViewModelFactory(DI.getKoin())).get(
            FilterViewModel::class.java)

        launchSuccess = view.findViewById<Spinner>(R.id.launch_success).apply {
            // It's a bad practice to use on UI layer classes from domain layer, but here we do it for speed up and simplification
            adapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, LaunchSuccess.values())
        }
        sortingOrder = view.findViewById<Spinner>(R.id.sorting_order).apply {
            // It's a bad practice to use on UI layer classes from domain layer, but here we do it for speed up and simplification
            adapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, SortOrder.values())
        }

        cancelButton = view.findViewById<Button>(R.id.cancel_button).apply {
            setOnClickListener {
                parentFragmentManager.setFragmentResult("filter", Bundle())
                dismiss()
            }
        }
        saveButton = view.findViewById<Button>(R.id.save_button).apply {
            setOnClickListener { saveSettings() }
        }

        slider = view.findViewById(R.id.slider)

        viewModel.state.observe(this) { data -> initFilters(data) }
        viewModel.loadData()
    }

    private fun initFilters(data: FilterState) {
        slider.values = listOf(data.startYear, data.endYear)
        launchSuccess.setSelection(LaunchSuccess.values().indexOf(data.launchSuccess))
        sortingOrder.setSelection(SortOrder.values().indexOf(data.sortOrder))
    }

    private fun saveSettings() {
        parentFragmentManager.setFragmentResult("filter", Bundle())
        viewModel.save(
            FilterState(
                startYear = slider.values.component1(),
                endYear = slider.values.component2(),
                launchSuccess = launchSuccess.selectedItem as LaunchSuccess,
                sortOrder = sortingOrder.selectedItem as SortOrder
            )
        )
        dismiss()
    }
}