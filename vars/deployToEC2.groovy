#!/usr/bin/env groovy

def call() {

                   echo 'deploying docker image to EC2...'

                   def shellCmd = "bash ./server-cmds.sh"
                   def ec2Instance = "ec2-user@34.223.6.188"


                   sshagent(['ec2-server-key']) {
                       sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
                       sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                       sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                   }
                }

