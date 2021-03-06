package com.example.dogexpertz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var db : QuizDataBase

    //Denna funktion skapas automatiskt av Android när Class MainActivity är skapad.
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Gömmer status bar.
        // Kallar på parent constructorn
        super.onCreate(savedInstanceState)
        // Justerar xml viewn till denna class
        setContentView(R.layout.activity_main)

        db = QuizDataBase.invoke(this)

        val question1 = Question("What is the name of this dog breed?",
             R.drawable.ic_english_bulldog,
            "Chihuahua", "English Bulldog",
            "Boxer", "Mastiff",
            2)

        val question2 = Question("What is the name of this dog breed?",
             R.drawable.ic_american_akita,
            "Bichon frisé", "Japaneese shiba",
            "American akita", "Siberian husky",3)

        val question3 = Question ("What is the name of this dog breed?",
            R.drawable.ic_border_collie,
            "Jack russell", "Border collie",
            "Shetland sheepdog", "Whippet", 2)

        val question4 = Question (
            "What is the name of this dog breed?",
            R.drawable.ic_cane_corso,
            "Pitbull terrier", "Cane corso",
            "Bullmastiff", "Amstaff", 2

                )

        val question5 = Question (
            "What is the name of this dog breed?",
            R.drawable.ic_german_shepherd,
            "Australian shepherd", "Rottweiler",
            "German shepherd", "Collie", 3

        )

        val question6 = Question (

            "What is the name of this dog breed?",
            R.drawable.ic_french_bulldog,
            "French bulldog", "Mallinois",
            "Boston terrier", "Staffordshire bullterrier", 1

        )

        val question7 = Question (
            "What is the name of this dog breed?",
            R.drawable.ic_tervuren,
            "Malteser", "Weimaraner",
            "Tervuren", "Golden retriever", 3

        )

        val question8 = Question (
            "What is the name of this dog breed?",
            R.drawable.ic_shih_tzu,
            "Lhasa apso", "Shih-Tzu",
            "Pekingeese", "Pomeranian", 2

        )

        val question9 = Question (

            "What is the name of this dog breed?",
            R.drawable.ic_rhodesian_ridgeback,
            "Springer spaniel", "Rhodesian ridgeback",
            "Dogo argentino", "Gran danois", 2

        )

        val question10 = Question (

            "What is the name of this dog breed?",
            R.drawable.ic_labrador,
            "Labrador", "Basenji",
            "Flat coated retriever", "Schnauzer", 1

        )

        saveQuestion(question1)
        saveQuestion(question2)
        saveQuestion(question3)
        saveQuestion(question4)
        saveQuestion(question5)
        saveQuestion(question6)
        saveQuestion(question7)
        saveQuestion(question8)
        saveQuestion(question9)
        saveQuestion(question10)



        }
        fun saveQuestion (question: Question){
            GlobalScope.launch(Dispatchers.IO){
                db.questionDao().insert(question)
        }

        CoroutineScope(Dispatchers.IO).async {
            db.questionDao().getAll().forEach {
                Log.d("!!!", it.toString())
            }
        }

        // Start-Knapp
        // Om namnfältet står tomt och man trycker på start så
        // kommer det upp en påminnelse längre ner vid skärmen  " Please enter your name "

        // Om namnfältet har text i sig och man trycker på start så kommer man vidare till nästa
        // aktivitet.
        btn_start.setOnClickListener () {
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,
                    "Please enter your name", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)

                // STEG 2: Passerar namnet via intent där man använder en constant variabel som
                // finns i Constants.
                // START
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                // END
                startActivity(intent)
                finish()

            }
        }
    }
}