package com.example.team.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team.ProfileAdapter
import com.example.team.R
import com.example.team.User
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    val postList=arrayListOf<User>(
        User("wallet", "분실", "10월 22일", "지갑을 잃어버렸습니다"),
        User("gul","나눔" ,"11월 23일","귤이 너무 많아서 나누고 싶어요...")
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