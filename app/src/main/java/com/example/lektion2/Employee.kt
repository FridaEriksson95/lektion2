package com.example.lektion2

data class Employee(val name: String, val salary : Double) {

    val id: Int = generateId()

    companion object{
        private var idCounter = 0

        private fun generateId():Int{
            idCounter++

            return idCounter
        }
    }

    override fun toString(): String {
        return "Employee name: $name, salary: $salary "
    }


}