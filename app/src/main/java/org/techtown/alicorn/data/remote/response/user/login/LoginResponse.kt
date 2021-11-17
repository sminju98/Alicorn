package org.techtown.alicorn.data.remote.response.user.login

import androidx.annotation.Keep

@Keep
data class LoginResponse(
    val `data`: String?,
    val message: String,
    val status: Int,
    val success: Boolean
)