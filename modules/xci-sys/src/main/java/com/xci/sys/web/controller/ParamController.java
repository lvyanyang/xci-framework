package com.xci.sys.web.controller;

import com.xci.annotation.Authorize;
import com.xci.exceptions.NotFoundException;
import com.xci.core.R;
import com.xci.core.RestResult;
import com.xci.core.XCI;
import com.xci.sys.entity.SysParam;
import com.xci.sys.filter.ParamFilter;
import com.xci.sys.service.SysService;
import com.xci.sys.web.model.JsonGrid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统参数控制器
 */
@Authorize
@Controller
@RequestMapping("/sys/param")
public class ParamController extends SysWebController {
    //region 页面视图

    /** 首页 */
    @GetMapping
    public String index() {
        return "sys/param/index";
    }

    /** 新建页 */
    @GetMapping("/create")
    @Authorize(code = R.Permission.SysParamInsert)
    public String create(ModelMap map) {
        createMark(map);
        var entity = new SysParam();
        entity.setId(XCI.nextId());
        map.put("entity", entity);
        return "sys/param/edit";
    }

    /** 编辑页 */
    @GetMapping("/edit")
    @Authorize(code = R.Permission.SysParamUpdate)
    public String edit(String id, ModelMap map) {
        var entity = SysService.me().paramService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/param/edit";
    }

    /** 详情页 */
    @GetMapping("/details")
    public String details(String id, ModelMap map) {
        var entity = SysService.me().paramService().selectById(Long.valueOf(id));
        if (entity == null) throw new NotFoundException(id);
        map.put("entity", entity);
        return "sys/param/details";
    }

    /** 设置页 */
    @GetMapping("/setting")
    public String setting(ModelMap map) {
        List<SysParam> list = SysService.me().paramService().selectList(new ParamFilter());
        for (SysParam entity : list) {
            map.put(entity.getCode(), entity.getValue());
        }
        return "sys/param/setting";
    }

    //endregion

    //region 数据处理

    /** 表格查询 */
    @ResponseBody
    @PostMapping("/grid")
    public JsonGrid grid(ParamFilter filter) {
        return new JsonGrid(SysService.me().paramService().selectPageList(filter));
    }

    /** 新增保存 */
    @ResponseBody
    @PostMapping("/createSave")
    @Authorize(code = R.Permission.SysParamInsert)
    public RestResult createSave(@ModelAttribute SysParam entity) {
        return SysService.me().paramService().insert(entity);
    }

    /** 修改保存 */
    @ResponseBody
    @PostMapping("/updateSave")
    @Authorize(code = R.Permission.SysParamUpdate)
    public RestResult updateSave(@ModelAttribute SysParam entity) {
        return SysService.me().paramService().update(entity);
    }


    /** 保存设置 */
    @ResponseBody
    @PostMapping("/setting-save")
    public RestResult settingSave(@RequestParam Map<String, String> params) {
        List<SysParam> list = SysService.me().paramService().selectList(new ParamFilter());
        for (var entity : list) {
            if (!params.containsKey(entity.getCode())) {
                continue;
            }
            String val = params.get(entity.getCode());
            if (val.equals(entity.getValue())) {
                continue;
            }
            entity.setValue(val);
            SysService.me().paramService().update(entity);
        }
        return RestResult.ok();
    }

    /** 删除 */
    @ResponseBody
    @PostMapping("/delete")
    @Authorize(code = R.Permission.SysParamDelete)
    public RestResult delete(String ids) {
        SysService.me().paramService().delete(ids);
        return RestResult.ok();
    }

    /** 导出 */
    @GetMapping("/export")
    public void export(ParamFilter filter) {
        XCI.exportExcel(SysService.me().paramService().selectList(filter), SysParam.class, "系统参数列表");
    }

    //endregion
}