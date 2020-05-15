package com.example.kotlin1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_jadwal.balik
import kotlinx.android.synthetic.main.activity_jadwal.txt1
import kotlinx.android.synthetic.main.activity_jadwal.txt2
import org.json.JSONArray
import org.json.JSONObject

class jadwal : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        balik.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        getdariserver()

        btnsimpann.setOnClickListener{
            var data_ashar = data_asharr.text.toString()
            var data_dhuha = data_dhuhaa.text.toString()
            var data_dhuhur = data_dhuhurr.text.toString()
            var data_isya = data_isyaa.text.toString()
            var data_maghrib = data_maghribb.text.toString()
            var data_shubuh = data_shubuhh.text.toString()

            postkeserver(data_ashar, data_dhuha, data_dhuhur, data_isya, data_maghrib, data_shubuh)
        }
    }

    fun getdariserver() {

        AndroidNetworking.get("http://192.168.1.35/kotlinn1/jadwal_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                        txt1.setText(jsonObject.optString("shubuh"))
                        txt2.setText(jsonObject.optString("dhuhur"))
                        txt3.setText(jsonObject.optString("ashar"))
                        txt4.setText(jsonObject.optString("maghrib"))
                        txt5.setText(jsonObject.optString("isya"))
                        txt6.setText(jsonObject.optString("dhuha"))

                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data_ashar:String,data_dhuhur:String,data_dhuha:String,data_isya:String,data_maghrib:String,data_shubuh:String){
        AndroidNetworking.post("http://192.168.1.35/kotlinn1/proses_jadwal.php")
            .addBodyParameter("ashar", data_ashar)
            .addBodyParameter("dhuhur", data_dhuhur)
            .addBodyParameter("dhuha", data_dhuha)
            .addBodyParameter("isya", data_isya)
            .addBodyParameter("maghrib", data_maghrib)
            .addBodyParameter("shubuh", data_shubuh)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })

    }
}
