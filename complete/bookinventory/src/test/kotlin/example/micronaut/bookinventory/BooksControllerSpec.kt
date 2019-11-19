package example.micronaut.bookinventory

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

object BookControllerSpec : Spek({
    describe("BookController Suite") {
        var embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client: HttpClient = HttpClient.create(embeddedServer.url)

        it("a book in stock returns true") {
            val req = HttpRequest.GET<Any>("/books/stock/1491950358")
            val hasStock = client.toBlocking().retrieve(req, Boolean::class.java)
            assertTrue(hasStock)
        }
        it("a book out of stock returns false") {
            val req = HttpRequest.GET<Any>("/books/stock/1680502395")
            val hasStock = client.toBlocking().retrieve(req, Boolean::class.java)
            assertFalse(hasStock)
        }

        it("non existing Isbn returns 404") {
            var exceptionThrown = false
            try {
                val req = HttpRequest.GET<Any>("/books/stock/XXXXX")
                client.toBlocking().retrieve(req, Boolean::class.java)
            } catch (e: HttpClientException) {
                exceptionThrown = true
            }
            assertTrue(exceptionThrown)
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})