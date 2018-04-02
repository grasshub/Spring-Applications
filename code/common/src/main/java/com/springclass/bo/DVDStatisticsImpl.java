package com.springclass.bo;


import com.springclass.domain.DVDInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * <p/>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Component
public final class DVDStatisticsImpl implements DVDStatistics {

    private Map<String, Integer> stats = new HashMap<String, Integer>();

    private static DVDStatistics currentInstance;

    public static DVDStatistics getInstance() {
        if (currentInstance == null) {
            currentInstance = new DVDStatisticsImpl();
        }
        return currentInstance;
    }

    public DVDStatisticsImpl() {}

    @Override
    public void addData(List<DVDInfo> data) {
        for (DVDInfo info : data) {
            String title = info.getTitle();
            if (stats.containsKey(title)) {
                int counter = stats.get(title);
                counter++;
                stats.put(title, counter);
            } else {
                stats.put(title, 1);
            }
        }
    }

    @Override
    public Map<String, Integer> getStatistics() {
        return stats;
    }

} // The End...
