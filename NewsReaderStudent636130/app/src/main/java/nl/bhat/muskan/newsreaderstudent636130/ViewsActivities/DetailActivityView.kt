package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import nl.bhat.muskan.newsreaderstudent636130.R

class DetailActivityView: AppCompatActivity() {
    private lateinit var article_title: TextView
    lateinit var article_summary: TextView
    lateinit var article_date: TextView
    lateinit var readmorebutton: Button
    lateinit var article_image: ImageView
    lateinit var likeBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        article_title= findViewById(R.id.article_title)
        article_summary=findViewById(R.id.article_summary)
        article_date=findViewById(R.id.article_date)
        article_image=findViewById(R.id.article_image)
        readmorebutton=findViewById(R.id.readmorebutton)
        likeBtn=findViewById(R.id.ic_likebutton_filled)


        likeBtn.setOnClickListener(View.OnClickListener{
            likeBtn=findViewById(R.id.ic_likebutton_filled)
            likeBtn.setColorFilter(Color.RED)
        })

        val intent = intent
        val url = intent.getStringExtra("Url")
        article_date.text = intent.getStringExtra("PublishDate")
        article_title.text = intent.getStringExtra("Title")
        article_summary.text =intent.getStringExtra("Summary")
        //Glide.with(this).load(intent.getStringExtra("Image")).into(holder.imageView)
        //article_image.src = intent.getStringExtra("Image")
        readmorebutton.setOnClickListener(View.OnClickListener {
            val intent2 = Intent(this, webView::class.java)
            intent2.putExtra("Url", url)
            this.startActivity(intent2)
        })


    }
}