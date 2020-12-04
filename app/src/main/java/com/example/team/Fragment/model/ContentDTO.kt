package com.example.team.Fragment.model

data class ContentDTO(
                      var imageUrl: String? = null, //사진
                      var uid: String? = null, //아이디 관리
                      var userId: String? = null, //올린 유저의 이미지 관ㄹㅣ
                      var category: String? =null, //카테고리
                      var title: String? =null, //제목
                      var location: String? =null, //우ㅣ치
                      var explain: String? = null, //설명
                      var timestamp: Long? = null


//                      var favoriteCount: Int = 0,
//                      var favorites: MutableMap<String, Boolean> = HashMap()
)

//    data class Comment(var uid: String? = null,
//                       var userId: String? = null,
//                       var comment: String? = null,
//                       var timestamp: Long? = null)
//}
