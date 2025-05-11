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
    }
}