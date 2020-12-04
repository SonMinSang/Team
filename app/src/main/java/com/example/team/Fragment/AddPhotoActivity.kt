package com.example.team.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.team.Fragment.model.ContentDTO
import com.example.team.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    var PICK_IMAGE_FROM_ALBUM = 0 // request code
    var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    var auth : FirebaseAuth? =null
    var firestore : FirebaseFirestore? =null
    val db : FirebaseDatabase= FirebaseDatabase.getInstance();
    val myRef:DatabaseReference= db.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val items = resources.getStringArray(R.array.catagory)
        // Initiate storage
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        // Open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)

        // add image upload event
        addphoto_btn_upload.setOnClickListener {
            contentUpload()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == Activity.RESULT_OK){
                // This is path to the seleted image
                photoUri = data?.data
                addphoto_image.setImageURI(photoUri)
            }else{
                // Exit the addPhotoActivity if you leave the album without selecting it
                finish()
            }
        }
    }
    fun contentUpload(){
        // Make filename
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imageFileName = "IMAGE_" + timestamp + "_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)

        // File upload
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener { uri ->
            var contentDTO = ContentDTO()


            // Insert uid of user
            contentDTO.uid = auth?.currentUser?.uid

            // Insert userId
            contentDTO.userId = auth?.currentUser?.email
            // Insert explain of content
            contentDTO.explain = addphoto_edit_explain.text.toString()
            contentDTO.type=addphoto_cate.selectedItem.toString()
            // Insert timestamp
            contentDTO.timestamp = System.currentTimeMillis()

            // Insert title
            contentDTO.title = addphoto_title.text.toString()

            // Insert location
            contentDTO.location = addphoto_location.text.toString()

            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child("timestamp").child(timestamp.toString()).child("type").setValue(contentDTO.type)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child("timestamp").child(timestamp.toString()).child("userId").setValue(contentDTO.userId.toString())
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child("timestamp").child(timestamp.toString()).child("explain").setValue(contentDTO.explain)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child("timestamp").child(timestamp.toString()).child("title").setValue(contentDTO.title)

            setResult(Activity.RESULT_OK)

            finish()
        }

    }
}


