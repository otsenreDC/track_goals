package io.bananalabs.goals;

import android.test.AndroidTestCase;

import java.util.Calendar;
import java.util.Date;

import io.bananalabs.goals.models.Record;
import io.bananalabs.goals.models.Target;
import za.co.cporm.model.CPOrm;
import za.co.cporm.model.query.Select;

/**
 * Created by EDC on 4/23/16.
 */
public class DatabaseTest extends AndroidTestCase {

    public void testA_setup() {
        CPOrm.deleteAll(Target.class);
        CPOrm.deleteAll(Record.class);
    }

    public void testB_createTarget() {
        Target t = new Target();
        t.setName(TARGET_NAME);
        t.setGoal(TARGET_GOAL);
        t.setCurrent(TARGET_CURRENT);
        t.description(TARGET_DESCRIPTION);
        t.save();
        delay();
        Target f = Select.from(Target.class).first();
        assertNotNull(f);
        assertEquals(TARGET_NAME, f.getName());
        assertEquals(TARGET_CURRENT, f.getCurrent());
        assertEquals(TARGET_DESCRIPTION, f.description());
        assertEquals(TARGET_GOAL, f.getGoal());

    }

    public void testC_fetchTarget() {
        Target t = Select.from(Target.class).whereEquals(Target.TargetContrat.COL_NAME, TARGET_NAME).first();
        assertNotNull(t);
    }

    public void testD_updateTarget() {
        Target t = Select.from(Target.class).first();
        t.setCurrent((float) 23);
        t.save();
        delay();
        Target f = Select.from(Target.class).first();
        assertEquals((float) 23, f.getCurrent());
    }

    public void testF_createRecord() {
        Record r = new Record();
        Target t = Select.from(Target.class).first();
        r.setTargetId(t.getId());
        r.setAmount(RECORD_AMOUNT_1);
        r.setCreatedDate(RECORD_CREATED_DATE_1);
        r.setTimestamp(RECORD_TIMESTAMP_1);
        r.save();
        delay();
    }

    public void testG_fetchRecord() {
        Record r = Select.from(Record.class).whereEquals(Record.RecordContract.COL_AMOUNT, RECORD_AMOUNT_1).first();
        assertNotNull(r);
        assertEquals(RECORD_AMOUNT_1, r.getAmount());
    }

    public void testH_updateRecord() {
        Record r = Select.from(Record.class).whereEquals(Record.RecordContract.COL_AMOUNT, RECORD_AMOUNT_1).first();
        assertNotNull(r);
        r.setAmount((float) 100);
        r.save();
        delay();
        Record f = Select.from(Record.class).first();
        assertNotNull(f);
        assertEquals((float) 100, f.getAmount());
    }

    private void delay() {
        long delay = 20;
        long initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - initialTime < delay) ;
    }

    public final String TARGET_NAME = "Auto";
    public final Float TARGET_GOAL = (float) 100;
    public final Float TARGET_CURRENT = (float) 28;
    public final String TARGET_DESCRIPTION = "One step closer.";

    public final Float RECORD_AMOUNT_1 = (float) 23;
    public final Date RECORD_CREATED_DATE_1 = Calendar.getInstance().getTime();
    public final Date RECORD_TIMESTAMP_1 = Calendar.getInstance().getTime();


}
