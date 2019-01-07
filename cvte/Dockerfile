FROM timtohy/jenkins:1.0.0

COPY jenkins-home/*.* $JENKINS_REF/
COPY jenkins-home/userContent $JENKINS_REF/userContent
COPY jenkins-home/jobs $JENKINS_REF/jobs/
COPY jenkins-home/init.groovy.d $JENKINS_REF/init.groovy.d/
COPY jenkins-home/dsl $JENKINS_REF/dsl/