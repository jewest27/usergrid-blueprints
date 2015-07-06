package org.apache.usergrid.drivers.blueprints;

/**
 * Created by nishitarao on 7/6/15.
 */
public class EntityId {

    public String type;
    public String UUID;

    public EntityId(String id){

        String[] parts = id.split(":");
        this.type = parts[0];
        this.UUID = parts[1];



    }

}
