package org.techtown.alicorn.data.remote.request.user

import androidx.annotation.Keep

@Keep
data class LoginRequstData (

    var email:String = "",
    var password:String = ""

)
