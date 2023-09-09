package uz.datatalim.networkrequest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.datatalim.networkrequest.R
import uz.datatalim.networkrequest.model.Post

class PostAdapter :RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    val posts=ArrayList<Post>()
    fun submitList(list:ArrayList<Post>){

        posts.clear()
        posts.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val list=posts[position]

        holder.apply {

            id.text=list.id.toString()
            title.text=list.title
            body.text=list.body

        }

    }

    class PostViewHolder(view:View):RecyclerView.ViewHolder(view){

        val id:TextView=view.findViewById(R.id.tvId)
        val title:TextView=view.findViewById(R.id.tvTitle)
        val body:TextView=view.findViewById(R.id.tvBody)

    }

}