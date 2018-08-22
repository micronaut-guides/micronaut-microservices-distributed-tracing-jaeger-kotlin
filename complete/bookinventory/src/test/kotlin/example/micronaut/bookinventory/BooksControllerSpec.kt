package example.micronaut.bookinventory

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object BookControllerSpec: Spek({
    describe("BookController Suite") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client : HttpClient  = HttpClient.create(embeddedServer.url)

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
            val ex = assertFailsWith<HttpClientException> {
                val req = HttpRequest.GET<Any>("/books/stock/XXXXX")
                val hasStock = client.toBlocking().retrieve(req, Boolean::class.java)
            }
            assertEquals(ex.message, "Page Not Found")
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})