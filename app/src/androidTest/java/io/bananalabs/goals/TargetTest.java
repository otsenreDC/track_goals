package io.bananalabs.goals;

import android.test.AndroidTestCase;

import io.bananalabs.goals.models.Target;

/**
 * Created by EDC on 4/1/16.
 */
public class TargetTest extends AndroidTestCase {

    private final String CAR = "Carro";

    public void testCreateUser() {
        Target target = Target.getInstance(getContext());
        assertNotNull(target);
        assertNotNull(target.getGoal());
        assertNotNull(target.getCurrent());
        assertNotNull(target.getName());

        target.setName(CAR);
        assertTrue(target.getName().equals(CAR));

    }

    public void testCurrentPercentage() {
        Target target = Target.getInstance(getContext());
        target.setCurrent((float) 10);
        target.setGoal((float) 100);
        assertTrue(target.getCurrentPercentage() == 10);
    }

    public void testAddingAmount() {
        Target target = Target.getInstance(getContext());
        float current = target.getCurrent();
        target.addAmountToCurrent(10);
        assertTrue(current + 10 == target.getCurrent());
    }

    public void testSubtractingAmount() {
        Target target = Target.getInstance(getContext());
        float current = target.getCurrent();
        target.subtractAmountFromCurrent(10);
        assertTrue(current - 10 == target.getCurrent());
    }
}
