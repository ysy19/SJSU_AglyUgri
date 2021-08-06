package com.example.mainpage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.mainpage.databinding.ActivitySub7sBinding


data class Week(var textbutton: TextView, val day: String, var date: String?, var check:Boolean)
data class Credit(var num:String, var validity:String, var cvc:String, var password:String)
data class Submission(var option:String, var productList: ArrayList<String>?,
                      var date: String, var destination: String, var credit: Credit?)

class SubActivity7s : AppCompatActivity() {
    private val binding3 by lazy { ActivitySub7sBinding.inflate(layoutInflater) }

    private var checked = false
    private var weeklist = arrayListOf<Int>(0, 0, 0, 0, 0, 0, 0)
    private var deliveryday: String? = null
    private var destination: String? = null
    private var credit: Credit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding3.root)

        setData()
//1. 페이지전환
        with(binding3) {
            //1-1. 이전페이지
            prev35.setOnClickListener {
                finish()
            }
            //1-2. 다음페이지
            cmpl35.setOnClickListener {
                val builder = AlertDialog.Builder(this@SubActivity7s)
                builder.setTitle("Warning")
                    .setMessage("Do you want to submit?\nAfter submission, you can't modify.\nPlease check it again.")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener() { _, _ ->
                        Toast.makeText(this@SubActivity7s, "Ordered was submitted.", Toast.LENGTH_SHORT).show()
                        val nextIntent = Intent(this@SubActivity7s, MainActivity3::class.java)
                        startActivity(nextIntent)
                        /*/데이터 저장
                        var submission = Submission( intent.getStringExtra("size").toString(),
                            intent.getStringArrayListExtra("products"), deliveryday.toString(),
                            destination.toString(), credit)
                        //데이터 전송 및 메인페이지로 이동*/
                    })
                    .setNegativeButton("No", null)
                    .show()
            }
            sunBtn5.setOnClickListener {
                if (!checked) {
                    sunBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[0]++
                    checked = true
                    textView5.text = "your shippding date : ${sunDate5.text}"
                }
                else if(weeklist[0] == 0) { }
                else {
                    sunBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[0]--
                    checked = false
                }
            }
            monBtn5.setOnClickListener{
                if (!checked) {
                    monBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[1]++
                    checked = true
                    textView5.text = "your shippding date : ${monDate5.text}"
                }
                else if(weeklist[1] == 0) { }
                else {
                    monBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[1]--
                    checked = false
                }
            }
            tueBtn5.setOnClickListener{
                if (!checked) {
                    tueBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[2]++
                    checked = true
                    textView5.text = "your shippding date : ${tueDate5.text}"
                }
                else if(weeklist[2] == 0) { }
                else {
                    tueBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[2]--
                    checked = false
                }
            }
            wedBtn5.setOnClickListener{
                if (!checked) {
                    wedBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[3]++
                    checked = true
                    textView5.text = "your shippding date : ${wedDate5.text}"
                }
                else if(weeklist[3] == 0) { }
                else {
                    wedBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[3]--
                    checked = false
                }
            }
            thuBtn5.setOnClickListener{
                if (!checked) {
                    thuBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[4]++
                    checked = true
                    textView5.text = "your shippding date : ${thuDate5.text}"
                }
                else if(weeklist[4] == 0) { }
                else {
                    thuBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[4]--
                    checked = false
                }
            }
            friBtn5.setOnClickListener{
                if (!checked) {
                    friBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[5]++
                    checked = true
                    textView5.text = "your shippding date : ${friDate5.text}"
                }
                else if(weeklist[5] == 0) { }
                else {
                    friBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[5]--
                    checked = false
                }
            }
            satBtn5.setOnClickListener{
                if (!checked) {
                    satBtn5.setBackgroundResource(R.drawable.box_roundg)
                    weeklist[6]++
                    checked = true
                    textView5.text = "your shippding date : ${satDate5.text}"
                }
                else if(weeklist[6] == 0) { }
                else {
                    satBtn5.setBackgroundResource(R.drawable.box_round)
                    weeklist[6]--
                    checked = false
                }
            }
        }
    }
    private fun setData() {
        with(binding3) {
            sunDate5.text = "08/15"
            monDate5.text = "08/16"
            tueDate5.text = "08/17"
            wedDate5.text = "08/18"
            thuDate5.text = "08/19"
            friDate5.text = "08/13"
            satDate5.text = "08/14"
        }
    }
}


