package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

class ChallengeSelect : AppCompatActivity() {
    lateinit var titleChallenge:TextView
    lateinit var explnChallenge:TextView
    lateinit var cardFood:CardView
    lateinit var cardPlastic:CardView
    lateinit var cardExercise:CardView
    lateinit var cardResource:CardView
    lateinit var cardOthers:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_select)

        titleChallenge = findViewById(R.id.titleChallenge)
        explnChallenge = findViewById(R.id.explnChallenge)
        cardFood = findViewById(R.id.cardFood)
        cardPlastic = findViewById(R.id.cardPlastic)
        cardExercise = findViewById(R.id.cardExercise)
        cardResource = findViewById(R.id.cardResource)
        cardOthers = findViewById(R.id.cardOthers)

        var id = "swim"

        cardFood.setOnClickListener {
            var intent = Intent(this, ChallengeActivityFood::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        cardPlastic.setOnClickListener {
            var intent = Intent(this, ChallengeActivityPlastic::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        cardExercise.setOnClickListener {
            var intent = Intent(this, ChallengeActivityExercise::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        cardResource.setOnClickListener {
            var intent = Intent(this, ChallengeActivityNatural::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        cardOthers.setOnClickListener {
            var intent = Intent(this, ChallengeActivityEtc::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}