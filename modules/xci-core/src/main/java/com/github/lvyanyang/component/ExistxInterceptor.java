/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.github.lvyanyang.component;

import com.github.lvyanyang.core.XCI;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * Mybatis插件,拦截以existx开头的方法,根据不同的数据方言生成不同的exist语句,目前只支持单表语句
 * <p>使用方法如下:
 * <code>
 * <pre>
 * <select id="existxByCode" resultType="java.lang.Boolean">
 *     select count(1) from sys_dept
 *     where code = #{code}
 *     <if test="excludeId != null">and id != #{excludeId}</if>
 * </select>
 * </pre>
 * </code>
 * <p>
 * 支持的数据库:
 * 1.oracle
 * 2.mysql
 * 3.sqlserver
 * 其他数据库则返回原始sql,即select count(1)...语句
 * <p>
 * 必须的条件:
 * 1.必须以existx开头
 * 2.只支持单表,对于其他复杂sql暂时没有解析
 * <p>
 * 拼接sql的原理:
 * 1.先获取原始sql,提取表名以及where后面的语句(不带where)
 * 2.根据不同的数据库方言,生成不同的语句,如下示例:
 * <code>
 * <pre>
 *     --oracle
 *     select case when exists(select 1 from sys_dept where code = #{code} and id != #{excludeId}) then 1 else 0 end from dual
 *     --mysql/sqlserver
 *     select case when exists(select 1 from where code = #{code} and id != #{excludeId}) then 1 else 0 end
 * </pre>
 * </code>
 * <p>
 * 如果要关闭此功能,有两种方法:
 * 1.方法不要以existx开头
 * 2.配置文件中增加配置:xci.mybatis.existx.enabled=false
 * @author 吕艳阳 18049047588@qq.com
 * @since 2020-05-02 01:57
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExistxInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        SqlCommandType commandType = ms.getSqlCommandType();
        //只处理select语句
        if (!commandType.equals(SqlCommandType.SELECT)) {
            return invocation.proceed();
        }
        var sqlId = ms.getId();
        // String className = sqlId.substring(0, sqlId.lastIndexOf("."));
        String methodName = sqlId.substring(sqlId.lastIndexOf(".") + 1, sqlId.length());
        //只处理以 existx 开头的方法
        if (!methodName.startsWith("existx")) {
            return invocation.proceed();
        }
        Object parameter = args[1];
        BoundSql boundSql = ms.getBoundSql(parameter);
        var sql = boundSql.getSql();
        String newSql = buildSQL(sql);
        MappedStatement newStatement = newMappedStatement(ms, new BoundSqlSqlSource(boundSql));
        MetaObject msObject = SystemMetaObject.forObject(newStatement);
        msObject.setValue("sqlSource.boundSql.sql", newSql);
        args[0] = newStatement;
        return invocation.proceed();
    }

    private String buildSQL(String originalSql) {
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(originalSql);
        } catch (JSQLParserException e) {
            log.error("解析existx原始SQL时出错:", e);
        }
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        var tableName = tablesNamesFinder.getTableList(select);
        var dbId = XCI.getDefaultDatabaseId();
        return XCI.getExistSqlStatement(dbId, tableName.get(0), plain.getWhere().toString());
    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
//
// /**
//      * 查询字段
//      * @param sql
//      * @return
//      * @throws JSQLParserException
//      */
//     private static List<String> test_select_items(String sql)
//             throws JSQLParserException {
//         CCJSqlParserManager parserManager = new CCJSqlParserManager();
//         Select select = (Select) parserManager.parse(new StringReader(sql));
//         PlainSelect plain = (PlainSelect) select.getSelectBody();
//         List<SelectItem> selectitems = plain.getSelectItems();
//         List<String> str_items = new ArrayList<String>();
//         if (selectitems != null) {
//             for (SelectItem selectitem : selectitems) {
//                 str_items.add(selectitem.toString());
//             }
//         }
//         return str_items;
//     }