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
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.GetResultsService
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.ApiRetrofit.RetrofitClientInstance
import nl.bhat.muskan.newsreaderstudent636130.LoginRegister.RegisterResponse
import nl.bhat.muskan.newsreaderstudent636130.MainActivity


class RegisterView : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)
        username = findViewById(R.id.eUsername)
        password = findViewById(R.id.ePassword)
        btnRegister = findViewById(R.id.btnRegister)
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
        val service = RetrofitClientInstance.retrofitInstance?.create(GetResultsService::class.java)
        val registerResponseCall = service?.register(username.getText().toString(), password.getText().toString())
        registerResponseCall?.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){
                    //Toast.makeText(applicationContext, "logged in successfully ", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish();
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Throwable "+t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }
        )

    }
}

