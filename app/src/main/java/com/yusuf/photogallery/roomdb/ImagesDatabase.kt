package com.yusuf.photogallery.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Image::class], version = 1)
abstract class ImagesDatabase : RoomDatabase() {

    abstract fun imageDao() : ImageDao

}