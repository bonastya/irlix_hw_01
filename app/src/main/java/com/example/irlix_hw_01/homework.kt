package com.example.irlix_hw_01

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
}

class Magazine(override val price: Double, override val wordCount: Int) : Publication {
    override fun getType():String {
        return "Magazine"
    }
}