fun List<User>.isUserInUsers(usersId: Int): Boolean = this.any{ it.idUser == usersId }
fun List<Chat>.isUserInChats(usersId: Int): Boolean = this.any{ it.userId == usersId }
fun List<Message>.isMessageInChats(chatId: Int): Boolean = this.any { it.chatId == chatId }

fun List<User>.isUserDeleted(usersId: Int): Boolean = this.any { !it.isDeleted && it.idUser == usersId }
fun List<Chat>.isUserChatDeleted(usersId: Int, chatId: Int): Boolean = this.filter { it.userId == usersId } .any { it.idChat == chatId }

fun List<Message>.filteredMessagesForDelete1(chatId: Int) = this.filter { it.chatId == chatId } .forEach { it.isDeletedMessage }

fun List<Message>.filteredMessagesForDelete2(usersId: Int, chatId: Int, messageId: Int): Boolean = this.filter { it.userId == usersId && !it.isDeletedMessage}
        .any { it.chatId == chatId && it.idMessage == messageId}
fun List<Message>.countNonDeletedMessagesInChat(chatId: Int): Int = this.count { !it.isDeletedMessage && it.chatId == chatId }

fun List<Message>.countAllMessagesInChat(chatId: Int): Int = this.filter { it.chatId == chatId } .count { !it.isDeletedMessage }
fun List<Message>.doAllMessagesInChatRead(chatId: Int) = this.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .forEach { it.isRead }
fun List<Message>.printAllMessagesInChat(chatId: Int) = this.filter { it.chatId == chatId } .forEach { println("${it.text}") }
fun List<Message>.maxIdMessageInChat(chatId: Int): Int? = this.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .maxOfOrNull { it.idMessage }

fun List<Message>.listUnreadLastMessages() = this.any { !it.isDeletedMessage && !it.isRead }

//fun List<Message>.listUnreadMessages() = this.filter { !it.isDeletedMessage && !it.isRead } .forEach { println("${it.text}") }
//fun List<Message>.messagesForDelete() = this.any { it.isDeletedMessage }
//fun List<Chat>.isChatUnDeleted(chatId: Int): Boolean = this.any { !it.isDeleted && it.idChat == chatId }


