package uz.datatalim.networkrequest.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import uz.datatalim.networkrequest.model.Post

interface ApiServis {

    @GET(value = "/posts")
    fun getAll():Call<ArrayList<Post>>

}