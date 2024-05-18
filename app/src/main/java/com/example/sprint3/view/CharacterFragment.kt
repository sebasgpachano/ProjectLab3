package com.example.sprint3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint3.RetrofitHelper
import com.example.sprint3.RickAdapter
import com.example.sprint3.RickApiClient
import com.example.sprint3.RickMortyModel
import com.example.sprint3.databinding.FragmentCharacterBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class CharacterFragment : Fragment() {

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

        binding.rvRickMorty.layoutManager = LinearLayoutManager(context)
        rickAdapter = RickAdapter(rickList)
        binding.rvRickMorty.adapter = rickAdapter

        fetchData()
    }

    private fun fetchData() {
        val retrofit = RetrofitHelper.getRetrofit()
        val rickApiClient = retrofit.create(RickApiClient::class.java)
        val call = rickApiClient.getCharacters()

        call.enqueue(object : Callback<List<RickMortyModel>> {
            override fun onResponse(call: Call<List<RickMortyModel>>, response: Response<List<RickMortyModel>>) {
                if (response.isSuccessful && response.body() != null) {
                    rickList.clear()
                    rickList.addAll(response.body()!!)
                    rickAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<RickMortyModel>>, t: Throwable) {
                //Error
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}