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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.views.PuppyUI
import com.example.androiddevchallenge.viewmodel.PuppyListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<PuppyListViewModel>()

    private val selectedPups by lazy { vm.puppyClicked.consumeAsFlow() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
        lifecycleScope.launch {
            selectedPups.collect { pup ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val extras = Bundle().apply {
                    putParcelable("puppy", pup)
                }
                intent.putExtras(extras)
                startActivity(intent)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(vm: PuppyListViewModel = viewModel()) {
    val puppersState = vm.pups.collectAsState(initial = emptyList())
    val puppers = puppersState.value
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Toolbar()
            LazyColumn {
                items(puppers) { pup -> PuppyUI(pup, vm::seeDetails, vm::togglePuppyAdoption) }
            }
        }
    }
}

@Composable
fun Toolbar() {
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
            Text("\uD83D\uDC3E \uD83D\uDC15 Adopt App Puppy! (get it?) \uD83D\uDC36", fontWeight = FontWeight.ExtraBold)
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
