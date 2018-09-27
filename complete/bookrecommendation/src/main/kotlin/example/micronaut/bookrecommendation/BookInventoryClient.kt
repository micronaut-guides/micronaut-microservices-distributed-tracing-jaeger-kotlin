package example.micronaut.bookrecommendation

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe

import javax.validation.constraints.NotBlank

@Client("http://localhost:8082")
interface BookInventoryClient : BookInventoryOperations {

    @Get("/books/stock/{isbn}")
    override fun stock(@NotBlank isbn: String): Maybe<Boolean>
}