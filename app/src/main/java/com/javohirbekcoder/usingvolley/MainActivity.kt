package com.javohirbekcoder.usingvolley

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.javohirbekcoder.usingvolley.adapter.MainRecyclerAdapter
import com.javohirbekcoder.usingvolley.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val productList: MutableList<ProductModel> = ArrayList()

    private var requestQueue: RequestQueue? = null

    private val endpoint = "products"
    private val baseUrl = "https://dummyjson.com/$endpoint"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        parseJson()
    }

    private fun parseJson() {

        productList.clear()

        val request = JsonObjectRequest(Request.Method.GET, baseUrl, null,
            { response ->
                val productsArray = response.getJSONArray("products")

                for (i in 0 until productsArray.length()) {
                    val productObject = productsArray.getJSONObject(i)
                    val id = productObject.getInt("id")
                    val title = productObject.getString("title")
                    val description = productObject.getString("description")
                    val price = productObject.getDouble("price")
                    val discountPercentage = productObject.getDouble("discountPercentage")
                    val rating = productObject.getDouble("rating")
                    val stock = productObject.getInt("stock")
                    val brand = productObject.getString("brand")
                    val category = productObject.getString("category")
                    val thumbnail = productObject.getString("thumbnail")
                    val imagesArray = productObject.getJSONArray("images")
                    val images = mutableListOf<String>()
                    for (j in 0 until imagesArray.length()) {
                        val imageUrl = imagesArray.getString(j)
                        images.add(imageUrl)
                    }

                    productList.add(
                        ProductModel(
                            brand,
                            category,
                            description,
                            discountPercentage,
                            id,
                            images,
                            price,
                            rating,
                            stock,
                            thumbnail,
                            title
                        )
                    )
                }
                binding.mainRecycler.visibility = View.VISIBLE
                binding.progressbar.visibility = View.GONE

                val adapter = MainRecyclerAdapter(this, productList)
                binding.mainRecycler.adapter = adapter
            },
            { error ->
                error.printStackTrace()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

        /*      val request = JsonObjectRequest(Request.Method.GET, baseUrl, null, Response.Listener<JSONObject> {
                      response ->
                  // Handle the JSON response
                  val productsArray = response.getJSONArray("products")

                  // Iterate over the products array and extract the data
                  for (i in 0 until productsArray.length()) {
                      val productObject = productsArray.getJSONObject(i)
                      val id = productObject.getInt("id")
                      val title = productObject.getString("title")
                      val description = productObject.getString("description")
                      val price = productObject.getDouble("price")
                      val discountPercentage = productObject.getDouble("discountPercentage")
                      val rating = productObject.getDouble("rating")
                      val stock = productObject.getInt("stock")
                      val brand = productObject.getString("brand")
                      val category = productObject.getString("category")
                      val thumbnail = productObject.getString("thumbnail")
                      val imagesArray = productObject.getJSONArray("images")
                      val images = mutableListOf<String>()
                      for (j in 0 until imagesArray.length()) {
                          val imageUrl = imagesArray.getString(j)
                          images.add(imageUrl)
                      }

                  }, Response.ErrorListener { error ->
                  error.printStackTrace()
              })
              }*/
    }
}