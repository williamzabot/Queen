package com.williamzabot.queens.presenter.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.williamzabot.queens.R
import com.williamzabot.queens.data.db.AppDatabase
import com.williamzabot.queens.data.repositories.FavoriteRepositoryImpl
import com.williamzabot.queens.databinding.FragmentDetailBinding
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import com.williamzabot.queens.presenter.extensions.urlImage
import com.williamzabot.queens.presenter.extensions.visible

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailViewModel(
                    FavoriteRepositoryImpl(
                        AppDatabase.getInstance(requireContext()).favoriteDAO
                    ) as FavoriteRepository
                ) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        initView()
    }

    private fun observeEvents() {
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            args.queen?.favorite = true
            binding.iconFavorite.setImageDrawable(getDrawable(requireContext(), R.drawable.heart))
        }
    }

    private fun initView() {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visible(false)
        args.queen?.let { queen ->
            viewModel.checkIsFavorite(queen)
            binding.apply {
                if(queen.favorite){
                    iconFavorite.setImageDrawable(getDrawable(requireContext(), R.drawable.heart))
                }
                imageQueen.urlImage(queen.image_url)
                nameQueen.text = queen.name
                quoteQueen.text = if (queen.quote.isNotEmpty() && queen.quote != "\"\"") {
                    "\"${queen.quote}\""
                } else {
                    "not a message"
                }
                if (queen.winner) {
                    winnerAnimation.visible(true)
                    textWinner.visible(true)
                }

                iconFavorite.setOnClickListener {
                    if (!queen.favorite) {
                        viewModel.favorite(queen)
                        queen.favorite = true
                        iconFavorite.setImageDrawable(
                            getDrawable(
                                requireContext(),
                                R.drawable.heart
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}