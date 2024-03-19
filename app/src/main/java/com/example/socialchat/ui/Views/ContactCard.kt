package com.example.socialchat.ui.Views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.socialchat.R
import com.example.socialchat.core.utils.Colors

@Composable
fun MyContactCard() {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
        border = BorderStroke(2.dp, color = Colors.primaryColor),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .border(2.dp, color = Colors.primaryColor)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {

                // Replace with Contact image
                AsyncImage(
                    model = R.drawable.contact_placeholder,
                    modifier = Modifier
                        .padding(8.dp)
                        .border(
                            2.dp,
                            color = Colors.primaryColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(shape = RoundedCornerShape(16.dp)),
                    placeholder = painterResource(R.drawable.contact_placeholder),
                    contentDescription = "Contact Image"
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(Colors.secondaryColor),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Llamar", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        colors = ButtonDefaults.buttonColors(Colors.secondaryColor),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Whatsapp", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {

                Text(
                    text = "Contact Name", modifier = Modifier.padding(8.dp),
                    fontSize = 32.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Contact Number", modifier = Modifier.padding(8.dp),
                    fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}