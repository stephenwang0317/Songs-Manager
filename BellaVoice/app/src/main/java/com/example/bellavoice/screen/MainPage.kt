package com.example.bellavoice.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bellavoice.component.BoxWithBottomBar
import com.example.bellavoice.component.MyTopBar
import com.example.bellavoice.component.VoiceCard
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.viewmodel.SongsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainPage(
    modifier: Modifier = Modifier
) {
    val songVM = SongsViewModel()
    val targetSong by songVM.targetSong.observeAsState(ArrayList<SongBean>())

    Scaffold(
        topBar = {
            MyTopBar()
        }
    ) { it ->
        Column {
            LazyColumn(
                contentPadding = it,
                content = {
                    item { Spacer(modifier = Modifier.height(5.dp)) }
                    items(targetSong) {
                        VoiceCard(
                            modifier = Modifier.fillMaxWidth(),
                            bean = it,
                            vm = songVM
                        )
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                },
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 5.dp, end = 5.dp
                    )
                    .weight(1f)
            )
        }
        BoxWithBottomBar(songVM)
    }
}