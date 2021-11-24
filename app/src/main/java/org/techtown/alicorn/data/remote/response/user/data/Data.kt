package org.techtown.alicorn.data.remote.response.user.data

data class Data(
    var allergies: List<String>?=null,
    var birthday: String?=null,
    var countryOfResidence: String?=null,
    var createdAt: String?=null,
    var drinking: String?=null,
    var email: String?=null,
    var familyHistories: List<String>?=null,
    var id: Int?=null,
    var kakaoId: String?=null,
    var name: String?=null,
    var password: String?=null,
    var phoneNumber: String?=null,
    var sex: Int?=null,
    var smoking: Boolean?=null,
    var underlyingDiseases: List<String>?=null,
    var updatedAt: String?=null,
    var useOfDrugs: List<String>?=null
)