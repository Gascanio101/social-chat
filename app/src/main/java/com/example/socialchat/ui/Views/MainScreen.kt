package com.example.socialchat.ui.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    LazyColumn(modifier = Modifier.padding(8.dp))  {
        items(5) {
            MyContactCard()
        }
    }
}
