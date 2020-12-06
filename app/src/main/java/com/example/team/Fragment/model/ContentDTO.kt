package com.example.team.Fragment.model

data class ContentDTO(var explain: String? = null,
                      var imageUrl: String? = null,
                      var uid: String? = null,
                      var userId: String? = null,
                      var timestamp: String? = null,
                      var title: String? =null,
                      var type: String?=null,
                      var category: CharCategory? =null,
                      var location: String? =null,
                      var latitude: String? =null,
                      var longtitude: String? =null

)

//                      var favoriteCount: Int = 0,
//                      var favorites: MutableMap<String, Boolean> = HashMap()


//    data class Comment(var uid: String? = null,
//                       var userId: String? = null,
//                       var comment: String? = null,
//                       var timestamp: Long? = null)
//}