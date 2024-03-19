import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.socialchat.R
import com.example.socialchat.core.models.Contact
import com.example.socialchat.core.utils.Colors

@Composable
fun MyContactCard(contact: Contact) {
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
                // Contact Image
                AsyncImage(
                    model = contact.photoUri,
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
                    // Call Button
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
                    // WhatsApp Button
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        colors = ButtonDefaults.buttonColors(Colors.secondaryColor),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "WhatsApp", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            // Contact Name and Number
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = contact.contactName,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = contact.phoneNumber ?: "",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
