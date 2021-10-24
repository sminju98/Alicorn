package org.techtown.alicorn.navigation.model

import androidx.annotation.Keep

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
    var info : String?= null,
    var context : String?=null,
    var phoneNumber : String ?= null
)