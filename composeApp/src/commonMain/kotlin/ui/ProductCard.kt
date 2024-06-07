package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Products


@Composable
fun ProductCard(list: List<Products>) {
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White,
                title = {
                    Text(
                        "Home",
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                },
            )
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(list.size) { index ->
                    val data = list[index]
                    ProductItem(
                        data.title,
                        data.description,
                        data.price.toString(),
                        data.discountPercentage.toString(),
                        data.thumbnail
                    )
                }
            }
        )
    }
}


@Composable
fun ProductItem(name: String, description: String, price: String, discount: String, image: String) {

    var isProductVisible by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    isProductVisible = !isProductVisible
                }
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        Column(
            modifier = Modifier
                .background(color = if (isProductVisible) Color.Red else Color.White)
                .height(380.dp)
                .padding(10.dp)
        ) {
            KamelImage(
                resource = asyncPainterResource(data = image),
                contentDescription = null,
                modifier = Modifier.height(150.dp).fillMaxWidth().clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = name,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = description,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$$price",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$discount% discount",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button,
                    color = Color(red = 0.1f, green = 0.8f, blue = 0.0f)
                )
            }
        }
    }
}