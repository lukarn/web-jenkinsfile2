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
                    bat 'docker version | findstr "Server" && if %errorlevel% EQU 0 (echo OK ) Else ( Echo ERROR FAILED &color CF )'
                    bat 'docker version | findstr "Server" & if %errorlevel% EQU 0 (echo OK ) Else ( Echo ERROR FAILED &color CF )'

                    }

                if ('docker version | findstr "Server"') {
                                        echo 'if - OK - error level'
                                        //bat 'errorLev = echo %errorlevel%'
                                        //bat 'echo $errorLev'
                                        //echo env[errorlevel]
                                        println("Running job ${env.JOB_NAME}")
                                        bat 'if %errorlevel% EQU 0 (echo OK ) Else ( Echo ERROR FAILED &color CF )'
                                        println(env.getProperty('%errorlevel%'))

                                        //if(errorlevel=='1'){
                                            //echo '1111111111111'
                                        //}
                                        //if(errorLev=='0'){
                                            //echo '00000000000000'
                                        //}

                                    }
                if ('docker version | findstr "Serverrrrr"') {
                                        echo 'if not found - OK - error level'
                                        //bat 'echo Hello ${errorlevel}'
                                        //echo %errorlevel%
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