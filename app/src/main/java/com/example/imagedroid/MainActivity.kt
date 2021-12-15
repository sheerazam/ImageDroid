package com.example.imagedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.imagedroid.data.base.produceUiState
import com.example.imagedroid.data.model.GetPhotosResponseItem
import com.example.imagedroid.data.repository.ImagesRepository
import com.example.imagedroid.ui.theme.ImageDroidTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ImagesRepository

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DroidImagesMainListScreen(repository = repository)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DroidImagesMainListScreen(repository: ImagesRepository) {
    val (photosUIState, refreshPhotos, clearError) = produceUiState(producer = repository) {
        repository.loadPhotos()
    }

    LoadingContent(
        empty = photosUIState.value.initialLoad,
        emptyContent = { FullScreenLoading() },
        loading = photosUIState.value.loading,
        onRefresh = { refreshPhotos() },
        swipeRefreshEnabled = true,
        content = {
            DroidImageContent(listItems = photosUIState.value.data ?: mutableListOf())
        }
    )
}


@ExperimentalCoilApi
@Composable
fun DroidImageContent(listItems: List<GetPhotosResponseItem>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = listItems,
        ) { image ->
            DroidImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                imageUrl = image.urls?.small ?: ""
            )
        }
    }
}

@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit = {},
    content: @Composable () -> Unit,
    swipeRefreshEnabled: Boolean = false
) {
    if (empty) {
        emptyContent()
    } else {
        if (swipeRefreshEnabled) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(loading),
                onRefresh = onRefresh,
                content = content,
            )
        } else {
            content()
        }
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@ExperimentalCoilApi
@Composable
fun DroidImage(modifier: Modifier, imageUrl: String) {
    Image(
        modifier = modifier,
        painter = rememberImagePainter(
            data = imageUrl,
        ),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageDroidTheme {
        Greeting("Android")
    }
}