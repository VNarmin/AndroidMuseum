package com.example.joy.user_interface

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.joy.adapters.MuseumAdapter
import com.example.joy.base.BaseFragment
import com.example.joy.databinding.FragmentMuseumBinding
import com.example.joy.utilities.gone
import com.example.joy.utilities.visible
import com.example.joy.view_models.MuseumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuseumFragment : BaseFragment < FragmentMuseumBinding > (FragmentMuseumBinding::inflate) {
    private val museumViewModel by viewModels < MuseumViewModel > ()
    private val museumAdapter = MuseumAdapter()
    private val args by navArgs < MuseumFragmentArgs > ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerViewMuseums.adapter = museumAdapter
        museumViewModel.getMuseums(args.city, args.region)
    }

    private fun observeData() {
        museumViewModel.museums.observe(viewLifecycleOwner) { museums ->
            museumAdapter.updateMuseums(museums)
        }
        museumViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBarMuseum.visible() else binding.progressBarMuseum.gone()
        }
        museumViewModel.success.observe(viewLifecycleOwner) { success ->
            if (!success) {
                binding.recyclerViewMuseums.gone()
                binding.imageViewMuseum.visible()
            }
        }
    }
}