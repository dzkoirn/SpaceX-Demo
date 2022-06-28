package app.spacexdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import app.spacexdemo.di.DI
import app.spacexdemo.ui.main.info.CompanyInfoAdapter
import app.spacexdemo.ui.main.list.LaunchListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var list: RecyclerView
    protected lateinit var progress: ProgressBar

    private lateinit var listAdapter: LaunchListAdapter
    private lateinit var companyInfoAdapter: CompanyInfoAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companyInfoAdapter = CompanyInfoAdapter()
        listAdapter = LaunchListAdapter()

        list = view.findViewById<RecyclerView?>(R.id.list).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ConcatAdapter(companyInfoAdapter, listAdapter)
        }

        progress = view.findViewById(R.id.progress)

        viewModel = ViewModelProvider(this, MainViewViewModelFactory(DI.getKoin())).get(MainViewModel::class.java)

        viewModel.screenState.observe(viewLifecycleOwner) { handleScreenState(it) }
        viewModel.loadData()
    }

    private fun handleScreenState(state: MainScreenState) {
        when(state) {
            is MainScreenState.Loading -> {
                progress.isVisible = true
            }
            is MainScreenState.ScreenData -> {
                companyInfoAdapter.setCompanyInfo(state.companyInfo)
                listAdapter.submitList(state.list)
                progress.isVisible = false
            }
        }
    }
}