package uz.datatalim.networkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.networkrequest.data.remote.ApiClient
import uz.datatalim.networkrequest.model.Post

class EditActivity : AppCompatActivity() {

    lateinit var progress:ProgressBar
    lateinit var list:Post
    lateinit var tvtitle:TextView
    lateinit var tvbody:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        initView()

    }

    private fun initView() {

        var id =0
        id=intent.getIntExtra("id",1)
        progress=findViewById(R.id.pbProgress)
        tvtitle=findViewById(R.id.tvTitle)
        tvbody=findViewById(R.id.tvBody)
        val btnUpdate:Button=findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener {

            val newtitle=tvtitle.text.toString()
            val newBody=tvbody.text.toString()
            list.title=newtitle
            list.body=newBody

            updatePost(list)

        }

        loadPost()

    }

    private fun updatePost(post: Post) {

        showProgress()
        ApiClient.apiServis.putPost(list.id,post).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                hideProgress()
                if (response.isSuccessful){

                    Toast.makeText(this@EditActivity, "updated", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                hideProgress()

            }
        })

    }

    fun showProgress(){

        progress.visibility= View.VISIBLE

    }
    fun hideProgress(){

        progress.visibility= View.GONE

    }
    private fun loadPost() {

        showProgress()
        ApiClient.apiServis.getPostById(id = 1).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if (response.isSuccessful){

                    list=response.body()!!
                    tvtitle.setText(list.title)
                    tvbody.setText(list.body)


                }
                hideProgress()

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                hideProgress()
            }


        })

    }


}