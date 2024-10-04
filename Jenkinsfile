@Library('jenkins-scripts-passgenius') _

import com.passgenius.*

pipeline {
    agent any
    environment {
        // Replace these with your Docker Hub credentials and repository info
        IMAGE_TAG = 'aashraf756/service-user-passgenius'
        IMAGE_VERSION = "" // or use env.BUILD_NUMBER or another unique identifier
        MANIFEST_REPO = "https://github.com/anas-ash99/deployment-manifest-passgenius"
        MANIFEST_REPO_NAME = "deployment-manifest-passgenius"
        DEPLOYMENT_FILE_PATH = "overlays\\dev\\user"
        GIT_CREDENTIALS = credentials('Github-token')
    }
    stages {
        stage('Build App') {
            steps {
                echo 'Building the app ...'
//                configFileProvider([configFile(fileId: 'df11f9a7-ff71-4d6b-80d7-f390bc7e79d6', variable: 'MAVEN_SETTINGS')]) {
//                    bat 'mvnw -s %MAVEN_SETTINGS% clean package'
//                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    IMAGE_VERSION = VersionUpdater.generateVersion()
                    echo "The image version is: ${IMAGE_VERSION}"
//                    bat "docker build -t ${IMAGE_TAG}:${IMAGE_VERSION} ."
                }
            }
        }

//        stage('Push Docker Image') {
//            steps {
//                script {
//                    docker.withRegistry('', 'aba091eb-3857-489f-8115-2993e248f42c') { // login into Docker Hub
//                        echo 'Pushing docker image...'
//                        bat  "docker push ${IMAGE_TAG}:${IMAGE_VERSION}"
//                    }
//                }
//            }
//        }

//        stage('Update Kubernetes Manifest') {
//            steps {
//                echo 'Updating manifest ...'
//                script {
//                    // Apply Kubernetes manifests
//                    bat """
//                       cd ..
//                       git config user.email "anas.ash099@example.com"
//                       git config user.name "Anas Ashraf"
//                       git clone ${MANIFEST_REPO}
//                       cd ${MANIFEST_REPO_NAME}
//                       powershell -Command "(Get-Content -Path '${DEPLOYMENT_FILE_PATH}\\deployment.yaml') -replace '${IMAGE_TAG}:.*', '${IMAGE_TAG}:${IMAGE_VERSION}' | Set-Content -Path '${DEPLOYMENT_FILE_PATH}\\deployment.yaml'"
//                       git add .
//                       git commit -m "update tag image by Jenkins to version ${IMAGE_VERSION}"
//                       git push https://${GIT_CREDENTIALS_USR}:${GIT_CREDENTIALS_PSW}@github.com/${GIT_CREDENTIALS_USR}/${MANIFEST_REPO_NAME}.git
//                       cd ..
//                       rmdir /S /Q ${MANIFEST_REPO_NAME}
//                    """
//                }
//            }
//        }
    }

    post {
        always {
            echo 'Cleaning up...'

        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
