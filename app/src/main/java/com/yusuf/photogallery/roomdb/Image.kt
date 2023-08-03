package com.yusuf.photogallery.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    var title: String,
    var place:String,
    var date:Int,
    var imageUrl:String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? =null
    )