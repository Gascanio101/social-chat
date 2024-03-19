package com.example.socialchat

import ContactViewModel
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.socialchat.ui.theme.SocialChatTheme
import android.Manifest
import android.app.Application
import com.example.socialchat.core.models.Contact
import com.example.socialchat.ui.Views.ContactListScreen

class MainActivity : ComponentActivity() {

    private val PERMISSIONS_REQUEST_READ_CONTACTS = 1001 // Custom request code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request permission to read contacts
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            // Permission has already been granted, proceed with your activity initialization
            setContent {
                SocialChatTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // MainScreen() or any other UI components
                        ContactListScreen(contactViewModel = ContactViewModel(Application()))
                    }
                }
            }
        }
    }

    // Override onRequestPermissionsResult to handle permission request result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with your activity initialization
                setContent {
                    SocialChatTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            // MainScreen() or any other UI components
                            ContactListScreen(contactViewModel = ContactViewModel(Application()))
                        }
                    }
                }
            } else {
                // Permission denied, you may want to inform the user or handle it in some other way
                // For example, show a toast or display an error message
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
