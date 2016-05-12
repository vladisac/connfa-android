package com.ls.hacktmcon.model.database;

import android.database.sqlite.SQLiteDatabase;

public interface IMigrationTask {
    void onUpgrade(SQLiteDatabase theDb, int oldVersion, int newVersion);
}
