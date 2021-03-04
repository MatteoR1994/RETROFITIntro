package com.example.retrofitintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var bottone: Button
    lateinit var risultato: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottone = findViewById(R.id.button)
        risultato = findViewById(R.id.textView)

        bottone.setOnClickListener {
            estraiCorsi()
        }
    }

    internal fun estraiCorsi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CourseService::class.java)
        val call = service.getCourseById(1)
        Log.d("RETROAPPINTRO","Chiamata funzione selezione corsi")
        call.enqueue(object : Callback<CourseSerializedName> {
            override fun onResponse(call: Call<CourseSerializedName>, response: Response<CourseSerializedName>) {
                Log.d("RETROAPPINTRO", "Risposta chiamata HTTP")
                if (response.code() == 200) {
                    val courseResponse = response.body()!!
                    risultato.text = "ID: " + courseResponse.courseId.toString() + "\n" +
                                     "TITOLO: " + courseResponse.courseTitle + "\n" +
                                     "N° ORE: " + courseResponse.courseNumHours.toString() + "\n" +
                                     "DESCR: " + courseResponse.courseDescription + "\n" +
                                     "PREZZO: " + courseResponse.courseCost.toString()
                } else {
                    risultato.text = response.code().toString()
                    Log.d("RETROAPPINTRO", response.code().toString())
                }
            }

            override fun onFailure(call: Call<CourseSerializedName>, t: Throwable) {
                    risultato.text = t.message
                Log.d("RETROAPPINTRO",t.message.toString())
            }
        })
    }


    companion object {
        val BASE_URL = "http://192.168.1.54:8080/api/" // Da cellulare mettere IP del proprio PC.
        //val BASE_URL = "http://10.0.2.2:8080/api/course/" Indirizzo per collegarsi da emulatore.
    }
}