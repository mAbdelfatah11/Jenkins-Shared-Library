#!/usr/bin/env groovy

def call() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockerhub-Credentails', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t mabdelfatah/mma-demo:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push mabdelfatah/mma-demo:${IMAGE_NAME}"
    }

}

