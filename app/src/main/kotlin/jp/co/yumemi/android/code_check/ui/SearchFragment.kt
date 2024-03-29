/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchBinding
import jp.co.yumemi.android.code_check.model.Repository

class SearchFragment: Fragment(R.layout.fragment_search)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val _binding= FragmentSearchBinding.bind(view)

        val _viewModel= SearchViewModel(requireContext())

        val _layoutManager= LinearLayoutManager(requireContext())
        val _dividerItemDecoration=
            DividerItemDecoration(requireContext(), _layoutManager.orientation)
        val _adapter= CustomAdapter(object : CustomAdapter.OnItemClickListener {
            override fun itemClick(repository: Repository){
                gotoRepositoryFragment(repository)
            }
        })

        _binding.searchInputText
            .setOnEditorActionListener{ editText, action, _ ->
                if (action== EditorInfo.IME_ACTION_SEARCH){
                    editText.text.toString().let {
                        _viewModel.searchResults(it).apply{
                            _adapter.submitList(this)
                        }
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        _binding.recyclerView.also{
            it.layoutManager= _layoutManager
            it.addItemDecoration(_dividerItemDecoration)
            it.adapter= _adapter
        }
    }

    fun gotoRepositoryFragment(repository: Repository) {
        val action: NavDirections = SearchFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }
}
