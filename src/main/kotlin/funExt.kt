fun List<User>.isUserInUsers(usersId: Int): Boolean = this.any{ it.idUser == usersId }
fun List<User>.isUserDeleted(usersId: Int): Boolean = this.any { !it.isDeleted && it.idUser == usersId }

fun List<Chat>.isUserInChats(usersId: Int): Boolean = this.any{ it.userId == usersId }
fun List<Chat>.isUserChatDeleted(usersId: Int, chatId: Int): Boolean = this.filter { it.userId == usersId } .any { it.idChat == chatId }

fun List<Message>.isMessageInChats(chatId: Int): Boolean = this.any { it.chatId == chatId }
fun List<Message>.filteredMessagesForDelete1(chatId: Int) = this.filter { it.chatId == chatId } .forEach { it.isDeletedMessage }
fun List<Message>.filteredMessagesForDelete2(usersId: Int, chatId: Int, messageId: Int): Boolean = this.filter { it.userId == usersId &&
        it.chatId == chatId && it.idMessage == messageId } .any { !it.isDeletedMessage }
fun List<Message>.countAllMessagesInChat(chatId: Int): Int = this.filter { it.chatId == chatId } .count { !it.isDeletedMessage }
fun List<Message>.doAllMessagesInChatRead(chatId: Int) = this.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .forEach { it.isRead }
fun List<Message>.printAllMessagesInChat(chatId: Int) = this.filter { it.chatId == chatId } .forEach { println("${it.text}") }
fun List<Message>.maxIdMessageInChat(chatId: Int): Int? = this.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .maxOfOrNull { it.idMessage }
fun List<Message>.listUnreadLastMessages() = this.any { !it.isDeletedMessage && !it.isRead }
fun List<Message>.isMessageNotDeleted(idMessage: Int): Boolean = this.any { it.idMessage == idMessage && !it.isDeletedMessage }