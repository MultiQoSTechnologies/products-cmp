package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProductScreen(
    name: String, description: String, price: String, discount: String, image: String
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {

        KamelImage(
            resource = asyncPainterResource(data = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        ) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = name,
                maxLines = 3,
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