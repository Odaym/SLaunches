package com.saltserv.slaunches.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saltserv.slaunches.R
import com.saltserv.slaunches.entity.Links
import com.saltserv.slaunches.entity.Rocket
import com.saltserv.slaunches.entity.RocketLaunch

@Preview
@Composable
fun Preview_RocketLaunchRow() {
    SpaceXLaunchesTheme {
        RocketLaunchRow(
            rocketLaunch = RocketLaunch(
                flightNumber = 2,
                missionName = "FalconSat",
                launchYear = 2007,
                launchDateUTC = "2007-03-21T01:10:00.000Z",
                rocket = Rocket(id = "falcon1", name = "Falcon 1", type = "Merlin C"),
                details = "Successful first stage burn and transition to second stage, " +
                        "maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, " +
                        "Failed to reach orbit, Failed to recover first stage",
                launchSuccess = false,
                links = Links(
                    missionPatchUrl = "https://images2.imgbox.com/3d/86/cnu0pan8_o.png",
                    articleUrl = "http://www.spacex.com/news/2013/02/11/falcon-1-flight-3-mission-summary"
                )
            )
        )
    }
}

@Composable
fun RocketLaunchRow(rocketLaunch: RocketLaunch) {
    val launchSuccessful = rocketLaunch.launchSuccess == true

    Column {
        Text(text = stringResource(id = R.string.launch_name, rocketLaunch.missionName))

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (launchSuccessful) {
                stringResource(id = R.string.launch_status_success)
            } else {
                stringResource(id = R.string.launch_status_unsuccessful)
            },
            color = if (launchSuccessful) {
                Color.Green
            } else {
                Color.Red
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = stringResource(R.string.launch_year, rocketLaunch.launchYear))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = stringResource(R.string.launch_details, rocketLaunch.details ?: ""))
    }

    Divider(
        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
        startIndent = 2.dp,
        thickness = 1.dp
    )
}

