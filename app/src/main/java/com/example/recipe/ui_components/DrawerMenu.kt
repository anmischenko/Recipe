package com.example.recipe.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipe.R
import com.example.recipe.ui.theme.BgTransparent
import com.example.recipe.utils.DrawerEvents

@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        Body() { event ->
            onEvent(event)
        }
    }
}

@Composable
fun Header() {
    Image(
        painter = painterResource(id = R.drawable.cooking),
        contentDescription = "Header image",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Body(onEvent: (DrawerEvents) -> Unit) {
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { index, title ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                backgroundColor = BgTransparent,
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}
