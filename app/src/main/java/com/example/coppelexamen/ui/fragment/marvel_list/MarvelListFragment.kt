package com.example.coppelexamen.ui.fragment.marvel_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coppelexamen.R
import com.example.coppelexamen.data.model.Character
import com.example.coppelexamen.ui.characterList.viewmodel.CharacterViewModel

/**
 * A fragment representing a list of Items.
 */
class MarvelListFragment : Fragment() {

    private lateinit var marvelAdapter:MarvelRecyclerViewAdapter
    private var herosMarvel: List<Character> = ArrayList()
    private var columnCount = 1
    private val viewModelMarvel: CharacterViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
            marvelAdapter = MarvelRecyclerViewAdapter()
            //viewModel = ViewModelProvider().get(CharacterViewModel::class.java)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = marvelAdapter
            }
        }
        viewModelMarvel.response.observe(viewLifecycleOwner, Observer {
            if(it != null && it.charecterList.isNotEmpty()) {
                herosMarvel = it.charecterList
                marvelAdapter.setData(herosMarvel)
            }
        })

        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MarvelListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}