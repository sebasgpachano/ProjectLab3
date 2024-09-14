package com.example.sprint3.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.databinding.FragmentDetailsBinding
import com.example.sprint3.ui.base.BaseFragment
import com.example.sprint3.ui.extensions.toastLong
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun inflateBinding() {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        detailsViewModel.getCharacter(args.id)
    }

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Detalles", showBack = true)
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.loadingFlow.collect {
                showLoading(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.detailsStateFlow.collect { character ->
                updateUI(character)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.errorFlow.collect { error ->
                requireContext().toastLong(error.message)
            }
        }

    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit


    private fun updateUI(character: CharacterDetailModel?) {
        character?.let {
            Picasso.get().load(it.image).into(binding?.ivCharacter)
            binding?.tvName?.text = "Nombre: ${it.name}"
            binding?.tvStatus?.text = "Status: ${it.status}"
            binding?.tvSpecies?.text = "Especie: ${it.species}"
            binding?.tvGender?.text = "Género: ${it.gender}"
        }
    }

}