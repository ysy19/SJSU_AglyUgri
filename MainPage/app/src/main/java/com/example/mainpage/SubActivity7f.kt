package com.example.mainpage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainpage.databinding.ActivitySub7fBinding


data class Product(val name:String, val image: ImageView)
private val fruitname = arrayListOf<String>("apple", "banana", "cherry", "grape", "grapefruit", "orange", "peach", "pineapple")
private val fruitimage = arrayListOf<Int>(R.drawable.f_apple, R.drawable.f_banana, R.drawable.f_cherry, R.drawable.f_grape,
    R.drawable.f_grapefruit, R.drawable.f_orange, R.drawable.f_peach, R.drawable.f_pineapple)

private val vegetname = arrayListOf<String>("avocado", "beet", "bellpepper", "broccoli", "cabbage", "carrot", "onion", "tomato")
private val vegetimage = arrayListOf<Int>(R.drawable.v_avocado, R.drawable.v_beet, R.drawable.v_bellpepper, R.drawable.v_broccoli,
    R.drawable.v_cabbage, R.drawable.v_carrot, R.drawable.v_onion, R.drawable.v_tomato)

var chkList = mutableMapOf<String, Boolean>()
var cnt = 11         //현재 선택된 상품 개수
var max:Int = 0

class SubActivity7f : AppCompatActivity() {

    private val binding2 by lazy { ActivitySub7fBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        if(intent.getStringExtra("size") == "small") max = 12
        else max = 16


        super.onCreate(savedInstanceState)
        setContentView(binding2.root)

        binding2.prev25.setOnClickListener {
            finish()
        }
        binding2.next25.setOnClickListener {
            if (cnt < 10) {
                Toast.makeText(
                    applicationContext,
                    "The minimum number of selections is 10.",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val thirdIntent = Intent(applicationContext, SubActivity7s::class.java)
                thirdIntent.putExtra("size", intent.getStringExtra("size"))
                thirdIntent.putExtra("products", getList(chkList))
                startActivity(thirdIntent)
            }
        }
        with(binding2) {
            vegetBtn5.setOnClickListener {
                changeColor(vegetBtn5, fruitBtn5)
                val productList = setData(true)
                val customAdapter = CustomAdapter(this, productList)
                recyclerView.adapter = customAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@SubActivity7f)
            }
            fruitBtn5.setOnClickListener {
                changeColor(fruitBtn5, vegetBtn5)
                val productList = setData(false)
                val customAdapter = CustomAdapter(this, productList)
                recyclerView.adapter = customAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@SubActivity7f)
            }
        }
    }


    private fun changeColor(v1:Button, v2:Button) {
        v1.setBackgroundColor(Color.parseColor("#C5E1A5"))
        v2.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
    private fun getList(c:MutableMap<String, Boolean>) : ArrayList<String> {
        val list = ArrayList<String>()
        for(i in c) {
            if(i.value) list.add(i.key)
        }
        return list
    }
    private fun setData(c:Boolean) :ArrayList<Product>{
        val dtList = arrayListOf<Product>()
        for(i in 0..7) {
            var name:String
            var image:ImageView
            if(c) { //vegetable
                name = vegetname[i]
                image = findViewById<ImageView>(vegetimage[i])
            }
            else {
                name = fruitname[i]
                image = findViewById<ImageView>(fruitimage[i])
            }
            dtList.add(Product(name, image))
        }
        return dtList
    }
}
class CustomAdapter(val c: ActivitySub7fBinding, val productList:ArrayList<Product>) : RecyclerView.Adapter<CustomAdapter.ProductViewHolder>() {
    class ProductViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        var check: CheckBox
        var image:ImageView

        init {
            check = v.findViewById(R.id.name5)
            image = v.findViewById(R.id.image5)

            check.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked) {
                    if(cnt < max) {
                        chkList[check.text.toString()] = true
                    }
                    else {
                        //Toast.makeText(this, "Up to ${max}.", Toast.LENGTH_SHORT).show()
                        check.isChecked = false
                    }
                }
                else {
                    chkList[check.text.toString()] = false
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_grider, parent, false)
        return ProductViewHolder(v)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val newList = productList[position]
        holder.check.text = newList.name
        holder.image = newList.image
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}
