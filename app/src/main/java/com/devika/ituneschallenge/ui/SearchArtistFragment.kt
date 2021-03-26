package com.devika.ituneschallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.devika.ituneschallenge.ItunesApplication
import com.devika.ituneschallenge.R
import com.devika.ituneschallenge.data.domain.UiState
import com.devika.ituneschallenge.data.domain.getList
import com.devika.ituneschallenge.databinding.FragmentSerachArtistBinding
import com.devika.ituneschallenge.utils.ItunesViewModelFactory
import javax.inject.Inject


class SearchArtistFragment : Fragment() {
    lateinit var binding: FragmentSerachArtistBinding

    @Inject
    lateinit var viewModelFactory: ItunesViewModelFactory

    @Inject
    lateinit var searchArtistAdapter: SearchArtistAdapter

    lateinit var viewModel: SearchArtistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as ItunesApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_serach_artist, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setupViewModel()
        setRecyclerView()
        setObserver()
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar?.elevation = 0f
            title = getString(R.string.title_iTunes)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchArtistViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun setObserver() {
        with(viewModel) {
            uiState.observe(viewLifecycleOwner, Observer {
                it.let {
                    if (it is UiState.Success) {
                        searchArtistAdapter.submitList(it.value.getList())
                    }
                }
            })
        }
    }

    private fun setRecyclerView() {
        binding.recycler.apply {
            val manager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = searchArtistAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchItem.expandActionView()
            searchView.queryHint = "Search here"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.getArtistData(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                   return false
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


}