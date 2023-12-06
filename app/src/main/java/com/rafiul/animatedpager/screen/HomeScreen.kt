package com.rafiul.animatedpager.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rafiul.animatedpager.model.locationList


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {


    val pagerStateHorizontal = rememberPagerState(pageCount = { locationList.count() })

    Column {
        HorizontalPager(
            state = pagerStateHorizontal,
            modifier = Modifier
                .padding(top = 32.dp)
                .weight(.7f)
        ) { page ->

            Box(
                modifier = Modifier
                    .zIndex(page * 2f)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(id = locationList[page].image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}