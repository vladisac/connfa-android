package com.ls.hacktmcon.model.managers;

import com.ls.drupal.AbstractBaseDrupalEntity;
import com.ls.drupal.DrupalClient;
import com.ls.hacktmcon.model.PreferencesManager;
import com.ls.hacktmcon.model.data.SettingsHolder;
import com.ls.hacktmcon.model.requests.SettingsRequest;
import com.ls.util.L;

public class SettingsManager extends SynchronousItemManager<SettingsHolder, Object, String> {

    public SettingsManager(DrupalClient client) {
        super(client);
    }

    @Override
    protected AbstractBaseDrupalEntity getEntityToFetch(DrupalClient client, Object requestParams) {
        return new SettingsRequest(client);
    }

    @Override
    protected String getEntityRequestTag(Object params) {
        return "settings";
    }

    @Override
    protected boolean storeResponse(SettingsHolder requestResponse, String tag) {
//        String timeZoneNumber = requestResponse.getSettings().getTimeZone();
//        String timeZone = String.format("GMT%s", timeZoneNumber);
        String timeZone = requestResponse.getSettings().getTimeZone();
        L.e("Settings timezone:" + timeZone);
        PreferencesManager.getInstance().saveTimeZone(timeZone);

        return true;
    }
}
