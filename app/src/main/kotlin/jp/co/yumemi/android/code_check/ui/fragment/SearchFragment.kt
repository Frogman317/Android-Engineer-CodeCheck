/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchBinding
import jp.co.yumemi.android.code_check.model.Repository
import jp.co.yumemi.android.code_check.ui.adapter.RepositoryAdapter
import jp.co.yumemi.android.code_check.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

class SearchFragment: Fragment(R.layout.fragment_search)
{
    private lateinit var viewModel : SearchViewModel
    private lateinit var adapter: RepositoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val binding= FragmentSearchBinding.bind(view)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val layoutManager= LinearLayoutManager(requireContext())
        val dividerItemDecoration=
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        adapter = RepositoryAdapter(object : RepositoryAdapter.OnItemClickListener {
            override fun itemClick(repository: Repository){
                gotoRepositoryFragment(repository)
            }
        })

        binding.searchInputText
            .setOnEditorActionListener{ editText, action, _ ->
                if (action== EditorInfo.IME_ACTION_SEARCH){
                    val inputText = editText.text.toString()
                    lifecycleScope.launch {
                        viewModel.searchResults(inputText)
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        binding.recyclerView.also{
            it.layoutManager= layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter= adapter
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { repositories ->
            adapter.submitList(repositories)
        }
    }

    fun gotoRepositoryFragment(repository: Repository) {
        val action: NavDirections =
            SearchFragmentDirections.actionRepositoriesFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }
}
