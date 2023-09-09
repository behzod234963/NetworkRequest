package uz.datatalim.networkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.networkrequest.data.remote.ApiClient
import uz.datatalim.networkrequest.model.Post

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initView()

    }

    private fun initView() {

        val tvtitle:EditText=findViewById(R.id.tvTitle)
        val tvbody:EditText=findViewById(R.id.tvBody)
        val btnSave:Button=findViewById(R.id.btnsave)

        btnSave.setOnClickListener {

            val title=tvtitle.text.toString()
            val body=tvbody.text.toString()
            if (title.isEmpty()||body.isEmpty()){

                Toast.makeText(this, "bir narsa yozzzzzzz", Toast.LENGTH_SHORT).show()
                finish()

            }else{
                val post=Post(4,4,title,body)
                savePost(post)

            }

        }

    }

    fun savePost(post:Post){

        ApiClient.apiServis.insertPost(post).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if (response.isSuccessful){

                    Toast.makeText(this@AddActivity, "${response.body()}", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                Toast.makeText(this@AddActivity, "Tuvirlab yoz", Toast.LENGTH_SHORT).show()

            }


        })

    }

}