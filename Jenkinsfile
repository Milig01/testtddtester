pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "milig/testtddtester:latest" //Замените на имя вашего образа
        KUBE_CONFIG = credentials('minikube-config') // Замените на ID credentials
        DOCKER_HUB = credentials('docker-hub-credentials') // Замените на ID credentials
    }

    tools {
        maven 'Maven 3.9' // Укажите имя Maven из настроек Jenkins
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 git url: 'https://github.com/<ваше_имя_пользователя>/<ваш_репозиторий>.git', branch: 'main' //Укажите путь к вашему репозиторию
//             }
//         }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
          steps {
            script {
              docker.withTool('docker') { //Используйте dockerTool
                docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB) {
                  docker.build(DOCKER_IMAGE, '.').push()
                }
              }
            }
          }
        }
        stage('Deploy to Minikube') {
            steps {
               script {
                  kubernetesDeploy(kubeconfig: KUBE_CONFIG, manifestFiles: 'deployment.yaml')
               }
            }
        }
    }
}