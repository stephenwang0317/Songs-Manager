package com.example.bellavoice.screen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.bellavoice.R
import com.example.bellavoice.Utils.LocalNavController
import com.example.bellavoice.component.BoxWithBottomBar
import com.example.bellavoice.component.VoiceCard
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.viewmodel.SongsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainPage(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val songVM = SongsViewModel()
    val coroutineScope = rememberCoroutineScope()
    val refreshLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGrant ->
        if (isGrant) {
            coroutineScope.launch { songVM.loadLocalSongs() }
        } else {
            Toast.makeText(context, "请授予权限", Toast.LENGTH_SHORT).show()
        }
    }

    val targetSong by songVM.targetSong.observeAsState(ArrayList<SongBean>())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("AddPage") }) {
                        Image(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
                title = {
                    Row() {
                        Image(painter = painterResource(id = R.drawable._ava), contentDescription = null)
                        Image(painter = painterResource(id = R.drawable._bella), contentDescription = null)
                        Image(painter = painterResource(id = R.drawable._diana), contentDescription = null)
                        Image(painter = painterResource(id = R.drawable._elieen), contentDescription = null)
                    }
                },
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                ),
                actions = {
                    IconButton(
                        onClick = {
                            when {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                ) == PackageManager.PERMISSION_GRANTED -> {
                                    coroutineScope.launch { songVM.loadLocalSongs() }
                                }

                                else -> {
                                    refreshLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                }
                            }
                        },
                    ) {
                        Image(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Black)
                        )
                    }
                }

            )
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