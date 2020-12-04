package com.example.team.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.team.Fragment.model.ContentDTO
import com.example.team.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.item_detail.*
//import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {

    val PICK_IMAGE_FROM_ALBUM = 0
    var photoUri: Uri? = null
    var storage: FirebaseStorage? = null
    var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val sAdapter = ArrayAdapter.createFromResource(this, R.array.content_type, android.R.layout.simple_spinner_dropdown_item)
        add_item_type.setAdapter(sAdapter);

        //storage 초기화
        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        //open album
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)

        add_photo_image.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        }

        add_photo_btn_upload.setOnClickListener {
            contentUpload()
        }


    }

//선택한 이미지 받기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
            //이미지 선택시
            if(resultCode == Activity.RESULT_OK){
                //이미지뷰에 이미지 세팅, path
               // println(data?.data)
                photoUri = data?.data
                add_photo_image.setImageURI(photoUri)
            } else{
                finish()
            }

        }
    }

    fun contentUpload(){
        //progress_bar.visibility = View.VISIBLE

        //파일 이름 생성
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_.png"

        val storageRef = storage?.reference?.child("images")?.child(imageFileName)

        storageRef?.putFile(photoUri!!)?.continueWithTask { task: com.google.android.gms.tasks.Task<UploadTask.TaskSnapshot> ->
            return@continueWithTask storageRef.downloadUrl
        }?.addOnSuccessListener { uri ->
            var contentDTO = ContentDTO()

            //이미지 주소
            contentDTO.imageUrl?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.imageUrl = uri.toString()

            //유저의 UID
            contentDTO.uid?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.uid = auth?.currentUser?.uid

            //유저의 아이디
            contentDTO.userId?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.userId = auth?.currentUser?.email

            //게시물의 설명
            contentDTO.explain?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.explain = add_photo_edit_explain.text.toString()

            //게시물 업로드 시간
            contentDTO.timestamp?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.timestamp = System.currentTimeMillis()

            //게시물의 제목
            contentDTO.title?.let { firestore?.collection("images")?.document()?.set(it) }
            // contentDTO.title = add_item_title.text.toString()

            //게시물의 위치
            contentDTO.location?.let { firestore?.collection("images")?.document()?.set(it) }
            //contentDTO.location = add_item_location.text.toString()

//            //게시물의 카테고리
//            contentDTO.category = add_item_type.toString()

            firestore?.collection("images")?.document()?.set(contentDTO)

            setResult(RESULT_OK)

            finish()
        }

//            ?.addOnFailureListener {
//                progress_bar.visibility = View.GONE
//
//                Toast.makeText(this, getString(R.string.upload_fail),
//                    Toast.LENGTH_SHORT).show()
//            }
        }

//                taskSnapshot ->
//            progress_bar.visibility = View.GONE
//
//            Toast.makeText(this, getString(R.string.upload_success),
//                Toast.LENGTH_SHORT).show()

//            val uri = taskSnapshot.downloadUrl
//            //디비에 바인딩 할 위치 생성 및 컬렉션(테이블)에 데이터 집합 생성
//

//
//            setResult(Activity.RESULT_OK)
//            finish()
//        }
//            ?.addOnFailureListener {
//                progress_bar.visibility = View.GONE
//
//                Toast.makeText(this, getString(R.string.upload_fail),
//                    Toast.LENGTH_SHORT).show()
            }


