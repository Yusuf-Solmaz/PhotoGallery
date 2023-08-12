package com.yusuf.photogallery.dependencyInjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.apps.common.testing.accessibility.framework.utils.contrast.Image
import com.yusuf.photogallery.roomdb.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context,ImagesDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}