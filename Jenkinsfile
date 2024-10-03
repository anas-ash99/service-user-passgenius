pipeline {
    agent any
    environment {
        // Replace these with your Docker Hub credentials and repository info
        IMAGE_TAG = 'aashraf756/service-auth-passgenius'
        IMAGE_VERSION = "v1.0" // or use env.BUILD_NUMBER or another unique identifier
        MANIFEST_REPO = "https://github.com/anas-ash99/deployment-manifest-passgenius"
        MANIFEST_REPO_NAME = "deployment-manifest-passgenius"
        DEPLOYMENT_FILE_PATH = "overlays/dev/user"
        GIT_CREDENTIALS = credentials('github-token')
    }

    stages {

        stage('Set Permissions') {
            steps {
                echo 'Setting execute permission for mvnw...'
                sh 'chmod +x ./mvnw'
            }
        }
        stage('Build App') {
            steps {
                echo 'Building the app ...'
                sh './mvnw clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_TAG}:${IMAGE_VERSION} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub') { // login into Docker Hub
                        echo 'Pushing docker image...'
                        sh  "docker push ${IMAGE_TAG}:${IMAGE_VERSION}"
                    }
                }
            }
        }

        stage('Update Kubernetes Manifest') {
            steps {
                echo 'Updating manifest ...'
                script {
                    // Apply Kubernetes manifests
                    sh """
                       cd ..
                       git config user.email "anas.ash099@example.com"
                       git config user.name "Anas Ashraf"
                       git clone ${MANIFEST_REPO}
                       cd ${MANIFEST_REPO_NAME}
                       sed -i 's|${IMAGE_TAG}:.*|${IMAGE_TAG}:${IMAGE_VERSION}|g' ${DEPLOYMENT_FILE_PATH}/deployment.yaml
                       git add .
                       git commit -m "update tag image by Jenkins to version ${IMAGE_VERSION}"
                       git push https://${GIT_CREDENTIALS_USR}:${GIT_CREDENTIALS_PSW}@github.com/${GIT_CREDENTIALS_USR}/${MANIFEST_REPO_NAME}.git
                    """
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            // Cleanup Docker resources
            sh '''
               docker system prune -f  # Clean up unused Docker resources
               docker rmi ${IMAGE_TAG}:${IMAGE_VERSION} || true  # Remove the built Docker image
               docker rm -f $(docker ps -a -q) || true  # Remove all stopped containers
               # Remove the cloned Git repository
               rm -rf ../${MANIFEST_REPO_NAME}  # Removes the cloned manifest repo
           '''
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
