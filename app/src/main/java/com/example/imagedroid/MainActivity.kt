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

@Composable
fun DroidImagesMainListScreen(repository: ImagesRepository){
    val (photosUIState, refreshPhotos, clearError) = produceUiState(producer = repository) {
        repository.loadPhotos()
    }
}

@ExperimentalCoilApi
@Composable
fun DroidImageListScreen() {
    val images = mutableListOf(
        "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60",
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = images,
        ) { image ->
            DroidImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                imageUrl = image
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DroidImageScreen() {
    DroidImage(
        modifier = Modifier,
        imageUrl = "https://www.android.com/static/2016/img/share/andy-lg.png"
    )
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