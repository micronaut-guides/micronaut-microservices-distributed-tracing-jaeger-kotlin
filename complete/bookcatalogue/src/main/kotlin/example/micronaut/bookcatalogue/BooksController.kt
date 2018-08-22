package example.micronaut.bookcatalogue

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/books")
class BooksController {

    @Get("/")
    internal fun index(): List<Book> {
        return listOf(Book("1491950358", "Building Microservices"),
                Book("1680502395", "Release It!"),
                Book("0321601912", "Continuous Delivery:"))
    }
}
