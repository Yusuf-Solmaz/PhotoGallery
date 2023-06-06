package com.yusuf.photogallery.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.photogallery.PhotoDao
import com.yusuf.photogallery.UploadImages
import com.yusuf.photogallery.databinding.RecyclerRowBinding


class PhotoAdapter (val photos: ArrayList<PhotoDao>): RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    class PhotoHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoHolder(binding)
    }


    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.binding.textView.text = photos[position].title
        holder.binding.imageView2.setImageBitmap(photos[position].image)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,UploadImages::class.java)
            intent.putExtra("info","old")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

}