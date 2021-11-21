package org.techtown.alicorn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.data.remote.api.CallApi
import org.techtown.alicorn.data.remote.response.user.login.LoginResponse
import org.techtown.alicorn.databinding.ActivityLoginBinding
import org.techtown.alicorn.navigation.model.UserDTO

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val db = Firebase.firestore
    var auth: FirebaseAuth? = null
    var googleSignInClient: GoogleSignInClient? = null
    var GOOGLE_LOGIN_CODE = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.emailLoginButton.setOnClickListener {
            signinEmail()
        }
        binding.emailSignupButton.setOnClickListener {
            startActivity(Intent(this, AgreeActivity::class.java))
        }
        binding.googleLoginButton.setOnClickListener {
            //첫번째 단계
            googleLogin()
        }

        binding.findPw.setOnClickListener{
            startActivity(Intent(this,FindPasswordActivity::class.java))

        }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("593874221588-7mqldmalbs82t54kd1pdoaga6e7g4quv.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signtestinEmail() {

    }


    public override fun onResume() {
        super.onResume()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            if(currentUser.isEmailVerified==true) {
                moveMainpage(currentUser)
            }
            else {
                Toast.makeText(this, "회원가입을 위해 이메일 인증을 부탁드립니다.", Toast.LENGTH_LONG).show()
            }

//            db.collection("users").document(currentUser.uid).get().addOnSuccessListener {
//                val userData = it.toObject<UserDTO>()
//                   val provider = try {
//                        currentUser.providerData[1].providerId
//                    } catch (e: Exception) {
//                        currentUser.providerData[0].providerId
//                    }
//                    Log.e("${currentUser.uid}", userData?.activation.toString())
//
//                    if (provider == "password") {
//                        if (userData?.activation == true) {
//                            moveMainpage(currentUser)
//                        } else {
//                        }
//                    } else {
//
//                          moveMainpage(currentUser)
//                    }
//                    db.collection("users").document(currentUser.uid).update()
//                      moveMainpage(currentUser)
                }
    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_CODE) {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result != null) {
                if (result.isSuccess) {
                    var account = result.signInAccount
                    //두번째 단계
                    firebaseAuthWithGoogle(account)
                }
            }
        }
    }

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    moveMainpage(task.result?.user)
                } else {
                    //로그인 실패, 에러메세지
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun signinEmail() {
        if (binding.emailEditText.text.toString().isEmpty() ||
            binding.passwordEditText.text.toString().isEmpty()
        ) {
            return
        }
        auth?.signInWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResume()
//                    //로그인 성공
//                        CallApi().userLogin(task.result?.user)
////                            } else {
//                            binding.emailEditText.text.toString(),
//                            binding.passwordEditText.text.toString()
//                        ) { b: Boolean, s: String, loginResponse: LoginResponse? ->
//                            Log.e("userLogin", "$b $s $loginResponse")
//                            if (b && loginResponse != null && loginResponse.success) {
//                                App.token = loginResponse.data
//                                moveMainpage(
//                                //로그인 실패, 에러메세지
//                                Toast.makeText(this, "로그인 실패. 고객센터에 문의해주세요.", Toast.LENGTH_LONG).show()
//                            }
                        }
                else {
                    //로그인 실패, 에러메세지
                    Toast.makeText(this, "로그인 실패. 이메일과 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
                }
            }

    }

    fun moveMainpage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
