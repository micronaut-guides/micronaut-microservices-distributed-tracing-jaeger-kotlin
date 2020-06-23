package example.micronaut.bookrecommendation

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("example.micronaut.bookrecommendation")
		.start()
}

