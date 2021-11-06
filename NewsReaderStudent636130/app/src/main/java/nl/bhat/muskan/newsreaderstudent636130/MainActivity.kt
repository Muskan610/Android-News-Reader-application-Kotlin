package nl.bhat.muskan.newsreaderstudent636130

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import nl.bhat.muskan.newsreaderstudent636130.Adapters.pagerAdapter
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.loginView
import android.app.UiModeManager
import androidx.core.content.ContextCompat.getSystemService
import android.R.attr
import android.R.attr.button
import android.widget.Toast
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences


class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    var mhome: TabItem? = null
    var msavedarticles:TabItem? = null
    var loginRegisterFragment:TabItem? = null
    lateinit var pagerAdapter: PagerAdapter
    var mtoolbar: Toolbar? = null
    lateinit var btnlogout: Button
    lateinit var btnlogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mtoolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mtoolbar)
        mhome = findViewById(R.id.home)
        msavedarticles = findViewById(R.id.savedArticles)
        loginRegisterFragment = findViewById(R.id.notLoggedIn)

       // uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);

        val viewPager = findViewById<ViewPager>(R.id.fragmentContainer)
        tabLayout = findViewById(R.id.include)


        pagerAdapter = pagerAdapter(supportFragmentManager, 2)
        viewPager.adapter = pagerAdapter


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                if (tab.position == 0 || tab.position == 1) {
                    pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        //to show logout btn only when user logged in
        btnlogout=findViewById(R.id.btnlogout)
        btnlogin=findViewById(R.id.btnlogin)


        AppPreferences.init(this)

        if(AppPreferences.isLogin){                          //this will work when user is logged in, only log out btn will be visible
            btnlogout.setVisibility(View.VISIBLE);
            btnlogin.setVisibility(View.GONE);
        }
        else
        {
            btnlogout.setVisibility(View.GONE);
            btnlogin.setVisibility(View.VISIBLE);
        }

        btnlogin.setOnClickListener(View.OnClickListener { openNewActivity() })
        btnlogout.setOnClickListener(View.OnClickListener { logout() })

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

    }

    fun openNewActivity() {
        val intent = Intent(this, loginView::class.java)
        startActivity(intent)
    }

    fun logout(){
        AppPreferences.token = ""
        AppPreferences.isLogin = false
        openNewActivity()
        Toast.makeText(applicationContext, "user logged out", Toast.LENGTH_LONG).show()
    }
}

