package com.jacksonoliveira.marvelapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.jacksonoliveira.marvelapp.R
import com.jacksonoliveira.marvelapp.databinding.ComicDetailsFragmentBinding

class ComicDetailsFragment : Fragment() {
    private val args: ComicDetailsFragmentArgs by navArgs()
    private lateinit var binding: ComicDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ComicDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.appBarDetails.setNavigationOnClickListener { navigateToHome() }
    }

    private fun navigateToHome() {
        val action = ComicDetailsFragmentDirections.actionComicDetailsFragmentToHomeFragment()
        findNavController().navigate(action)

    }

    private fun setupViews() = with(binding) {
        appBarDetails.setNavigationIcon(R.drawable.baseline_arrow_back_24px)
        val imagePath = args.comicData.thumbnail?.imagePath
        tvTitle.text = args.comicData.title
        tvDescription.text = args.comicData.description

        if (imagePath != null) {
            imageBackground.loadImageWithBlur(imagePath)
        }

        context?.let { context ->
            Glide.with(context)
                .asBitmap()
                .load(args.comicData.thumbnail?.imagePath)
                .transition(withCrossFade())
                .into(imageViewComicDetails)
        }

    }
}