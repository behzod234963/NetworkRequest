
package uz.datatalim.networkrequest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Response
import uz.datatalim.networkrequest.Adapters.PostAdapter
import uz.datatalim.networkrequest.data.remote.ApiClient
import uz.datatalim.networkrequest.model.Post
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PostAdapter
    lateinit var list: ArrayList<Post>
    lateinit var process: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {

        val rvPost:RecyclerView=findViewById(R.id.rvPost)
        val fabADD:FloatingActionButton=findViewById(R.id.fabCreate)
        process=findViewById(R.id.pbProgress)
        adapter= PostAdapter()
        rvPost.adapter=adapter
        process=findViewById(R.id.pbProgress)

        loadList()
        adapter.itemClick={position->

            val dialog= AlertDialog.Builder(this)
            dialog.setMessage("What you want to do")
            dialog.setTitle("Bu reklama")
            dialog.setIcon(R.drawable.ic_launcher_foreground)
            dialog.setCancelable( false)
            dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    val intent=Intent(this@MainActivity,EditActivity::class.java)
                    intent.putExtra("id",list[position].id)
                    startActivity(intent)

                }


            })
            dialog.setNegativeButton("Delete",object : DialogInterface.OnClickListener{

                override fun onClick(dialog: DialogInterface?, which: Int) {

                    deletePost(list[position].id)
                    loadList()
                    Toast.makeText(this@MainActivity, "Ochdi", Toast.LENGTH_SHORT).show()

                }


            })

            dialog.show()

        }

        fabADD.setOnClickListener {

            val intent= Intent(this,AddActivity::class.java)
            startActivity(intent)

        }

    }

    private fun deletePost(id: Int) {

        showProgress()

        ApiClient.apiServis.deletePost(id).enqueue(object :retrofit2.Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                hideProgress()

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

                hideProgress()

            }


        })

    }

    private fun loadList() {

        showProgress()

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

                hideProgress()

            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                hideProgress()
            }
        })

    }
    fun showProgress(){

        process.visibility=View.VISIBLE

    }
    fun hideProgress(){

        process.visibility=View.GONE

    }
}