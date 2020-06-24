pipeline {
    agent any
    tools {
            jdk 'jdk11'
        }

    stages {

        stage('Run selenium grid') {
            steps {
                bat 'docker-compose up -d' // Uruchiomienie Docker Selenium
            }
        }

        stage('Compile Stage') {
                steps {
                    echo 'Starting Compile..'
                    withMaven (maven : 'myMavenRep'){
                        bat 'mvn clean compile'
                    }
                }
        }

        stage('Testing Stage1') {
                 steps {
                     echo 'Starting Testing..'
                     withMaven (maven : 'myMavenRep'){
                         bat 'mvn test'
                     }
                 }
        }


        stage('Deploy Stage') {
                steps {
                    echo 'Starting Deploy.. - empty for now'
                    //withMaven (maven : 'myMavenRep'){
                        //bat 'mvn deploy'
                    //}
                }
        }



    }
}