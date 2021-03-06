package com.xci.core.lang;

import com.xci.core.R;
import com.xci.core.SMap;
import com.xci.core.XCI;
import com.xci.model.StringConverterType;
import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XCITest {
    @Test
    public void testObjectStringConverter() {
        SMap map = SMap.newMap()
                .append("a", "  12　　")
                .append("b", "  1  2　　")
                .append("c", "　　1ｘ2ｘ3　　")
                .append("d", "　　create_user _id　　");
        System.out.println("-----------------");
        System.out.println(map);
        XCI.objectStringConverter(map, new StringConverterType[]{StringConverterType.trimAll, StringConverterType.toDBC, StringConverterType.toCamelCase}, null, R.Empty, R.Empty);
        System.out.println("-----------------");
        System.out.println(map);

        var bean = new TestBean();
        bean.setStr1("  12　　");
        bean.setStr2("  1  2　　");
        bean.setStr3("　　1ｘ2ｘ3　　");
        bean.setStr4("　　create_user _id　　");
        System.out.println("-----------------");
        System.out.println(bean);
        XCI.objectStringConverter(bean, new StringConverterType[]{StringConverterType.trimAll, StringConverterType.toDBC, StringConverterType.toCamelCase}, null, R.Empty, R.Empty);
        System.out.println("-----------------");
        System.out.println(bean);
    }

    @Test
    public void testStringConvert() {
        var item = R.Empty;
        var actual = R.Empty;

        //测试前空格
        item = "   12";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeft}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //测试前中文空格
        item = "  12";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeft}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //测试前全角空格
        item = "　　12";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeft}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //测试后全角空格
        item = "12　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimRight}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //测试前后角空格
        item = "  12　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeftRight}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //测试所有空格
        item = "  1  2　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimAll}, null, R.Empty, R.Empty);
        assertEquals("12", actual);
        System.out.println(actual);

        //全角转半角
        item = "　　1ｘ2ｘ3　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeft, StringConverterType.trimRight, StringConverterType.toDBC}, null, R.Empty, R.Empty);
        assertEquals("1x2x3", actual);
        System.out.println(actual);

        //半角转全角
        item = "　　1x2x3　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimLeft, StringConverterType.trimRight, StringConverterType.toSBC}, null, R.Empty, R.Empty);
        assertEquals("１ｘ２ｘ３", actual);
        System.out.println(actual);

        //将驼峰式命名的字符串转换为下划线方式
        item = "　　create User Id　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimAll, StringConverterType.toUnderlineCase}, null, R.Empty, R.Empty);
        assertEquals("create_user_id", actual);
        System.out.println(actual);

        //将下划线方式命名的字符串转换为驼峰式
        item = "　　create_user _id　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimAll, StringConverterType.toCamelCase}, null, R.Empty, R.Empty);
        assertEquals("createUserId", actual);
        System.out.println(actual);

        //替换
        item = "　　create_user _id　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimAll,
                StringConverterType.toCamelCase, StringConverterType.replace}, null, "e,s", "5,9");
        assertEquals("cr5at5U95rId", actual);
        System.out.println(actual);

        //替换
        item = "　　create_user _id　　";
        actual = XCI.stringConvert(item, new StringConverterType[]{StringConverterType.trimAll,
                StringConverterType.toCamelCase, StringConverterType.replace}, null, "e,s", null);
        assertEquals("cratUrId", actual);
        System.out.println(actual);

    }

    @Data
    public static class TestBean {
        private String str1;
        private String str2;
        private String str3;
        private String str4;
    }
}