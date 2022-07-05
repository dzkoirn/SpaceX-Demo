package app.spacexdemo.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import app.spacexdemo.SpacexDemoViewModelFactory
import app.spacexdemo.di.DI
import app.spacexdemo.ui.filter.FilterFragment
import app.spacexdemo.ui.filter.FilterFragment.Constants.isResultSave
import app.spacexdemo.ui.info.LaunchInfoDetailFragment
import app.spacexdemo.ui.main.info.CompanyInfoAdapter
import app.spacexdemo.ui.main.list.LaunchListAdapter
import spacexdemo.domain.dto.LaunchInfo

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var errorMessage: TextView
    private lateinit var retryButton: Button
    private lateinit var list: RecyclerView
    protected lateinit var progress: ProgressBar

    private lateinit var listAdapter: LaunchListAdapter
    private lateinit var companyInfoAdapter: CompanyInfoAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        parentFragmentManager.setFragmentResultListener(FilterFragment.FILTER_TAG, this) { requestKey, result ->
            handleFragmentResult(requestKey, result)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorMessage = view.findViewById(R.id.error_message)
        retryButton = view.findViewById<Button?>(R.id.retry_button).apply {
            setOnClickListener { viewModel.loadData() }
        }

        companyInfoAdapter = CompanyInfoAdapter()
        listAdapter = LaunchListAdapter(::handleListItemClick)

        list = view.findViewById<RecyclerView?>(R.id.list).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ConcatAdapter(companyInfoAdapter, listAdapter)
        }

        progress = view.findViewById(R.id.progress)

        viewModel = ViewModelProvider(this, SpacexDemoViewModelFactory(DI.getKoin())).get(MainViewModel::class.java)

        viewModel.screenState.observe(viewLifecycleOwner) { handleScreenState(it) }
        viewModel.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.filter -> handleMenuClick()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleScreenState(state: MainScreenState) {
        when(state) {
            is MainScreenState.Loading -> {
                errorMessage.isVisible = false
                retryButton.isVisible = false
                progress.isVisible = true
            }
            is MainScreenState.ScreenData -> {
                // Log.d("DEBUG", "handleScreenState MainScreenState.ScreenData, list = ${state.list}")
                companyInfoAdapter.setCompanyInfo(state.companyInfo)
                listAdapter.submitList(state.list) {
                    // Log.d("DEBUG", "submitList callback called")
                    progress.isVisible = false
                }
            }
            is MainScreenState.LoadingError -> {
                errorMessage.isVisible = true
                retryButton.isVisible = true
                progress.isVisible = false
            }
        }
    }

    private fun handleListItemClick(item: LaunchInfo) {
        val instance = LaunchInfoDetailFragment.newInstance(item)
        instance.setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalDialog)
        instance.show(parentFragmentManager, "info")
    }

    private fun handleMenuClick(): Boolean {
        val instance = FilterFragment()
        instance.setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalDialog)
        instance.show(parentFragmentManager, FilterFragment.FILTER_TAG)
        return true
    }

    private fun handleFragmentResult(requestKey: String, result: Bundle) {
        when(requestKey) {
            FilterFragment.FILTER_TAG -> if (result.isResultSave()) {
                // reload data
                // Log.d("DEBUG", "handleFragmentResult loadData")
                viewModel.loadData()
            }
        }
    }
}