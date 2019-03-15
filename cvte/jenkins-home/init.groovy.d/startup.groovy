import jenkins.model.Jenkins
import hudson.model.*
import java.util.logging.Logger

Logger.global.info("[Running] startup script")

configureSecurity()

Jenkins.instance.save()

buildJob('seed')

Logger.global.info("[Done] startup script")

Logger.global.info("[Running] warm up script")

//Thread.sleep(30000)

jobs = Hudson.instance.getAllItems()

// iterate through the jobs
for (j in jobs) {
    def pattern = 'seed';
    def m = j.getName() =~ pattern;
    Logger.global.info("job name ==> " + j.getName())

    // if pattern does not match, then run the job
    if (!m) {
        // first check, if job is buildable
        if (j instanceof BuildableItem) {
            // run that job
            Logger.global.info("[Running]  warm up task ... ")
            Logger.global.info(j.getName())

            j.scheduleBuild();
        }
    }
}

// 保证定时任务可以被调度成功
Thread.sleep(10000)

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
