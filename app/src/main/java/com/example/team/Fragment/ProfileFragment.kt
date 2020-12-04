package com.example.team.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team.Fragment.model.ContentDTO
import com.example.team.ProfileAdapter
import com.example.team.R
import com.example.team.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef : DatabaseReference = db.getReference()
    val postList=arrayListOf<ContentDTO>(
        ContentDTO("지갑을 잃어버렸습니다..", "wallet", null, null, 20201115,"지갑분실","분실"),
        ContentDTO("귤 무료나눔합니다","gul",null,null,20201022,"귤 나눔","무료 나눔")
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





            return inflater.inflate(R.layout.fragment_profile, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Ladapter = ProfileAdapter(requireContext(),postList)
        recycler_post.adapter = Ladapter
        recycler_post.layoutManager = LinearLayoutManager(requireContext())
    }

}