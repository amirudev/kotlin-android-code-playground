package com.developers.coroutinesthread

class CodeMine {
    var messageCode = ""
    private var printedMessage = ""

    fun thread1(): String {
        val thread = Thread {
            messageCode = "${Thread.currentThread()} has run."
        }.start()

        return messageCode
    }

    fun thread2(): String {
        val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
        repeat(3) {
            Thread {
                messageCode += "${Thread.currentThread()} has started\n"

                for (i in states) {
                    messageCode += "${Thread.currentThread()} - $i\n"
                    Thread.sleep(50)
                }
            }.start()
        }

        return messageCode
    }

    fun getMessage(): String {
        return messageCode
    }

    fun clearMessage(): String {
        messageCode = ""

        return ""
    }
}