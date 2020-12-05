package com.example.team.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team.ProfileAdapter
import com.example.team.R
import com.example.team.data.profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef: DatabaseReference = db.getReference("uid")
    var auth: FirebaseAuth? = null


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Ladapter = ProfileAdapter(requireContext(), profile.profile_list)
        recycler_post.adapter = Ladapter
        recycler_post.layoutManager = LinearLayoutManager(requireContext())
    }

}