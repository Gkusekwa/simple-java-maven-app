def buildApp() {
    echo "Building the application..."
    echo "Building version ${params.VERSION}..."
    sh 'mvn -B -DskipTests clean package'
}

def testApp() {
    echo "Running tests..."
    sh 'mvn test'
}

def deployApp() {
    echo "Deploying application..."
    echo "Logging in to Docker Hub..."
    sh "echo \${PWD} | docker login -u \${USER} --password-stdin"
    echo "Building Docker image..."
    sh "docker build -t myapp:\${NEW_VERSION} ."
    echo "Pushing Docker image to registry..."
    sh "docker tag myapp:\${NEW_VERSION} \${USER}/myapp:\${NEW_VERSION}"
    sh "docker push \${USER}/myapp:\${NEW_VERSION}"
}

return this
