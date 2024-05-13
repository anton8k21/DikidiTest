@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.dikiditest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dikiditest.R
import com.example.dikiditest.domain.model.CatalogModel
import com.example.dikiditest.domain.State
import com.example.dikiditest.presentation.ui.theme.DikidiTestTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DikidiTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: ViewModel) {
    val state by viewModel.state.collectAsState()
    var catalog by remember {
        mutableStateOf((listOf(CatalogModel())))
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6E6))
            .verticalScroll(rememberScrollState())

    ) {
        Header()
        if (state is State.Result) {
            catalog = (state as State.Result).homeInfo.data?.blocks!!.catalog
            Category(state as State.Result)
            VipTariff(catalog)
            Popular(catalog)
        }
        Certificates()

    }
}

@Composable
private fun Certificates() {
    Text(
        text = "Сертификаты",
        modifier = Modifier.padding(8.dp)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.certificate),
            contentDescription = ""
        )
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Выбрать сертификат")
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun Popular(catalog: List<CatalogModel>) {
    Text(
        text = "Популярные",
        modifier = Modifier.padding(8.dp)
    )
    val statePage = rememberPagerState {
        if (catalog.isNotEmpty()) {
            val itemSize = catalog.filter { (it.rating ?: 0.0) >= 4.5 }.size
            itemSize
        } else {
            0
        }

    }


    HorizontalPager(
        state = statePage,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) { pagerScope ->
        val item = catalog.filter { (it.rating ?: 0.0) >= 4.5 }[pagerScope]

        Card(
            modifier = Modifier
                .fillMaxWidth(1F)
                .padding(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GlideImage(
                    model = item.image!!.origin,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(80.dp)
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        fontSize = 16.sp,
                        text = item.rating.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        fontSize = 16.sp,
                        text = item.name.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        fontSize = 16.sp,
                        text = item.street.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        fontSize = 16.sp,
                        text = "4.81км",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun VipTariff(catalog: List<CatalogModel>) {
    val vipList = catalog.filter { it.vipTariff == true }
    if (vipList.isNotEmpty()){
        Text(
            text = "Премиум",
            modifier = Modifier.padding(12.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp), colors = CardDefaults.cardColors(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(0.dp, 250.dp)
                    .padding(8.dp),
            ) {
                items(vipList) { catalog ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GlideImage(
                            model = catalog.image!!.origin,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = "Ирина Константиновна",
                                Modifier.width(150.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text(
                                text = "ресницы, эпиляция, дипиляция, fddfdfdfgfdgcbcb",
                                Modifier.width(150.dp),
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(8.dp),
                            shape = MaterialTheme.shapes.large,
                            border = BorderStroke(
                                1.dp,
                                Color.Blue
                            )
                        ) {
                            Text(text = "Записаться")
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun Category(state: State.Result) {
    Text(
        text = "Категория",
        modifier = Modifier.padding(12.dp)
    )
    LazyHorizontalGrid(
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = Modifier.height(220.dp),
        rows = GridCells.Adaptive(minSize = 100.dp),

        ) {
        items(state.categories) { item ->

            Box(contentAlignment = Alignment.Center) {
                Card(
                    modifier = Modifier
                        .size(width = 200.dp, height = 120.dp)
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.image), contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }

                Text(
                    text = item.name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }


        }
    }
}

@Composable
private fun Header() {
    var textFieldState by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
    ) {
        Text(
            text = "Онлайн-запись",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        Text(
            text = "город",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Row {
            OutlinedTextField(
                value = textFieldState, onValueChange = {
                    textFieldState = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        }
    }
}
