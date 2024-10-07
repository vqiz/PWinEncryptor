import java.util.Properties

plugins {
    id("java")
    application
}

group = "org.vqiz"

// Funktion zum Erhöhen der Patch-Version
fun incrementVersion(version: String): String {
    val versionParts = version.split(".").toMutableList()
    val patchVersion = versionParts.last().toInt() + 1
    versionParts[versionParts.size - 1] = patchVersion.toString()
    return versionParts.joinToString(".")
}

// Datei, die die Versionsnummer enthält
val versionFile = file("version.properties")

// Prüfen, ob die Versionsdatei existiert
if (!versionFile.exists()) {
    throw GradleException("version.properties file not found!")
}

// Properties-Objekt laden, um die Versionsnummer zu lesen
val versionProps = Properties()
versionFile.inputStream().use { stream ->
    versionProps.load(stream)
}

// Aktuelle Version lesen und inkrementieren
val currentVersion = versionProps.getProperty("version") ?: "1.0.0"
val newVersion = incrementVersion(currentVersion)

// Neue Version in der Datei speichern
versionProps.setProperty("version", newVersion)
versionFile.outputStream().use { stream ->
    versionProps.store(stream, null)
}

// Setze die Projektversion auf die neue Version
version = newVersion

// Logge die neue Version beim Build
println("New version: $newVersion")

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}


tasks.withType<Jar> {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to newVersion
        )
    }
}
application {

    mainClass.set("org.vqiz.Main")
}
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to application.mainClass.get()
        )
    }
}