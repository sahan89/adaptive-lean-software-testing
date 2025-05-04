pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/develop']],
                          userRemoteConfigs: [[url: 'https://github.com/sahan89/adaptive-lean-software-testing.git']]
                ])
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
/*      post {
        always {
            junit 'target/surefire-reports *//*  *//*.xml'
            archiveArtifacts artifacts: 'target *//*  *//*.jar', fingerprint: true
        }
    } */
}
