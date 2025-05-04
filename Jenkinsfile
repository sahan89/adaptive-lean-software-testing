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
                sh 'mvn clean build'
            }
        }
        stage('Test - High Priority') {
            steps {
                sh 'mvn test -Dtest=AppTest#testCheckoutProcess,AppTest#testPaymentProcess'
            }
        }
        stage('Test - All') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
/*     post {
        always {
            junit 'target/surefire-reports *//*.xml'
            archiveArtifacts artifacts: 'target *//*.jar', fingerprint: true
        }
    } */
}
