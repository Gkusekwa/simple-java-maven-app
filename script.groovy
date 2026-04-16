def buildApp() {
    echo "Building the application..."
    echo "Building version ${params.VERSION}..."
    sh 'mvn -B -DskipTests clean package'
}

def testApp() {
    echo "Running tests..."
    sh 'mvn test'
}

def deployApp(String environment) {
    echo "Deploying to ${environment}..."
    echo "Deploying version ${params.VERSION} to ${environment}..."
    echo "Logging in to Docker Hub..."
    sh "echo \${DOCKER_PASS} | docker login -u \${DOCKER_USER} --password-stdin"
    echo "Building Docker image..."
    sh "docker build -t myapp:\${NEW_VERSION} ."
    echo "Pushing Docker image to registry..."
    sh "docker tag myapp:\${NEW_VERSION} \${DOCKER_USER}/myapp:\${NEW_VERSION}"
    sh "docker push \${DOCKER_USER}/myapp:\${NEW_VERSION}"
}

return this
