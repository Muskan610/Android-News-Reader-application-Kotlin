package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import nl.bhat.muskan.newsreaderstudent636130.Adapters.AdapterResults
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.GetResultsService
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.RetrofitClientInstance
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultList
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultsDTO
import nl.bhat.muskan.newsreaderstudent636130.MyPositionListener
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class saveArticlesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var adapter: AdapterResults? = null
    private val articles = mutableListOf<ResultsDTO>()
    val service = RetrofitClientInstance.retrofitInstance?.create(GetResultsService::class.java)
    val call = service?.getAllLikedArticles(AppPreferences.token)

    lateinit var recyclerViewofhome: RecyclerView
    //lateinit var noSavedAticles: TextView
    private var nextId: Int = 0
    private var fetching: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onResume()
        return inflater.inflate(R.layout.savedarticlesfragment, null as ViewGroup?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AppPreferences.init(requireContext())
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshSAVED)
        swipeRefreshLayout.setOnRefreshListener(this)
        recyclerViewofhome = view.findViewById(R.id.recyclerviewSAVED)

        adapter = AdapterResults(requireContext(), articles!!, object: MyPositionListener {
            override fun onItemClicked(position: Int) {
                val intent = Intent(context, DetailActivityView::class.java)
                val sendThisArticle = articles[position]

                val arrayListResultsDTO = ArrayList<ResultsDTO>()
                arrayListResultsDTO.add(sendThisArticle)

                intent.putExtra("ARTICLE_LIST", arrayListResultsDTO)
                startActivity(intent, savedInstanceState)
            }
        })
        recyclerViewofhome.setLayoutManager(LinearLayoutManager(context))
        recyclerViewofhome.setAdapter(adapter)
        loadNews()

        recyclerViewofhome.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)){
                    getNextArticle()
                }
            }
        })


        super.onViewCreated(view, savedInstanceState)
    }


    private fun loadNews(){
        swipeRefreshLayout.setRefreshing(true)
        //service?.getAllResults()?.enqueue(object  : Callback<ResultList> {
            service?.getAllLikedArticles(AppPreferences.token)?.enqueue(object  : Callback<ResultList>{
            override fun onResponse(call: Call<ResultList>, response: Response<ResultList>) {
                val body = response.body()
                Log.d("CHECK1", "getting response")
                if (body != null) {
                    for(article in body.Results){
                        articles.add(article)
                    }

                    nextId = body.NextId
                    adapter!!.notifyDataSetChanged()
                    swipeRefreshLayout.setRefreshing(false)
                }
            }
            override fun onFailure(call: Call<ResultList>, t: Throwable) {
                Toast.makeText(context, "something went wrong we are trying to fix it :(", Toast.LENGTH_LONG).show()
            }

        })
    }


    fun getNextArticle(){
        val callByNextId = service?.getAllResultsByNextId(nextId)
        callByNextId?.enqueue(object  : Callback<ResultList> {
            override fun onResponse(call: Call<ResultList>, response: Response<ResultList>) {
                val body = response.body()
                Log.d("CHECK1", "getting response")
                if (body != null) {
                    nextId = body.NextId
                    for(article in body.Results){
                        articles.add(article)
                    }
                    Log.d("Next ID check", "NextId is "+nextId.toString())

                    adapter!!.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<ResultList>, t: Throwable) {
                Toast.makeText(context, "something went wrong we are trying to fix it :(", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onRefresh() {
        articles.clear()
        loadNews()
    }

    override fun onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
}