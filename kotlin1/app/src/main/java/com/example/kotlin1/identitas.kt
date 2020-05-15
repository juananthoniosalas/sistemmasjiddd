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
import com.example.kotlin1.identitas
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_jadwal.balik
import kotlinx.android.synthetic.main.activity_jadwal.txt1
import kotlinx.android.synthetic.main.activity_jadwal.txt2
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class identitas : AppCompatActivity() {

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        balik.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

        getdariserver()

        btnsimpan.setOnClickListener{
            var data_namamasjid = data_nama.text.toString()
            var data_identitasmasjid = data_identitas.text.toString()

            postkeserver(data_namamasjid, data_identitasmasjid)
        }

    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.1.35/kotlinn1/identitas_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_masjid"))

                        txt1.setText(jsonObject.optString("nama_masjid"))
                        txt2.setText(jsonObject.optString("alamat_masjid"))

                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }


    fun postkeserver(data_namamasjid:String,data_identitasmasjid:String){
    AndroidNetworking.post("http://192.168.1.35/kotlinn1/proses_identitas.php")
        .addBodyParameter("nama_masjid", data_namamasjid)
        .addBodyParameter("alamat_masjid", data_identitasmasjid)
        .setPriority(Priority.MEDIUM)
        .build()
        .getAsJSONArray(object : JSONArrayRequestListener{
            override fun onResponse(response: JSONArray?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(anError: ANError?) {
                Log.i("_err", anError.toString())
            }
        })

    }

}
