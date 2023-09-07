package uz.datatalim.networkrequest.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val isTester=true
    private val SERVER_DEV="https://jsonplaceholder.typicode.com"
    private val SERVER_PRODUCTION="https://jsonplaceholder.typicode.com"

    private val retrofit=Retrofit.Builder().baseUrl(baseUrl()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun baseUrl(): String{

        return if (isTester){

            SERVER_DEV

        }else{

            SERVER_PRODUCTION

        }

    }

    val apiServis= retrofit.create(ApiServis::class.java)


}