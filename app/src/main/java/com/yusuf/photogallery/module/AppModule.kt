package com.yusuf.photogallery.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusuf.photogallery.R
import com.yusuf.photogallery.api.RetrofitApi
import com.yusuf.photogallery.repo.ImageRepository
import com.yusuf.photogallery.repo.ImageRepositoryInterface
import com.yusuf.photogallery.roomdb.ImageDao
import com.yusuf.photogallery.roomdb.ImagesDatabase
import com.yusuf.photogallery.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,ImagesDatabase::class.java,"PhotoGalleryDB"
    ).build()


    @Singleton
    @Provides
    fun injectDao(database: ImagesDatabase) = database.imageDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitApi {
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide( @ApplicationContext context: Context ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )


    @Singleton
    @Provides
    fun injectNormalRepo(dao: ImageDao, api: RetrofitApi) = ImageRepository(dao,api) as ImageRepositoryInterface

}