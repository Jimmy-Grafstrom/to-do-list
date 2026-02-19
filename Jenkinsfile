pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
            // Mappa lokala .m2 katalogen till root i containern, men vi kör som en användare som kanske inte är root.
            // Bättre strategi: Sätt user.home till workspace så Maven skapar .m2 där.
            args '-v $HOME/.m2:/root/.m2' 
        }
    }
    environment {
        // Tvinga Maven att använda en cache-katalog inuti workspace istället för /root/.m2
        // Detta löser rättighetsproblemet eftersom workspace ägs av jenkins-användaren.
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}