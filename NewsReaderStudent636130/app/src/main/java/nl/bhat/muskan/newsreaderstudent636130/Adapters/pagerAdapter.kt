package nl.bhat.muskan.newsreaderstudent636130.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.homeFragment
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.saveArticlesFragment

class pagerAdapter(fm: FragmentManager, var tabcount: Int) : FragmentPagerAdapter(fm, tabcount) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> homeFragment()
            1 -> saveArticlesFragment()
            else -> homeFragment()
        }
    }

    override fun getCount(): Int {
        return tabcount
    }
}