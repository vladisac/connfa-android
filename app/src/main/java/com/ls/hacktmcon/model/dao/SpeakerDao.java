package com.ls.hacktmcon.model.dao;

import com.ls.hacktmcon.R;
import com.ls.hacktmcon.model.AppDatabaseInfo;
import com.ls.hacktmcon.model.data.Speaker;
import com.ls.hacktmcon.model.database.AbstractEntityDAO;

import android.content.Context;

import java.util.List;


public class SpeakerDao extends AbstractEntityDAO<Speaker, Long> {

    public static final String TABLE_NAME = "table_speaker";
    private final Context mContext;

    public SpeakerDao(Context context) {
        mContext = context;
    }

    @Override
    protected String getSearchCondition() {
        return "_id=?";
    }

    @Override
    protected String[] getSearchConditionArguments(Long theId) {
        return new String[]{theId.toString()};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getDatabaseName() {
        return AppDatabaseInfo.DATABASE_NAME;
    }

    @Override
    protected Speaker newInstance() {
        return new Speaker();
    }

    @Override
    protected String[] getKeyColumns() {
        return new String[0];
    }

    public List<Speaker> getSpeakerById(long speakerId) {
        String query = "SELECT * FROM table_speaker WHERE _id =" + speakerId;

        return getDataBySqlQuerySafe(query, null);
    }

    public List<Speaker> getSpeakersByEventId(long eventId) {
        String query = "SELECT * FROM table_speaker WHERE _id IN (" +
                "SELECT _speaker_id FROM table_event_and_speaker WHERE _event_id = " + eventId + ")";
        return getDataBySqlQuerySafe(query, null);
    }

    public List<Speaker> selectSpeakersOrderedByName() {
        String query = mContext.getString(R.string.select_speakers_ordered_by_name);
        return getDataBySqlQuerySafe(query, null);
    }
}
