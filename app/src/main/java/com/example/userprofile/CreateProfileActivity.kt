package com.example.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_profile.*

/**
 * @author Raeef Ibrahim
 * student nr 500766393
 */

const val GALLERY_REQUEST_CODE = 100
// URi image
private var profileUri: Uri? = null

class CreateProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initViews()

    }

    private fun initViews() {
        openGallery.setOnClickListener { onGalleryClick() }
        confirmbtn.setOnClickListener { onConfirmClick() }
    }

    private fun onGalleryClick() {
        // Create an Intent with action as ACTION_PICK
        val galleryIntent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        galleryIntent.type = "image/*"
        // Start the activity using the gallery intent
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    // Check if the result code equals Activity.RESULT_OK and check if the requestCode equals GALLERY_REQUEST_CODE.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    profileUri = data?.data
                    // set the image in image view
                    imageProfile.setImageURI(profileUri)
                }
            }
        }
    }

    // send a profile object
    private fun onConfirmClick() {
        val profile = Profile(
            editTextFirstname.text.toString(),
            editTextLastName.text.toString(),
            editTextDes.text.toString(),
            profileUri
        )
// navigate to the profile activity
        val profileActivityIntent = Intent(this, ProfileActivity::class.java)
        profileActivityIntent.putExtra(ProfileActivity.PROFILE_EXTRA, profile)
        startActivity(profileActivityIntent)

    }
}
