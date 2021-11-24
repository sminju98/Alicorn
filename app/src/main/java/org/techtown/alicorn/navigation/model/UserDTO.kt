package org.techtown.alicorn.navigation.model

import androidx.annotation.Keep

@Keep
data class UserDTO(

    var uid:String? = "",
    var email:String? = "",
    var activation:Boolean? = false
)