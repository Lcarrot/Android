package com.example.myapplication

class Database {
    private val users = arrayListOf(User("Mihail", "Zubenko", "lapa@gmail.com", "myGrail123"),
                                    User("Valery", "Albertovich","babaika@mail.ru", "Nggd453454")
    )

    fun checkUser(mail: String?, password: String?): Boolean {
        for (i in 0 until users.size) {
            if (mail == users[i].mail && password == users[i].password) {
                return true
            }
        }
        return false
    }

    fun getUser(mail: String?, password: String?): User? {
        for (i in 0 until users.size) {
            if (mail == users[i].mail && password == users[i].password) {
                return users[i]
            }
        }
        return null
    }
}
