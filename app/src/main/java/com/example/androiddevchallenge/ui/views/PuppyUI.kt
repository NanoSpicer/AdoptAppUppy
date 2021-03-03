/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.AppColors

typealias func<T> = (T) -> Unit

internal val boneEdgeSize = 64.dp

@Composable fun PuppyUI(puppy: Puppy, click: func<Puppy>? = null, onAdopt: func<Puppy>? = null) {
    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(click != null) { click?.invoke(puppy) }
    ) {
        SimplePuppy(puppy, onAdopt)
    }
}

@Composable fun BoneEdgeBall(modifier: Modifier = Modifier) = Box(
    modifier
        .size(boneEdgeSize)
        .clip(CircleShape)
        .background(AppColors.boneWhite)
)

@Composable fun BoneEdge(modifier: Modifier = Modifier) = Column {
    val pullBallsTogether = (boneEdgeSize / 9)
    BoneEdgeBall(
        modifier
            .zIndex(0.2f)
            .offset(y = pullBallsTogether)
    )
    BoneEdgeBall(
        modifier
            .zIndex(0.1f)
            .offset(y = -pullBallsTogether)
            .shadow(4.dp, CircleShape, false)
    )
}

@Composable
private fun SimplePuppy(puppy: Puppy, onAdopt: func<Puppy>? = null) = Column {
    val pullImageDown = boneEdgeSize / 3
    val pullTextUp = boneEdgeSize / 2
    val pullBallsToCenter = 12.dp
    val iconRef = if (puppy.adopted) R.drawable.ic_remove else R.drawable.ic_paw
    val callToAction = if (!puppy.adopted) "Adopt me!" else "Let me go…"
    val imageBitmap = painterResource(puppy.image)
    val adoptBitmap = painterResource(iconRef)

    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            // modifier =  Modifier.padding(0.dp, 16.dp, 16.dp, 0.dp),
            onClick = { onAdopt?.invoke(puppy) },
            shape = CircleShape
        ) {
            Text(callToAction)
            Spacer(Modifier.size(8.dp))
            Image(adoptBitmap, contentDescription = "Action icon for adopting")
        }
    }

    // Image
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = pullImageDown),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            imageBitmap,
            modifier =
            Modifier
                .fillMaxWidth(0.4f)
                .shadow(16.dp, CircleShape),
            contentDescription = "A puppy named ${puppy.name}",
        )
    }

    // Bone banner
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(y = -pullTextUp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BoneEdge(
            Modifier.offset(pullBallsToCenter)
        )
        Column(
            modifier = Modifier.zIndex(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth(0.56f)
                    .background(AppColors.boneWhite)
                    .padding(0.dp, 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = puppy.name, textAlign = TextAlign.Center,color = AppColors.engraving,
                    fontStyle = FontStyle.Italic, fontSize = 32.sp
                )
                Text(text = "the ${puppy.race.toLowerCase()}", textAlign = TextAlign.Center, color = AppColors.engraving)
            }
            Column(
                Modifier
                    .fillMaxWidth(0.482f)
                    .shadow(1.dp)
                    .background(Color.Transparent)
                    .height((0.15).dp)
                    .offset(x = 6.dp, y = (0.15).dp)
                    .zIndex(1f)

            ) {
            }
        }
        BoneEdge(
            Modifier.offset(-pullBallsToCenter)
        )
    }
}

@Preview("Puppy UI preview")
@Composable fun PuppyUI_Preview() {
    val data = Puppy("Bobby Socks", "shoob", "puppo", R.drawable._4, false)
    SimplePuppy(data)
}
