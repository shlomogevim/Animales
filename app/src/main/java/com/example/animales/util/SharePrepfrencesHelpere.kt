package com.example.animales.util

import android.content.Context

class SharePrepfrencesHelpere(context: Context) {
    private val PREF_API_KEY="pref api key"
    private val prefs=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun saveApiKey(key:String?){
        prefs.edit().putString(PREF_API_KEY,key).apply()
    }
    fun  getApiKey()=prefs.getString(PREF_API_KEY,null)


}