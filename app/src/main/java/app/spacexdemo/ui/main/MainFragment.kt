package app.spacexdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.spacexdemo.R
import app.spacexdemo.di.DI
import app.spacexdemo.ui.main.list.LaunchListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var list: RecyclerView
    private lateinit var listAdapter: LaunchListAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = LaunchListAdapter()
        list = view.findViewById<RecyclerView?>(R.id.list).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = listAdapter
        }


        viewModel = ViewModelProvider(this, MainViewViewModelFactory(DI.getKoin())).get(MainViewModel::class.java)

        viewModel.companyInfo.observe(viewLifecycleOwner) { listAdapter.submitList(it) }
        viewModel.loadList()
    }

}