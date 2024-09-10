package com.example.sprint3.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint3.RickAdapter
import com.example.sprint3.RickMortyModel
import com.example.sprint3.databinding.FragmentCharacterBinding


class CharacterFragment : Fragment(), RickAdapter.OnItemClickListener {

    private val characterViewModel: CharacterViewModel by viewModels()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private lateinit var rickAdapter: RickAdapter
    private val rickList = mutableListOf<RickMortyModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        characterViewModel.rickList.observe(viewLifecycleOwner, Observer { list ->
            rickList.clear()
            rickList.addAll(list)
            rickAdapter.notifyDataSetChanged()
        })

        characterViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })

        characterViewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvRickMorty.layoutManager = LinearLayoutManager(context)
        rickAdapter = RickAdapter(rickList, this)
        binding.rvRickMorty.adapter = rickAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: Int) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailsFragment(item)
        findNavController().navigate(action)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingBar.visibility = View.VISIBLE
        } else {
            binding.loadingBar.visibility = View.GONE
        }
    }

}