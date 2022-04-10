#!/usr/bin/env groovy

def call() {

                   echo 'deploying docker image to EC2...'

                   def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                   def ec2Instance = "ec2-user@35.180.251.121"
// step1: 
//use sshagent method to ssh and run the required script in ec2 server
// you already have uploaded a private key to jenkins credentails "see jenkins-aws notes file" so mention it in the sshagent method

// step2: 
// you should copy the bash script file and docker compose file to the server 
// -o StrictHostKeyChecking=no >>>> prevent ec2 to ask any question about key during connection request

//step3:
// ssh directly and run the bash script
// note that :
// i have passed an image_name global variable, that global variable created firstly in the incremntVersion job, then passed as a tag to the buildIMage job, also that image name with same tag will be pulled by docker-compose to run in the server
//in shell script: export IMAGE=$1
// this will export the first varible passed to the shell command that runs that bash script,so the IMAGE variable will pass the value of IMAGE_NAME variable to the docker-compose file
                   sshagent(['ec2-server-key']) {
                       sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
                       sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                       sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                   }
                }
            }
        }

}
