import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
@Library('jenkins-scripts-passgenius') _

def IMAGE_TAG_NAME = generateTagName()
def IMAGE_TAG = 'aashraf756/service-user-passgenius'
def MANIFEST_REPO = "https://github.com/anas-ash99/deployment-manifest-passgenius"
def MANIFEST_REPO_NAME = "deployment-manifest-passgenius"
def DEPLOYMENT_FILE_PATH = "overlays\\dev\\user"

pipeline {
    agent any
    environment {
        GIT_CREDENTIALS = credentials('Github-token')
    }
    stages {

        stage('Build App') {
            steps {
                echo "Building the app ..."
                configFileProvider([configFile(fileId: 'df11f9a7-ff71-4d6b-80d7-f390bc7e79d6', variable: 'MAVEN_SETTINGS')]) {
                    bat 'mvnw -s %MAVEN_SETTINGS% clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo "Building the image..."
                        bat "docker build -t ${IMAGE_TAG}:${IMAGE_TAG_NAME} ."
                    }else {
                        Utils.markStageSkippedForConditional( STAGE_NAME )
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        docker.withRegistry('', 'aba091eb-3857-489f-8115-2993e248f42c') { // login into Docker Hub
                            echo 'Pushing docker image...'
                            bat  "docker push ${IMAGE_TAG}:${IMAGE_TAG_NAME}"
                        }
                    }else {
                        Utils.markStageSkippedForConditional( STAGE_NAME )
                    }
                }
            }
        }

        stage('Update Kubernetes Manifest') {
            steps {
                echo 'Updating manifest ...'
                script {
                    if (env.BRANCH_NAME == 'main') {
                        bat """
                           cd ..
                           git clone ${MANIFEST_REPO}
                           cd ${MANIFEST_REPO_NAME}
                           powershell -Command "(Get-Content -Path '${DEPLOYMENT_FILE_PATH}\\deployment.yaml') -replace '${IMAGE_TAG}:.*', '${IMAGE_TAG}:${IMAGE_TAG_NAME}' | Set-Content -Path '${DEPLOYMENT_FILE_PATH}\\deployment.yaml'"
                           git add .
                           git commit -m "update tag image by Jenkins to version ${IMAGE_TAG_NAME}"
                           git push https://${GIT_CREDENTIALS_USR}:${GIT_CREDENTIALS_PSW}@github.com/${GIT_CREDENTIALS_USR}/${MANIFEST_REPO_NAME}.git
                           cd ..
                           rmdir /S /Q ${MANIFEST_REPO_NAME}
                        """
                    }else {
                        Utils.markStageSkippedForConditional( STAGE_NAME )
                    }

                }
            }
        }
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
