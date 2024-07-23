package com.example.joy.user_interface

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.joy.adapters.CityAdapter
import com.example.joy.base.BaseFragment
import com.example.joy.databinding.FragmentCityBinding
import com.example.joy.utilities.gone
import com.example.joy.utilities.visible
import com.example.joy.view_models.CityUiState
import com.example.joy.view_models.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : BaseFragment<FragmentCityBinding>(FragmentCityBinding::inflate) {
    private val cityViewModel by viewModels<CityViewModel>()
    private val cityAdapter = CityAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerViewCities.adapter = cityAdapter
        cityAdapter.onClick = { city ->
            findNavController().navigate(
                CityFragmentDirections.actionCityFragmentToRegionFragment(
                    city
                )
            )
        }
    }

    private fun observeData() {
        cityViewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is CityUiState.CityList -> {
                    cityAdapter.updateCities(it.cities)
                    binding.progressBarCity.gone()
                }
                is CityUiState.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                    .show()

                is CityUiState.Loading -> binding.progressBarCity.visible()
            }
        }
    }
}