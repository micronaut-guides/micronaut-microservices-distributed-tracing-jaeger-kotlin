package example.micronaut.bookrecommendation

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

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