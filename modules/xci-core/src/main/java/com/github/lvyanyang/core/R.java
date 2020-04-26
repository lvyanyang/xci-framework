/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.core;

/**
 * 系统常量
 * @author 吕艳阳
 */
public class R {
    /**
     * 默认日期时间格式字符串
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期分钟格式字符串
     */
    public static final String DEFAULT_DATE_MS_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 默认日期格式字符串
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 默认日期格式字符串
     */
    public static final String Directory_DATE_PATTERN = "yyyyMMdd";

    /**
     * 默认时间格式字符串
     */
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 根节点Id
     */
    public static final Long ROOT_NODE_ID = 0L;

    /**
     * 单个路径长度
     */
    public static final Integer PATH_LENGTH = 4;

    /**
     * 接口文档是否启用配置名
     */
    public static final String SWAGGER_ENABLED_ON_PROPERTY = "xci.swagger.enabled";

    /**
     * 空字符串
     */
    public static final String Empty = "";

    /**
     * 作者
     */
    public static final String LYY = "吕艳阳";

    /**
     * JwtToken加密解密密钥
     */
    public static final String JWT_SECRET_KEY = "3MZq/0BYyGcXYoXjhS4%QbAM+2YdlLCwKRr2gvVJO/J";

    /**
     * AES加密方法
     */
    public static final String AES_METHOD = "AES";

    /**
     * AES实例字符串
     */
    public static final String AES_INSTANCE = "AES/CBC/PKCS5Padding";

    /**
     * AES加解密密钥
     */
    public static final String AES_SECRET_KEY = "3Zq/0Yy/Gc4Y/b8%";

    /**
     * AES填充向量
     */
    public static final String AES_Padding_IV = "0000000000000000";

    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static final String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 导出图片类型
     */
    public static final String PRO_JPEG = "image/jpeg";

    /**
     * 响应类型:octet-stream
     */
    public static final String PROOCTET = "application/octet-stream";

    /**
     * 响应类型:json
     */
    public static final String PROJSON = "application/json";

    /**
     * 响应类型:x-www-form-urlencoded
     */
    public static final String PROFORM = "application/x-www-form-urlencoded";

    /**
     * token字段
     */
    public static final String TOKEN = "token";

    /**
     * ID字段
     */
    public static final String ID = "id";

    /**
     * 正序排序方式
     */
    public static final String ASC = "asc";

    /**
     * 倒序排序方式
     */
    public static final String DESC = "desc";

    /**
     * 新建记录标记
     */
    public static final String CREATE_MARK = "_create_";


    /**
     * 请求头app键名
     */
    public static final String HEADER_APPID_KEY = "appId";

    /**
     * 请求头timestamp键名
     */
    public static final String HEADER_TIMESTAMP_KEY = "timestamp";

    /**
     * 请求头token键名
     */
    public static final String HEADER_TOKEN_KEY = "token";

    /**
     * 请求头文件token键名
     */
    public static final String HEADER_FILE_TOKEN_KEY = "fileToken";

    /**
     * 升级包目录
     */
    public static final String UPGRADE_FOLDER = "upgrade";

    /**
     * 当前应用键名
     */
    public static final String CURRENT_APP_API_KEY = "_currentAppApi_";
    /**
     * 当前用户Api键名
     */
    public static final String CURRENT_USER_API_KEY = "_currentUserApi_";

    /**
     * 当前ApiTokenID键名
     */
    public static final String CURRENT_API_TOKEN_ID_KEY = "_currentApiTokenID_";

    /**
     * 当前用户Session键
     */
    public static final String CURRENT_USER_Session_KEY = "_currentUserSession_";

    /**
     * 当前用户Cookie键名
     */
    public static final String CURRENT_USER_COOKIE_KEY = "_currentUserCookie_";

    /**
     * 是 Excel替换
     */
    public static final String YesStatusReplace = "是_true";

    /**
     * 否 Excel替换
     */
    public static final String NoStatusReplace = "否_false";

    /**
     * 启用状态Excel替换
     */
    public static final String EnabledStatusReplace = "启用_true";

    /**
     * 禁用状态Excel替换
     */
    public static final String DisabledStatusReplace = "禁用_false";

    /**
     * 启用状态名称
     */
    public static final String EnabledStatusName = "启用";

    /**
     * 禁用状态名称
     */
    public static final String DisabledStatusName = "禁用";

    /**
     * true
     */
    public static final String TRUE = "true";

    /**
     * false
     */
    public static final String FALSE = "false";

    /**
     * 启用状态
     */
    public static final int EnabledStatus = 1;

    /**
     * 禁用状态
     */
    public static final int DisabledStatus = 0;

    /**
     * 用户主键字段
     */
    public static final String USER_ID = "id";

    /**
     * 创建日期字段
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 更新日期字段
     */
    public static final String UPDATE_TIME = "updateTime";

    /**
     * 操作日期字段
     */
    public static final String OPERATE_DATE_TIME = "operateDateTime";

    /**
     * 用户数据资源名称
     */
    public static final String R_USER = "user";

    /**
     * 角色数据资源名称
     */
    public static final String R_ROLE = "role";

    /**
     * 机构数据资源名称
     */
    public static final String R_DEPT = "dept";

    /**
     * 机构数据资源名称
     */
    public static final String R_DEPT_DATA = "dept.data";

    /**
     * 模块数据资源名称
     */
    public static final String R_MODULE = "module";

    public static final String M_SYS = "System";

    public static final String IG_PAGE_INDEX = "filter.pageIndex";
    public static final String IG_PAGE_SIZE = "filter.pageSize";
    public static final String IG_SORT_NAME = "filter.sortName";
    public static final String IG_SORT_DIR = "filter.sortDir";
    public static final String IG_FIELDNAMES = "filter.fieldNames";

    /**
     * Api接口Url前缀
     */
    public static final String SysApiPrefix = "/api/v2/sys";

    /**
     * 权限编码
     */
    public static class Permission {
        public static final String SysUserSelect = "sys.user.select";
        public static final String SysUserInsert = "sys.user.insert";
        public static final String SysUserUpdate = "sys.user.update";
        public static final String SysUserSave = "sys.user.insert,sys.user.update";
        public static final String SysUserDelete = "sys.user.delete";

        public static final String SysDeptSelect = "sys.dept.select";
        public static final String SysDeptInsert = "sys.dept.insert";
        public static final String SysDeptUpdate = "sys.dept.update";
        public static final String SysDeptSave = "sys.dept.insert,sys.dept.update";
        public static final String SysDeptDelete = "sys.dept.delete";

        public static final String SysRoleSelect = "sys.role.select";
        public static final String SysRoleInsert = "sys.role.insert";
        public static final String SysRoleUpdate = "sys.role.update";
        public static final String SysRoleSave = "sys.role.insert,sys.role.update";
        public static final String SysRoleDelete = "sys.role.delete";

        public static final String SysDicSelect = "sys.dic.select";
        public static final String SysDicInsert = "sys.dic.insert";
        public static final String SysDicUpdate = "sys.dic.update";
        public static final String SysDicSave = "sys.dic.insert,sys.dic.update";
        public static final String SysDicDelete = "sys.dic.delete";

        public static final String SysDicCategorySelect = "sys.dicCategory.select";
        public static final String SysDicCategoryInsert = "sys.dicCategory.insert";
        public static final String SysDicCategoryUpdate = "sys.dicCategory.update";
        public static final String SysDicCategorySave = "sys.dicCategory.insert,sys.dicCategory.update";
        public static final String SysDicCategoryDelete = "sys.dicCategory.delete";

        public static final String SysParamSelect = "sys.param.select";
        public static final String SysParamInsert = "sys.param.insert";
        public static final String SysParamUpdate = "sys.param.update";
        public static final String SysParamSave = "sys.param.insert,sys.param.update";
        public static final String SysParamDelete = "sys.param.delete";

        public static final String SysSeqSelect = "sys.seq.select";
        public static final String SysSeqInsert = "sys.seq.insert";
        public static final String SysSeqUpdate = "sys.seq.update";
        public static final String SysSeqSave = "sys.seq.insert,sys.seq.update";
        public static final String SysSeqDelete = "sys.seq.delete";

        public static final String SysAppSelect = "sys.app.select";
        public static final String SysAppInsert = "sys.app.insert";
        public static final String SysAppUpdate = "sys.app.update";
        public static final String SysAppSave = "sys.app.insert,sys.app.update";
        public static final String SysAppDelete = "sys.app.delete";

        public static final String SysReportSelect = "sys.report.select";
        public static final String SysReportInsert = "sys.report.insert";
        public static final String SysReportUpdate = "sys.report.update";
        public static final String SysReportSave = "sys.report.insert,sys.report.update";
        public static final String SysReportDelete = "sys.report.delete";

        public static final String SysModuleSelect = "sys.module.select";
        public static final String SysModuleInsert = "sys.module.insert";
        public static final String SysModuleUpdate = "sys.module.update";
        public static final String SysModuleSave = "sys.module.insert,sys.module.update";
        public static final String SysModuleDelete = "sys.module.delete";
    }

    /**
     * 数据字典类型编码
     */
    public static class DicCategory {
        public static final String SysParamCategory = "sys.param.category";
    }

    /**
     * 模块
     */
    public static class Module {
        /**
         * 系统应用
         */
        public static final String App = "系统应用";

        /**
         * 系统参数
         */
        public static final String Param = "系统参数";

        /**
         * 系统字典类型
         */
        public static final String DicCategory = "系统字典类型";

        /**
         * 系统字典
         */
        public static final String Dic = "系统字典";

        /**
         * 系统角色
         */
        public static final String Role = "系统角色";

        /**
         * 系统用户
         */
        public static final String User = "系统用户";

        /**
         * 系统机构
         */
        public static final String Dept = "系统机构";

        /**
         * 系统文件
         */
        public static final String File = "系统文件";

        /**
         * 系统序列
         */
        public static final String Seq = "系统序列";

        /**
         * 系统模块
         */
        public static final String Module = "系统模块";

        /**
         * 系统报表
         */
        public static final String Report = "系统报表";

        /**
         * 系统图表
         */
        public static final String Chart = "系统图表";

        /**
         * 系统部件
         */
        public static final String Widget = "系统部件";

        public static final String LockUser = "锁定用户";
        public static final String OnlineUser = "在线用户";
    }
}