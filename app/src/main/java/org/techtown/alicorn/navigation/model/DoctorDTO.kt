package org.techtown.alicorn.navigation.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp

/**
 * keep
 * 정해진 데이터타입
 * 초기값
 * 네이밍룰
 * nullnlable
 */
@Keep
data class DoctorDTO(
    var id : String?=null,

    var doctorName : String?= null,
    var clinicName : String?= null,
    var profileImageUrl : String?= null,
    var clinicImage : String?= null,

    var info : String?= null,
    var context : String?=null,
    var major : String?=null,
    var tag : ArrayList<String> ?= null,
    var careers : ArrayList<String> ?= null,

    var price: String ?=null,
    //var times: ArrayList<Timestamp>? = null,
    var phoneNumber : String ?= null,
    var telephone : String ?= null


)