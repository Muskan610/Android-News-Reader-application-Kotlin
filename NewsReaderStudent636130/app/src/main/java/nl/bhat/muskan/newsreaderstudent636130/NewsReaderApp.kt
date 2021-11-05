package nl.bhat.muskan.newsreaderstudent636130

import android.app.Application
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences

class NewsReaderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}
