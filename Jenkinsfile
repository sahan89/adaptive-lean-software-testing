pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/sahan89/adaptive-lean-software-testing.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test - High Priority') {
            steps {
                // Run only high-priority tests (Order 1 & 2)
                sh 'mvn test -Dtest=AppTest#testCheckoutProcess,AppTest#testPaymentProcess'
            }
        }
        stage('Test - All') {
            steps {
                // Run all tests (including lower priority)
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}