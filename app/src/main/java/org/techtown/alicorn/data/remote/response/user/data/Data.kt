package org.techtown.alicorn.data.remote.response.user.data

data class Data(
    val allergies: List<String>,
    val birthday: String,
    val countryOfResidence: String,
    val createdAt: String,
    val drinking: Any,
    val email: String,
    val familyHistories: List<Any>,
    val id: Int,
    val kakaoId: String,
    val name: String,
    val password: String,
    val phoneNumber: String,
    val sex: Int,
    val smoking: Boolean,
    val underlyingDiseases: List<Any>,
    val updatedAt: String,
    val useOfDrugs: List<String>
)