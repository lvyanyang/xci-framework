<div id="gridtoolbar">
    <form id="gridform" class="jxform jxlayout-form form-inline">
        <div class="form-group">
            <input name="name" class="form-control w-200px" placeholder="请输入字典名称/简拼" autocomplete="off">
        </div>
        <div class="form-group">
            <select name="status" class="jxselect form-control"
                    data-select-submit="true" data-allow-clear="false"
                    data-width="110">
                 ${web.statusSelectOptions()}
            </select>
        </div>
        <button class="btn btn-primary ml-5" type="submit">
            <i class="fa fa-search"></i> 查询
        </button>
        <div class="btn-group tool-mt">
            <a id="btn-create" class="btn btn-default">
                <i class="icon-plus"></i> 新增
            </a>
            <a id="btn-edit" class="btn btn-default">
                <i class="icon-pencil"></i> 编辑
            </a>
            <a id="btn-delete" class="btn btn-default">
                <i class="icon-trash"></i> 删除
            </a>
            <a id="btn-export" class="btn btn-default">
                <i class="icon-share-alt"></i> 导出
            </a>
        </div>
    </form>
</div>