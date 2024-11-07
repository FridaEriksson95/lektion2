package com.example.lektion2

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var getName : EditText
    lateinit var getSalary : EditText
    lateinit var lvEmployee: ListView
    lateinit var adapter: EmployeeAdapter
    var employeeList = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        getName = findViewById(R.id.get_name)
        getSalary =findViewById(R.id.get_salary)
        lvEmployee = findViewById(R.id.lv_employees)

        val btnAdd : Button = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            createEmployee()
        }


        //val e = Employee("Frida", 29.0)
        //Log.i("sout", e.toString())
        //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()

        //this.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,employeeList)
        this.adapter = EmployeeAdapter(this, employeeList)

        lvEmployee.adapter = this.adapter

        lvEmployee.setOnItemLongClickListener {parent, view, position, id ->
            employeeList.removeAt(position)

            adapter.notifyDataSetChanged()

            true
        }
    }

    fun createEmployee(){
        if(getName.text.isNotBlank() && getSalary.text.isNotBlank()) {
            val name = getName.text.toString()
            val salary = getSalary.text.toString().toDouble()
            //Toast.makeText(this, "Input recieved", Toast.LENGTH_SHORT).show()

            val e = Employee(name, salary)
            employeeList.add(e)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            getName.text.clear()
            getSalary.text.clear()
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "no empty fields", Toast.LENGTH_SHORT).show()
        }
    }
}