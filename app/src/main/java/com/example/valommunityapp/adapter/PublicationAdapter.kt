package com.example.valommunityapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.valommunityapp.Publication
import com.example.valommunityapp.R
import com.google.android.material.imageview.ShapeableImageView

class PublicationAdapter(private val newsList: ArrayList<Publication>) : RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_publication, parent, false)
        return PublicationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.namePublication.text = currentItem.name
        holder.imagePublication.text = currentItem.photo
        holder.descriptionPublication.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    class PublicationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val namePublication : TextView = itemView.findViewById(R.id.namePublication)
        val imagePublication : TextView = itemView.findViewById(R.id.urlImg)
        val descriptionPublication : TextView = itemView.findViewById(R.id.descriptionPublication)
    }
}