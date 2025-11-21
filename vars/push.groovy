def call(String imageName, String dockerTag){
  withCredentials([
    usernamePassword(
      credentialsId: "Docker-Hub",
      usernameVariable: "dockerUser",
      passwordVariable: "dockerPass"
      
      )
  ]){
    echo "Pushing the code..."
    sh "docker login -u ${dockerUser} -p ${dockerPass}"
    sh "docker push ${dockerUser}/${imageName}:${dockerTag}"
    echo "Push successful..."
  }
}
