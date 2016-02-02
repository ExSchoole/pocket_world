package org.exschool.pocketworld.controllers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;


/**
 * Created by kgavrylchenko on 02.02.2016.
 */
@Controller
@RequestMapping("build")
public class BuildController {
    @Autowired
    BuildQueueService buildQueueService;
    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "timers", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Timer> timers() {
        List<BuildQueueRecord> allByUser = buildQueueService.getAllByUser(playerService.getPlayerByLogin("login-1").getId());
        Collection<BuildQueueRecord> activeRecords = Collections2.filter(allByUser, new Predicate<BuildQueueRecord>() {
            @Override
            public boolean apply(BuildQueueRecord input) {
                return input.getStatus() == Status.QUEUED && input.getBuildEnd().isAfterNow();
            }
        });
        return Collections2.transform(activeRecords, new Function<BuildQueueRecord, Timer>() {
            @Override
            public Timer apply(BuildQueueRecord input) {
                return Timer.create().type(input.getType().name().toLowerCase()).buildEndInSeconds(1000L).position(3).get();
            }
        });
    }

    private static class Timer {

        private final String type;
        private final long buildEndInSeconds;
        private final int position;

        private Timer(String type, long buildEndInSeconds, int position) {
            this.type = type;
            this.buildEndInSeconds = buildEndInSeconds;
            this.position = position;
        }

        public static Builder create() {
            return new Builder();
        }

        private static class Builder {

            private String type;
            private long buildEndInSeconds;
            private int position;

            public Builder type(String type) {
                this.type = type;
                return this;
            }

            public Builder buildEndInSeconds(long seconds) {
                this.buildEndInSeconds = seconds;
                return this;
            }

            public Builder position(int position) {
                this.position = position;
                return this;
            }


            public Timer get() {
                return new Timer(type, buildEndInSeconds, position);
            }
        }

    }
}
