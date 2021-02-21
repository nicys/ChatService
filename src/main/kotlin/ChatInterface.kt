interface ChatInterface<C, M, U> {

    fun addUser(u: U): U

    fun deleteUser(u: U, i: Int): Boolean

    fun addChat(c: C, i: Int): Boolean

    fun deleteChat(c: C, i: Int, y: Int): Boolean

    fun createMessage(m: M, i: Int, y: Int): M

    fun deleteMessage(c: C, m: M, i: Int, y: Int, z: Int): Boolean

    fun editMessage(m: M, i: Int): Boolean

    fun receiveUnreadMessagesInChat()

    fun receiveAllMessagesInChat(i: Int)

}