package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.build_queue.model.BuildQueue;
import org.exschool.pocketworld.build_queue.model.Status;
import org.exschool.pocketworld.build_queue.model.Type;

import java.util.Date;

/**
 * Created by skandy on 07.12.15.
 */
public final class BuildQueueBuilder {
    private Long id;
    private String name;
    private int level;
    private Type type;
    private Date buildEnd;
    private Long userId;
    private Status status;
    private Long buildingId;

    public static BuildQueueBuilder builder() {
        return new BuildQueueBuilder();
    }

    public BuildQueueBuilder() {
    }
    public BuildQueueBuilder id (Long id){
        this.id=id;
        return this;
    }
    public BuildQueueBuilder name(String name){
        this.name=name;
        return this;
    }
    public BuildQueueBuilder level (int level){
        this.level = level;
        return this;
    }
    public BuildQueueBuilder type (Type type){
        this.type = type;
        return this;
    }
    public BuildQueueBuilder buildEnd(Date buildEnd) {
        this.buildEnd=buildEnd;
        return this;
    }
    public BuildQueueBuilder userId(Long userId) {
        this.userId=userId;
        return this;
    }
    public BuildQueueBuilder status (Status status) {
        this.status = status;
        return this;
    }
    public BuildQueueBuilder buildingId(Long buildingId) {
        this.buildingId=buildingId;
        return this;
    }

   public BuildQueue build(){
       BuildQueue buildQueue = new BuildQueue();
       buildQueue.setId(this.id);
       buildQueue.setName(this.name);
       buildQueue.setLevel(this.level);
       buildQueue.setType(this.type);
       buildQueue.setBuildEnd(this.buildEnd);
       buildQueue.setUserId(this.userId);
       buildQueue.setStatus(this.status);
       buildQueue.setBuildingId(this.buildingId);
       return  buildQueue;

    }


}
