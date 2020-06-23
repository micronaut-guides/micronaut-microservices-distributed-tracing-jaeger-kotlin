package example.micronaut.bookinventory

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("example.micronaut.bookinventory")
		.start()
}

