package com.example.team


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team.Fragment.model.ContentDTO
import kotlinx.android.synthetic.main.profil.view.*
class ProfileAdapter(val context: Context, val postList : ArrayList<ContentDTO>):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

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
        fun bind(ContentDTO:ContentDTO,context:Context){
            if (ContentDTO.imageUrl != "") {
                val resourceId = context.resources.getIdentifier(ContentDTO.imageUrl, "drawable", context.packageName)
                postImage?.setImageResource(resourceId)
            } else {
                postImage?.setImageResource(R.mipmap.ic_launcher)
            }

            postTitle?.text=ContentDTO.title
            postType?.text=ContentDTO.type
            postTime?.text=ContentDTO.timestamp.toString()
            postContent?.text=ContentDTO.explain
        }

    }

}






