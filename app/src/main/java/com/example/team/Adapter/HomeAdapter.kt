package com.example.team.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.team.Fragment.DetailActivity
import com.example.team.Fragment.DetailFragment
import com.example.team.Fragment.model.ContentDTO
import com.example.team.R
import com.example.team.data.User
import kotlinx.android.synthetic.main.card_layout.view.*

class HomeAdapter(val context: Context, val cardList : ArrayList<User> ):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent, false)
        return HomeViewHolder(view)
    }
    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder?.bind(cardList[position], context)
        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView?.context, DetailActivity::class.java)
            intent.putExtra("postId", position)
            ContextCompat.startActivity(holder.itemView.context, intent, null)

        }

    }


    override fun getItemCount(): Int {
        return cardList.size
    }

    class HomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val cardImage=itemView?.card_item_image
        val cardId = itemId
        val cardTitle=itemView?.card_item_title
        val cardType=itemView?.card_item_type
        val cardLocation=itemView?.card_item_location
        fun bind(User: User, context:Context){
            /*if (User.imageUrl != "") {
                val resourceId = context.resources.getIdentifier(ContentDTO.imageUrl, "drawable", context.packageName)
                postImage?.setImageResource(resourceId)
            } else {
                postImage?.setImageResource(R.mipmap.ic_launcher)
            }*/

            cardTitle?.text=User.title
            cardType?.text=User.type
            //이미지
            cardLocation?.text=User.location
        }


    }

}

