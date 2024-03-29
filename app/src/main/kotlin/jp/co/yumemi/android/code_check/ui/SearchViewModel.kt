/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui

import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.api.GithubAPI
import jp.co.yumemi.android.code_check.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * TwoFragment で使う
 */
class SearchViewModel() : ViewModel() {
    suspend fun searchResults(inputText: String) : List<Repository> {
        return withContext(Dispatchers.IO) {
            GithubAPI.getData(inputText)
        }
    }
}
