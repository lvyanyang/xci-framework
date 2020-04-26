/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.upload.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.xci.annotation.SingleJson;
import com.xci.base.ApiController;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.upload.configuration.UploadFileProperties;
import com.xci.upload.model.UpgradeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 软件更新包接口
 * @author 吕艳阳
 */
@Api(tags = "软件更新包接口")
@RestController
@RequestMapping(value = "/upload/v2/upgrade", produces = R.PROJSON)
@ConditionalOnProperty(name = "xci.upload.enable-upgrade", havingValue = R.TRUE)
public class UpgradeApiController extends ApiController {
    @Resource private UploadFileProperties fileUploadProperties;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "检测更新")
    @ApiImplicitParam(name = "version", value = "当前版本号")
    @PostMapping("/check")
    public RestResult<List<UpgradeModel>> check(@SingleJson Integer version) {
        //String appId = ApiHelper.currentApp().getId();
        List<UpgradeModel> data = loadPackageInfo();
        List<UpgradeModel> newData = data.stream().filter(p -> p.getVersion() > version).collect(Collectors.toList());
        setDataUrl(newData);
        return RestResult.ok(newData);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "上传更新包")
    @ApiImplicitParam(name = "file", value = "上传的更新包", dataType = "__File")
    @PostMapping("/upload")
    public RestResult<FileUploadService.UploadFileInfo> upload(MultipartFile file, @RequestParam(required = false) String password) {
        // String appId = ApiHelper.currentApp().getId();
        if (XCI.isBlank(password) || !SecureUtil.md5(fileUploadProperties.getUpgradePassword()).equals(password)) {
            return RestResult.fail("您没有上传权限");
        }

        UploadFileProperties setting = new UploadFileProperties();
        setting.setRoot(XCI.format("/{webroot}/{}", R.UPGRADE_FOLDER));
        setting.setExtensions(new String[]{"zip"});//只允许zip文件
        setting.setMaxSize(1024L * 50);//最大50M
        var result = FileUploadService.me().upload(file, null, setting);
        if (result.isOk()) {
            List<UpgradeModel> data = loadPackageInfo();
            UpgradeModel upgradeModel = new UpgradeModel();
            upgradeModel.setVersion(1);
            data.stream().max(Comparator.comparingInt(UpgradeModel::getVersion)).ifPresent(p -> upgradeModel.setVersion(p.getVersion() + 1));
            upgradeModel.setName(FileUtil.getName(result.getData().getUrl()));
            upgradeModel.setCreateTime(new Date());
            data.add(upgradeModel);
            savePackageInfo(data);
            return RestResult.ok();
        } else {
            return result;
        }
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询更新记录")
    @PostMapping("/list")
    public RestResult<List<UpgradeModel>> list() {
        //String appId = ApiHelper.currentApp().getId();
        List<UpgradeModel> data = loadPackageInfo();
        setDataUrl(data);
        return RestResult.ok(data);
    }

    private List<UpgradeModel> loadPackageInfo() {
        return XCI.loadJsonList(getPackageFile(), ArrayList.class, UpgradeModel.class);
    }

    private void savePackageInfo(List<UpgradeModel> data) {
        XCI.saveJsonList(getPackageFile(), data);
    }

    private File getPackageFile() {
        return XCI.buildRootFile(XCI.format("/{}/upgrades.json", R.UPGRADE_FOLDER));
    }

    private void setDataUrl(List<UpgradeModel> data) {
        for (UpgradeModel info : data) {
            info.setUrl(XCI.getSiteFullUrl(XCI.format("/{}/{}", R.UPGRADE_FOLDER, info.getName())));
        }
    }
}