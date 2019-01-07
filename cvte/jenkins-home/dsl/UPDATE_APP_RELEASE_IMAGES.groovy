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
                                ccloudPub "${params.pubType}" "${params.pubParam}"
                            }
                        }
                    }
                }''')
        }
    }
}