package com.example.ptesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var signup: TextView
    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var submit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signup=findViewById(R.id.signup_link)
        signup.setOnClickListener {
            val s= Intent(this,CreateAccount::class.java)
            startActivity(s)
        }
        email=findViewById(R.id.username_etv)
        pass=findViewById(R.id.password_etv)
        submit=findViewById(R.id.submit_btn)
        var obje=DatabaseHandler(this);
        val list:List<MyUser>
        list=obje.viewUsers()
        submit.setOnClickListener {
            if(check(list,email.toString(),pass.toString()))
            {
                val s= Intent(this,showing::class.java)
                startActivity(s)
            }
            else
            Toast.makeText(this, "invalid login", Toast.LENGTH_SHORT).show()

        }

    }
}
fun check(list:List<MyUser>,email:String,password:String):Boolean {
    if (list.isEmpty())
        print("emptyArray()")
    for(  user in list){
        if (user.password==password && user.email==email)
        {
            return true
        }
    }
    return false
}