
package uz.datatalim.networkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import uz.datatalim.networkrequest.Adapters.PostAdapter
import uz.datatalim.networkrequest.data.remote.ApiClient
import uz.datatalim.networkrequest.model.Post
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PostAdapter
    lateinit var list: ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {

        loadList()
        val rvPost:RecyclerView=findViewById(R.id.rvPost)
        adapter= PostAdapter()
        rvPost.adapter=adapter

    }

    private fun loadList() {

        ApiClient.apiServis.getAll().enqueue(object :retrofit2.Callback<ArrayList<Post>>{
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {

                if (response.isSuccessful){

                    val alist=response.body()
                    if (alist != null) {
                        list=alist
                    }
                    adapter.submitList(list)

                }

            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}