$(function () {
    var delay = jx.delay();
    var $iconsSection = $('#icons');
    var $searchInput = $("#search-input");
    var $searchResultsSection = $("#search-results");
    var $searchInputClear = $("#search-clear");
    var template = $("#search-template").html();
    var notemplate = $("#search-no-template").html();

    var search = function () {
        delay.run(function () {
            var query = $searchInput.val();
            if (query !== "") {
                showResults(query);
            } else {
                clearResults();
            }
        }, 200);
    };
    var showResults = function (query) {
        $searchResultsSection.html("");
        $searchResultsSection.removeClass("hide");
        $searchInputClear.removeClass("hide");
        $iconsSection.addClass("hide");
        var results = [];
        var html = '';

        $iconsSection.find('a:Contains(' + query + ')').each(function (i, v) {
            results.push(v.parentNode.outerHTML);
        });

        if (results.length > 0) {
            html = jx.formatString(template, query, results.join(""));
        }
        else {
            html = jx.formatString(notemplate, query);
        }
        $searchResultsSection.html(html);
    };
    var clearResults = function () {
        $searchInput.val("").focus();
        $searchResultsSection.addClass("hide");
        $searchResultsSection.html("");
        $searchInputClear.addClass("hide");
        $iconsSection.removeClass("hide");
    }
    var sectionIcons = function (e) {
        if (e.target && e.target.nodeName === 'A') {
            var icon = $(e.target).find('i').attr('class');
            var inputId = jx.getUrlParam("inputId");
            if (inputId) {
                pwin.$('#' + inputId).val(icon);
            }
            jx.closeDialog();
        }
    };

    var init = function () {
        $(document.body).dblclick(sectionIcons);
        $searchInput.keyup(search);
        $searchInputClear.click(clearResults);
    }();
})
//解析脚本
/*
document.querySelectorAll('.classes .item span').forEach(function (k, v) {
    console.log('<div class="fa-hover col-md-3 col-sm-4"><a ><i class="' + k.className + '"></i> ' + k.className + '</a></div>')
});
$('#ll-medical .b-icon a').each(function (i, v) {
    console.log('<div class="fa-hover col-md-3 col-sm-4"><a><i class="' + $(v).find('.la').attr('class') + '"></i> ' + v.innerText.replace('→ ', '') + '</a></div>')
});*/