package com.example.imagedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.imagedroid.data.model.GetPhotosResponseItem
import com.example.imagedroid.data.repo.ImagesRepository
import com.example.imagedroid.data.utils.Result
import com.example.imagedroid.ui.theme.ImageDroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imagesRepository: ImagesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DroidImageListScreen(imagesRepository = imagesRepository)
                }
            }
        }
    }
}

@Composable
fun DroidImageListScreen(imagesRepository: ImagesRepository){
    val imagesList = remember {
        mutableStateOf(mutableListOf<GetPhotosResponseItem>())
    }

    LaunchedEffect(key1 = "abc", block = {
        withContext(Dispatchers.IO){
            val result = imagesRepository.loadPhotos()
            withContext(Dispatchers.Main){
                when(result){
                    is Result.Error -> {

                    }
                    is Result.Success -> {
                        imagesList.value = result.data.toMutableList()
                    }
                }
            }
        }
    })

    DroidImageListContent(imagesList = imagesList.value)
}

@ExperimentalCoilApi
@Composable
fun DroidImageListContent(imagesList : List<GetPhotosResponseItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = imagesList,
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