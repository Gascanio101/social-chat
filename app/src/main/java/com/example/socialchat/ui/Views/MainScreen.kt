package com.example.socialchat.ui.Views

import ContactViewModel
import MyContactCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ContactListScreen(vm: ContactViewModel) {

    // Loads the contact list from the viewModel
    val contactList by vm.contactList

    // Displays the contact list as a RecyclerView
    LazyColumn {
        items(contactList.size) { contact ->
            MyContactCard(contact = contactList[contact])
        }
    }
}