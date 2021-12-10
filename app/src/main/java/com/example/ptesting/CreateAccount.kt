package com.example.ptesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateAccount : AppCompatActivity() {
   private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var dob:EditText
    private lateinit var password:EditText
    private lateinit var address:EditText
    private lateinit var createAccountbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        name=findViewById(R.id.full_name_et)

        email=findViewById(R.id.email_et)
        password=findViewById(R.id.password_et)

        dob=findViewById(R.id.dob_et)
        address=findViewById(R.id.address_et)
        createAccountbtn=findViewById(R.id.CreateAccount_btn)
        var objectuser:MyUser=MyUser(name.toString(),password.toString(),email.toString(),dob.toString(),address.toString())
        var db=DatabaseHandler(this)
        createAccountbtn.setOnClickListener {
            var b:Boolean=(db.addUser(objectuser)>=0)
            if (b)
            {
                Toast.makeText(this, "account created", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()


        }
    }
}