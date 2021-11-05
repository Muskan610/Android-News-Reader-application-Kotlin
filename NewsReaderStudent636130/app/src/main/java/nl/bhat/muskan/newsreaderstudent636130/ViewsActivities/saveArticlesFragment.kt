package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences

class saveArticlesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppPreferences.init(requireContext())

        if(!AppPreferences.isLogin){ //this will work
            return inflater.inflate(R.layout.loginregisterfragment, null)
        }
        else
        {
            return inflater.inflate(R.layout.savedarticlesfragment, null)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}