package com.example.ptesting

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "LoginAppDatabase"
        private const val TABLE_CONTACTS = "LoginUsers"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val DateOfBirth = "DateOfBirth"
        private const val address = "Address"
        private const val password = "Password"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, AUTOINCREMENT" + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + DateOfBirth + "TEXT" + address + "TEXT" + password + "TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(usr: MyUser): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, usr.name) // EmpModelClass Name
        contentValues.put(KEY_EMAIL, usr.email) // EmpModelClass Phone
        contentValues.put(password, usr.password)
        contentValues.put(DateOfBirth, usr.Dob)
        contentValues.put(address, usr.address)

        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    @SuppressLint("Range")
    fun viewUsers():List<MyUser>{
        val UserList:ArrayList<MyUser> = ArrayList<MyUser>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userName: String
        var userEmail: String
        var password:String
        var DateOfBirth:String
        var address:String

        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                password = cursor.getString(cursor.getColumnIndex("Password"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                DateOfBirth = cursor.getString(cursor.getColumnIndex("DateOfBirth"))
                address= cursor.getString(cursor.getColumnIndex("Address"))
                val usr= MyUser(userName,password,userEmail,DateOfBirth,address)
                UserList.add(usr)
            } while (cursor.moveToNext())
        }
        return UserList
    }


}
