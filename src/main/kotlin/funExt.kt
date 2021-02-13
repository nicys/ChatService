import ChatService.users

fun isUser(userId: Int) = users.filter { it.idUser == userId }