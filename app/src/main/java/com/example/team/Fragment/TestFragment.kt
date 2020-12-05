package com.example.team.Fragment

import android.content.Context
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
import com.example.team.data.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.profil.view.*

class ProfileAdapter(val context: Context, val postList : ArrayList<User>):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.profil,parent, false)
        return ProfileViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProfileAdapter.ProfileViewHolder, position: Int) {
        holder?.bind(postList[position], context)



    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class ProfileViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val postImage=itemView?.product
        val postTitle=itemView?.post_title
        val postType=itemView?.post_type
        val postTime=itemView?.post_time
        val postContent=itemView?.post_content
        fun bind(User: User, context: Context){
            /*if (User.imageUrl != "") {
                val resourceId = context.resources.getIdentifier(ContentDTO.imageUrl, "drawable", context.packageName)
                postImage?.setImageResource(resourceId)
            } else {
                postImage?.setImageResource(R.mipmap.ic_launcher)
            }*/

            postTitle?.text=User.title
            postType?.text=User.type
            postTime?.text=User.timestamp.toString()
            postContent?.text=User.explain
        }

    }

}






class TestFragment :Fragment(){
    var firestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_detail,container,false)
        firestore = FirebaseFirestore.getInstance()

        view.detailviewfragment_recyclerview.adapter = DetailViewRecyclerViewAdapter()
        view.detailviewfragment_recyclerview.layoutManager = LinearLayoutManager(activity)
        return view
    }
    inner class DetailViewRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var contentDTOs: ArrayList<ContentDTO> = arrayListOf()
        var contentUidList: ArrayList<String> = arrayListOf()

        init {

            firestore?.collection("images")?.orderBy("timestamp")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                contentDTOs.clear()
                contentUidList.clear()
                for(snapshot in querySnapshot!!.documents){
                    var item = snapshot.toObject(ContentDTO::class.java)
                    contentDTOs.add(item!!)
                    contentUidList.add(snapshot.id)
                }
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent,false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewholder = (holder as CustomViewHolder).itemView

            // User Id
            viewholder.detailviewitem_profile_uid.text = contentDTOs!![position].userId

            // Image
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(viewholder.detailviewitem_imageview_content)

            // Explain
            viewholder.detailviewitem_explain.text = contentDTOs!![position].explain

            // ProfileImage
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(viewholder.detailviewitem_profile_image)

            // title
            viewholder.detailviewitem_explain_title.text = contentDTOs!![position].title

            // category
            viewholder.detailviewitem_cate.text = contentDTOs!![position].category.toString()

            // location
            viewholder.detailviewitem_explain_loc.text = contentDTOs!![position].location
        }

    }
}