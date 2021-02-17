import ChatService.addUser
import ChatService.chats
//import ChatService.createChat
import ChatService.deleteChat
import ChatService.createChatFirstMessage
import ChatService.createNewMessage
import ChatService.deleteMessage
import ChatService.deleteUser
import ChatService.messages
import ChatService.receiveAllMessagesInChat
import ChatService.receiveUnreadMessagesInChat
import ChatService.users

fun main(args: Array<String>) {

    val user_1 = User(0, "Nik", "Marty", false)
    val user_2 = User(0, "Rik", "Morty", false)
    val user_3 = User(0, "Alex", "Alex", false)
    val user_4 = User(0, "Alexis", "Alexis", false)

    addUser(user_2)
    addUser(user_1)
    addUser(user_4)
    addUser(user_4)
    addUser(user_3)
//    println(users)

//    deleteUser(user_4, 6) // Exception: is OK
//    println(users)

    val chat_1 = Chat(0, "Nik", 0, false, false)
    val chat_2 = Chat(0, "Nik", 0, false, false)

    val mess_1 = Message(0,0, 0, "Hello mess", false, false)
    val mess_2 = Message(0,0, 0, "Hello mess2", false, false)

    createChatFirstMessage(mess_1, chat_1, 2)
    createChatFirstMessage(mess_2, chat_2, 1)

//    println(chats)
    println(messages)

//    deleteChat(chat_2, 1, 2) // Exception: is OK / Correct userId: is OK

    deleteMessage(chat_2, mess_2,2, 1, 2)
    println(messages)
//    println(users)
    println(chats)



//    receiveUnreadMessagesInChat()
//    receiveAllMessagesInChat(2)
//    createNewMessage(mess_2, 2, 1)
//    println(chats)
//    println(messages)
//
//    println(users)
//    println("***************************************")
//    println(chats)
//    println(users)

//
//    deleteUser(user_2, 2)
//
//    println(users)
//    deleteUser(user_1,1)
//    println("***************************************")
//    println(users)
//    println(chats)
////
//    println(isUserDeleted(1))
//    println(isUserDeleted(2))


}

