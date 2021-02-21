data class Chat(
        var idChat: Int,
        val nameOwn: String = "Nik",
        var userId: Int,
        var isDeletedUser: Boolean = false,
        var isDeleted: Boolean = false
)

data class User(
        var idUser: Int = 0,
        val name: String = "noName",
        val sirName: String = "noSirName",
        var isDeleted: Boolean = false
) {
    override fun toString(): String {
        return """|  
            |  User: idUser = $idUser, 
            |name = '$name', sirName = '$sirName', 
            |deleted = $isDeleted""".trimMargin()
    }
}

data class Message(
        var chatId: Int,
        var userId: Int,
        var idMessage: Int,
        val text: String?,
        var isRead: Boolean = false,
        var isDeletedMessage: Boolean = false
)
