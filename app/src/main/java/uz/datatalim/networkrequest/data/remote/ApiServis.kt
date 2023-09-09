package uz.datatalim.networkrequest.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.datatalim.networkrequest.model.Post

interface ApiServis {

    @GET(value = "/posts")
    fun getAll():Call<ArrayList<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id:Int):Call<Post>

    @POST("/posts")
    fun insertPost(@Body post:Post):Call<Post>

    @PUT("/posts/{id}")
    fun putPost(@Path("id")id: Int,@Body post: Post):Call<Post>

    @DELETE("/posts/{id}")
    fun deletePost(@Path("id")id:Int):Call<Post>

}