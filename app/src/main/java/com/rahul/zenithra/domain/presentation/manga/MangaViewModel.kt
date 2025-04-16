package com.rahul.zenithra.domain.presentation.manga

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.zenithra.domain.model.Manga
import com.rahul.zenithra.domain.usecase.GetMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val getMangaUseCase: GetMangaUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var mangaList by mutableStateOf<List<Manga>>(emptyList())
        private set

    private var page = 1
    private var isLoading = false

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            val connected = isConnected(context)
            val newItems = getMangaUseCase(page, connected)
            mangaList = mangaList + newItems
            page++
            isLoading = false
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
