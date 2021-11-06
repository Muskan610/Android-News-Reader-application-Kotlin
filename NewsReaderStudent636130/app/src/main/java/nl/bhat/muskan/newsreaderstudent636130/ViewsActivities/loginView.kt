package nl.bhat.muskan.newsreaderstudent636130.ViewsActivities

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.util.Log
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.GetResultsService
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.RetrofitClientInstance
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.LoginResponse
import nl.bhat.muskan.newsreaderstudent636130.MainActivity
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences
import org.w3c.dom.Text


class loginView : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var btnbacktomain: Button
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var ifReg: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        username = findViewById(R.id.eUsername)
        password = findViewById(R.id.ePassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnbacktomain=findViewById(R.id.btnbacktomain)
        ifReg=findViewById(R.id.ifReg)

        btnbacktomain.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        ifReg.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterView::class.java)
            startActivity(intent)
        })

        AppPreferences.init(this)
        btnLogin.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(username.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString())
            ) {
                Toast.makeText(applicationContext, "please fill in all the fields", Toast.LENGTH_LONG).show()
            } else {
                login(username, password)
            }
        })
    }

    //user accounts

    //username: joey69
    //password: verysecurepassword

    //alishya69
    //8901qwwe

    fun login(username: TextInputEditText, password: TextInputEditText){
        val service = RetrofitClientInstance.retrofitInstance?.create(GetResultsService::class.java)
        val loginResponseCall = service?.login(username.getText().toString(), password.getText().toString())
        loginResponseCall?.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    AppPreferences.isLogin = true
                    AppPreferences.token = response.body()?.AuthToken.toString()
                    var bundle = Bundle().apply {
                        putInt("FEED_ID", 0)
                    }

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish();
                    Toast.makeText(applicationContext, "logged in successfully ", Toast.LENGTH_LONG).show()
                    Log.d("Login successful", "okay")
                }
                else{
                    username.getText()?.clear()
                    password.getText()?.clear()
                    Toast.makeText(applicationContext, "credentials incorrect, please try again", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Throwable "+t.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("Login unsuccessful", "not okay bro")
            }

        }
        )

    }
}

