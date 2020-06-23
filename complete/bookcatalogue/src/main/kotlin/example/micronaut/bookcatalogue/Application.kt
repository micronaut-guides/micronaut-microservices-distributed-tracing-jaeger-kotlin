package example.micronaut.bookcatalogue

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("example.micronaut.bookcatalogue")
		.start()
}

