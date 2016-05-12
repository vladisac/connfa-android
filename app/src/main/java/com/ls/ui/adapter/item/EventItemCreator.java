package com.ls.ui.adapter.item;

import com.ls.hacktmcon.model.data.Event;

public interface EventItemCreator {

    EventListItem getItem(Event event);

}
