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
        if (isUserDeleted(userId)) {
            users[userId - 1] = user.copy(idUser = userId, isDeleted = true)
            return true
        }
        throw UserWasDeletedException("Пользователь был уже удален или его не существует!")
    }

    override fun createChatFirstMessage(message: Message, chat: Chat, idUser: Int): Message {
        if (isUserInUsers(idUser)) {
            if (!isUserInChats(idUser)) {
                val newChat = chat.copy(idChat = chats.size + 1, userId = idUser)
                chats.plusAssign(newChat)
                val firstMessage = message.copy(idMessage = messages.size + 1, chatId = chats.size, userId = idUser)
                messages.plusAssign(firstMessage)
                return messages.last()
            }
            throw ChatFoundException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
        }
        throw UserNotFoundException("Пользователь не найден!")
    }

    override fun deleteChat(chat: Chat, chatId: Int, idUser: Int): Boolean {
        if (isUserInUsers(idUser)) {
            if (isUserChatDeleted(idUser, chatId)) {
                chats[chatId - 1] = chat.copy(idChat = chatId, userId = idUser, isDeleted = true)
                if (isMessageInChats(chatId)) {
                    filteredMessagesForDelete_1(chatId)
                }
                return true
            }
            throw ChatUserIncorrectedException("Данный чат и пользователь не совместимы!")
        }
        throw UserNotFoundException("Пользователь не найден! Добавьте пользователя в свои контакты!")
    }

    override fun createNewMessage(message: Message, idUser: Int, chatId: Int): Message {
        if (isUserInUsers(idUser)) {
            if (isUserChatDeleted(idUser, chatId)) {
                val newMessage = message.copy(idMessage = messages.size + 1, chatId = chatId, userId = idUser)
                messages.plusAssign(newMessage)
                return messages.last()
            }
            throw ChatUserIncorrectedException("Данный чат и пользователь не совместимы!")
        }
        throw UserNotFoundException("Пользователь не найден! Добавьте пользователя в свои контакты!")
    }

    override fun deleteMessage(chat: Chat, message: Message, idMessage: Int, idUser: Int, chatId: Int): Boolean {
        if (filteredMessagesForDelete_2(idUser, chatId, idMessage)) {
            if (countNonDeletedMessagesInChat(chatId) == 1) {
                chats[chatId - 1] = chat.copy(idChat = chatId, userId = idUser, isDeleted = true)
                messages[chatId - 1] = message.copy(isDeletedMessage = true)
            } else {
                messages[chatId - 1] = message.copy(isDeletedMessage = true)
            }
            return true
        }
        throw MessageNotFind("Сообщение не найдено!")
    }

    override fun editMessage(message: Message, idMessage: Int, idUser: Int, chatId: Int): Boolean {
        if (filteredMessagesForDelete_2(idUser, chatId, idMessage)) {
                val newMessage = message.copy(text = message.text)
                messages.plusAssign(newMessage)
                return true
            }
        throw MessageNotFind("Сообщение не найдено!")
    }

    override fun receiveUnreadMessagesInChat() {
        for (chat in chats) {
            if (!chat.isDeleted) {
                if (!listUnreadLastMessages()) {
                    println("В чате с id - ${chat.idChat} отсутствуют непрочитанные сообщения!")
                }
                println("В чате с id - ${chat.idChat} есть непрочитанные сообщения!")
            }
        }
    }

    override fun receiveAllMessagesInChat(chatId: Int) {
        val countMessages = countAllMessagesInChat(chatId)
        println("Всего сообщений в чате: $countMessages.")
        doAllMessagesInChatRead(chatId)
        printAllMessagesInChat(chatId)
        println("Последнее messageID: ${maxIdMessageInChat(chatId)}")
    }




}

class ChatFoundException(message: String) : RuntimeException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
class UserNotFoundException(message: String) : RuntimeException("Пользователь не найден!")
class UserWasDeletedException(message: String) : RuntimeException("Пользователь был уже удален или его не существует!")
class ChatUserIncorrectedException(message: String) : RuntimeException("Данный чат и пользователь не совместимы!")
class MessageNotFind(message: String) : RuntimeException("Сообщение не найдено!")

class ChatNotFoundException(message: String) : RuntimeException("Вы не можете отправить сообщение! Создайте сначала чат!")
class ChatWasDeletedException(message: String) : RuntimeException("Чат был удален! Вы не можете удалить его дважды!")
class UserIsNotInChat(message: String) : RuntimeException("Пользователь в данном чате не общается!")


//    fun createChat(chat: Chat, idUser: Int): Chat {
//        if (isUserInUsers(idUser)) {
//            if (!isUserInChats(idUser)) {
//                val newChat = chat.copy(idChat = chats.size + 1, userId = idUser)
//                chats.plusAssign(newChat)
//                return chats.last()
//            }
//            throw ChatFoundException("Чат с данным пользователем уже существует. Вы не можете создать второй чат!")
//        }
//        throw UserNotFoundException("Пользователь не найден! Добавьте пользователя в свои контакты!")
//    }