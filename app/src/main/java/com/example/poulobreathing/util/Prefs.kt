package com.example.poulobreathing.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences
import com.example.poulobreathing.MainActivity
import java.util.*

class Prefs(activity: Activity) {
    private  var prefences: SharedPreferences=activity.getPreferences(Activity.MODE_PRIVATE)

     fun setDate(milliseconds: Long) {
        prefences.edit().putLong("seconds", milliseconds)
            .apply()
    }

    val date: String
    get(){
        val milliDate = prefences.getLong("seconds", 0)
        var amOrPm: String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)
        if (a == Calendar.AM) amOrPm = "AM"
                         else amOrPm = "PM"
        return ("Last ${calendar.get(Calendar.HOUR_OF_DAY)} :" +
                "${calendar.get(Calendar.MINUTE)} $amOrPm") // 11.45PM/AM

    }

    var sessions: Int
        get() = prefences.getInt("sessions", 0)
        set(session) = prefences.edit().putInt("sessions", session).apply()

    var breaths: Int
        get() = prefences.getInt("breaths", 0)
        set(breath) = prefences.edit().putInt("breaths", breath).apply()

   /* fun getDate(): String {
        val milliDate = prefences.getLong("seconds", 0)
        var amOrPm: String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)
        if (a == Calendar.AM) amOrPm = "AM"
        else amOrPm = "PM"
        val time = "Last ${calendar.get(Calendar.HOUR_OF_DAY)} :" +
                "${calendar.get(Calendar.MINUTE)} $amOrPm" // 11.45PM/AM
        return time
    }*/


    }
