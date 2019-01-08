pipelineJob('UPDATE_APP_RELEASE_IMAGES') {
    definition {
        cps {
            script('''
pipeline {
    agent any
    parameters {
        string(name: 'pubType', defaultValue: 'pre-release', description: '发布类型')
        string(name: 'pubParam', defaultValue: '{}', description: '发布参数')
    }

    stages {
        stage('调用CCloud'){
            steps {
                ccloudPub "${params.pubType}-${params.pubParam}"
            }
        }
    }
    
    post {
        always {
            notifyResult("""
                {
                    "buildId": "${currentBuild.id}",
                    "jobName": "${currentBuild.rawBuild.log.bytes.encodeBase64().toString()}"
                }
            """)
        }
    }
}

def notifyResult(body) {
    httpRequest httpMode: 'POST', url: "http://192.0.75.1:8090/callback/jenkins/" , contentType: 'APPLICATION_JSON',requestBody: body
}
''')
        }
    }
}