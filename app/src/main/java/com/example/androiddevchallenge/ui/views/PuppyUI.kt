package com.example.androiddevchallenge.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.AppColors


typealias func<T> = (T) -> Unit


internal val boneEdgeSize = 64.dp



@Composable fun PuppyUI(puppy: Puppy, click: func<Puppy>?=null, onAdopt: func<Puppy>?=null) {

    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(click != null) { click?.invoke(puppy) }
    ){
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
            .offset(y = pullBallsTogether))
    BoneEdgeBall(
        modifier
            .zIndex(0.1f)
            .offset(y = -pullBallsTogether)
            .shadow(4.dp, CircleShape, false)
    )
}


@Composable
private fun SimplePuppy(puppy: Puppy, onAdopt: func<Puppy>?=null) = Column {

    val pullImageDown = boneEdgeSize / 3
    val pullTextUp    = boneEdgeSize / 2
    val pullBallsToCenter = 12.dp
    val imageBitmap = painterResource(puppy.image)
    val adoptBitmap = painterResource(if(!puppy.adopted) R.drawable.ic_paw else R.drawable.ic_remove)
    val callToAction = if(!puppy.adopted) "Adopt me!" else "Let go"

    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            //modifier =  Modifier.padding(0.dp, 16.dp, 16.dp, 0.dp),
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
        ){
            Column(
                modifier =
                Modifier
                    .fillMaxWidth(0.56f)
                    .background(AppColors.boneWhite)
                    .padding(0.dp, 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = puppy.name, color = AppColors.engraving)
                Text(text = puppy.tagline, color = AppColors.engraving)
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