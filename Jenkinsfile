pipeline {
    agent any
    environment {
        GIT_CREDENTIALS_ID = 'github-credentials'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    def branch = env.BRANCH_NAME
                    echo "Building branch: ${branch}"

                    // Checkout do código
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "${branch}"]],
                        userRemoteConfigs: [[
                            url: 'https://github.com/MateusHCandido/bank-payment-service',
                            credentialsId: env.GIT_CREDENTIALS_ID
                        ]]
                    ])
                }
            }
        }
        stage('Push to GitHub') {
            steps {
                script {
                    def branchExists = sh(script: "git ls-remote --heads origin ${env.BRANCH_NAME}", returnStatus: true) == 0

                    if (!branchExists) {
                        sh "git c
