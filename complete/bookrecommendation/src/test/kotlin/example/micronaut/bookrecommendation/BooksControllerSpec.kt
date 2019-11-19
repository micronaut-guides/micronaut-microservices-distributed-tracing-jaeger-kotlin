package example.micronaut.bookrecommendation

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

object BookControllerSpec: Spek({
    describe("BookController Suite") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client : RxStreamingHttpClient  = embeddedServer.applicationContext.createBean(RxStreamingHttpClient::class.java, embeddedServer.url)

        it("books can be retrieved") {
            val req = HttpRequest.GET<Any>("/books")
            val books = client.jsonStream(req, BookRecommendation::class.java)
            assertEquals(books.toList().blockingGet().size, 1)
            assertEquals(books.toList().blockingGet()[0].name, "Building Microservices")
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})