package com.jacksonoliveira.marvelapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.databinding.HomeFragmentBinding
import com.jacksonoliveira.marvelapp.presentation.adapter.ComicsAdapter
import com.jacksonoliveira.marvelapp.presentation.adapter.ComicsViewHolder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by viewModel()
    private var comicsAdapter: PagingDataAdapter<Comic, ComicsViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() = with(binding) {
        comicsAdapter = ComicsAdapter { comicData -> navigateToComicDetailsScreen(comicData) }
        with(binding.rvComicsList) {
            context?.let {
                layoutManager =
                    StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = comicsAdapter
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { pagingList ->
            showComicsList(pagingList)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            comicsAdapter?.loadStateFlow?.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> showLoading()
                    is LoadState.Error -> showError()
                    else -> hideLoading()
                }
            }
        }
    }

    private fun navigateToComicDetailsScreen(comicData: Comic) {
        val action = HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(comicData)
        findNavController().navigate(action)
    }

    private fun showComicsList(comicsList: PagingData<Comic>) {
        comicsAdapter?.submitData(viewLifecycleOwner.lifecycle, comicsList)
        binding.rvComicsList.isVisible = true
    }

    private fun showError() = with(binding) {
        errorViewContainer.root.isVisible = true
        rvComicsList.isVisible = false
    }

    private fun hideLoading() = with(binding) {
        shimmerLayout.isVisible = false
        shimmerLayout.stopShimmer()
    }

    private fun showLoading() = with(binding) {
        shimmerLayout.isVisible = true
        shimmerLayout.startShimmer()
        rvComicsList.isVisible = false
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}