package io.bananalabs.goals.orm;

import java.util.ArrayList;
import java.util.List;

import io.bananalabs.goals.models.Record;
import io.bananalabs.goals.models.Target;
import za.co.cporm.model.CPOrmConfiguration;

/**
 * Created by EDC on 4/23/16.
 */
public class GoalsCPOrmConfiguration implements CPOrmConfiguration {
    @Override
    public String getDatabaseName() {
        return "bl_goals.db";
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @Override
    public boolean isQueryLoggingEnabled() {
        return false;
    }

    @Override
    public List<Class<?>> getDataModelObjects() {
        List<Class<?>> domainObjects = new ArrayList<>();
        domainObjects.add(Target.class);
        domainObjects.add(Record.class);
        return domainObjects;
    }
}
