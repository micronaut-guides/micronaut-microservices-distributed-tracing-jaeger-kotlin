package example.micronaut.bookrecommendation

import io.reactivex.Maybe

import javax.validation.constraints.NotBlank

interface BookInventoryOperations {
    fun stock(@NotBlank isbn: String): Maybe<Boolean>
}
