package com.yusuf.photogallery.api

import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuerry: String,
        @Query("key") apiKey: String = API_KEY
    ) : Response<ImageResponse>

}