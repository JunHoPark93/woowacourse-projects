dependencies {
    api(project(":nextstep-di"))
    
    implementation("javax.servlet:jstl:1.2")
    implementation("javax.servlet:javax.servlet-api:3.1.0")

    api("com.fasterxml.jackson.core:jackson-databind:2.9.9.1")
    implementation("com.github.jknack:handlebars:4.1.2")
    implementation("org.apache.ant:ant:1.10.6")
}
