pipeline {
    agent any
    environment {
        registry = "422288715120.dkr.ecr.us-east-2.amazonaws.com/ss-utopia-fs-xiao-email"
    }

    stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature/fs_cloud']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/byte-crunchers/ss-utopia-email-registration.git']]])
            }
        }



    // Building Docker images
    stage('Building image') {
      steps{
        script {
          sh 'docker build -t ss-utopia-fs-xiao-email .'
          sh 'docker tag ss-utopia-fs-xiao-email:latest 422288715120.dkr.ecr.us-east-2.amazonaws.com/ss-utopia-fs-xiao-email:latest'
        }
      }
    }

    // Uploading Docker images into AWS ECR
    stage('Pushing to ECR') {
     steps{
         script {
                sh'aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 422288715120.dkr.ecr.us-east-2.amazonaws.com'
                sh 'docker push 422288715120.dkr.ecr.us-east-2.amazonaws.com/ss-utopia-fs-xiao-email:latest'
         }
        }
      }

         // Stopping Docker containers for cleaner Docker run
     stage('stop previous containers') {
         steps {
            sh 'docker ps -f name=ss-utopia-fs-xiao-email -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=ss-utopia-fs-xiao-email -q | xargs -r docker container rm'
         }
       }

    stage('Docker Run') {
     steps{
         script {
                sh 'docker run -d -p 8097:8090 --rm --name ss-utopia-fs-xiao-account 422288715120.dkr.ecr.us-east-2.amazonaws.com/ss-utopia-fs-xiao-email:latest'
            }
      }
    }
    }
}