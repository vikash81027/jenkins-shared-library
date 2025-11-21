def call(String dockerUser, String dockerTag) {
    def services = sh(
        script: "ls -d */ | sed 's#/##' | xargs -I {} find {} -maxdepth 1 -name Dockerfile -printf '%h\n'",
        returnStdout: true
    ).trim().split("\n")

    echo "Found Docker services: ${services}"

    services.each { service ->
        echo "Building image for ${service}"
        dir(service) {
            sh "docker build -t ${dockerUser}/${service}:${dockerTag} ."
        }
    }
}
