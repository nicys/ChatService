data class Chat(
    val idChat: Int,
    val idOwn: Int
)

data class User(
    val idUser: Int,
    val name: String,
    val sirName: String
)

data class Message(
    val idMessage: Int,
    val text: String
)
