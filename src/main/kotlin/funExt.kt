import ChatService.chats
import ChatService.messages
import ChatService.users

val isUserInUsers = fun(usersId: Int): Boolean = users.any{ it.idUser == usersId }
val isUserInChats = fun(usersId: Int): Boolean = chats.any{ it.userId == usersId }
val isMessageInChats = fun(chatId: Int): Boolean = messages.any { it.chatId == chatId}

val isUserDeleted = fun(usersId: Int): Boolean = users.any { !it.isDeleted && it.idUser == usersId }
val isChatUnDeleted = fun(chatId: Int): Boolean = chats.any { !it.isDeleted && it.idChat == chatId }
val isUserChatDeleted = fun(usersId: Int, chatId: Int): Boolean = chats.filter { it.userId == usersId } .any { it.idChat == chatId }

val filteredMessagesForDelete_1 = fun(chatId: Int) = messages.filter { it.chatId == chatId } .forEach { it.isDeletedMessage }
val messagesForDelete = fun() = messages.any { it.isDeletedMessage }

val filteredMessagesForDelete_2 = fun(usersId: Int, chatId: Int, messageId: Int): Boolean = messages.filter { it.userId == usersId && !it.isDeletedMessage}
        .any { it.chatId == chatId && it.idMessage == messageId}
val countNonDeletedMessagesInChat = fun(chatId: Int): Int = messages.count { !it.isDeletedMessage && it.chatId == chatId }

val countAllMessagesInChat = fun(chatId: Int): Int = messages.filter { it.chatId == chatId } .count { !it.isDeletedMessage }
val doAllMessagesInChatRead = fun(chatId: Int) = messages.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .forEach { it.isRead }
val printAllMessagesInChat = fun(chatId: Int) = messages.filter { it.chatId == chatId } .forEach { println("${it.text}") }
val maxIdMessageInChat = fun(chatId: Int): Int? = messages.filter { it.chatId == chatId } .filter { !it.isDeletedMessage } .maxOfOrNull { it.idMessage }

val listUnreadMessages = fun() = messages.filter { !it.isDeletedMessage && !it.isRead } .forEach { println("${it.text}") }
val listUnreadLastMessages = fun() = messages.any { !it.isDeletedMessage && !it.isRead }