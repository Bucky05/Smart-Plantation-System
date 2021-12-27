package com.example.smartagriculturesystem

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.smartagriculturesystem.R.color.white
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ProfileActivity : AppCompatActivity(), UIUpdaterInterface {
    private val JSON_URL = ""
    lateinit var mQueue :RequestQueue
    var Status:String = ""
    var dataUP:String =""
    var statusWater =1




    var mqttManager: MQTTmanager? = null
    var host:String ="broker.mqttdashboard.com"

    var topicHumidity:String = "humidity_status"
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mQueue = Volley.newRequestQueue(this)



        val intent = intent
        val name = intent.getStringExtra("plantName")
       // loadPlantData()
        plantNameTxt.text = name.toString()
       // connect(topicHumidity)

        updateInfo.setOnClickListener{
            jsonParse()
            Toast.makeText(this, "Requested",Toast.LENGTH_SHORT).show()
            //urlreq()
           // sendMessage()}
    }
        waterBtn.setOnClickListener{
            urlreq()
            if(statusWater ==1)
            {
                statusWater=0
                waterBtn.setTextColor(R.color.white)
                Toast.makeText(this,"Watering Plant",Toast.LENGTH_LONG).show()

            }
            else{
                statusWater=1
                Toast.makeText(this,"Watering Stopped",Toast.LENGTH_LONG).show()
                waterBtn.setTextColor(R.color.dark_green)
            }
        }}

    private fun urlreq() {
        var queue: RequestQueue =  Volley.newRequestQueue(this)
        var base = ""
        var api_key = "api_key"
        var field_1 = "field"
        var dataUP = statusWater.toString()

        val builtURI: Uri? =
            Uri.parse(base).buildUpon().appendQueryParameter(api_key,"CRC2RMPLTDWGPZSU").appendQueryParameter(field_1, dataUP).build()
        //var url  = ""
        var url: String = builtURI.toString()
        var stringRequest =  StringRequest(Request.Method.GET, url,
            { response ->
                if(response.equals("0"))
                {

                }
                else
                {

                }


            }, {
                //commment


            }
        )
        queue.add(stringRequest)
    }

    fun jsonParse()
    {
        //Toast.makeText(this,"Invoked",Toast.LENGTH_SHORT).show()
        val urlfeed = "https://api.thingspeak.com/channels/1616618/feeds.json?api_key=CRC2RMPLTDWGPZSU&results=2"
        var request:JsonObjectRequest = JsonObjectRequest(Request.Method.GET,urlfeed,null,
            { response ->
                try {

                    //Toast.makeText(this,"Inside try",Toast.LENGTH_SHORT).show()
                    val jsonArray :JSONArray = response.getJSONArray("feeds")
                   val feeds :JSONObject = jsonArray.getJSONObject(0)
                    Status = feeds.getString("field1")

                    humiditytxt.text = "$Status%"
                    Status = feeds.getString("field2")

                    temperaturetxt.text = "$Status%"

                    Status = feeds.getString("field3")
                    Status =Status.toString()
                    Status.trim()
                    Log.i("Msg is",Status)
                    Status = Status.subSequence(0,6).toString()
                    moisturetxt.text = "$Status%"

                }
                catch(e: JSONException)
                {
                    e.printStackTrace()
                }
            }, Response.ErrorListener{ }

        )
        mQueue.add(request)}



//    private fun loadPlantData() {
//       var stringRequest: StringRequest? = StringRequest(Request.Method.GET,JSON_URL)
//    }

//    override fun resetUIWithConnection(status: Boolean) {
//
//    }
//
//    override fun updateStatusViewWith(status: String) {
//        TODO("Not yet implemented")
//    }

//    override fun update(message: String) {
//
//        var newText = "$message%"
//        //var newText = text.toString() + "\n" + message +  "\n"
//        humiditytxt.text = newText
//
//    }

//    private fun connect(topic:String){
//
//
//            Log.e("mqtt","INsdie if condition")
//
//            //topic = "SmartPlantationSystem"
//            var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"anirudhrathore","Aguero@2020")
//            mqttManager = MQTTmanager(connectionParams,applicationContext,this)
//
//            mqttManager?.connect()
//
//
//
//    }

    fun sendMessage(){

        mqttManager?.publish("Water")

        //messageField.setText("")
    }

    override fun resetUIWithConnection(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun updateStatusViewWith(status: String) {
        TODO("Not yet implemented")
    }

    override fun update(message: String) {
        TODO("Not yet implemented")
    }

//    fun disconnect(view: View) {
//        var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"anirudhrathore","Aguero@2020")
//        mqttManager = MQTTmanager(connectionParams,applicationContext,this)
//        mqttManager?.disconnect()
//    }

//    fun unsub(view: View) {
//
//        var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"anirudhrathore","Aguero@2020")
//        mqttManager = MQTTmanager(connectionParams,applicationContext,this)
//
//        mqttManager?.unsubscribe(topic)
//    }
}





