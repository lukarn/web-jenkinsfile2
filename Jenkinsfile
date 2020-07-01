pipeline {
    agent any
    tools {
            jdk 'jdk11'
        }

    stages {

        stage('Run selenium grid') {
            steps {
                bat 'docker version'
                //echo 'Docker is running: '
                bat 'docker version | findstr "Server"'
                script{
                if (true) {
                                                        echo 'if check - OK'
                                                    }

                if ('docker version | findstr "Server"') {
                                        echo 'if - OK - error level'
                                        bat 'echo %errorlevel%'

                                    }
                if ('docker version | findstr "Serverrrrr"') {
                                        echo 'if not found - OK - error level'
                                        echo %errorlevel%
                                    }

                          }
                echo 'not found example: '
                //bat 'docker version | findstr "Serverrrr"'
                bat 'docker-compose up -d' // Docker Selenium
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

        stage('Tear down stage') {
            steps {
                bat 'docker-compose down'
                bat 'docker system prune -f'
            }
        }

    }
}