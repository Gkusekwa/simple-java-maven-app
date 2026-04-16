pipeline {
    agent any
    environment {
        NEW_VERSION = '1.3.0'
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
    }
    tools {
        maven 'jenkins-maven'
    }
    stages {
        stage('Build') {
            steps {
                echo "Building the application ......."
                echo "Building version ${env.NEW_VERSION}..."
             //   sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing the application...'
         //       sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                    sh '''
                        echo "Logging in to Docker Hub..."
                        

                        echo "Building Docker image..."
                    

                        echo "Pushing Docker image to registry..."

                    '''
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
