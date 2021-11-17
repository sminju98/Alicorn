package org.techtown.alicorn.data.remote.response.user.data

import androidx.annotation.Keep

@Keep
data class UserDataResponse(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)