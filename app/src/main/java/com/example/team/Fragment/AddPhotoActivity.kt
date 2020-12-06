package com.example.team.Fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.BitmapFactory

import android.graphics.Rect
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.team.Fragment.model.ContentDTO
import com.example.team.R
import com.example.team.data.Card
import com.example.team.data.User
import com.example.team.data.profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
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

    var bitmap_string:String? =null
    var addressList:List<Address>?=null
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
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri)



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

    fun BitmapToString(bitmap: Bitmap): String? {
        try{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos)
        val bytes: ByteArray = baos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
          }catch (e:Exception){
              return null
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
            val geocoder = Geocoder(this)



            contentDTO.location=addphoto_location.text.toString()
            // Insert uid of user
            contentDTO.uid = auth?.currentUser?.uid

            // Insert userId
            contentDTO.userId = auth?.currentUser?.email
            // Insert explain of content
            contentDTO.explain = addphoto_edit_explain.text.toString()
            contentDTO.type=addphoto_cate.selectedItem.toString()
            // Insert timestamp
            contentDTO.timestamp = System.currentTimeMillis().toString()

            // Insert title
            contentDTO.title = addphoto_title.text.toString()
            addressList = geocoder.getFromLocationName(
                contentDTO.location, // 주소
                1
            )
            try{
                contentDTO.latitude= (addressList as MutableList<Address>?)!!.get(0)!!.getLatitude().toString()

            contentDTO.longitude= (addressList as MutableList<Address>?)!!.get(0)!!.getLongitude().toString()
            }
            catch(e:Exception){
                contentDTO.latitude=null
                contentDTO.longitude=null
            }

                Log.d("hi", contentDTO.latitude!!)
            Log.d(addressList .toString(),addressList.toString())

            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "imageUrl"
            ).setValue(contentDTO.imageUrl)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "type"
            ).setValue(contentDTO.type)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "userId"
            ).setValue(contentDTO.userId.toString())
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "explain"
            ).setValue(contentDTO.explain)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "title"
            ).setValue(contentDTO.title)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "latitude"
            ).setValue(contentDTO.latitude)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child(
                "longitude"
            ).setValue(contentDTO.longitude)
            // Insert location
            contentDTO.location = addphoto_location.text.toString()

            val postid = timestamp.toString()

            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child("type").setValue(contentDTO.type)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child("userId").setValue(contentDTO.userId.toString())
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child("explain").setValue(contentDTO.explain)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child("title").setValue(contentDTO.title)
            myRef.child("uid").child(auth?.currentUser?.uid.toString()).child(timestamp.toString()).child("imageUrl").setValue(contentDTO.imageUrl)


            profile.profile_list.add(
                User(contentDTO.title,contentDTO.type,contentDTO.explain,timestamp)
            )
            Card.card_list.add(
                User(contentDTO.title,contentDTO.type,contentDTO.location,contentDTO.imageUrl)
            )

                }


            setResult(Activity.RESULT_OK)
            finish()
        }

    }










