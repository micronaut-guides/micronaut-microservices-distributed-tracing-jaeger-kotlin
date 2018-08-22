package example.micronaut.bookrecommendation

import io.reactivex.Flowable

interface BookCatalogueOperations {
    fun findAll(): Flowable<Book>
}
