def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.3.0', '1.3.1', '1.4.0'], description: 'Version to build and deploy')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Whether to run tests')
    }
    environment {
        NEW_VERSION = "${params.VERSION}"
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
    }
    tools {
        maven 'jenkins-maven'
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage('Deploy') {
            input {
                message 'Select the environment to deploy to:'
                parameters {
                    choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: 'Deployment environment')
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    script {
                        echo "Deploying to ${ENV}..."
                        gv.deployApp()
                    }
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
