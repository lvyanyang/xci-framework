/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.sys.api;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.lvyanyang.sys.core.SysApiController;
import com.google.common.collect.Lists;
import com.github.lvyanyang.core.RestResult;
import com.github.lvyanyang.core.GMap;
import com.github.lvyanyang.core.R;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 图表测试数据接口
 * @author 吕艳阳
 */
@Api(tags = "图表测试数据接口")
@ApiSort(41)
@RestController
@RequestMapping(value = R.SysApiPrefix + "/chart/demo", produces = R.PROJSON)
public class ChartDemoApiController extends SysApiController {
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "各客运站实时旅客发送量统计")
    @PostMapping("/ticketSummary")
    public RestResult<List> realTicketStat() {
        List list = Lists.newArrayList(GMap.newMap("series", "售票量").append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", "售票量").append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", "售票量").append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", "售票量").append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", "售票量").append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)),
                                       GMap.newMap("series", "售票量").append("label", "西安市客运站").append("value", RandomUtil.randomInt(21001, 59999)));
        return RestResult.ok(list);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "各客运站日旅客发送量统计")
    @PostMapping("/dayTicketStat")
    public RestResult<List> dayTicketStat() {
        List list = Lists.newArrayList(GMap.newMap("series", "昨日").append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", "昨日").append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", "昨日").append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", "昨日").append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", "昨日").append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)),
                                       GMap.newMap("series", "昨日").append("label", "西安市客运站").append("value", RandomUtil.randomInt(21001, 59999)),

                                       GMap.newMap("series", "今日").append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", "今日").append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", "今日").append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", "今日").append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", "今日").append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)));
        return RestResult.ok(list);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "各客运站三日旅客发送量对比统计")
    @PostMapping("/dateTicketStat")
    public RestResult<List> dateTicketStat() {
        String d1 = DateUtil.offsetDay(new Date(), -2).toString(DatePattern.CHINESE_DATE_PATTERN);
        String d2 = DateUtil.offsetDay(new Date(), -1).toString(DatePattern.CHINESE_DATE_PATTERN);
        String d3 = DateTime.of(new Date()).toString(DatePattern.CHINESE_DATE_PATTERN);

        List list = Lists.newArrayList(GMap.newMap("series", d1).append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", d1).append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", d1).append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", d1).append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", d1).append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)),
                                       GMap.newMap("series", d1).append("label", "西安市客运站").append("value", RandomUtil.randomInt(21001, 59999)),

                                       GMap.newMap("series", d2).append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", d2).append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", d2).append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", d2).append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", d2).append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)),
                                       GMap.newMap("series", d2).append("label", "西安市客运站").append("value", RandomUtil.randomInt(21001, 59999)),

                                       GMap.newMap("series", d3).append("label", "城东客运站").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("series", d3).append("label", "城西客运站").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("series", d3).append("label", "城南客运站").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("series", d3).append("label", "城北客运站").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("series", d3).append("label", "三府湾客运站").append("value", RandomUtil.randomInt(999, 9999)));
        return RestResult.ok(list);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "各客运站网上订票源占比情况统计")
    @PostMapping("/networkTicketStat")
    public RestResult<List> networkTicketStat() {
        List list = Lists.newArrayList(GMap.newMap("label", "携程网").append("value", RandomUtil.randomInt(11001, 19999)),
                                       GMap.newMap("label", "创旅同程").append("value", RandomUtil.randomInt(11002, 29999)),
                                       GMap.newMap("label", "畅途网").append("value", RandomUtil.randomInt(11003, 39999)),
                                       GMap.newMap("label", "纺织城微信").append("value", RandomUtil.randomInt(11004, 49999)),
                                       GMap.newMap("label", "西安公路客票网").append("value", RandomUtil.randomInt(999, 9999)));
        return RestResult.ok(list);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "各线路旅客发送量统计")
    @PostMapping("/routeTicketStat")
    public RestResult<List> routeTicketStat() {
        List list = Lists.newArrayList(GMap.newMap("label", "兴平").append("value", RandomUtil.randomInt(1001, 69999)),
                                       GMap.newMap("label", "乾县").append("value", RandomUtil.randomInt(2001, 69999)),
                                       GMap.newMap("label", "汉中").append("value", RandomUtil.randomInt(3001, 69999)),
                                       GMap.newMap("label", "礼泉").append("value", RandomUtil.randomInt(4001, 69999)),
                                       GMap.newMap("label", "武功").append("value", RandomUtil.randomInt(5001, 69999)),
                                       GMap.newMap("label", "扶风").append("value", RandomUtil.randomInt(6001, 69999)),
                                       GMap.newMap("label", "杨凌").append("value", RandomUtil.randomInt(7001, 69999)),
                                       GMap.newMap("label", "彬县").append("value", RandomUtil.randomInt(8001, 69999)),
                                       GMap.newMap("label", "眉县").append("value", RandomUtil.randomInt(9001, 69999)),
                                       GMap.newMap("label", "永寿").append("value", RandomUtil.randomInt(10001, 69999)));
        return RestResult.ok(list);
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "出租车日营运里程/总里程对比统计")
    @PostMapping("/taxiMileageStat")
    public RestResult<List> taxiMileageStat() {
        List list = Lists.newArrayList(GMap.newMap("label", "6:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "7:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "8:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "9:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "15:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "17:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "18:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "20:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "21:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)),
                                       GMap.newMap("label", "22:00").append("营运里程", RandomUtil.randomInt(2000000, 3000000)).append("总里程", RandomUtil.randomInt(3000000, 5000000)));
        return RestResult.ok(list);
    }
}