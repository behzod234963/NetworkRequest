package uz.datatalim.networkrequest.model

data class Post(
    val userId:Int,
    val id:Int,
    var title:String,
    var body:String
) {
    override fun toString(): String {
        return "userId:$userId\nid:$id\ntitle:$title'\nbody:'$body')"
    }
}
