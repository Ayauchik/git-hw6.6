package com.example.hwviewmodels.domain

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hwviewmodels.R
import com.example.hwviewmodels.data.Routes
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageHorizontalPager(navController: NavController) {

    val imageList = listOf(
        R.drawable.im_1,
        R.drawable.im_2,
        R.drawable.im_3
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            pageCount = imageList.size
        ) { page ->
            val imageResource = imageList.getOrNull(page)
            if (imageResource != null) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 360.dp, height = 300.dp)
                        .padding(12.dp),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Surface(
                    modifier = Modifier
                        .size(200.dp, 200.dp)
                        .padding(16.dp),
                    color = Color.Gray
                ) {
                    Text(
                        text = "No Image",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        val coroutineScope = rememberCoroutineScope()
        if(pagerState.currentPage == imageList.size - 1){
            androidx.compose.material.Button(onClick = {
                navController.navigate(Routes.second)
            }) {
                Text(text = "Finish")
            }
        }else{
            androidx.compose.material.Button(onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage.plus(1))
                }
            }) {
                Text("Next")
            }
        }

    }
}