object ChatService : ChatInterface<Chat, Message, User>{
    var chats = mutableListOf<Chat>()
    var users = mutableListOf<User>()
    var messages = mutableListOf<Message>()

    override fun addUser(user: User): User {
        val newUser = user.copy(idUser = users.size + 1, isDeleted = false)
        users.plusAssign(newUser)
        return users.last()
    }

    override fun deleteUser(user: User, userId: Int): Boolean {
        if (users.isUserDeleted(userId)) {
            users[userId - 1] = user.copy(idUser = userId, isDeleted = true)
            return true
        }
        throw UserWasDeletedException("Пользователь был уже удален или его не существует!")
    }

    override fun addChat(chat: Chat, userId: Int): Boolean {
        if (users.isUserInUsers(userId)) {
            if (!chats.isUserInChats(userId)) {
                val newChat = chat.copy(idChat = chats.size + 1, userId = userId)
                chats.plusAssign(newChat)
                return true
            }
            throw ChatFoundException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
       }
        throw UserNotFoundException("Пользователь не найден!")
    }

    override fun deleteChat(chat: Chat, chatId: Int, idUser: Int): Boolean {
        if (users.isUserInUsers(idUser)) {
            if (chats.isUserChatDeleted(idUser, chatId)) {
                chats[chatId - 1] = chat.copy(idChat = chatId, userId = idUser, isDeleted = true)
                if (messages.isMessageInChats(chatId)) {
                    messages.filteredMessagesForDelete1(chatId)
                }
                return true
            }
            throw ChatUserIncorrectedException("Данный чат и пользователь не совместимы!")
        }
        throw UserNotFoundException("Пользователь не найден! Добавьте пользователя в свои контакты!")
    }

    override fun createMessage(message: Message, idUser: Int, chatId: Int): Message {
        if (users.isUserInUsers(idUser)) {
            if (chats.isUserChatDeleted(idUser, chatId)) {
                val newMessage = message.copy(idMessage = messages.size + 1, chatId = chatId, userId = idUser)
                messages.plusAssign(newMessage)
                return messages.last()
            }
            throw ChatUserIncorrectedException("Вы не можете отправить сообщение пользователю. Создайте сначала чат!")
        }
        throw UserNotFoundException("Пользователь не найден! Добавьте пользователя в свои контакты!")
    }

    override fun deleteMessage(chat: Chat, message: Message, idMessage: Int, idUser: Int, chatId: Int): Boolean {
        if (messages.filteredMessagesForDelete2(idUser, chatId, idMessage)) {
            messages[idMessage - 1] = message.copy(isDeletedMessage = true)
            return true
        }
        throw MessageNotFind("Сообщение не найдено!")
    }

    override fun editMessage(newMessage: Message, idMessage: Int): Boolean {
        if (messages.isMessageNotDeleted(idMessage)) {
            for ((index, message) in messages.withIndex()) {
                messages[index] = message.copy(idMessage = newMessage.idMessage, text = newMessage.text)
                return true
            }
        }
        throw MessageNotFind("Сообщение не найдено!")
    }

    override fun receiveUnreadMessagesInChat() {
        for (chat in chats) {
            if (!chat.isDeleted) {
                if (messages.listUnreadLastMessages()) {
                    println("В чате с id - ${chat.idChat} отсутствуют непрочитанные сообщения!")
                }
                println("В чате с id - ${chat.idChat} есть непрочитанные сообщения!")
            }
        }
    }

    override fun receiveAllMessagesInChat(chatId: Int) {
        val countMessages = messages.countAllMessagesInChat(chatId)
        println("Всего сообщений в чате: $countMessages.")
        messages.doAllMessagesInChatRead(chatId)
        messages.printAllMessagesInChat(chatId)
        println("Последнее messageID: ${messages.maxIdMessageInChat(chatId)}")
    }
}

class ChatFoundException(message: String) : RuntimeException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
class UserNotFoundException(message: String) : RuntimeException("Пользователь не найден!")
class UserWasDeletedException(message: String) : RuntimeException("Пользователь был уже удален или его не существует!")
class ChatUserIncorrectedException(message: String) : RuntimeException("Вы не можете отправить сообщение пользователю. Создайте сначала чат!")
class MessageNotFind(message: String) : RuntimeException("Сообщение не найдено!")
class ChatIs(message: String) : RuntimeException("Чат уже существует с другим пользователем")

//    override fun createChatFirstMessage(message: Message, chat: Chat, idUser: Int): Message {
//        if (users.isUserInUsers(idUser)) {
//            if (!chats.isUserInChats(idUser)) {
//                val newChat = chat.copy(idChat = chats.size + 1, userId = idUser)
//                chats.plusAssign(newChat)
//                val firstMessage = message.copy(idMessage = messages.size + 1, chatId = chats.size, userId = idUser)
//                messages.plusAssign(firstMessage)
//                return messages.last()
//            }
//            throw ChatFoundException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
//        }
//        throw UserNotFoundException("Пользователь не найден!")
//    }