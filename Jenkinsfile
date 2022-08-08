pipeline {
    agent any
    stages {
        stage("Verify tooling") {
            steps {
            bat '''
            docker version
            docker compose version
            docker info
            curl --version
            '''
            }
        }
        stage('Gradle clean build') {
            steps {
            bat './gradlew clean build'
            }
        }
        stage('Gradle docker push image') {
            steps {
                withDockerRegistry([credentialsId: "dockerhub", url: ""]){
                bat './gradlew dockerPushImage'
                }
            }
        }
    }
}
