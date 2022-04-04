#!/usr/bin/env groovy
         
 def call() {
 	    echo "commiting the new changes to remote repo..."
            withCredentials([usernamePassword(credentialsId: 'Github-Credentails', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                // git config here for the first time run
                // AS ALternative: you can configure the author configs. in the jenkins server itself.
                //sh 'git config --global user.email "jenkins@widebot.net"'
                //sh 'git config --global user.name "jenkins"'
                
                //sh "git remote add origin https://${USER}:${PASS}@github.com/mAbdelfatah11/java-maven-app-jenkins.git"
                sh 'git add .'
                sh 'git commit -m "ci: version bump"'
                sh 'git push origin HEAD:Jenkins-Using-SharedLibraray-to-ManageJobs'
                
                }
             
             }
