/*
 西交投业务管理系统框架
 Microsoft SQL Server 2008 (SP4) - 10.0.6241.0 (X64)
*/


-- ----------------------------
-- sys_app - 系统应用
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_app')
            AND type IN ('U'))
    DROP TABLE dbo.sys_app
GO

CREATE TABLE dbo.sys_app
(
    id     BIGINT        NOT NULL,
    name   NVARCHAR(100) NOT NULL,
    status TINYINT       NOT NULL,
    remark NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_app
    ADD CONSTRAINT pk_sys_app_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_app
    ADD CONSTRAINT uq_sys_app_name UNIQUE NONCLUSTERED (name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统应用', 'SCHEMA', N'dbo', 'TABLE', N'sys_app', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_app', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_app', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_app', 'COLUMN', N'remark'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_app', 'COLUMN', N'status'
GO

INSERT INTO dbo.sys_app (id, name, status, remark)
VALUES (1117664844619845632, 'default', 1, NULL);


-- ----------------------------
-- sys_param - 系统参数
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_param')
            AND type IN ('U'))
    DROP TABLE dbo.sys_param
GO

CREATE TABLE dbo.sys_param
(
    id       BIGINT         NOT NULL,
    name     NVARCHAR(100)  NOT NULL,
    code     NVARCHAR(100)  NOT NULL,
    spell    NVARCHAR(100)  NULL,
    value    NVARCHAR(4000) NULL,
    category TINYINT        NOT NULL DEFAULT 0,
    remark   NVARCHAR(500)  NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_param
    ADD CONSTRAINT pk_sys_param_id PRIMARY KEY CLUSTERED (id ASC) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_param
    ADD CONSTRAINT uq_sys_param_code UNIQUE NONCLUSTERED (code ASC) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统参数', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数值', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'value'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数类型', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'参数备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_param', 'COLUMN', N'remark'
GO

INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204734059289382912, '是否解析IP地理位置', 'sys.api.resolvingIpLocation', 'sfjxipdlwz', '0', 1, '1:解析;0:不解析');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1126748805002301440, '是否允许重复登录', 'sys.allowRepeatLogin', 'sfyxzfdl', '1', 0, '0 不允许重复登录 1 允许重复登录');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204637968552300544, '系统接口分页允许的最大页数', 'sys.api.maxPageSize', 'xtjkfyyxdzdys', '200', 1, '系统服务端分页接口中每页最大记录数');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204638273960546304, '系统账户密码强度检测正则表达式', 'sys.user.passwordStrongPattern', 'xtzhmmqdjczzbds',
        '(?=.*0-9)(?=.*a-zA-Z)(?=(\x21-\x7e+)^a-zA-Z0-9).{6,}', 2, '');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204638890305130496, '系统账户需要验证码重试次数', 'sys.user.requireCaptchaCount', 'xtzhxyyzmzscs', '1', 2, '如果登陆失败次数大于当前参数值时,则登陆时要求输入验证码');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204639103811981312, '系统账户锁定允许的最大重试次数', 'sys.user.lockMaxRetryCount', 'xtzhsdyxdzdzscs', '5', 2, '登陆失败次数超过当前参数值,则当前账户会被锁定');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204639294199828480, '系统账户是否允许重复登录', 'sys.user.allowRepeatLogin', 'xtzhsfyxzfdl', '1', 2, '1:允许;0:不允许');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204639535896596480, '系统账户密码过期提醒天数', 'sys.user.pwdExpireRemindDays', 'xtzhmmgqtxts', '30', 2, '当密码过期天数少于当前参数时,则在用户登陆时提醒用户修改密码');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204640012130455552, '系统账户密码有效天数', 'sys.user.pwdAvailableDays', 'xtzhmmyxts', '90', 2, '系统账户在修改密码之后的参数值天内有效,超过此天数,账户会被停用');
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204733912161587200, '系统服务器名称', 'sys.api.serverName', 'xtfwqmc', '测试机', 1, NULL);
INSERT INTO dbo.sys_param (id, name, code, spell, value, category, remark)
VALUES (1204969582125780992, '系统账户密码强度检测错误消息', 'sys.user.passwordStrongErrorMsg', 'xtzhmmqdjccwxx',
        '不满足密码策略，密码要求必须包含数字、小写或大写字母、特殊字符、不少于6个字符。', 2, NULL);



-- ----------------------------
-- sys_user - 系统用户
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_user')
            AND type IN ('U'))
    DROP TABLE dbo.sys_user
GO

CREATE TABLE dbo.sys_user
(
    id               BIGINT        NOT NULL,
    name             NVARCHAR(100) NOT NULL,
    spell            NVARCHAR(100) NULL,
    account          NVARCHAR(100) NOT NULL,
    category         TINYINT       NOT NULL DEFAULT 0,
    dept_id          BIGINT        NOT NULL,
    role_id          BIGINT        NULL,
    pwd              NVARCHAR(500) NOT NULL,
    pwd_salt         NVARCHAR(100) NOT NULL,
    pwd_must_modify  TINYINT       NOT NULL DEFAULT 0,
    pwd_allow_modify TINYINT       NOT NULL DEFAULT 1,
    pwd_never_expire TINYINT       NOT NULL DEFAULT 0,
    pwd_expire_time  DATETIME      NULL,
    allow_start_time DATETIME      NULL,
    allow_end_time   DATETIME      NULL,
    admin            TINYINT       NOT NULL,
    mobile           NVARCHAR(100) NULL,
    email            NVARCHAR(100) NULL,
    post             NVARCHAR(100) NULL,
    login_times      INT           NOT NULL,
    first_visit_time DATETIME      NULL,
    last_visit_time  DATETIME      NULL,
    ent_id           NVARCHAR(100) NULL,
    ent_name         NVARCHAR(200) NULL,
    visible          TINYINT       NOT NULL DEFAULT 1,
    deleted          BIGINT        NOT NULL DEFAULT 0,
    status           TINYINT       NOT NULL,
    remark           NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_user
    ADD CONSTRAINT pk_sys_user_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_user
    ADD CONSTRAINT uq_sys_user_account_deleted UNIQUE NONCLUSTERED (account, deleted) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统用户', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'账号', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'account'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否超管 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'administrator'
GO
EXEC sp_addextendedproperty N'MS_Description', N'结束有效期', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'allow_end_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'开始有效期', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'allow_start_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'账号类型 [1-企业账号, 0-系统账号]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'删除状态 [0-未删除]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'deleted'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'dept_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'电子邮件', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'email'
GO
EXEC sp_addextendedproperty N'MS_Description', N'企业主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'ent_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'企业名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'ent_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'首次登录时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'first_visit_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'用户主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'最后登录时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'last_visit_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'登录次数', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'login_times'
GO
EXEC sp_addextendedproperty N'MS_Description', N'手机号码', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'mobile'
GO
EXEC sp_addextendedproperty N'MS_Description', N'用户姓名', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'职位', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'post'
GO
EXEC sp_addextendedproperty N'MS_Description', N'密码', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd'
GO
EXEC sp_addextendedproperty N'MS_Description', N'密码永不过期 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd_never_expire'
GO
EXEC sp_addextendedproperty N'MS_Description', N'密码过期时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd_expire_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'必须修改密码 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd_must_modify'
GO
EXEC sp_addextendedproperty N'MS_Description', N'允许修改密码 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd_allow_modify'
GO
EXEC sp_addextendedproperty N'MS_Description', N'密码盐', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'pwd_salt'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'remark'
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'role_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'用户简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'显示状态 [1-显示, 0-不显示]', 'SCHEMA', N'dbo', 'TABLE', N'sys_user', 'COLUMN', N'visible'
GO

INSERT INTO dbo.sys_user (id, name, spell, account, category, dept_id, role_id, pwd, pwd_salt, pwd_must_modify, pwd_allow_modify, pwd_never_expire, pwd_expire_time, allow_start_time, allow_end_time, admin, mobile, email, post, login_times, first_visit_time, last_visit_time, ent_id, ent_name, visible, deleted, status, remark) VALUES (1, '管理员', 'admin', 'admin', 0, 1122462624387305472, 1194563452912406528, '785b154a41baf9977cb3d34bb2fb9558', '39b5281aa1fb30bb2d09119cf7255e37', 0, 0, 1, null, null, null, 1, null, null, '董事长', 1070, '2019-12-27 17:34:00.783', '2020-03-19 19:26:46.513', null, null, 1, 0, 1, null);


-- ----------------------------
-- sys_role - 系统角色
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_role')
            AND type IN ('U'))
    DROP TABLE dbo.sys_role
GO

CREATE TABLE dbo.sys_role
(
    id         BIGINT        NOT NULL,
    name       NVARCHAR(100) NOT NULL,
    code       NVARCHAR(100) NOT NULL,
    spell      NVARCHAR(100) NULL,
    dept_id    BIGINT        NOT NULL DEFAULT 0,
    dept_scope TINYINT       NULL,
    path       INT           NOT NULL DEFAULT 0,
    status     TINYINT       NOT NULL DEFAULT 1,
    remark     NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_role
    ADD CONSTRAINT pk_sys_role_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_role
    ADD CONSTRAINT uq_sys_role_code UNIQUE NONCLUSTERED (code) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_role
    ADD CONSTRAINT uq_sys_role_dept_id_name UNIQUE NONCLUSTERED (dept_id, name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统角色', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构主键 [0-全局角色]', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'dept_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构数据权限 [1-全部, 2-自定义, 3-所在部门, 4-所在部门及所有下级, 5-仅本人]', 'SCHEMA', N'dbo', 'TABLE', N'sys_role',
     'COLUMN',
     N'dept_scope'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序号', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_role', 'COLUMN', N'remark'
GO


-- ----------------------------
-- sys_dept - 系统机构
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_dept')
            AND type IN ('U'))
    DROP TABLE dbo.sys_dept
GO

CREATE TABLE dbo.sys_dept
(
    id        BIGINT        NOT NULL,
    parent_id BIGINT        NOT NULL DEFAULT 0,
    name      NVARCHAR(100) NOT NULL,
    code      NVARCHAR(100) NOT NULL,
    spell     NVARCHAR(100) NULL,
    category  NVARCHAR(100) NULL,
    nature    NVARCHAR(100) NULL,
    leader_id BIGINT        NULL,
    path      INT           NOT NULL DEFAULT 0,
    deleted   BIGINT        NOT NULL DEFAULT 0,
    status    TINYINT       NOT NULL DEFAULT 1,
    remark    NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dept
    ADD CONSTRAINT pk_sys_dept_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dept
    ADD CONSTRAINT uq_sys_dept_code_deleted UNIQUE NONCLUSTERED (code, deleted) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dept
    ADD CONSTRAINT uq_sys_dept_parent_id_name UNIQUE NONCLUSTERED (parent_id, name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统机构', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'上级主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'parent_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构类型', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构性质', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'nature'
GO
EXEC sp_addextendedproperty N'MS_Description', N'机构负责人主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'leader_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序号', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_dept', 'COLUMN', N'remark'
GO

INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1122462624387305472, 0, '西安交通信息', '100', 'xasjtj', '1', '1', 1, 0, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555463119802368, 1122462624387305472, '领导层', '100.001', 'ldc', NULL, NULL, NULL, 2, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555517134049280, 1122462624387305472, '综合部', '100.002', 'zhb', NULL, NULL, NULL, 6, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555557328064512, 1122462624387305472, '财务部', '100.003', 'cwb', NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555591419367424, 1122462624387305472, '质量部', '100.004', 'zlb', NULL, NULL, NULL, 4, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555625548419072, 1122462624387305472, '总工办', '100.005', 'zgb', NULL, NULL, NULL, 5, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555678170157056, 1122462624387305472, '运营部', '100.006', 'yyb', NULL, NULL, NULL, 3, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555724802428928, 1122462624387305472, '技术部', '100.007', 'jsb', NULL, NULL, NULL, 0, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555762916069376, 1122462624387305472, '保障部', '100.008', 'bzb', NULL, NULL, NULL, 7, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555794268491776, 1122462624387305472, '工程部', '100.009', 'gcb', NULL, NULL, NULL, 8, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555882776694784, 1122462624387305472, '公交事业部', '100.010', 'gjsyb', NULL, NULL, NULL, 9, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194555925688619008, 1122462624387305472, '借调人员', '100.011', 'jdry', NULL, NULL, NULL, 10, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194557931744530432, 1194555724802428928, '公交组', '100.007.001', 'gjz', NULL, NULL, NULL, 0, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194557968419524608, 1194555724802428928, '出租组', '100.007.002', 'czz', NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194558002007511040, 1194555724802428928, '运管组', '100.007.003', 'ygz', NULL, NULL, NULL, 2, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194558057179385856, 1194555724802428928, '维修驾培组', '100.007.004', 'wxjpz', NULL, NULL, NULL, 3, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194558098304536576, 1194555724802428928, '客运组', '100.007.005', 'kyz', NULL, NULL, NULL, 4, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194558128969093120, 1194555724802428928, '综合组', '100.007.006', 'zhz', NULL, NULL, NULL, 5, 0, 1, NULL);
INSERT INTO dbo.sys_dept (id, parent_id, name, code, spell, category, nature, leader_id, path, deleted, status, remark)
VALUES (1194558159843364864, 1194555724802428928, '测试组', '100.007.007', 'csz', NULL, NULL, NULL, 6, 0, 1, NULL);


-- ----------------------------
-- sys_dic_category - 系统字典类型
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_dic_category')
            AND type IN ('U'))
    DROP TABLE dbo.sys_dic_category
GO

CREATE TABLE dbo.sys_dic_category
(
    id        BIGINT        NOT NULL,
    parent_id BIGINT        NOT NULL DEFAULT 0,
    name      NVARCHAR(100) NOT NULL,
    code      NVARCHAR(100) NOT NULL,
    spell     NVARCHAR(100) NULL,
    path      INT           NOT NULL DEFAULT 0,
    remark    NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dic_category
    ADD CONSTRAINT pk_sys_dic_category_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dic_category
    ADD CONSTRAINT uq_sys_dic_category_code UNIQUE NONCLUSTERED (code) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dic_category
    ADD CONSTRAINT uq_sys_dic_category_name UNIQUE NONCLUSTERED (name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统字典类型', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型上级主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'parent_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序号', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic_category', 'COLUMN', N'remark'
GO

INSERT INTO dbo.sys_dic_category (id, parent_id, name, code, spell, path, remark)
VALUES (1123639887514439680, 0, '系统角色分类', 'sys.role.category', 'xtjsfl', 0, NULL);
INSERT INTO dbo.sys_dic_category (id, parent_id, name, code, spell, path, remark)
VALUES (1194194845837692928, 0, '系统参数分类', 'sys.param.category', 'xtcsfl', 1, NULL);
INSERT INTO dbo.sys_dic_category (id, parent_id, name, code, spell, path, remark)
VALUES (1194268979401723904, 0, '系统部门分类', 'sys.dept.category', 'xtbmfl', 2, NULL);
INSERT INTO dbo.sys_dic_category (id, parent_id, name, code, spell, path, remark)
VALUES (1194269035307601920, 0, '系统部门性质', 'sys.dept.nature', 'xtbmxz', 3, NULL);
INSERT INTO dbo.sys_dic_category (id, parent_id, name, code, spell, path, remark)
VALUES (1194449800859357184, 0, '系统用户职位', 'sys.user.post', 'xtyhzw', 4, NULL);


-- ----------------------------
-- sys_dic - 系统字典
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_dic')
            AND type IN ('U'))
    DROP TABLE dbo.sys_dic
GO

CREATE TABLE dbo.sys_dic
(
    id            BIGINT        NOT NULL,
    parent_id     BIGINT        NOT NULL DEFAULT 0,
    category_code NVARCHAR(100) NOT NULL,
    name          NVARCHAR(100) NOT NULL,
    spell         NVARCHAR(100) NULL,
    value         NVARCHAR(500) NOT NULL,
    path          INT           NOT NULL DEFAULT 0,
    status        TINYINT       NOT NULL DEFAULT 1,
    remark        NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dic
    ADD CONSTRAINT pk_sys_dic_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_dic
    ADD CONSTRAINT uq_sys_dic_code_name UNIQUE NONCLUSTERED (category_code, name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统字典', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典上级主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'parent_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典类型编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'category_code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'字典值', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'value'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序号', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_dic', 'COLUMN', N'remark'
GO

INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1123649418978004992, 0, 'sys.role.category', '角色', NULL, '角色', 0, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1123649437554577408, 0, 'sys.role.category', '用户组', NULL, '用户组', 0, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1123649473394905088, 0, 'sys.role.category', '岗位', NULL, '岗位', 0, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1123649519322533888, 0, 'sys.role.category', '职位', NULL, '职位', 0, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194211370111995904, 0, 'sys.param.category', '系统', 'xt', '0', 1, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194211370111995905, 0, 'sys.param.category', '默认参数', 'mrcs', '1', 1, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194211476399853568, 0, 'sys.param.category', '账户', 'zh', '2', 2, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194211528098844672, 0, 'sys.param.category', '服务器', 'fwq', '3', 3, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194211585711804416, 0, 'sys.param.category', '客户端', 'khd', '4', 4, 1, NULL);


INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194269913989124096, 0, 'sys.dept.category', '职能机构', 'znjg', '职能机构', 1, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194269950102081536, 0, 'sys.dept.category', '项目组', 'xmz', '项目组', 2, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194269972042485760, 0, 'sys.dept.category', '行业组', 'xyz', '行业组', 3, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270021673684992, 0, 'sys.dept.category', '项目', 'xm', '项目', 4, 1, NULL);


INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270297822466048, 0, 'sys.dept.nature', '集团', 'jt', '集团', 1, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270357872316416, 0, 'sys.dept.nature', '公司', 'gs', '公司', 2, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270379183575040, 0, 'sys.dept.nature', '分公司', 'fgs', '分公司', 3, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270431142612992, 0, 'sys.dept.nature', '科室', 'ks', '科室', 4, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194270454035124224, 0, 'sys.dept.nature', '企业', 'qy', '企业', 5, 1, NULL);


INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194449873420816384, 0, 'sys.user.post', '董事长', 'dsz', '董事长', 1, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194449909496025088, 0, 'sys.user.post', '总经理', 'zjl', '总经理', 2, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194449945005002752, 0, 'sys.user.post', '财务总监', 'cwzj', '财务总监', 3, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194450002039148544, 0, 'sys.user.post', '会计', 'hj', '会计', 4, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194450028987551744, 0, 'sys.user.post', '出纳', 'cn', '出纳', 5, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194450068653084672, 0, 'sys.user.post', '人事经理', 'rsjl', '人事经理', 6, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194450092812275712, 0, 'sys.user.post', '销售经理', 'xsjl', '销售经理', 7, 1, NULL);
INSERT INTO dbo.sys_dic (id, parent_id, category_code, name, spell, value, path, status, remark)
VALUES (1194450129789259776, 0, 'sys.user.post', '采购经理', 'cgjl', '采购经理', 8, 1, NULL);


-- ----------------------------
-- sys_seq - 系统序列
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_seq')
            AND type IN ('U'))
    DROP TABLE dbo.sys_seq
GO

CREATE TABLE dbo.sys_seq
(
    id            BIGINT        NOT NULL,
    name          NVARCHAR(100) NOT NULL,
    code          NVARCHAR(100) NOT NULL,
    start_with    BIGINT        NOT NULL,
    current_value BIGINT        NOT NULL,
    increment_by  INT           NOT NULL,
    remark        NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_seq
    ADD CONSTRAINT pk_sys_seq_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_seq
    ADD CONSTRAINT uq_sys_seq_code UNIQUE NONCLUSTERED (code) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统序列', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'序列主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序列名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序列编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'开始值', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'start_with'
GO
EXEC sp_addextendedproperty N'MS_Description', N'当前值', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'current_value'
GO
EXEC sp_addextendedproperty N'MS_Description', N'步长', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'increment_by'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_seq', 'COLUMN', N'remark'
GO


-- ----------------------------
-- sys_object_map - 系统对象关联关系
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_object_map')
            AND type IN ('U'))
    DROP TABLE dbo.sys_object_map
GO
CREATE TABLE dbo.sys_object_map
(
    object_name NVARCHAR(100) NOT NULL,
    object_id   BIGINT        NOT NULL,
    target_name NVARCHAR(100) NOT NULL,
    target_id   BIGINT        NOT NULL
)
GO
CREATE CLUSTERED INDEX idx_sys_object_map_object ON dbo.sys_object_map (object_name ASC, object_id ASC, target_name ASC) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_object_map_target ON dbo.sys_object_map (target_name ASC, target_id ASC, object_name ASC) INCLUDE (object_id) ON [PRIMARY]
GO
EXEC sp_addextendedproperty 'MS_Description', '系统对象关联关系', 'SCHEMA', 'dbo', 'TABLE', 'sys_object_map'
GO
EXEC sp_addextendedproperty 'MS_Description', '对象名称', 'SCHEMA', 'dbo', 'TABLE', 'sys_object_map', 'COLUMN', 'object_name'
GO
EXEC sp_addextendedproperty 'MS_Description', '对象主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_object_map', 'COLUMN', 'object_id'
GO
EXEC sp_addextendedproperty 'MS_Description', '目标名称', 'SCHEMA', 'dbo', 'TABLE', 'sys_object_map', 'COLUMN', 'target_name'
GO
EXEC sp_addextendedproperty 'MS_Description', '目标主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_object_map', 'COLUMN', 'target_id'
GO



-- ----------------------------
-- sys_file - 系统文件
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_file')
            AND type IN ('U'))
    DROP TABLE dbo.sys_file
GO
CREATE TABLE dbo.sys_file
(
    id           BIGINT        NOT NULL,
    category     NVARCHAR(50)  NULL,
    record_id    BIGINT        NOT NULL,
    file_name    NVARCHAR(500) NOT NULL,
    file_path    NVARCHAR(500) NULL,
    file_size    BIGINT        NOT NULL,
    content_type NVARCHAR(200) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_file
    ADD CONSTRAINT pk_sys_file_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_file_record_id ON dbo.sys_file (record_id) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统文件', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件分组', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文档类型', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'content_type'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'file_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件大小', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'file_size'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件路径', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'file_path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'记录主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_file', 'COLUMN', N'record_id'
GO


-- ----------------------------
-- sys_job - 系统定时任务
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_job')
            AND type IN ('U'))
    DROP TABLE dbo.sys_job
GO
CREATE TABLE dbo.sys_job
(
    id              BIGINT        NOT NULL,
    name            NVARCHAR(100) NOT NULL,
    spell           NVARCHAR(100) NULL,
    job_group       NVARCHAR(100) NOT NULL,
    job_expression  NVARCHAR(500) NOT NULL,
    cron_expression NVARCHAR(500) NOT NULL,
    misfire_policy  TINYINT       NOT NULL,
    concurrent      TINYINT       NOT NULL,
    status          TINYINT       NOT NULL,
    remark          NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_job
    ADD CONSTRAINT pk_sys_job_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统定时任务', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务组', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'job_group'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务表达式', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'job_expression'
GO
EXEC sp_addextendedproperty N'MS_Description', N'触发表达式', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'cron_expression'
GO
EXEC sp_addextendedproperty N'MS_Description', N'计划执行错误策略 [1-立即执行, 2-执行一次, 3-放弃执行]', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN',
     N'misfire_policy'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否允许并发 [1-允许, 0-禁止]', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'concurrent'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_job', 'COLUMN', N'remark'
GO


-- ----------------------------
-- sys_history_log - 系统历史日志
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_history_log')
            AND type IN ('U'))
    DROP TABLE dbo.sys_history_log
GO
CREATE TABLE dbo.sys_history_log
(
    id                BIGINT        NOT NULL,
    category          TINYINT       NOT NULL,
    table_name        NVARCHAR(100) NOT NULL,
    primary_key       NVARCHAR(100) NOT NULL,
    before_data       NVARCHAR(MAX) NULL,
    after_data        NVARCHAR(MAX) NULL,
    diff              NVARCHAR(MAX) NULL,
    operate_user_id   BIGINT        NULL,
    operate_user_name NVARCHAR(100) NULL,
    operate_date_time DATETIME      NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_history_log
    ADD CONSTRAINT pk_hsys_history_log_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_history_log_primary_key ON dbo.sys_history_log (primary_key) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统历史日志', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作后数据', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'after_data'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作前数据', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'before_data'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作类型 [1-新增, 2-修改, 3-删除]', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN',
     N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'差异信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'diff'
GO
EXEC sp_addextendedproperty N'MS_Description', N'主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'operate_date_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'operate_user_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人姓名', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'operate_user_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'记录主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'primary_key'
GO
EXEC sp_addextendedproperty N'MS_Description', N'表名', 'SCHEMA', N'dbo', 'TABLE', N'sys_history_log', 'COLUMN', N'table_name'
GO



-- ----------------------------
-- sys_job_log - 系统任务日志
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_job_log')
            AND type IN ('U'))
    DROP TABLE dbo.sys_job_log
GO
CREATE TABLE dbo.sys_job_log
(
    id                BIGINT         NOT NULL,
    job_id            BIGINT         NOT NULL,
    job_name          NVARCHAR(500)  NOT NULL,
    job_expression    NVARCHAR(2000) NOT NULL,
    msg               NVARCHAR(2000) NULL,
    error_msg         NVARCHAR(MAX)  NULL,
    trigger_category  TINYINT        NOT NULL,
    status            TINYINT        NOT NULL,
    operate_date_time DATETIME       NOT NULL,
    start_date_time   DATETIME       NULL,
    end_date_time     DATETIME       NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_job_log
    ADD CONSTRAINT pk_sys_job_log_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_job_log_job_id ON dbo.sys_job_log (job_id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_job_log_operate_date_time ON dbo.sys_job_log (operate_date_time) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统任务日志', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务执行错误信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'error_msg'
GO
EXEC sp_addextendedproperty N'MS_Description', N'主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务表达式', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'job_expression'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'job_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'job_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'任务执行消息', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'msg'
GO
EXEC sp_addextendedproperty N'MS_Description', N'创建时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'operate_date_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'执行状态 [1-成功, 0-失败]', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'执行类型 [1-手动触发, 0-系统触发]', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN',
     N'trigger_category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'开始时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'start_date_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'结束时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_job_log', 'COLUMN', N'end_date_time'
GO

-- ----------------------------
-- sys_login_log - 系统登录日志
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_login_log')
            AND type IN ('U'))
    DROP TABLE dbo.sys_login_log
GO
CREATE TABLE dbo.sys_login_log
(
    id                BIGINT        NOT NULL,
    category          INT           NOT NULL DEFAULT 1,
    account           NVARCHAR(100) NOT NULL,
    user_agent        NVARCHAR(200) NULL,
    status            TINYINT       NOT NULL DEFAULT 1,
    msg               NVARCHAR(500) NULL,
    app_id            NVARCHAR(100) NULL,
    app_name          NVARCHAR(100) NULL,
    ip                NVARCHAR(100) NULL,
    operate_date_time DATETIME      NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_login_log
    ADD CONSTRAINT PK_sys_login_log PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_login_log_account ON dbo.sys_login_log (account) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_login_log_odt ON dbo.sys_login_log (operate_date_time) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统登录日志', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'登录账号', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'account'
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'app_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'app_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'类型 [1-登录, 2-注销]', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'IP地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'ip'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'msg'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-成功, 0-失败]', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'登录时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'operate_date_time'
GO

EXEC sp_addextendedproperty N'MS_Description', N'浏览器标识', 'SCHEMA', N'dbo', 'TABLE', N'sys_login_log', 'COLUMN', N'user_agent'
GO

-- ----------------------------
-- sys_error_log - 系统错误日志
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_error_log')
            AND type IN ('U'))
    DROP TABLE dbo.sys_error_log
GO
CREATE TABLE dbo.sys_error_log
(
    id                BIGINT         NOT NULL,
    req_url           NVARCHAR(2000) NULL,
    req_method        NVARCHAR(20)   NULL,
    req_param         NVARCHAR(MAX)  NULL,
    msg               NVARCHAR(MAX)  NULL,
    details           NVARCHAR(MAX)  NULL,
    app_id            NVARCHAR(100)  NULL,
    app_name          NVARCHAR(100)  NULL,
    ip                NVARCHAR(100)  NULL,
    operate_user_id   BIGINT         NULL,
    operate_user_name NVARCHAR(100)  NULL,
    operate_date_time DATETIME       NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_error_log
    ADD CONSTRAINT pk_sys_error_log_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_error_log_operate_date_time ON dbo.sys_error_log (operate_date_time) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统错误日志', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'app_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'app_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'错误详细信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'details'
GO
EXEC sp_addextendedproperty N'MS_Description', N'主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'IP地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'ip'
GO
EXEC sp_addextendedproperty N'MS_Description', N'错误消息', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'msg'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'operate_date_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'operate_user_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人姓名', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'operate_user_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'请求方法', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'req_method'
GO
EXEC sp_addextendedproperty N'MS_Description', N'请求参数', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'req_param'
GO
EXEC sp_addextendedproperty N'MS_Description', N'请求地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_error_log', 'COLUMN', N'req_url'
GO


-- ----------------------------
-- sys_operate_log - 系统操作日志
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_operate_log')
            AND type IN ('U'))
    DROP TABLE dbo.sys_operate_log
GO
CREATE TABLE dbo.sys_operate_log
(
    id                BIGINT         NOT NULL,
    tag               NVARCHAR(500)  NULL,
    msg               NVARCHAR(4000) NULL,
    method            NVARCHAR(1000) NULL,
    req_url           NVARCHAR(2000) NULL,
    req_method        NVARCHAR(50)   NULL,
    cost_time         BIGINT         NULL,
    user_agent        NVARCHAR(200)  NULL,
    status            TINYINT        NOT NULL DEFAULT 1,
    execute_param     NVARCHAR(MAX)  NULL,
    execute_result    NVARCHAR(MAX)  NULL,
    app_id            NVARCHAR(100)  NULL,
    app_name          NVARCHAR(100)  NULL,
    ip                NVARCHAR(100)  NULL,
    operate_dept_id   BIGINT         NULL,
    operate_dept_name NVARCHAR(100)  NULL,
    operate_user_id   BIGINT         NULL,
    operate_user_name NVARCHAR(100)  NULL,
    operate_date_time DATETIME       NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_operate_log
    ADD CONSTRAINT pk_sys_operate_log_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
CREATE NONCLUSTERED INDEX idx_sys_operate_log_operate_date_time ON dbo.sys_operate_log (operate_date_time) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统操作日志', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'app_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'应用名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'app_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'耗时(毫秒数)', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'cost_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'执行参数信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'execute_param'
GO
EXEC sp_addextendedproperty N'MS_Description', N'执行结果信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'execute_result'
GO
EXEC sp_addextendedproperty N'MS_Description', N'主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'IP地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'ip'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作方法', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'method'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作信息', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'msg'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作时间', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'operate_date_time'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作机构主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'operate_dept_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作机构名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'operate_dept_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'operate_user_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'操作人姓名', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'operate_user_name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'请求方法', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'req_method'
GO
EXEC sp_addextendedproperty N'MS_Description', N'请求地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'req_url'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-成功, 0-失败]', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'tag'
GO
EXEC sp_addextendedproperty N'MS_Description', N'浏览器标识', 'SCHEMA', N'dbo', 'TABLE', N'sys_operate_log', 'COLUMN', N'user_agent'
GO


-- ----------------------------
-- sys_user_role_map - 系统用户角色关联
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_user_role_map')
            AND type IN ('U'))
    DROP TABLE dbo.sys_user_role_map
GO

CREATE TABLE dbo.sys_user_role_map
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
) ON [PRIMARY]
GO
CREATE CLUSTERED INDEX idx_sys_user_role_map_user_id ON dbo.sys_user_role_map (user_id) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统用户角色关联', 'SCHEMA', N'dbo', 'TABLE', N'sys_user_role_map', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'角色主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user_role_map', 'COLUMN', N'role_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'用户主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_user_role_map', 'COLUMN', N'user_id'
GO


-- ----------------------------
-- sys_module - 系统模块
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_module')
            AND type IN ('U'))
    DROP TABLE dbo.sys_module
GO

CREATE TABLE dbo.sys_module
(
    id          BIGINT         NOT NULL,
    parent_id   BIGINT         NOT NULL,
    name        NVARCHAR(100)  NOT NULL,
    code        NVARCHAR(100)  NOT NULL,
    spell       NVARCHAR(100)  NULL,
    param       NVARCHAR(4000) NULL,
    web_setting NVARCHAR(4000) NULL,
    win_setting NVARCHAR(4000) NULL,
    web_url     NVARCHAR(500) NULL,
	web_cls     NVARCHAR(500) NULL,
	win_url     NVARCHAR(500) NULL,
	win_cls     NVARCHAR(500) NULL,
    menu        TINYINT        NOT NULL,
    web         TINYINT        NOT NULL,
    win         TINYINT        NOT NULL,
    expand      TINYINT        NOT NULL,
    publiced    TINYINT        NOT NULL,
    path        INT            NULL,
    status      TINYINT        NOT NULL,
    remark      NVARCHAR(500)  NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_module
    ADD CONSTRAINT pk_sys_module_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_module
    ADD CONSTRAINT uq_sys_module_code UNIQUE NONCLUSTERED (code) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_module
    ADD CONSTRAINT uq_sys_module_parent_id_name UNIQUE NONCLUSTERED (parent_id, name) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统模块', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否展开 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'expand'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否菜单 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'menu'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否公共 [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'publiced'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否Web [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'web'
GO
EXEC sp_addextendedproperty N'MS_Description', N'是否Win [1-是, 0-否]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'win'
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块参数', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'param'
GO
EXEC sp_addextendedproperty N'MS_Description', N'上级主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'parent_id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'序号', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'remark'
GO
EXEC sp_addextendedproperty N'MS_Description', N'模块简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Web配置选项', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'web_setting'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Win配置选项', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'win_setting'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Web路径', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'web_url'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Web图标', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'web_cls'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Win路径', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'win_url'
GO
EXEC sp_addextendedproperty N'MS_Description', N'Win图标', 'SCHEMA', N'dbo', 'TABLE', N'sys_module', 'COLUMN', N'win_cls'
GO


INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (1, 0, N'系统管理', N'sys', N'xtgl', null, null, null, null, N'fa fa-cogs', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (2, 1, N'系统参数', N'sys.param', N'xtcs', null, null, null, N'/sys/param', N'icon-settings', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (3, 1, N'系统字典', N'sys.dic', N'xtzd', null, null, null, N'/sys/dic', N'icon-notebook', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (4, 1, N'在线用户', N'sys.onlineUser', N'zxyh', null, null, null, N'/sys/onlineUser', N'icon-user-following', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (5, 1, N'锁定用户', N'sys.lockUser', N'slyh', null, null, null, N'/sys/lockUser', N'icon-user-unfollow', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (6, 1, N'操作日志', N'sys.operateLog', N'czrz', null, null, null, N'/sys/operateLog', N'icon-bell', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (7, 1, N'历史日志', N'sys.historyLog', N'srz', null, null, null, N'/sys/historyLog', N'fa fa-history', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (8, 1, N'系统角色', N'sys.role', N'xtjs', null, null, null, N'/sys/role', N'icon-users', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (9, 1, N'系统用户', N'sys.user', N'xtyh', null, null, null, N'/sys/user', N'icon-user', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (10, 1, N'组织机构', N'sys.dept', N'xtjg', null, null, null, N'/sys/dept', N'fa fa-sitemap', null, null, 1, 1, 1, 1, 0, 1, 1, null);
INSERT INTO dbo.sys_module (id, parent_id, name, code, spell, param, web_setting, win_setting, web_url, web_cls, win_url, win_cls, menu, web, win, expand, publiced, path, status, remark) VALUES (11, 1, N'系统序列', N'sys.seq', N'xtxl', null, null, null, N'/sys/seq', N'fa fa-random', null, null, 1, 1, 1, 1, 0, 1, 1, null);



-- ----------------------------
-- sys_report - 系统报表
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'dbo.sys_report')
            AND type IN ('U'))
    DROP TABLE dbo.sys_report
GO
CREATE TABLE dbo.sys_report
(
    id       BIGINT        NOT NULL,
    code     NVARCHAR(100) NOT NULL,
    name     NVARCHAR(100) NOT NULL,
    spell    NVARCHAR(100) NULL,
    url      NVARCHAR(200) NULL,
    path     NVARCHAR(500) NULL,
    md5      NVARCHAR(100) NULL,
    status   TINYINT       NOT NULL,
    category TINYINT       NOT NULL DEFAULT 0,
    remark   NVARCHAR(500) NULL
) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_report
    ADD CONSTRAINT pk_report_id PRIMARY KEY CLUSTERED (id) ON [PRIMARY]
GO
ALTER TABLE dbo.sys_report
    ADD CONSTRAINT uk_report_code UNIQUE NONCLUSTERED (code) ON [PRIMARY]
GO
EXEC sp_addextendedproperty N'MS_Description', N'系统报表', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', NULL, NULL
GO
EXEC sp_addextendedproperty N'MS_Description', N'报表编码', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'code'
GO
EXEC sp_addextendedproperty N'MS_Description', N'报表主键', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'id'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件指纹', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'md5'
GO
EXEC sp_addextendedproperty N'MS_Description', N'报表名称', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'name'
GO
EXEC sp_addextendedproperty N'MS_Description', N'文件路径', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'path'
GO
EXEC sp_addextendedproperty N'MS_Description', N'备注', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'remark'
GO
EXEC sp_addextendedproperty N'MS_Description', N'报表简拼', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'spell'
GO
EXEC sp_addextendedproperty N'MS_Description', N'状态 [1-启用, 0-禁用]', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'status'
GO
EXEC sp_addextendedproperty N'MS_Description', N'报表类型', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'category'
GO
EXEC sp_addextendedproperty N'MS_Description', N'接口地址', 'SCHEMA', N'dbo', 'TABLE', N'sys_report', 'COLUMN', N'url'
GO