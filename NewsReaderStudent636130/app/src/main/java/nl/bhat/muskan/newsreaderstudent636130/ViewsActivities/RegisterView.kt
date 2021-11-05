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
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.RegisterResponse
import nl.bhat.muskan.newsreaderstudent636130.MainActivity
import nl.bhat.muskan.newsreaderstudent636130.SharedPreferences.AppPreferences


class RegisterView : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var ifLogin: Button
    private lateinit var btnbacktomain: Button
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    val service = RetrofitClientInstance.retrofitInstance?.create(GetResultsService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)
        username = findViewById(R.id.eUsername)
        password = findViewById(R.id.ePassword)
        btnRegister = findViewById(R.id.btnRegister)
        ifLogin = findViewById(R.id.ifLogin)
        btnbacktomain = findViewById(R.id.btnbacktomain)

        btnbacktomain.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        ifLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, loginView::class.java)
            startActivity(intent)
        })

        AppPreferences.init(this)
        btnRegister.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(username.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString())
            ) {
                Toast.makeText(applicationContext, "please fill in all the fields", Toast.LENGTH_LONG).show()
            } else {
                register(username, password)
            }
        })
    }

    fun register(username: TextInputEditText, password: TextInputEditText){

        val registerResponseCall = service?.register(username.getText().toString(), password.getText().toString())
        registerResponseCall?.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){
                    AppPreferences.isLogin = true
                    var isSuccess = response.body()?.Success.toString()
                    if(isSuccess.equals("True",ignoreCase = true)){
                        //if registering a new user is successful
                        loginAfterRegister(username, password)
                        Log.d("Register", "Register plus Login successful")
                    }
                    else{
                        //username.getText()?.clear()
                        //password.getText()?.clear()
                        var message = response.body()?.Message.toString()
                        Toast.makeText(applicationContext, message+" ,please try again", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    username.getText()?.clear()
                    password.getText()?.clear()
                    Toast.makeText(applicationContext, "credentials incorrect, please try again", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Throwable "+t.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("Login unsuccessful", "not okay bro")
            }

        }
        )

    }

    fun loginAfterRegister(username: TextInputEditText, password: TextInputEditText){
        val loginResponseCall = service?.login(username.getText().toString(), password.getText().toString())
        loginResponseCall?.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    AppPreferences.isLogin = true
                    AppPreferences.token = response.body()?.AuthToken.toString()
                    var bundle = Bundle().apply {
                        putInt("FEED_ID", 0)
                    }
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish();
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Throwable "+t.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("Login unsuccessful", "not okay bro")
            }

        }
        )

    }
}

