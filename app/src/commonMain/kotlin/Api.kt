import io.github.aakira.napier.Napier
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

object Api {

	private const val META_BASE_URL = "https://piston-meta.mojang.com/mc/game/"
	private const val PATCH_NOTES_BASE_URL = "https://launchercontent.mojang.com/v2/"

	suspend fun getVersionManifest(): Result<JavaVersionManifest> = runCatching {
		httpClient.get(META_BASE_URL) {
			url {
				appendPathSegments("version_manifest_v2.json")
			}
		}.body<JavaVersionManifest>()
	}.onFailure { handleError(it) }

	suspend fun getJavaPatchNotes(): Result<PatchNotes<JavaPatchNote>> = runCatching {
		httpClient.get(PATCH_NOTES_BASE_URL) {
			url {
				appendPathSegments("javaPatchNotes.json")
			}
		}.body<PatchNotes<JavaPatchNote>>()
	}.onFailure { handleError(it) }

	private fun handleError(throwable: Throwable) {
		val tag = "Api"
		when (throwable) {
			is ClientRequestException -> Napier.e(tag = tag, throwable = throwable) {
				throwable.message
			}

			is ServerResponseException -> Napier.e(tag = tag, throwable = throwable) {
				throwable.message
			}

			is HttpRequestTimeoutException -> Napier.e(tag = tag, throwable = throwable) {
				throwable.message
					?: "Request timed out"
			}

			is Exception -> Napier.e(tag = tag, throwable = throwable) {
				throwable.message
					?: "Unknown error"
			}
		}
	}
}
