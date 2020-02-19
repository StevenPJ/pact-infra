def consumerGitUrl = 'https://github.com/StevenPJ/pact-explore.git'
def producerGitUrl = 'https://github.com/StevenPJ/pact-producer'

pipelineJob("pact-producer") {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url(producerGitUrl)
                    }
                    branch('master')
                    extensions {}
                }
            }
            scriptPath("jenkins/Jenkinsfile")
        }
    }
}

pipelineJob("pact-consumer") {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url(consumerGitUrl)
                    }
                    branch('master')
                    extensions {}
                }
            }
            scriptPath("$app/jenkins/Jenkinsfile")
        }
    }
}

// Provider job that only executes contract tests, usually triggered by webhook
pipelineJob("pact-producer-run-contract-tests") {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url(producerGitUrl)
                    }
                    branch('master')
                    extensions {}
                }
            }
            scriptPath("jenkins/Jenkinsfile-contract-tests")
        }
    }
}