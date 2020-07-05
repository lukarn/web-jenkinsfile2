pipeline {
    agent any
    tools {
            jdk 'jdk11'
            maven 'myMavenRep'
        }

    stages {

        stage('Clean workspace') {
                steps {
                    echo 'Cleaning workspace..'
                    deleteDir()
                }
        }

        stage('Run selenium grid') {
            steps {

                script{
                    for (int i = 0; i < 50; i++) {
                        bat 'docker version | findstr "Server" || PING localhost -n 10 > NUL'
                             try {
                                 echo 'Waiting for Docker'
                                 bat 'docker version | findstr "Server"'
                                 i=50
                                 }
                             catch (Exception e) {
                                 echo "Stage failed, but we continue"
                                 }
                        }
                    //bat 'docker version | findstr "Server" & if %errorlevel% EQU 0 (echo OK ) Else ( Echo ERROR FAILED &color CF )'
                    //bat 'docker version | findstr "Server" & if %errorlevel% NEQ 0 (echo NOOOOOK ) Else ( Echo OKOKOK )'
                    //bat 'if %errorlevel% NEQ 0 (timeout 10) Else ( Echo OKOKOK )'

                    bat 'docker version'

                    println("Running job ${env.JOB_NAME}")
                }

                bat 'docker-compose up -d' // Docker Selenium
            }
        }

        stage('Compile Stage') {
                steps {
                    echo 'Starting Compile..'
                    bat 'mvn clean compile'
                }
        }

        stage('Testing Stage1') {
                 steps {
                     echo 'Starting Testing..'
                     bat 'mvn test'
                 }
        }


        //stage('Deploy Stage') {
                //steps {
                    //echo 'Starting Deploy.. - empty for now'
                    //withMaven (maven : 'myMavenRep'){
                        //bat 'mvn deploy'
                    //}
                //}
        //}

        stage('Tear down stage') {
            steps {
                bat 'docker-compose down'
                bat 'docker system prune -f'
            }
        }

    }
}