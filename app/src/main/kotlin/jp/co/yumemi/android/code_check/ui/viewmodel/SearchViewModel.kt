/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.api.GithubAPI
import jp.co.yumemi.android.code_check.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * TwoFragment で使う
 */
class SearchViewModel : ViewModel() {
    private val _searchResults = MutableLiveData<List<Repository>>()
    val searchResults: LiveData<List<Repository>> = _searchResults

    suspend fun searchResults(inputText: String) {
        if (inputText != "") {
            val results = withContext(Dispatchers.IO) {
                GithubAPI.getData(inputText)
            }
            _searchResults.postValue(results)
        }
    }
}