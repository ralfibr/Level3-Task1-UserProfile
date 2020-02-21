package com.example.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    // get intent profile object
    private fun initViews() {
        val profile = intent.getParcelableExtra<Profile>(PROFILE_EXTRA)
        if (profile != null) {
            nameProfile.text = getString(R.string.name, profile.firstName, profile.lastName)
            description.text = profile.description
            if (profile.imageUri != null) profileimg.setImageURI(profile.imageUri)
        }
    }

    // Extra profile
    companion object {
        const val PROFILE_EXTRA = "PROFILE_EXTRA"
    }
}
