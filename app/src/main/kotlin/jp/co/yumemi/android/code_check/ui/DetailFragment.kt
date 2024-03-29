/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentDetailBinding
import jp.co.yumemi.android.code_check.model.Repository

class DetailFragment : Fragment(R.layout.fragment_detail)
{
    private val args: DetailFragmentArgs by navArgs()

    private var binding: FragmentDetailBinding? = null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        binding = FragmentDetailBinding.bind(view)

        val repository: Repository = args.repository

        _binding.ownerIconView.load(repository.ownerIconUrl);
        _binding.nameView.text = repository.name;
        _binding.languageView.text = repository.language;
        _binding.starsView.text = "${repository.stargazersCount} stars";
        _binding.watchersView.text = "${repository.watchersCount} watchers";
        _binding.forksView.text = "${repository.forksCount} forks";
        _binding.openIssuesView.text = "${repository.openIssuesCount} open issues";
    }
}
