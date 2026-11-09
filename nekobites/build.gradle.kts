
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    id("com.vanniktech.maven.publish") version "0.37.0"
}

android {
    namespace = "com.sosauce.nekobites"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.graphics.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.colorpicker.compose)
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()
}

mavenPublishing {
    coordinates(group.toString(), "nekobites", version.toString())

    pom {
        name.set("Nekobites")
        description.set("My UI library for all my apps")
        inceptionYear.set("2026")
        url.set("https://github.com/sosauce/nekobites/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("sosauce")
                name.set("sosauce")
                url.set("https://github.com/sosauce/")
            }
        }
        scm {
            url.set("https://github.com/sosauce/nekobites/")
            connection.set("scm:git:git://github.com/sosauce/nekobites.git")
            developerConnection.set("scm:git:ssh://git@github.com/sosauce/nekobites .git")
        }
    }
}

