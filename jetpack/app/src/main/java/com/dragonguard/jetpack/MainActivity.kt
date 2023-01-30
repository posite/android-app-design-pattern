package com.dragonguard.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dragonguard.jetpack.ui.theme.RecyclerviewTheme
import com.dragonguard.jetpack.ui.theme.Typography
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerviewTheme {
                MyApp()
            }

        }
    }
}


@Composable
fun MyApp() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent()
    }
}

@Composable
fun RecyclerViewContent() {
    val puppies = remember { DataProvider.puppyList }
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = puppies,
            itemContent = { PuppyListItem(it) }
        )
    }
}


@Composable
fun PuppyListItem(puppy: Puppy) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 12.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            PuppyImage(puppy = puppy)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = puppy.name, style = Typography.h6)
                Text(text = puppy.content, style = Typography.caption, modifier = Modifier.padding(0.dp,2.dp))
            }
        }
    }
}

@Composable
fun PuppyImage(puppy: Puppy) {
    AsyncImage(
        model = puppy.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecyclerviewTheme {
        MyApp()
    }
}