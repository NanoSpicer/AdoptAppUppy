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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.datasource.PuppyRepo
import com.example.androiddevchallenge.model.IDS
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.views.PuppyUI
import com.example.androiddevchallenge.viewmodel.PuppyListViewModel

class DetailActivity : AppCompatActivity() {

    private val puppy by lazy {
        intent.getParcelableExtra<Puppy>("puppy")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                DetailView(puppy) { onBackPressed() }
            }
        }
    }
}

// Start building your app here!
@Composable
fun DetailView(pup: Puppy?, goBack: () -> Unit) {
    val paw = painterResource(R.drawable.ic_paw)
    Surface(color = MaterialTheme.colors.background) {
        Column {
            val title =
                pup?.let { "\uD83D\uDC36 ${it.name} – the ${it.race}" } ?: "Sorry, we couldn't find that pupper!"
            Toolbar(title = title, goBack)
            if(pup != null) {
                PupperDetails(pup)
            } else {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = paw, contentDescription = "icon")
                    Image(painter = paw, contentDescription = "icon")
                    Image(painter = paw, contentDescription = "icon")
                    Text("Sorry, we couldn't find that pupper!")
                    Image(painter = paw, contentDescription = "icon")
                    Image(painter = paw, contentDescription = "icon")
                    Image(painter = paw, contentDescription = "icon")
                }
            }
        }
    }
}

@Composable
fun Toolbar(title: String, goBack: () -> Unit) {
    val backBitmap = painterResource(R.drawable.ic_back)
    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colors.primarySurface,
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.background(Color.Transparent),
                elevation = null,
                onClick = goBack,
                shape = CircleShape
            ) {
                Image(backBitmap, contentDescription = "Go back")
            }
            Text(title, fontWeight = FontWeight.ExtraBold)
        }
    }
}



@Composable
fun PupperDetails(pup: Puppy) = Column(Modifier.fillMaxWidth()){
    val adoptedText = if(pup.adopted) "You have adopted this dog!" else "You haven't adopted this dog"
    val color = if(pup.adopted) Color.Green else Color.Red
    val icon = if(pup.adopted) R.drawable.ic_paw else R.drawable.ic_remove
    val iconBmp = painterResource(icon)
    val bmp = painterResource(pup.image)
    Image(
        bmp,
        contentScale = ContentScale.Crop,
        contentDescription = "A picture of a ${pup.race} dog named ${pup.name}",
        modifier =
        Modifier
            .fillMaxWidth()
            .height(242.dp)
    )
    Text( modifier=Modifier.fillMaxWidth().padding(16.dp),text = pup.tagline, textAlign = TextAlign.Center, fontSize = 24.sp)

    Row(
        modifier=Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = iconBmp,
            colorFilter = ColorFilter.tint(color),
            contentDescription = "An icon"
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = adoptedText, color = color, fontSize = 18.sp)
    }

    Text(
        modifier=Modifier.fillMaxWidth().padding(16.dp),
        text =
            """
                ${pup.name} is a very gooooooooood boyyyyy 🐕. 
                
                Runs around the house 🏠, plays fetch 🐕 , eats 🥫, farts 💨 and even sleeps 😴💤! 
                It's a lovely 💕 dog like any other you will find in this app.
                
                Can you resist his face? 🤩 
                I can't; I'll adopt him if you don't 😉
            """.trimIndent()
    )

}


@Preview
@Composable
fun DetailView_Preview() {
    DetailView(
        Puppy(
            name = "Bobby",
            tagline= "tagline",
            race = "race",
            image = R.drawable._1,
            adopted = false,
            id = -1L
        )
    ){}
}
