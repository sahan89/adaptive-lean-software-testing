pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    stages {
        stage('Checkout Application') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/develop']],
                          userRemoteConfigs: [[url: 'https://github.com/sahan89/adaptive-lean-software-testing.git']]
                ])
            }
        }
        stage('Maven Clean Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Run Test Cases') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build Docker Image') {
             steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
             }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials',
                                                 usernameVariable: 'DOCKER_USER',
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }
    }
}
