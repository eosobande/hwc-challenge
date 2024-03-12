package com.challenge.hwg.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.challenge.hwg.R
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme

@Composable
fun ImageGallery(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { // start margin
            Spacer(modifier = Modifier.width(10.dp))
        }
        itemsIndexed(
            items = images,
            key = { _, it -> it }
        ) { index, item ->
            // AsyncImage not loading on emulator for some reason
            AsyncImage(
                model = item,
                contentDescription = stringResource(id = R.string.property_photo),
                modifier = Modifier
                    .height(250.dp)
                    .clip(
                        when (index) {
                            // first item, left corners rounded
                            0 -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                            // last item, right corners rounded
                            images.size - 1 -> RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                            // square edges for all items in between
                            else -> RoundedCornerShape(0.dp)
                        }
                    ),
                contentScale = ContentScale.Crop
            )
//            Image(
//                painter = painterResource(id = R.drawable.lhwrzhujtgcoegidsugx),
//                contentDescription = "Translated description of what the image contains",
//                modifier = Modifier
//                    .height(250.dp)
//                    .clip(
//                        when (index) {
//                            0 -> RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
//                            images.size - 1 -> RoundedCornerShape(
//                                topEnd = 10.dp,
//                                bottomEnd = 10.dp
//                            )
//
//                            else -> RoundedCornerShape(0.dp)
//                        }
//                    ),
//                contentScale = ContentScale.Crop,
//            )
        }
        item { // end margin
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewImageGallery() {
    HWGCodeChallengeTheme {
        ImageGallery(listOf("a", "b"))
    }
}