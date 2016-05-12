package com.ls.hacktmcon.model.dao;

import com.ls.hacktmcon.model.AppDatabaseInfo;
import com.ls.hacktmcon.model.data.Level;
import com.ls.hacktmcon.model.database.AbstractEntityDAO;


public class LevelDao extends AbstractEntityDAO<Level, Long> {

    public static final String TABLE_NAME = "table_level";

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
    protected Level newInstance() {
        return new Level();
    }

    @Override
    protected String[] getKeyColumns() {
        return new String[0];
    }
}
