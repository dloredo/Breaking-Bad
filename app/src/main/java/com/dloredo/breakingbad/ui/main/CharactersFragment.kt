package com.dloredo.breakingbad.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.dloredo.breakingbad.BreakingBadApplication
import com.dloredo.breakingbad.Character
import com.dloredo.breakingbad.CharactersAdapter
import com.dloredo.breakingbad.databinding.FragmentCharactersBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CharactersFragment : Fragment() {

    private lateinit var mBinding: FragmentCharactersBinding
    private lateinit var mAdapter: CharactersAdapter
    private var characterList = mutableListOf<Character>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCharactersBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getCharacters()
    }

    private fun getCharacters() {
        val url = "https://breakingbadapi.com/api/characters"

        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET,url,null,{ response ->

            Log.i("objeto", response.toString())
            val mutableListType = object: TypeToken<MutableList<Character>>(){}.type
            characterList = Gson().fromJson(response.toString(), mutableListType)

            mAdapter.setItems(characterList)
        },{
            it.printStackTrace()
            Log.e("errorVolley",it.toString())
            Toast.makeText(requireContext(), "Hubo un error", Toast.LENGTH_LONG).show()
        })

        BreakingBadApplication.breakingBadAPI.addToRequestQueue(jsonObjectRequest)
    }

    private fun setupRecyclerView() {

        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        mAdapter = CharactersAdapter(mutableListOf())

       mBinding.recyclerViewChar.apply {
            setHasFixedSize(true)
            layoutManager = lm
            adapter = mAdapter
        }
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}