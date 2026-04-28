package com.example.mostrawell.domain.util

fun validateAge(age: String): String? {       //Return age as a string if valid, else return null
    try {
        val ageNum = age.toInt()
        if (ageNum in 12..100) {        //Неясная причина отказа, если возраст выходит за допустимые рамки
            return age
        }
        return null
    }
    catch (e: NumberFormatException) {
        return null
    }
}