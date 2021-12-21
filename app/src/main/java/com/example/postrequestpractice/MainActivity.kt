package com.example.postrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {

    var list = ArrayList<BooksItem>()
    lateinit var recyclerView: RecyclerView
    lateinit var rvAdapter: RVAdapter

    lateinit var nameEdit: EditText
    lateinit var locationEdit: EditText
    lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.RV)
        rvAdapter = RVAdapter(list)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getBooks()?.enqueue(object: Callback<Books>{
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                list.addAll(response.body()!!)

                rvAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Books>, t: Throwable) {
                Log.d("MAIN", "ISSUE")
            }

        })

        nameEdit = findViewById(R.id.editTextName)
        locationEdit = findViewById(R.id.editTextLocation)
        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener{

            var name = nameEdit.text.toString()
            var location = locationEdit.text.toString()
            var book = BooksItem(location, name, 0)

            apiInterface?.addBook(book)?.enqueue(object : Callback<BooksItem>{
                override fun onResponse(call: Call<BooksItem>, response: Response<BooksItem>) {
                    Toast.makeText(this@MainActivity, "Book Added", Toast.LENGTH_SHORT).show()
                    rvAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<BooksItem>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }



//    @POST("/books/")
//    fun addBook(@Body bookData: Book) {
//
//    }
//
//    fun addBook(){
//        apiInterface.addBook(Book("William Shakespeare", "Helmet")).enqueue(object: Callback<Book> {
//            override fun onResponse(call: Call<Book>, response: Response<Book>) {
//                Toast.makeText(this@MainActivity, "Book Added", Toast.LENGTH_SHORT).show()
//            }
//            override fun onFailure(call: Call<Book>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}

//data class Book(val author: String, val title: String) {
//
//}

