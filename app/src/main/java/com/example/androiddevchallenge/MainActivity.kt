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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
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
        LazyColumn {
            items(puppers) {
                PuppyUI(puppy = it)
            }
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
