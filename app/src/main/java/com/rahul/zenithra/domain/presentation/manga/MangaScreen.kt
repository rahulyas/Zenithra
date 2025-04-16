package com.rahul.zenithra.domain.presentation.manga

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rahul.zenithra.core.common.MangaItem
import com.rahul.zenithra.domain.model.Manga

@Composable
fun MangaScreen(onMangaClick: (Manga) -> Unit) {
    val viewModel: MangaViewModel = hiltViewModel()
    val mangaList = viewModel.mangaList  // ← Access directly since it's a Compose state

    // Call loadNextPage() only once when screen is composed
    LaunchedEffect(Unit) {
        viewModel.loadNextPage()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(mangaList.size) { index ->
            MangaItem(
                manga = mangaList[index],
                onClick = { onMangaClick(mangaList[index]) }
            )
        }
    }
}
