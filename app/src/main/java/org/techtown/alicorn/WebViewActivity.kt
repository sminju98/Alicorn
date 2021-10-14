package org.techtown.alicorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import org.techtown.alicorn.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    private val type: String by lazy { intent.getStringExtra("TYPE") ?: "" }
    private val number: Int by lazy { intent.getIntExtra("NUMBER", 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setWebView()

    }

    private fun setWebView() {
        val url = if (type == "AGREE") {
            when (number) {
                1 -> "https://www.drugunicorn.com"
                2 -> "https://www.drugunicorn.com"
                3 -> "https://www.drugunicorn.com"
                4 -> "https://www.drugunicorn.com"
                5 -> "https://www.drugunicorn.com"
                else -> "https://www.naver.com"
            }
        } else {
            "https://google.com"
        }
        binding.webview.loadUrl(url)
        binding.webview.webViewClient = WebViewClient()
        binding.webview.webChromeClient = WebChromeClient()
    }

    fun onClick(view: android.view.View) {
        when (view) {
            binding.backButton -> finish()
        }
    }
}