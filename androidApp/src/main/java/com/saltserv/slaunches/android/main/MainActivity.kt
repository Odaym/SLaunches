package com.saltserv.slaunches.android.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saltserv.slaunches.R
import com.saltserv.slaunches.android.ui.ErrorDialog
import com.saltserv.slaunches.android.ui.RocketLaunchRow
import com.saltserv.slaunches.android.ui.SpaceXLaunchesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewModel.state

            SpaceXLaunchesTheme {
                Column {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.app_name))
                        },
                        elevation = 10.dp,
                        actions = {
                            Text(
                                text = stringResource(id = R.string.reload_items),
                                modifier = Modifier.clickable {
                                    viewModel.loadItems()
                                }
                            )
                        }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (state) {
                            is MainViewModel.MainScreenState.Success -> {
                                Column {
                                    Spacer(modifier = Modifier.height(20.dp))

                                    LazyColumn(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color.White)
                                            .padding(10.dp)
                                    ) {
                                        items(
                                            items = state.rocketLaunches,
                                            key = {
                                                it.missionName
                                            }
                                        ) {
                                            RocketLaunchRow(it)
                                        }
                                    }
                                }
                            }
                            is MainViewModel.MainScreenState.Error -> {
                                ErrorDialog(
                                    title = stringResource(id = R.string.fetch_results_failure),
                                    message = state.throwable.message.toString()
                                )
                            }
                            is MainViewModel.MainScreenState.Loading -> {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}