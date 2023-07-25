package com.hanafish.hanawiki.mapper;

import com.hanafish.hanawiki.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {

    public void genSnapshot();

    List<StatisticResp> getStatistic();
//
//    List<StatisticResp> get30Statistic();
}
