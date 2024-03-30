/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentDetailBinding
import jp.co.yumemi.android.code_check.model.Repository
import jp.co.yumemi.android.code_check.ui.DetailFragmentArgs

class DetailFragment : Fragment(R.layout.fragment_detail)
{
    private val args: DetailFragmentArgs by navArgs()

    private var binding: FragmentDetailBinding? = null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        val repository: Repository = args.repository

        _binding.ownerIconView.load(repository.ownerIconUrl)
        _binding.nameView.text = repository.name
        _binding.languageView.text = repository.language
        _binding.starsView.text = getString(R.string.stargazersCount, repository.stargazersCount.toString())
        _binding.watchersView.text = getString(R.string.watchersCount, repository.watchersCount.toString())
        _binding.forksView.text = getString(R.string.forksCount, repository.forksCount.toString())
        _binding.openIssuesView.text = getString(R.string.openIssuesCount, repository.openIssuesCount.toString())
    }
}
