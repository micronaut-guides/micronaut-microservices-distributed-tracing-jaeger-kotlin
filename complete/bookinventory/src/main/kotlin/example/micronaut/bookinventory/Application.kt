package example.micronaut.bookinventory

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("example.micronaut.bookinventory")
                .mainClass(Application.javaClass)
                .start()
    }
}