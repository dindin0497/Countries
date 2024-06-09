package com.din.countries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.din.countries.domain.DetailCountry
import com.din.countries.domain.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountryViewModel.CountriesState,
    onSelectCountry : (code: String) -> Unit,
    onDismissCountryDialog : () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()){
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries){ country ->
                    CountryItem(country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCountry(country.code) }
                            .padding(16.dp))
                }
            }
            state.selectedCountry?.let {
                CountryDialog(country = it,
                    onDismiss = onDismissCountryDialog,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp))
            }
        }

    }
}

@Composable
fun CountryItem( country: SimpleCountry, modifier: Modifier = Modifier){
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = country.emoji, fontSize = 30.sp)

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 24.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = country.continent, fontSize = 16.sp)
        }
    }
}

@Composable
fun CountryDialog( country: DetailCountry,
                   onDismiss: ()->Unit,
                   modifier: Modifier = Modifier){

    val lang = remember(country.language) {
        country.language.joinToString()
    }

    Dialog (onDismissRequest=onDismiss){
        Column (modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = country.name, fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "continent: ${country.continent}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "currency: ${country.currency}", fontSize = 20.sp)

            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "languages: $lang", fontSize = 20.sp)
        }

    }

}