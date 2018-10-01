package example.micronaut.bookrecommendation

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Flowable

@Client("http://localhost:8081") // <1>
interface BookCatalogueClient : BookCatalogueOperations {

    @Get("/books")
    override fun findAll(): Flowable<Book>
}