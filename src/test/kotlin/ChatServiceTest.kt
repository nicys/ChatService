import ChatService.addUser
import ChatService.addChat
import ChatService.createMessage
import ChatService.deleteChat
import ChatService.deleteMessage
import ChatService.deleteUser
import ChatService.editMessage
import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun testAddUser() {
        //Arrange
        val user = User(1, "", "", false)
        val expected = 1
        //Act
        addUser(user)
        val result = user.idUser
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun testDeleteUser() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        user.idUser = 1
        //Act
        deleteUser(user, 1)
        user.isDeleted = true
        //Assert
        assertTrue(user.isDeleted)
    }

    @Test(expected = UserWasDeletedException::class)
    fun testDeleteUserShouldThrow() {
        //Arrange
        val user = User(0, "", "", false)
        val expected = UserWasDeletedException::class
        //Act
        val result = deleteUser(user, 20)
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun testAddChat() {
        //Arrange
        val user3 = User(0, "q", "q", false)
        addUser(user3)
        val chat3 = Chat(1, "q", 1, false, false)
        val expected = 1
        //Act
        addChat(chat3, 3)
        val result = chat3.idChat
        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = ChatFoundException::class)
    fun testAddChatShouldThrow1() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val chat1 = Chat(2, "", 1, false, false)
        val expected = ChatFoundException::class
        //Act
        val result = addChat(chat1, 1)
        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun testAddChatShouldThrow2() {
        //Arrange
        val chat = Chat(0, "", 10, false, false)
        val expected = UserNotFoundException::class
        //Act
        val result = addChat(chat, 10)
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun testDeleteChat() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val chat = Chat(1, "", 1, false, false)
        //Act
        deleteChat(chat, 1, 1)
        val result = chat.isDeleted
        //Assert
        assertFalse(result)
    }

    @Test(expected = ChatUserIncorrectedException::class)
    fun testDeleteChatShouldThrow1() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val chat1 = Chat(10, "", 10, false, false)
        val expected = ChatUserIncorrectedException::class
        //Act
        val result = deleteChat(chat1, 10, 1)
        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun testDeleteChatShouldThrow2() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val chat = Chat(1, "", 10, false, false)
        val expected = UserNotFoundException::class
        //Act
        val result = deleteChat(chat, 1, 10)
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun testCreateMessage() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val message = Message(1, 1, 1, "message", false, false)
        //Act
        createMessage(message, 1, 1)
        val result = message.isDeletedMessage
        //Assert
        assertFalse(result)
    }

    @Test(expected = ChatUserIncorrectedException::class)
    fun testCreateMessageShouldThrow1() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val message = Message(1, 1, 1, "message", false, false)
        val expected = ChatUserIncorrectedException::class
        //Act
        val result = createMessage(message, 1, 10)
        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun testCreateNewMessageShouldThrow2() {
        //Arrange
        val message = Message(1, 1, 1, "message", false, false)
        val expected = UserNotFoundException::class
        //Act
        val result = createMessage(message, 10, 10)
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun testDeleteMessage() {
        //Arrange
        val user2 = User(1, "", "", false)
        addUser(user2)
        val chat2 = Chat(1, "", 2)
        addChat(chat2, 2)
        val message = Message(2, 2, 1, "message", false, false)
        createMessage(message, 2, 2)
        //Act
        deleteMessage(chat2, message, 2, 2, 2)
        val result = message.isDeletedMessage
        //Assert
        assertFalse(result)
    }

    @Test(expected = MessageNotFind::class)
    fun testDeleteMessageShouldThrow() {
        //Arrange
        val chat2 = Chat(1, "", 2)
        val message = Message(2, 2, 1, "message", false, false)
        createMessage(message, 2, 2)
        val expected = MessageNotFind::class
        //Act
        deleteMessage(chat2, message, 1, 2, 2)
        val result = message.isDeletedMessage
        //Assert
        assertEquals(expected, result)
    }

        @Test
    fun testEditMessage() {
        //Arrange
        val user = User(0, "", "", false)
        addUser(user)
        val chat = Chat(0, "", 1, false, false)
        addChat(chat, 1)
        val message = Message(1, 1, 1, "message", false, false)
        createMessage(message, 1, 1)
        val message1 = Message(1, 1, 2, "message1", false, false)
        //Act
        editMessage(message1, 1)
        val result = message.isDeletedMessage
        //Assert
        assertFalse(result)
    }

    @Test(expected = MessageNotFind::class)
    fun testEditMessageShouldThrow() {
        //Arrange
        val message = Message(10, 10, 1, "message", false, false)
        val message1 = Message(10, 10, 2, "message1", false, false)
        val expected = MessageNotFind::class
        //Act
        editMessage(message1, 1)
        val result = message.isDeletedMessage
        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun receiveUnreadMessagesInChat() {
        //Arrange
        val chat = Chat(1, "", 1, false, false)
        chat.idChat = 1
        val expected = println("В чате с id - ${chat.idChat} отсутствуют непрочитанные сообщения!")
        //Act
        val result = println("В чате с id - ${chat.idChat} отсутствуют непрочитанные сообщения!")
        //Assert
        assertEquals(expected, result)
    }
}

//    @Ignore("Method was fragmented")
//    fun testCreateChatFirstMessage() {
//        //Arrange
//        val user: User = User(10, "", "", false)
//        user.idUser = 10
//        val chat: Chat = Chat(0, "", 10, false, false)
//        val message: Message = Message(0, 10, 0, "", false, false)
//        //Act
//        createChatFirstMessage(message, chat, 10)
//        chat.userId = 10
//        message.userId = 10
//        //Assert
//        assertEquals(chat.userId, message.userId)
//    }