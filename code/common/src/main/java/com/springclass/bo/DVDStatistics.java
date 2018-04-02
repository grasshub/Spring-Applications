package com.springclass.bo;

import com.springclass.domain.DVDInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by mickknutson on 11/15/14.
 */
public interface DVDStatistics {

    void addData(List<DVDInfo> data);

    Map<String, Integer> getStatistics();

} // The End...
