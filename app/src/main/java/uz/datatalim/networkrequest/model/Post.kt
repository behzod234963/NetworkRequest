package uz.datatalim.networkrequest.model

data class Post(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
) {
    override fun toString(): String {
        return "userId:$userId\nid:$id\ntitle:$title'\nbody:'$body')"
    }
}
