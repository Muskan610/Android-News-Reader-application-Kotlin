package nl.bhat.muskan.newsreaderstudent636130.Adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.LoginRegisterFragment
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.homeFragment
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.saveArticlesFragment

class pagerAdapter(fm: FragmentManager, var tabcount: Int) : FragmentPagerAdapter(fm, tabcount) {
    override fun getItem(position: Int): Fragment {
        if(AppPreferences.isLogin){
            Log.d("login", "user islogin is true")
            return when (position) {
                0 -> homeFragment()
                1 -> saveArticlesFragment()
                else -> homeFragment()
            }
        }
        else{
            Log.d("login", "user islogin is false, not logged in")
            return when (position) {

                0 -> homeFragment()
                1 -> LoginRegisterFragment()
                else -> homeFragment()
            }
        }

    }

    override fun getCount(): Int {
        return tabcount
    }
}