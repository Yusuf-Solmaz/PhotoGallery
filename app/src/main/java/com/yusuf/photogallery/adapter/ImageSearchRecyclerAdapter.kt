package com.yusuf.photogallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.roomdb.Image
import javax.inject.Inject

class ImageSearchRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ImageSearchRecyclerAdapter.ImageSearchHolder>(){

    class ImageSearchHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    private var onItemClickListener : ((String) -> Unit) ?= null

    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var imagesUrl: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.search_image_row,parent,false)
       return ImageSearchHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesUrl.size
    }

    fun setOnItemClickListener(listener : (String) -> Unit) {
        onItemClickListener=listener
    }

    override fun onBindViewHolder(holder: ImageSearchHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.searchImageView)
        val url = imagesUrl[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)
            onItemClickListener?.let {
                it(url)
            }
        }
    }
}