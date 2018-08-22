package example.micronaut.bookcatalogue

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("example.micronaut.bookcatalogue")
                .mainClass(Application.javaClass)
                .start()
    }
}