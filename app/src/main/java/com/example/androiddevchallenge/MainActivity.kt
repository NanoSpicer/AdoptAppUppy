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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.views.PuppyUI
import com.example.androiddevchallenge.viewmodel.PuppyListViewModel

class MainActivity : AppCompatActivity() {

    private val puppyListVM by viewModels<PuppyListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(puppyListVM)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(vm: PuppyListViewModel) {
    val puppers = vm.pups.collectAsState(initial = emptyList()).value
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Toolbar()
            LazyColumn {
                items(puppers) {
                    PuppyUI(puppy = it, vm::seeDetails, vm::togglePuppyAdoption)
                }
            }
        }
    }
}

@Composable
fun Toolbar()  {
    Surface(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        color = MaterialTheme.colors.primarySurface,
        elevation = 16.dp
    ) {
        Row (
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Adopt App Puppy!", fontWeight = FontWeight.ExtraBold)
        }

    }
}

/*
@Preview("Light Theme", widthDp = 360, heightDp = 320)
@Composable
fun LightPreview() {
    val vm = PuppyListViewModel()
    MyTheme {
        MyApp(vm)
    }
}


@Preview("Dark Theme", widthDp = 360, heightDp = 320)
@Composable
fun DarkPreview() {
    val vm = PuppyListViewModel()
    MyTheme(darkTheme = true) {
        MyApp(vm)
    }
}*/
