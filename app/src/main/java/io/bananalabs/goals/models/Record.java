package io.bananalabs.goals.models;

import java.util.Calendar;
import java.util.Date;

import za.co.cporm.model.CPDefaultRecord;
import za.co.cporm.model.annotation.Column.Column;
import za.co.cporm.model.annotation.References;
import za.co.cporm.model.annotation.Table;

/**
 * Created by eslamb on 4/22/16.
 */
@Table
public class Record extends CPDefaultRecord<Record> {
    @Column(required = true)
    @References(Target.class)
    private Long mTargetId;
    @Column(required = true)
    private Date mCreatedDate;
    @Column(required = true)
    private Float mAmount;
    @Column(required = true)
    private Date mTimestamp;

    public Record() {
        mTimestamp = Calendar.getInstance().getTime();
    }

    public Long getTargetId() {
        return mTargetId;
    }

    public void setTargetId(Long mTargetId) {
        this.mTargetId = mTargetId;
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(Date mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public Float getAmount() {
        return mAmount;
    }

    public void setAmount(Float mAmount) {
        this.mAmount = mAmount;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public static class RecordContract {
        public static final String COL_ID = "_id";
        public static final String COL_TARGET_ID = "m_target_id";
        public static final String COL_CREATED_DATE = "m_created_date";
        public static final String COL_AMOUNT = "m_AMOUNT";
        public static final String COL_TIMESTAMP = "m_timestamp";
    }
}
