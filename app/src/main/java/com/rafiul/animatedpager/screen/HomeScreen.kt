package com.rafiul.animatedpager.screen

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
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
                    .padding(
                        start = 64.dp,
                        end = 32.dp
                    )
                    .graphicsLayer {
                        val startOffset = pagerStateHorizontal.startOffsetForPage(page)
                        translationX = size.width * (startOffset * .99f)

                        alpha = (2f - startOffset) / 2

                        val blur = (startOffset * 20).coerceAtLeast(.1f)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            renderEffect = android.graphics.RenderEffect
                                .createBlurEffect(
                                    blur,
                                    blur,
                                    android.graphics.Shader.TileMode.DECAL
                                )
                                .asComposeRenderEffect()
                        }
                        val scale = 1f - (startOffset * .1f)
                        scaleX = scale
                        scaleY = scale
                    }
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

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction


@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int) = offsetForPage(page).coerceAtLeast(0f)



















