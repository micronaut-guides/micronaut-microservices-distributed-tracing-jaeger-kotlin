package example.micronaut.bookcatalogue

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object BooksControllerSpec: Spek({
    describe("BookController Suite") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client : HttpClient  = HttpClient.create(embeddedServer.url)

        it("books can be retrieved") {
            val req = HttpRequest.GET<Any>("/books") // <1>
            val books = client.toBlocking().retrieve(req, Argument.of(List::class.java, Book::class.java)) // <2>
            assertEquals(expected = books.size, actual = 3)
            assertTrue(books.contains(Book("1491950358", "Building Microservices")))
            assertTrue(books.contains(Book("1680502395", "Release It!")))
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})