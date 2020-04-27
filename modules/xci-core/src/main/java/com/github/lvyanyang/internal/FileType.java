/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.internal;

import com.github.lvyanyang.core.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 根据文件内容检查文件类型
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-04-27 10:55
 */
public class FileType {
    //默认判断文件头前三个字节内容
    public static int CHECK_BYTES_NUMBER = 3;
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    private FileType() {
    }

    static {
        getAllFileType(); //初始化文件类型信息
    }

    /**
     * 常见文件头信息
     */
    private static void getAllFileType() {
        // 第一级分类
        // text	普通文本
        // image	某种图像
        // audio	某种音频文件
        // video	某种视频文件
        // application	应用数据
        // multi-part	复合内容

        // 第二级分类
        // audio/wav	wave音频流媒体文件
        // audio/webm	webm 音频文件格式
        // audio/ogg	ogg多媒体文件格式的音频文件
        // audio/mpeg	mpeg多媒体文件格式的音频文件
        // image/gif	gif图片
        // image/jpeg	jpeg图片
        // image/png	png图片
        // image/svg+xml	svg矢量图片
        // application/json	json格式
        // application/xml	xml格式
        // application/xhtml+xml	扩展html格式
        // application/x-www-form-urlencoded	表单url内容编码
        // application/octet-stream	二进制格式
        // application/pdf	pdf文档
        // application/atom+xml	atom订阅feed流
        // multipart/form-data	多文档格式
        // text/plain	普通文本
        // text/html	html文档
        // text/css	css文件
        // text/javascript	javascript文件
        // text/markdown	markdown文档
        // video/mpeg	mpeg多媒体视频文件
        // video/quicktime	mov多媒体视频文件

        FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
        FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); //PNG (png)
        FILE_TYPE_MAP.put("47494638396126026f01", "gif"); //GIF (gif)
        FILE_TYPE_MAP.put("49492a00227105008037", "tif"); //TIFF (tif)
        FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); //16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); //24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
        FILE_TYPE_MAP.put("41433130313500000000", "dwg"); //CAD (dwg)
        FILE_TYPE_MAP.put("3c21444f435459504520", "html"); //HTML (html)
        FILE_TYPE_MAP.put("3c21646f637479706520", "htm"); //HTM (htm)
        FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); //css
        FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); //js
        FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("38425053000100000000", "psd"); //Photoshop (psd)
        FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
        // FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
        // FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf"); //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
        FILE_TYPE_MAP.put("464c5601050000000900", "flv"); //flv与f4v相同
        FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
        FILE_TYPE_MAP.put("49443303000000002176", "mp3");
        FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
        FILE_TYPE_MAP.put("52494646e27807005741", "wav"); //Wave (wav)
        FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
        FILE_TYPE_MAP.put("4d546864000000060001", "mid"); //MIDI (mid)
        FILE_TYPE_MAP.put("504b0304140000000800", "zip");
        FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
        FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
        FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
        FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");//可执行文件
        FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");//jsp文件
        FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");//MF文件
        FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");//xml文件
        FILE_TYPE_MAP.put("494e5345525420494e54", "sql");//xml文件
        FILE_TYPE_MAP.put("7061636b616765207765", "java");//java文件
        FILE_TYPE_MAP.put("406563686f206f66660d", "bat");//bat文件
        FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");//gz文件
        FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");//bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");//bat文件
        FILE_TYPE_MAP.put("49545346030000006000", "chm");//bat文件
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");//bat文件
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");//docx文件
        FILE_TYPE_MAP.put("6431303a637265617465", "torrent");


        FILE_TYPE_MAP.put("6D6F6F76", "mov"); //Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); //WordPerfect (wpd)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)
        FILE_TYPE_MAP.put("2142444E", "pst"); //Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); //Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); //Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); //Real Audio (ram)
    }

    /**
     * 根据文件的文件头判断文件类型
     * @param path 文件路径
     * @return 如果成功返回文件类型, 否则返回空字符串
     */
    public static String getFileType(String path) {
        FileInputStream is;
        try {
            is = new FileInputStream(path);
            return getFileType(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return R.Empty;
    }

    /**
     * 根据文件的文件头判断文件类型
     * @param in 文件流
     * @return 如果成功返回文件类型, 否则返回空字符串
     */
    public static String getFileType(InputStream in) {
        try {
            byte[] b = new byte[CHECK_BYTES_NUMBER];
            in.read(b, 0, b.length);
            String fileCode = Objects.requireNonNull(bytesToHexString(b)).toLowerCase();

            //这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            for (String key : FILE_TYPE_MAP.keySet()) {
                if (key.toLowerCase().startsWith(fileCode) || fileCode.startsWith(key.toLowerCase())) {
                    FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.Empty;
    }

    /**
     * 得到文件头
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件头检查字节数
     */
    public static int getCheckBytesNumber() {
        return CHECK_BYTES_NUMBER;
    }

    /**
     * 设置文件头检查字节数
     */
    public static void setCheckBytesNumber(int checkBytesNumber) {
        CHECK_BYTES_NUMBER = checkBytesNumber;
    }
}
