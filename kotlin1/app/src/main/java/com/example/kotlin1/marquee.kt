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
import com.example.kotlin1.marquee
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_jadwal.balik
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_marquee.*
import org.json.JSONArray
import org.json.JSONObject

class marquee : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)
        balik.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }


        getdariserver()
        btnsimpannn.setOnClickListener{
            var data_isimarquee = data_marquee.text.toString()


            postkeserver(data_isimarquee)
        }

    }





    fun getdariserver(){

        AndroidNetworking.get("http://192.168.1.35/kotlinn1/marquee_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("isi_marquee"))

                        marqueee.setText(jsonObject.optString("isi_marquee"))

                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }
fun postkeserver(data_isimarquee:String){
    AndroidNetworking.post("http://192.168.1.35/kotlinn1/proses_marquee.php")
        .addBodyParameter("isi_marquee", data_isimarquee)

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
