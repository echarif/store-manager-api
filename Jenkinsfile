#!groovy
import static Constants.*

class Constants {

    static final DEV_BRANCH_NAME = "develop"
    static final MASTER_BRANCH_NAME = "master"
}

def sendNotificationOk(message, color) {
    slackSend(
            color: color,
            message: "${message}: - <${env.JOB_URL}|${env.JOB_NAME}> <${env.BUILD_URL}|${env.BUILD_NUMBER}>",
            channel: "playtomic-dev"
    )
}

def isFeatureBranch() {
  return DEV_BRANCH_NAME != env.BRANCH_NAME && MASTER_BRANCH_NAME != env.BRANCH_NAME
}

def sendNotificationOk(message) {
    if (!isFeatureBranch()) {
      sendNotificationOk(message, 'good')
    }
}

def sendNotificationError(message) {
    if (!isFeatureBranch()) {
      sendNotificationOk(message, 'danger')
    }
}

def lastLocalCommitHash() {
    echo("INFO: env.GIT_REVISION is: " + env.GIT_REVISION)
    if (env.GIT_REVISION == null) {
        env.GIT_REVISION = sh(script: "git rev-parse HEAD", returnStdout: true).trim()
    }
    return env.GIT_REVISION
}

def dockerLogin() {
    // TODO Password shouldn't be here
    sh "docker login -u dockeruser -p dockeruser123 nexus.syltek.com:5000"
}

def buildDocker(version) {
    env.PLAYTOMIC_VERSION = version
    sh "docker-compose build"
    dockerLogin()
    sh "docker-compose push"
}

def downloadCodeAndBuild() {
    stage('Download code') {
        deleteDir()
        git branch: env.BRANCH_NAME, credentialsId: 'syltek-agent', url: 'https://github.com/syltek/playtomic-web.git'
        env.COMMIT_HASH = lastLocalCommitHash()
    }

    stage('Build and test code') {
        try {
            sh "npm install"
            sh "npm run lint"
            sh "npm run coverage"
            sh "npm run build"

            sendNotificationOk('Build ok')
        } catch (e) {
            sendNotificationError("Build error: ${e}")
            error 'Build failed'
        }
    }
}

def deploy(environment) {
    DOMAIN = "playtomic"

    // no suffix for production
    if (environment != "prod") {
        DOMAIN = DOMAIN + "-${environment}"
        env.DOCKER_CERT_PATH = "/root/.docker/machine/machines/syswaman0"
        DOCKER_HOST = "10.1.1.31:2376"
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    } else {
        env.DOCKER_CERT_PATH = "/root/.docker/machine/machines/syproswaman0"
        DOCKER_HOST = "10.1.1.141:2376"
        DOCKER_COMPOSE_FILE = "docker-compose-pro.yml"
    }

    env.DOCKER_TLS_VERIFY = 1
    env.PLAYTOMIC_DOMAIN = "${DOMAIN}.syltek.com"
    echo "Playtomic version: ${PLAYTOMIC_VERSION}"
    sh "docker -H ${DOCKER_HOST} stack deploy ${DOMAIN} -c ${DOCKER_COMPOSE_FILE} --with-registry-auth"

    if (environment != "prod") {
        echo "Creating domain ${DOMAIN}"
        try {
            sh "ssh sydns3.syltek.com 'add_record.sh ${DOMAIN} 10.1.1.40'"
        } catch (e) {
            echo "Error creating domain. Maybe the domain is already created"
        }
    }

    sendNotificationOk("Deployed to ${environment}")
}

node('docker') {
    sendNotificationOk('New build started')
    downloadCodeAndBuild()
}

if (DEV_BRANCH_NAME == env.BRANCH_NAME || MASTER_BRANCH_NAME == env.BRANCH_NAME) {
    node('docker') {

        stage('Build docker image') {
            buildDocker("${BRANCH_NAME}-${BUILD_NUMBER}-${COMMIT_HASH}")
        }

        stage('Deploy to dev') {
            try {
                deploy("develop")
            } catch (e) {
                sendNotificationError("Error deploying to dev: ${e}")
                error 'Build failed'
            }
        }
    }
}

if (MASTER_BRANCH_NAME == env.BRANCH_NAME) {
    stage('Confirm staging deployment') {
        input message: "Promote to staging?"
        milestone() // Abort older builds
    }

    node('docker') {
        stage('Deploy to staging') {
            try {
                deploy("staging")
            } catch (e) {
                sendNotificationError("Error deploying to staging: ${e}")
                error 'Build failed'
            }
        }
    }

    stage('Confirm production deployment') {
        input message: "Promote to production?"
        milestone() // Abort older builds
    }

    node('docker') {
        stage('Deploy to production') {
            try {
                deploy("prod")
            } catch (e) {∫
                sendNotificationError("Error deploying to production: ${e}")
                error 'Build failed'
            }
        }
    }
}