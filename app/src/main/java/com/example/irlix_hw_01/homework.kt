package com.example.irlix_hw_01

import java.lang.Exception

interface Publication {
    val price:Double
    val wordCount:Int

    fun getType():String {
        return ""
    }
}

class Book(override val price: Double, override val wordCount: Int) : Publication {

    override fun getType():String {
        if(wordCount<=1000)
            return "Flash Fiction"
        else if(wordCount<=7500)
            return "Short Story"
        else
            return "Novel"
    }

    override fun equals(other: Any?): Boolean {
        if(other is Book){
            return price==other.price && wordCount == other.wordCount && getType()==other.getType()
        }
        else
            return super.equals(other)
    }


}

class Magazine(override val price: Double, override val wordCount: Int) : Publication {
    override fun getType():String {
        return "Magazine"
    }
}


fun main(){

    println("--------------------------------------------")
    println("Создаются и выводятся 2 объекта Book и 1 - Magazine")

    val book1 = Book(45.8, 1500)
    val book2 = Book(100.7, 8800)
    val magazine = Magazine(10.7, 20)

    println("1. Тип: "+book1.getType()+", Кол-во строк:"+book1.wordCount+", Цена:"+book1.price)
    println("2. Тип: "+book2.getType()+", Кол-во строк:"+book2.wordCount+", Цена:"+book2.price)
    println("3. Тип: "+magazine.getType()+", Кол-во строк:"+magazine.wordCount+", Цена:"+magazine.price)

    println("--------------------------------------------")
    println("Два объекта сравниваются сначала по ссылке, затем через переопределенный equals")

    val book3 = Book(100.7, 8800)
    if(book2===book3)
        println("Это одна и та же книга(по ссылке)")
    else
        println("Это разные книги(по ссылке)")

    if(book2.equals(book3))
        println("Это одна и та же книга(по equals)")
    else
        println("Это разные книги(по equals)")

    println("--------------------------------------------")
    println("Метод buy с notnull параметром работает только с notnull агрументом")
    val book4 = Book(100.7, 8800)
    val book5:Book? = null

    buy(book4)
    //buy(book5)
    //Вызов сделать не получится так как в условии сказано что
    //метод buy должен принимать notnull значения
    //а передать в него - null
    //(можно исправить добавив ? к типу параметра publication в buy)

    println("--------------------------------------------")
    println("Лямбда-выражение суммирует 2 числа и выводит в консоль")
    val sum = {x: Int, y: Int ->
        println(x + y)

    }
    sum(10, 15)

    println("--------------------------------------------")
    println("Ленивое поле startTime класса User")
    //одно и то же поле два раза
    val user = User(1,"nastya",21,Type.FULL)
    println("Время:"+user.startTime)
    Thread.sleep(1000)
    println("Время:"+user.startTime)

    println("--------------------------------------------")
    println("Работа со списками User")

    val userList:MutableList<User> = mutableListOf(user)
    userList.apply {
        userList.add(User(2,"olya",22,Type.DEMO))
        userList.add(User(3,"kirill",20,Type.FULL))
        userList.add(User(4,"anna",21,Type.DEMO))
        userList.add(User(5,"sophie",18,Type.DEMO))
        userList.add(User(6,"vlad",210,Type.FULL))
    }

    val userListWithFull:List<User> = userList.filter { user -> user.type == Type.FULL}

    val userNamesListWithFull:List<String> =  userListWithFull.map { user -> user.name}
    println("Первый user списка: "+userNamesListWithFull.first())
    println("Последний user списка: "+userNamesListWithFull.last())


    val registration = Action.Registration()
    val login = Action.LogIn(user)
    val logout = Action.LogOut()

    doAction(registration)
    doAction(login)
    doAction(logout)

    auth({ updateCache() }, login.user)




}

fun buy(publication:Publication){
    println("The purchase is complete. The purchase amount was "+publication.price)

}

enum class Type() {
    DEMO,
    FULL
}

data class User(val id:Int, val name: String, val age:Int, val type: Type){

    val startTime: String by lazy { java.util.Calendar.getInstance().time.toString() }

}

fun User.isElderThan18(){
    if(this.age>=18){
        println("User старше 18 лет ")
    }
    else
        throw Exception("User младше 18 лет")
}

interface AuthCallback {
    fun authSuccess() {}
    fun authFailed() {}
}

val authCallback = object: AuthCallback{
    override fun authSuccess() {
        println("Авторизация успешна")
    }
    override fun authFailed() {
        println("Авторизация не успешна")
    }
}

inline fun auth( updateCache: () -> Unit, user: User){
//-	Реализовать inline функцию auth,
// принимающую в качестве параметра функцию updateCache.
// Функция updateCache должна выводить в лог информацию об обновлении кэша.

    try{
        user.isElderThan18()
        authCallback.authSuccess()
        updateCache()
    }
    catch(e:Exception) {
        authCallback.authFailed()
    }


}

fun updateCache(){
    println("Обновление кэша")
}


sealed class Action {
    class Registration(): Action(){}
    class LogIn(val user: User): Action(){}
    class LogOut(): Action(){}
}

fun doAction(action: Action){

    if (action is Action.LogIn)
        println("Action LogIn")
    if (action is Action.LogOut)
        println("Action LogOut")
    if (action is Action.Registration)
        println("Action Registration")
}

