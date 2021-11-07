package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import nl.bhat.muskan.newsreaderstudent636130.R
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.graphics.Paint
import android.net.Uri
import android.util.Log
import android.widget.*
import nl.bhat.muskan.newsreaderstudent636130.Adapters.AdapterResults
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.GetResultsService
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.RetrofitClientInstance
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultsDTO
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivityView: AppCompatActivity() {
    //var articleList: ArrayList<ResultsDTO>? = null
    companion object {
        const val ARTICLE_LIST = "ARTICLE_LIST"
    }

    val service = RetrofitClientInstance.retrofitInstance?.create(GetResultsService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppPreferences.init(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val articleList = intent.getSerializableExtra(ARTICLE_LIST) as ArrayList<ResultsDTO>
        val article = articleList.get(0)
        Log.d("transfer works", "data transferred via intent")

        val title = findViewById<TextView>(R.id.article_title)
        val text = findViewById<TextView>(R.id.article_summary)
        val image = findViewById<ImageView>(R.id.article_image)
        val likebutton = findViewById<ImageView>(R.id.ic_likebutton_filled)
        val readmorebutton = findViewById<Button>(R.id.readmorebutton)
        val dateholder = findViewById<TextView>(R.id.article_date)
        val backbtn = findViewById<ImageView>(R.id.backbtn)

        val date = article.PublishDate.substring(0, 10)
        val time = article.PublishDate.substring(11, 16)

        dateholder.setText("Op $date om $time")
        title.setText(article.Title)
        text.setText(article.Summary)

        Glide.with(this).load(article.Image).into(image)
        //Glide.with(this).load(article.Image).into(holder.imageView)


        if(article.IsLiked.equals("true", ignoreCase = true)){
            likebutton.setColorFilter(Color.RED)
        }
        else{
            likebutton.setColorFilter(Color.GRAY)
        }

        likebutton.setOnClickListener {
            if(AppPreferences.isLogin){
                var likecall = service?.likeArticle(article.Id.toInt(), AppPreferences.token)
                if(article.IsLiked.equals("true", ignoreCase = true)){
                    likecall = service?.dislikeArticle(article.Id.toInt(), AppPreferences.token)
                }

                if (likecall != null) {
                    likecall.enqueue(object: Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Log.d("Like", response.toString())
                            if(article.IsLiked.equals("true", ignoreCase = true)){
                                likebutton.setColorFilter(Color.GRAY)
                                Toast.makeText(applicationContext, "article unliked", Toast.LENGTH_LONG).show()
                            }
                            else{
                                likebutton.setColorFilter(Color.RED)
                                Toast.makeText(applicationContext, "article liked", Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(applicationContext, "somthing went wrong", Toast.LENGTH_LONG).show()
                        }

                    })
                }
            }
            else {
                Toast.makeText(applicationContext, "you should be logged in to like articles", Toast.LENGTH_LONG).show()
            }
        }


        readmorebutton.setOnClickListener(View.OnClickListener {
           val intent2 = Intent(this, webView::class.java)
            intent2.putExtra("Url", article.Url)
            startActivity(intent2)

            /*val uri: Uri = Uri.parse(article.Url) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)*/
        })

        backbtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}