package com.example.qdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase




class MainScreen : AppCompatActivity() {


    private lateinit var database: DatabaseReference


    lateinit var ref : DatabaseReference
    lateinit var ref2 : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        supportActionBar?.hide()

        var bpm : TextView = findViewById(R.id.bpm_val)
        var spo2 : TextView = findViewById(R.id.spo2_val)

        database = Firebase.database.reference

        ref = FirebaseDatabase.getInstance().getReference("HeartRate")
        ref2 = FirebaseDatabase.getInstance().getReference("SPO2")
        val dd_id = ref.push().key
        val dd = Data(HeartRate = null, SPO2 = null)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bpm.text = snapshot.getValue<String>()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

        ref2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                spo2.text = snapshot.getValue<String>()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}