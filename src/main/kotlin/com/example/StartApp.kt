package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StartApp

fun main(args: Array<String>) {
    runApplication<StartApp>(*args)
}

