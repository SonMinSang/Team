package com.example.team.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.team.Fragment.model.ContentDTO
import com.example.team.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_detail.view.*
import java.util.ArrayList

class DetailViewFragment : Fragment() {
    var firestore : FirebaseFirestore? =null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_detail, container, false)
        firestore = FirebaseFirestore.getInstance()

        view.recyclerview_main.adapter = DetailViewRecyclerViewAdapter()
        view.recyclerview_main.layoutManager = LinearLayoutManager(activity)
        return view
    }
    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val contentDTOs: ArrayList<ContentDTO> = arrayListOf()
        val contentUidList: ArrayList<String> = arrayListOf()
        init{
            firestore?.collection("images")?.orderBy("timestamp")?.addSnapshotListener{querySnapshot, firebaseFirestoreException ->
                contentDTOs.clear()
                contentUidList.clear()
                if (querySnapshot != null) {
                    for(snapshot in querySnapshot.documents){
                        var item = snapshot.toObject(ContentDTO::class.java)
                        contentDTOs.add(item!!)
                        contentUidList.add(snapshot.id)
                    }
                }
                notifyDataSetChanged()
            }
        }
        override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(p0.context).inflate(R.layout.item_detail,p0,false)
            return  CustomViewHolder(view)
        }

        inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

            var viewholder = (p0 as CustomViewHolder).itemView

            //userID mapping
            viewholder.detail_item_userid.text = contentDTOs!![p1].userId

            //location
            viewholder.detail_item_location.text = contentDTOs!![p1].location.toString()

            //image
            Glide.with(p0.itemView.context).load(contentDTOs!![p1].imageUrl).into(viewholder.detail_item_image)

            //catagory
            viewholder.detail_item_type.text = contentDTOs!![p1].category

            //title
            viewholder.detail_item_title.text = contentDTOs!![p1].title

            //text
            viewholder.detail_item_text.text = contentDTOs!![p1].explain

            //profile image
            Glide.with(p0.itemView.context).load(contentDTOs!![p1].imageUrl).into(viewholder.detailviewitem_profile_image)

        }

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

    }

}