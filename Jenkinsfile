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
                    def branchName = env.BRANCH_NAME
                    def branchExists = sh(script: "git ls-remote --heads origin ${branchName}", returnStatus: true) == 0

                    if (!branchExists) {
                        sh "git checkout -b ${branchName}"
                    } else {
                        sh "git checkout ${branchName}"
                    }

                    sh """
                        git add .
                        git commit -m 'Automated commit'
                        git push origin ${branchName}
                    """
                }
            }
        }
    }
}
