import kotlinx.serialization.Serializable

@Serializable
data class JavaLatestVersions(val release: String, val snapshot: String)

@Serializable
data class JavaVersion(val id: String, val type: String, val url: String, val time: String, val releaseTime: String)

@Serializable
data class JavaVersionManifest(val latest: JavaLatestVersions, val versions: List<JavaVersion>)

@Serializable
data class PatchNoteImage(val title: String, val url: String)

interface PatchNote

@Serializable
data class JavaPatchNote(
	val title: String,
	val version: String,
	val type: String,
	val image: PatchNoteImage,
	val contentPath: String,
	val id: String,
	val date: String,
	val shortText: String,
) : PatchNote

@Serializable
data class BedrockPatchNote(
	val title: String,
	val version: String,
	val patchNoteType: String,
	val image: PatchNoteImage,
	val contentPath: String,
	val id: String,
	val date: String,
	val shortText: String,
) : PatchNote

@Serializable
data class DungeonsPatchNote(
	val title: String,
	val version: String,
	val date: String,
	val image: PatchNoteImage,
	val body: String,
	val id: String,
	val contentPath: String,
) : PatchNote

@Serializable
data class LegendsPatchNote(
	val title: String,
	val version: String,
	val patchNoteType: String,
	val date: String,
	val image: PatchNoteImage,
	val body: String,
	val id: String,
	val contentPath: String,
) : PatchNote

@Serializable
data class PatchNotes<T : PatchNote>(val entries: List<T>)
