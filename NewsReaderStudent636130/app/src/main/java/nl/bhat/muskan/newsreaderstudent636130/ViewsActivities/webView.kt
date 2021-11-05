package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.os.Bundle
import nl.bhat.muskan.newsreaderstudent636130.R
import android.content.Intent
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class webView : AppCompatActivity() {
    //how to load the news inside the web view
    var toolbar: Toolbar? = null
    var webView: WebView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        toolbar = findViewById(R.id.toolbar)
        webView= findViewById(R.id.webview)
        setSupportActionBar(toolbar)

        //to get the url if user clicks on a specific news
        val intent = intent
        val url = intent.getStringExtra("Url")
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl(url!!)
    }

}