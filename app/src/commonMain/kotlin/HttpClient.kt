import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val httpClient = HttpClient(Js) {
	install(Logging) {
		logger = object : Logger {
			override fun log(message: String) {
				Napier.v { "$message\n" }
			}
		}
		level = LogLevel.HEADERS
	}
	install(ContentNegotiation) {
		json(Json {
			ignoreUnknownKeys = true
			explicitNulls = false
		})
	}
}.also {
	Napier.base(DebugAntilog())
}
