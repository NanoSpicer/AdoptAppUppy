package com.example.androiddevchallenge.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy



@Composable fun PuppyUI(puppy: Puppy) {

    val imageBitmap = painterResource(R.drawable._1)

    Surface(
        modifier = Modifier.background(Color.Blue)
    ) {
        Row {
            Column {
                Image(imageBitmap, contentDescription = "A puppy named ${puppy.name}", modifier = Modifier.size(48.dp))
            }
            Column(

            ) {
                Text(text = puppy.name)
                Text(text = puppy.tagline)
            }

        }


    }

}


@Preview
@Composable fun PuppyUI_Preview() {
    val pup = Puppy("Puppy name test", "mega pupper", "puppo", null, false)
    PuppyUI(pup)
}