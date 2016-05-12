package com.ls.hacktmcon.model.requests;

import com.ls.drupal.DrupalClient;
import com.ls.hacktmcon.model.data.SettingsHolder;
import com.ls.http.base.BaseRequest;

import java.util.Map;

public class SettingsRequest extends BaseSafeConsumeContainerRequest<SettingsHolder> {

    public SettingsRequest(DrupalClient client) {
        super(client, new SettingsHolder());
    }

    @Override
    protected String getPath() {
        return "getSettings";
    }

    @Override
    protected Map<String, String> getItemRequestPostParameters() {
        return null;
    }

    @Override
    protected Map<String, Object> getItemRequestGetParameters(BaseRequest.RequestMethod method) {
        return null;
    }

}
