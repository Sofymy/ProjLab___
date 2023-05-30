package com.bme.projlab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.TopViewModel
import com.bme.projlab.ui.items.TriangleEdgeShape
import com.bme.projlab.ui.items.TriangleEdgeShapeReversed
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer

@Composable
fun ProfileScreen(
    viewModel: TopViewModel = hiltViewModel(),
    navigateToVisited: () -> Unit,
    navigateToBadges: () -> Unit,
    navigateToWelcome: () -> Unit
) {
    val space = Modifier.height(10.dp)
    Column(modifier = Modifier
        .padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            VisitedButton(navigateToVisited)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            BadgesButton(navigateToBadges)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            SignOutButton(viewModel, navigateToWelcome)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(15.dp),
        ){
            Typing()
        }
    }
}

@Composable
fun Typing() {
    Column {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max),
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Purple,
                        shape = TriangleEdgeShapeReversed(50)
                    )
                    .width(15.dp)
                    .fillMaxHeight()
            ) {
            }
            Column(
                modifier = Modifier.background(
                    color = Purple,
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 0.dp)
                )
            ) {
                Text(
                    "  ...  ",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(15.dp).shimmer(),
                    color = White
                )
            }
        }
    }
}

@Composable
fun VisitedButton(navigateToVisited: () -> Unit) {
    Column(
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .clickable {
                    navigateToVisited()
                },
        ) {
            Column(
                modifier = Modifier.background(
                    color = Grey,
                    shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 10.dp)
                )
            ) {
                Text(
                    "Click here to add a new visited location",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(15.dp),
                    color = White
                )
            }
            Column(
                modifier = Modifier
                    .background(
                        color = Grey,
                        shape = TriangleEdgeShape(50)
                    )
                    .width(3.dp)
                    .fillMaxHeight()
            ) {
            }
        }
    }
}

    @Composable
    fun BadgesButton(navigateToBadges: () -> Unit) {
        Column(
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .clickable {
                        navigateToBadges()
                    },
            ) {
                Column(
                    modifier = Modifier.background(
                        color = Grey,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 10.dp)
                    )
                ) {
                    Text(
                        "Tap this to see your earned badges",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(15.dp),
                        color = White
                    )
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = Grey,
                            shape = TriangleEdgeShape(50)
                        )
                        .width(3.dp)
                        .fillMaxHeight()
                ) {
                }
            }
        }
    }

    @Composable
    fun SignOutButton(viewModel: TopViewModel, navigateToWelcome: () -> Unit) {
        Column(
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .clickable {
                        viewModel.signOut()
                        navigateToWelcome()
                    },
            ) {
                Column(
                    modifier = Modifier.background(
                        color = Grey,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 10.dp)
                    )
                ) {
                    Text(
                        "You can sign out by clicking here. But why would you sign out?!",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(15.dp),
                        color = White
                    )
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = Grey,
                            shape = TriangleEdgeShape(50)
                        )
                        .width(3.dp)
                        .fillMaxHeight()
                ) {
                }
            }
        }
    }

