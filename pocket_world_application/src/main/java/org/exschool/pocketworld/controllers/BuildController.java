package org.exschool.pocketworld.controllers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.city.common.service.BuildService;
import org.exschool.pocketworld.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
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

    @Autowired
    BuildService buildService;

    @RequestMapping(value = "timers", method = RequestMethod.GET)
    @ResponseBody
    public Collection timers() {
        /*List<BuildQueueRecord> allByUser = buildQueueService.getAllByUser(playerService.getPlayerByLogin("login-1").getId());
        Collection<BuildQueueRecord> activeRecords = Collections2.filter(allByUser, new Predicate<BuildQueueRecord>() {
            @Override
            public boolean apply(BuildQueueRecord input) {
                return input.getStatus() == Status.QUEUED && input.getBuildEnd().isAfterNow();
            }
        });
        *//*return*//* Collections2.transform(activeRecords, new Function<BuildQueueRecord, Timer>() {
            @Override
            public Timer apply(BuildQueueRecord input) {
                return Timer.create().type(input.getType().name().toLowerCase()).buildEndInSeconds(1000L).position(3).get();
            }
        });
        return Arrays.asList(Timer.create().type(Type.BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get(),
                Timer.create().type(Type.BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get(),
        Timer.create().type(Type.RESOURCE_BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get());*/
        return buildService.activeTimers(playerService.getPlayerByLogin("login-1").getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity build() {
        buildService.build(1L);
        return HttpEntity.EMPTY;
    }
}
