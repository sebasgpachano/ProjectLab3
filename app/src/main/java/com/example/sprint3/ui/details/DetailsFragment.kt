package com.example.sprint3.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sprint3.R
import com.example.sprint3.RickMortyModel
import com.example.sprint3.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(), View.OnClickListener {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailsFragmentArgs by navArgs()
        val id = args.id

        binding.btBack.setOnClickListener(this)

        detailsViewModel.fetchItemDetails(id)
        observeViewModel()
    }

    private fun observeViewModel() {
        detailsViewModel.characterDetails.observe(viewLifecycleOwner, Observer { character ->
            updateUI(character)
        })

        detailsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })

        detailsViewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun updateUI(character: RickMortyModel?) {
        character?.let {
            Picasso.get().load(it.image).into(binding.ivCharacter)
            binding.tvName.text = "Nombre: ${it.name}"
            binding.tvStatus.text = "Status: ${it.status}"
            binding.tvSpecies.text = "Especie: ${it.species}"
            binding.tvGender.text = "Género: ${it.gender}"
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btBack -> findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToCharacterFragment())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingBar.visibility = View.VISIBLE
        } else {
            binding.loadingBar.visibility = View.GONE
        }
    }
}