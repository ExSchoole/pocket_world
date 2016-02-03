package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.joda.time.DateTime;



/**
 * Created by skandy on 07.12.15.
 */
public final class BuildQueueBuilder {
    private Long id;
    private String name;
    private int level;
    private Type type;
    private DateTime buildEnd;
    private Long userId;
    private Status status;
    private Long buildingId;
    private int position;

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
    public BuildQueueBuilder buildEnd(DateTime buildEnd) {
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
    public BuildQueueBuilder position(int position) {
        this.position = position;
        return this;
    }

   public BuildQueueRecord build(){
       BuildQueueRecord buildQueueRecord = new BuildQueueRecord();
       buildQueueRecord.setId(this.id);
       buildQueueRecord.setName(this.name);
       buildQueueRecord.setLevel(this.level);
       buildQueueRecord.setType(this.type);
       buildQueueRecord.setBuildEnd(this.buildEnd);
       buildQueueRecord.setUserId(this.userId);
       buildQueueRecord.setStatus(this.status);
       buildQueueRecord.setBuildingId(this.buildingId);
       buildQueueRecord.setPosition(this.position);
       return buildQueueRecord;

    }


}
