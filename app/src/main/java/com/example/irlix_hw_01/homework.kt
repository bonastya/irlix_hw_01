package com.example.irlix_hw_01

import android.content.ContentValues.TAG
import android.util.Log

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



}

fun buy(publication:Publication){
    println("The purchase is complete. The purchase amount was "+publication.price)

}