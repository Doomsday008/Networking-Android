package com.example.networking_android;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var data: List<User> = ArrayList()
    var onItemClick:((login: String)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size


    fun swapData(data: List<User>){
        this.data = data
        notifyDataSetChanged()
    }


   inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // here comes all the views / variables from view.findViewById()
        // Example: val textView = view.findViewById(R.id.textView1)
        fun bindView(item: User) {
            with(itemView) {
                textView.text = item.name
                textView2.text = item.login
                Picasso.get().load(item.avatarUrl).into(imageView)
                setOnClickListener {
                    onItemClick?.invoke(item.login!!)
                }
            }
        }
    }

}