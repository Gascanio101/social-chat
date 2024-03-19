import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialchat.core.models.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>> = _contactList

    fun loadContacts() {
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
            while (cursor.moveToNext()) {
                val name = if (displayNameIndex >= 0) cursor.getString(displayNameIndex) ?: "" else ""
                val id = if (idIndex >= 0) cursor.getString(idIndex) ?: "" else ""
                val photoUri = if (photoUriIndex >= 0) cursor.getString(photoUriIndex) ?: "" else ""
                val phoneNumber = getPhoneNumber(contentResolver, id)
                val contact = Contact(name, phoneNumber, photoUri)
                contacts.add(contact)
            }
            cursor.close()
        }
        _contactList.value = contacts
    }
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
