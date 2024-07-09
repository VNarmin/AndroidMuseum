package com.example.joy.user_interface

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.joy.adapters.RegionAdapter
import com.example.joy.base.BaseFragment
import com.example.joy.databinding.FragmentRegionBinding
import com.example.joy.utilities.gone
import com.example.joy.utilities.visible
import com.example.joy.view_models.RegionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegionFragment : BaseFragment < FragmentRegionBinding > (FragmentRegionBinding::inflate) {
    private val regionViewModel by viewModels < RegionViewModel > ()
    private val regionAdapter = RegionAdapter()
    private val args by navArgs < RegionFragmentArgs > ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerViewRegions.adapter = regionAdapter
        regionViewModel.getRegions(args.city)
        regionAdapter.onClick = { region ->
            findNavController().navigate(RegionFragmentDirections.
            actionRegionFragmentToMuseumFragment(args.city, region))
        }
    }

    private fun observeData() {
        regionViewModel.regions.observe(viewLifecycleOwner) { regions ->
            regionAdapter.updateRegions(regions)
        }
        regionViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBarRegion.visible() else binding.progressBarRegion.gone()
        }
        regionViewModel.success.observe(viewLifecycleOwner) { success ->
            if (!success) {
                binding.recyclerViewRegions.gone()
                binding.imageViewRegion.visible()
            }
        }
    }
}