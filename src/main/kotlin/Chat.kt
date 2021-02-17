data class Chat(
        val idChat: Int,
        val nameOwn: String = "Nik",
        val userId: Int,
        var isDeletedUser: Boolean = false,
        var isDeleted: Boolean = false
)

data class User(
    val idUser: Int = 0,
    val name: String = "noName",
    val sirName: String = "noSirName",
    val isDeleted: Boolean = false
) {
    override fun toString(): String {
        return """|  
            |  User: idUser = $idUser, 
            |name = '$name', sirName = '$sirName', 
            |deleted = $isDeleted""".trimMargin()
    }
}

data class Message(
        val chatId: Int,
        val userId: Int,
        val idMessage: Int,
        val text: String?,
        var isRead: Boolean = false,
        var isDeletedMessage: Boolean = false
)
