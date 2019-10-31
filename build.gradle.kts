import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.21"
}

buildscript {
	extra.set("springVersion", "5.1.8.RELEASE")
	extra.set("tomcatVersion", "8.5.42")
}

allprojects {
	group = "nextstep"
	version = "1.0.0"

	repositories {
		jcenter()
	}

}

subprojects {
	apply(plugin = "kotlin")

	dependencies {
		val springVersion = rootProject.extra.get("springVersion")

		implementation("ch.qos.logback:logback-classic:1.2.3")
		implementation("com.google.guava:guava:28.0-jre")
		implementation("org.apache.commons:commons-lang3:3.9")
		implementation("org.reflections:reflections:0.9.11")
		implementation("org.springframework:spring-beans:5.2.0.RELEASE")
		testImplementation("org.junit.jupiter:junit-jupiter:5.5.1")
		testImplementation("org.assertj:assertj-core:3.12.2")
		testImplementation("org.springframework:spring-test:$springVersion")
		testImplementation("org.springframework:spring-webflux:$springVersion")
		testImplementation("io.projectreactor.netty:reactor-netty:0.8.10.RELEASE")
	}

	tasks.withType<KotlinCompile>().configureEach {
		println("Configuring $name in project ${project.name}...")
		kotlinOptions {
			jvmTarget = "1.8"
			freeCompilerArgs = listOf("-Xjsr305=strict")
		}
	}
}
