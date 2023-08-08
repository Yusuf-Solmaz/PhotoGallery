package com.yusuf.photogallery.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.roomdb.Image
import javax.inject.Inject

class FeedRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<FeedRecyclerAdapter.FeedRecyclerHolder>()


 {

   class FeedRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

   }

     private val diffUtil = object : DiffUtil.ItemCallback<Image>(){
         override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
             return oldItem == newItem
         }

         override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
             return oldItem == newItem
         }

     }

     private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

     var images: List<Image>
         get() = recyclerListDiffer.currentList
         set(value) = recyclerListDiffer.submitList(value)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedRecyclerHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row,parent,false)
         return FeedRecyclerHolder(view)
     }

     override fun getItemCount(): Int {
         return images.size
     }

     override fun onBindViewHolder(holder: FeedRecyclerHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.image)
        val imageName = holder.itemView.findViewById<TextView>(R.id.imageName)
        val imagePlace = holder.itemView.findViewById<TextView>(R.id.imagePlace)
        val imageDate = holder.itemView.findViewById<TextView>(R.id.imageTime)


         val image = images[position]
         holder.itemView.apply {
             imageName.text=image.title
             imagePlace.text=image.place
             imageDate.text = image.date
             glide.load(image.imageUrl).into(imageView)
         }
     }
 }