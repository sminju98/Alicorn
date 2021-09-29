package org.techtown.alicorn.navigation.model

import com.google.gson.annotations.SerializedName

data class DoctorDTO(
    @SerializedName("doctorName") val doctorName : String?= null,
    @SerializedName("clinicName") val clinicName : String?= null,
    @SerializedName("doctorImg") val doctorImg : String?= null
)

{

    constructor(): this("","","")
}

