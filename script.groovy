def buildApp() {
    echo "Building the application..."
    echo "Building version ${params.VERSION}..."
    // sh 'mvn -B -DskipTests clean package'
}

def testApp() {
    echo "Running tests..."
    // sh 'mvn test'
}

def deployApp() {
    echo "Deploying application..."
    echo "Logging in to Docker Hub..."
    echo "Building Docker image..."
    echo "Pushing Docker image to registry..."
}

return this
