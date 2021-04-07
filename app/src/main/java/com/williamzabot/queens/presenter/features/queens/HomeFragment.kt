package com.williamzabot.queens.presenter.features.queens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.williamzabot.queens.R
import com.williamzabot.queens.databinding.FragmentHomeBinding
import com.williamzabot.queens.presenter.extensions.visible
import com.williamzabot.queens.presenter.features.queens.HomeFragmentDirections.Companion.homeToDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val navController by lazy { findNavController() }
    private val queenAdapter by lazy {
        QueenAdapter { queen ->
            navController.navigate(homeToDetail(queen))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectEvents()
    }

    private fun initView() {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visible(true)
        binding.apply {
            queenAdapter.run {
                recyclerviewQueens.adapter = withLoadStateFooter(NewsLoadStateAdapter())
                addLoadStateListener { state ->
                    progressbarHome.isVisible = state.refresh is LoadState.Loading
                }
            }
        }
    }

    private fun collectEvents() {
        lifecycleScope.launch {
            viewModel.queens.collectLatest { queens ->
                queenAdapter.submitData(queens)
            }
        }
    }

}