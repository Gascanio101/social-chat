import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialchat.core.models.Contact
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val _contactList: MutableState<List<Contact>> = mutableStateOf(listOf())
    val contactList= _contactList

    // Initialiaze the contact list
    init{
        loadContacts()
    }

    // Load the contacts from the device in an Async way
    fun loadContacts() {
        viewModelScope.launch {
            val contacts = mutableListOf<Contact>()
            val contentResolver = getApplication<Application>().contentResolver
            val cursor: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            cursor?.let {
                val displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
                val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val photoUriIndex = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)
                Log.d("gabs", "Display Name Index: $displayNameIndex, ID Index: $idIndex, Photo URI Index: $photoUriIndex")

                // Iterate over the contacts and handles null values
                while (cursor.moveToNext()) {
                    val name = if (displayNameIndex >= 0) cursor.getString(displayNameIndex) ?: "" else ""
                    val id = if (idIndex >= 0) cursor.getString(idIndex) ?: "" else ""
                    val photoUri = if (photoUriIndex >= 0) cursor.getString(photoUriIndex) ?: "" else ""
                    val phoneNumber = getPhoneNumber(contentResolver, id) ?: "No hay telefono"
                    val contact = Contact(name, phoneNumber, photoUri)
                    contacts.add(contact)
                    Log.d("gabs", "Contact added successfully: $name, $phoneNumber, $photoUri")
                }
                cursor.close()
            }
            // Stores the list of contacts in the private variable
            _contactList.value = contacts
        }
    }

    // Access the device contacts and retrieves the phone number if it exists
    private fun getPhoneNumber(contentResolver: ContentResolver, contactId: String): String? {
        var phoneNumber: String? = null
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(contactId),
            null
        )
        cursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                val phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if (phoneNumberIndex != -1) {
                    phoneNumber = cursor.getString(phoneNumberIndex)
                }
            }
        }
        return phoneNumber
    }
}
