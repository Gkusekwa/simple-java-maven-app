pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.3.0', '1.3.1', '1.4.0'], description: 'Version to build and deploy')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Whether to run tests')
    }
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
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo 'Running tests...'
             //   sh 'mvn test'
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
