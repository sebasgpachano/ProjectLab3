package com.example.sprint3.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint3.databinding.FragmentCharacterBinding
import com.example.sprint3.ui.base.BaseFragment
import com.example.sprint3.ui.character.adapter.RickAdapter
import com.example.sprint3.ui.extensions.toastLong
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(),
    RickAdapter.OnItemClickListener {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var rickAdapter: RickAdapter

    override fun inflateBinding() {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.rvRickMorty?.layoutManager = LinearLayoutManager(context)
        rickAdapter = RickAdapter(arrayListOf(), this)
        binding?.rvRickMorty?.adapter = rickAdapter
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        characterViewModel.getCharacterList()
    }

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Personajes", showBack = true)
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            characterViewModel.loadingFlow.collect {
                showLoading(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            characterViewModel.rickStateFlow.collect { dataSet ->
                rickAdapter.refreshData(dataSet)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            characterViewModel.errorFlow.collect { error ->
                requireContext().toastLong(error.message)
            }
        }
    }

    override fun onItemClick(item: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailsFragment(item)
        findNavController().navigate(action)
    }

}