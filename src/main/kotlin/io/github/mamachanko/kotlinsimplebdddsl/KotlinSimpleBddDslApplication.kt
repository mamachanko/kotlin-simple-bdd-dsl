package io.github.mamachanko.kotlinsimplebdddsl

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinSimpleBddDslApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinSimpleBddDslApplication::class.java, *args)
}
