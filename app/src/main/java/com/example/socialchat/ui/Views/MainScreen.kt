package com.example.socialchat.ui.Views

import ContactViewModel
import MyContactCard
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ContactListScreen(contactViewModel: ContactViewModel) {
//    val contactList by contactViewModel.contactList.observeAsState(initial = emptyList())

    val contactList by contactViewModel.contactList.observeAsState(initial = emptyList())
    LazyColumn {
        items(contactList) { contact ->
            MyContactCard(contact = contact)
        }
    }
}

