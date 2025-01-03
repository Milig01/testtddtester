pipeline {
    agent { label 'windows' }
    environment {
        KUBE_CONFIG = 'C:/Users/mig/.kube/config'
        DOCKER_IMAGE = 'milig/testtddtester:latest'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Milig01/testtddtester.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${DOCKER_IMAGE} ."
                bat "docker push ${DOCKER_IMAGE}"
            }
        }
        stage('Deploy to Minikube') {
            steps {
                kubernetesDeploy(kubeconfig: KUBE_CONFIG, manifestFiles: 'deployment.yaml')
            }
        }
    }
}