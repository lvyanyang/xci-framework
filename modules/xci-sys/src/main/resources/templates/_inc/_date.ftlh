<#macro griddatepopover title="" id="griddate-popover" formid="griddateform"
    startLabel="开始日期" startId="operateStartDateTime" endLabel="结束日期" endtId="operateEndDateTime">
<div id="${id}" class="popover popover-x popover-default popover-lg">
    <div class="arrow"></div>
    <form id="${formid}" class="jxform form-horizontal">
        <div class="popover-title">
            <span class="close" data-dismiss="popover-x">&times;</span>${title}
        </div>
        <div class="popover-content">
            <div class="form-group">
                <label class="col-md-3 control-label">${startLabel}</label>
                <div class="col-md-8">
                    <div class="input-group date jxdate" data-options="endDate: '0d'">
                        <input id="${startId}" name="${startId}" class="form-control"
                               data-validate="{required: [true,'请选择${startLabel}']}" autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">${endLabel}</label>
                <div class="col-md-8">
                    <div class="input-group date jxdate" data-options="endDate: '0d'">
                        <input id="${endtId}" name="${endtId}" class="form-control"
                               data-validate="{required: [true,'请选择${endLabel}'],compareEqualDate:'#${startId}'}" autocomplete="off">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="popover-footer">
            <button id="btn-datesearch" type="button" class="btn btn-success">确定</button>
        </div>
    </form>
</div>
</#macro>