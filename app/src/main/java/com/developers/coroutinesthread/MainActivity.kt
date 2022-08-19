package com.developers.coroutinesthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnActionRefresh: Button
    private lateinit var btnActionClear: Button
    private lateinit var btnCode1: Button
    private lateinit var btnCode2: Button
    private lateinit var tvConsole: TextView
    private lateinit var tvLog: TextView

    private lateinit var codeMine: CodeMine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCode1 = findViewById(R.id.btn_c_1)
        btnCode2 = findViewById(R.id.btn_c_2)
        btnActionRefresh = findViewById(R.id.btn_c_refresh)
        btnActionClear = findViewById(R.id.btn_c_clear)
        tvConsole = findViewById(R.id.tv_console)
        tvLog = findViewById(R.id.tv_log)

        btnCode1.setOnClickListener(this)
        btnCode2.setOnClickListener(this)
        btnActionRefresh.setOnClickListener(this)
        btnActionClear.setOnClickListener(this)

        codeMine = CodeMine()
    }

    private fun codeMine(codeId: String): String {
        return when (codeId) {
            "refresh-message" -> {
                if (codeMine.messageCode != tvConsole.text) {
                    codeMine.getMessage()
                } else {
                    codeMine.clearMessage()
                }
            }
            "clear-message" -> codeMine.clearMessage()
            "thread-1" -> codeMine.thread1()
            "multiple-thread" -> codeMine.thread2()
            else -> "No Command Exist"
        }
    }

    private fun writeConsole(text: String) {
        tvConsole.text = text
    }

    private fun writeLog(logType: String, logMessage: String) {
        val currentTime = Calendar.getInstance()
        val currentDateTime = "${currentTime.get(Calendar.YEAR)}/${currentTime.get(Calendar.MONTH)}/${currentTime.get(Calendar.DATE)} ${currentTime.get(Calendar.HOUR)}:${currentTime.get(Calendar.MINUTE)}:${currentTime.get(Calendar.SECOND)}:${currentTime.get(Calendar.MILLISECOND)}"
        tvLog.text = "$currentDateTime - $logType: $logMessage\n${tvLog.text}"
    }

    override fun onClick(v: View?) {
        val message = when (v?.id) {
            R.id.btn_c_refresh -> codeMine("refresh-message")
            R.id.btn_c_clear  -> {
                tvLog.text = ""
                ""
            }
            R.id.btn_c_1 -> codeMine("thread-1")
            R.id.btn_c_2 -> codeMine("multiple-thread")

            else -> "Error!"
        }

        writeConsole(message)
        writeLog("Exec Log", message)
    }
}