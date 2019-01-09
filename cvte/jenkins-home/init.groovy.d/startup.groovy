import jenkins.model.Jenkins
import hudson.model.*
import java.util.logging.Logger

Logger.global.info("[Running] startup script")

configureSecurity()

Jenkins.instance.save()

buildJob('seed')

Logger.global.info("[Done] startup script")

private void configureSecurity() {
    Jenkins.getInstance().disableSecurity()
}

private def buildJob(String jobName) {
    Logger.global.info("Building job '$jobName")
    def job = Jenkins.instance.getJob(jobName)
    def waiting = Jenkins.instance.queue.schedule2(job, 0, new CauseAction(new Cause() {
        @Override
        String getShortDescription() {
            'Jenkins startup script'
        }
    })).getItem().getFuture().get()
}
