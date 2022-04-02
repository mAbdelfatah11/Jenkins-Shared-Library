#!/usr/bin/env groovy

package com.example

class Docker implements Serializable {

    def script
    Docker(script) { this.script=script }

    def versionIncrement() {

            sh 'mvn build-helper:parse-version versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                versions:commit'
            def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
            def version = matcher[0][1]
            env.IMAGE_NAME = "$version-$BUILD_NUMBER"
 
    }

    def buildJar() {
            echo "building the application..."
            sh 'mvn package'

    }
    def buildImage() {
            
            sh "docker build -t mabdelfatah/mma-demo:${IMAGE_NAME} ."
    }

    def dockerLogin() {

            withCredentials([usernamePassword(credentialsId: 'Dockerhub-Credentails', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                sh "echo $PASS | docker login -u $USER --password-stdin"
            }
    }

    def dockerPush() {
                
            sh "docker push mabdelfatah/mma-demo:${IMAGE_NAME}"


    }
}