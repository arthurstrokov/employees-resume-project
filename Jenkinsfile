pipeline {
    agent any
    stages {
        stage("Verify tooling") {
            steps {
            bat '''
            docker version
            docker compose version
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
        stage('Deploy application using HTTP API'){
            steps{
            bat 'curl -H "Content-Type: application/json" -X POST -d @marathon.json http://localhost:8000/v2/apps'
            }
        }
    }
}
