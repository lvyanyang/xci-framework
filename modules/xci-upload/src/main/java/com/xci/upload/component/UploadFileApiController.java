/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.upload.component;

import com.xci.annotation.SingleJson;
import com.xci.base.ApiController;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.exceptions.ValidException;
import com.xci.upload.model.UploadFileModel;
import com.xci.upload.configuration.UploadFileProperties;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件上传下载接口
 * @author 吕艳阳
 */
@Slf4j
@Api(tags = "文件上传下载接口")
@Controller
@RequestMapping(value = "/upload/v2/file", produces = R.PROJSON)
public class UploadFileApiController extends ApiController {
    @Resource private UploadFileProperties fileUploadProperties;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uFile", value = "上传的文件", dataType = "__File"),
            @ApiImplicitParam(name = "subCatalog", value = "子目录,可为空")
    })
    @ResponseBody
    @PostMapping("/upload")
    public RestResult<UploadFileModel> upload(MultipartFile uFile, @RequestParam(required = false) String subCatalog) {
        //String appId = ApiHelper.currentApp().getId();
        //MultiValueMap<String, MultipartFile> multipartFiles = ((StandardMultipartHttpServletRequest) request).getMultiFileMap();
        if (uFile == null) {
            return RestResult.fail("请指定要上传的文件");
        }

        var result = FileUploadService.me().upload(uFile, subCatalog);
        if (result.isOk()) {
            FileUploadService.UploadFileInfo uploadInfo = result.getData();
            UploadFileModel fileModel = new UploadFileModel();
            fileModel.setId(XCI.nextIdStr());
            // fileModel.setName(FileUtil.getName(uploadInfo.getUrl()));
            fileModel.setName(uploadInfo.getName());
            fileModel.setContentType(uploadInfo.getContentType());
            fileModel.setUrl(uploadInfo.getUrl());
            fileModel.setWebUrl(FileUploadService.me().buildFileWebUrl(uploadInfo.getUrl(), null));
            fileModel.setSize(uploadInfo.getSize());
//            File rfile = FileHelper.buildRootFile(uploadInfo.getUrl());
//            try(FileInputStream fis = new FileInputStream(rfile)) {
//                fileModel.setMd5(DigestUtils.md5Hex(fis));
//            }
            log.debug("成功上传文件:{}", fileModel.toString());
            return RestResult.ok(fileModel);
        }
        return RestResult.fail(result.getMsg());
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "下载文件")
    @ResponseBody
    @PostMapping(value = "/download", produces = {R.PROOCTET, R.PROJSON})
    public void download(@RequestBody DownloadBody body) {
        var path = body.getPath();
        var md5 = body.getMd5();
        if (XCI.isBlank(path)) {
            throw new ValidException("请指定下载文件名称");
        }
        path = path.replace("\\", "/");
        File file = new File(FileUploadService.me().buildDistFilePath(path));
        if (!file.exists()) {
            throw new ValidException("无效的文件名称");
        }
        HttpServletResponse response = getResponse();
        try {
            if (XCI.isNotBlank(md5)) {
                var smd5 = DigestUtils.md5Hex(new FileInputStream(file));
                if (smd5.equalsIgnoreCase(md5)) {
                    //md5一致,默认不传输
                    response.setContentType("application/md5-identical");
                    return;
                }
            }
        } catch (IOException e) {
            throw new ValidException("下载文件失败:" + e.getMessage());
        }

        XCI.download(file, file.getName(), null, response);
        log.debug("成功下载文件:path={},md5={}", path, md5);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除文件")
    @ApiImplicitParam(name = "path", value = "文件路径")
    @ResponseBody
    @PostMapping(value = "/delete")
    public RestResult delete(@SingleJson String path) {
        if (!fileUploadProperties.isAllowDelete()) {
            return RestResult.fail("此功能未开通");
        }

        if (XCI.isBlank(path)) {
            return RestResult.fail("请指定下载文件名称");
        }
        path = path.replace("\\", "/");
        File file = new File(FileUploadService.me().buildDistFilePath(path));
        if (file.isDirectory()) {
            return RestResult.fail("输入的路径不是一个有效的文件");
        }
        if (!file.exists()) {
            return RestResult.fail("指定文件无效");
        }
        if (file.delete()) {
            log.debug("成功删除文件:path={}", path);
            return RestResult.ok();
        }
        return RestResult.fail("删除失败");
    }

    @Data
    public static class DownloadBody {
        @ApiModelProperty(value = "文件路径", required = true)
        private String path;
        @ApiModelProperty(value = "md5")
        private String md5;
    }
}