package com.ls.hacktmcon.model.managers;

import com.ls.drupal.AbstractBaseDrupalEntity;
import com.ls.drupal.DrupalClient;
import com.ls.hacktmcon.model.dao.LocationDao;
import com.ls.hacktmcon.model.data.Location;
import com.ls.hacktmcon.model.requests.LocationRequest;

import java.util.List;

public class LocationManager extends SynchronousItemManager<Location.Holder, Object, String> {

    private LocationDao mLocationDao;
    public LocationManager(DrupalClient client) {
        super(client);
        mLocationDao = new LocationDao();
    }

    @Override
    protected AbstractBaseDrupalEntity getEntityToFetch(DrupalClient client, Object requestParams) {
        return new LocationRequest(client);
    }

    @Override
    protected String getEntityRequestTag(Object params) {
        return "location";
    }

    @Override
    protected boolean storeResponse(Location.Holder requestResponse, String tag) {
        List<Location> locations = requestResponse.getLocations();
        if (locations == null) {
            return false;
        }

        mLocationDao.saveOrUpdateDataSafe(locations);
        for (Location location : locations) {
            if (location != null) {
                if (location.isDeleted()) {
                    mLocationDao.deleteDataSafe(location.getId());
                }
            }
        }
        return true;
    }

    public List<Location> getLocations() {
        return mLocationDao.getAllSafe();
    }

    public void clear() {
        mLocationDao.deleteAll();
    }
}
