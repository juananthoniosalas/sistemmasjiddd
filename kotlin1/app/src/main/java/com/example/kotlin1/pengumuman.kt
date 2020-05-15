package com.example.kotlin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_marquee.*
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class pengumuman : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)
        balik.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

        getdariserver()
        btnsimpannnn.setOnClickListener{
            var data_judulpengumuman = data_judul.text.toString()
            var data_isipengumuman = data_isi.text.toString()


            postkeserver(data_judulpengumuman, data_isipengumuman)
        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.1.35/kotlinn1/pengumuman_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))

                        txt1.setText(jsonObject.optString("judul_pengumuman"))
                        txt2.setText(jsonObject.optString("isi_pengumuman"))

                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data_judulpengumuman:String,data_isipengumuman:String){
        AndroidNetworking.post("http://192.168.1.35/kotlinn1/proses_pengumuman.php")
            .addBodyParameter("judul_pengumuman", data_judulpengumuman)
            .addBodyParameter("isi_pengumuman", data_isipengumuman)
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
