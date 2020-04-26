<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <title>图标库</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${web.cdn('/jx.css')}" rel="stylesheet"/>
    <link href="${web.url('/sys/css/icon.css')}" rel="stylesheet"/>
</head>
<body>
<div class="page-loading"></div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <section id="search">
                <label for="search-input"><i class="fa fa-search" aria-hidden="true"></i><span class="sr-only">搜索图标</span></label>
                <input id="search-input" class="form-control input-lg" placeholder="搜索图标" autocomplete="off" spellcheck="false"
                       autocorrect="off" autofocus>
                <a id="search-clear" href="#" class="fa fa-times-circle hide"><span class="sr-only">清除搜索结果</span></a>
            </section>
        </div>
    </div>
    <div id="search-results" class="hide">
    </div>
    <div id="icons">
        <section id="new">
            <h2 class="page-header">Font Awesome 4.7 新增图标(双击选择)</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-book"></i> address-book</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-book-o"></i> address-book-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-card"></i> address-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-card-o"></i> address-card-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bandcamp"></i> bandcamp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bath"></i> bath</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bathtub"></i> bathtub <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drivers-license"></i> drivers-license <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drivers-license-o"></i> drivers-license-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eercast"></i> eercast</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-open"></i> envelope-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-open-o"></i> envelope-open-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-etsy"></i> etsy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-free-code-camp"></i> free-code-camp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-grav"></i> grav</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-handshake-o"></i> handshake-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-badge"></i> id-badge</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-card"></i> id-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-card-o"></i> id-card-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-imdb"></i> imdb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linode"></i> linode</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meetup"></i> meetup</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microchip"></i> microchip</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-podcast"></i> podcast</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quora"></i> quora</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ravelry"></i> ravelry</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-s15"></i> s15 <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shower"></i> shower</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snowflake-o"></i> snowflake-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-superpowers"></i> superpowers</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-telegram"></i> telegram</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer"></i> thermometer <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-0"></i> thermometer-0 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-1"></i> thermometer-1 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-2"></i> thermometer-2 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-3"></i> thermometer-3 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-4"></i> thermometer-4 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-empty"></i> thermometer-empty</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-full"></i> thermometer-full</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-half"></i> thermometer-half</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-quarter"></i> thermometer-quarter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-three-quarters"></i> thermometer-three-quarters</a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-rectangle"></i> times-rectangle <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-rectangle-o"></i> times-rectangle-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-circle"></i> user-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-circle-o"></i> user-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-o"></i> user-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vcard"></i> vcard <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vcard-o"></i> vcard-o <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-close"></i> window-close</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-close-o"></i> window-close-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-maximize"></i> window-maximize</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-minimize"></i> window-minimize</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-restore"></i> window-restore</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpexplorer"></i> wpexplorer</a></div>
            </div>
        </section>
        <section id="web-application">
            <h2 class="page-header">Web Application Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-book"></i> address-book</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-book-o"></i> address-book-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-card"></i> address-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-address-card-o"></i> address-card-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-adjust"></i> adjust</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i
                        class="fa fa-american-sign-language-interpreting"></i>american-sign-language...</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-anchor"></i> anchor</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-archive"></i> archive</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-area-chart"></i> area-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows"></i> arrows</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-h"></i> arrows-h</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-v"></i> arrows-v</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asl-interpreting"></i> asl-interpreting <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-assistive-listening-systems"></i> assistive-listening-systems</a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asterisk"></i> asterisk</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-at"></i> at</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-audio-description"></i> audio-description</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-automobile"></i> automobile <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-balance-scale"></i> balance-scale</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ban"></i> ban</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bank"></i> bank <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart"></i> bar-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart-o"></i> bar-chart-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-barcode"></i> barcode</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bars"></i> bars</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bath"></i> bath</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bathtub"></i> bathtub <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery"></i> battery <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-0"></i> battery-0 <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-1"></i> battery-1 <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-2"></i> battery-2 <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-3"></i> battery-3 <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-4"></i> battery-4 <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-empty"></i> battery-empty</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-full"></i> battery-full</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-half"></i> battery-half</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-quarter"></i> battery-quarter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-three-quarters"></i> battery-three-quarters</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bed"></i> bed</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-beer"></i> beer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell"></i> bell</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-o"></i> bell-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-slash"></i> bell-slash</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-slash-o"></i> bell-slash-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bicycle"></i> bicycle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-binoculars"></i> binoculars</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-birthday-cake"></i> birthday-cake</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-blind"></i> blind</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth"></i> bluetooth</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth-b"></i> bluetooth-b</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bolt"></i> bolt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bomb"></i> bomb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-book"></i> book</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bookmark"></i> bookmark</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bookmark-o"></i> bookmark-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-braille"></i> braille</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-briefcase"></i> briefcase</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bug"></i> bug</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-building"></i> building</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-building-o"></i> building-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bullhorn"></i> bullhorn</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bullseye"></i> bullseye</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bus"></i> bus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cab"></i> cab <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calculator"></i> calculator</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar"></i> calendar</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-check-o"></i> calendar-check-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-minus-o"></i> calendar-minus-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-o"></i> calendar-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-plus-o"></i> calendar-plus-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-times-o"></i> calendar-times-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-camera"></i> camera</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-camera-retro"></i> camera-retro</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-car"></i> car</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-down"></i> caret-square-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-left"></i> caret-square-o-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-right"></i> caret-square-o-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-up"></i> caret-square-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cart-arrow-down"></i> cart-arrow-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cart-plus"></i> cart-plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc"></i> cc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-certificate"></i> certificate</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check"></i> check</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-circle"></i> check-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-circle-o"></i> check-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square"></i> check-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square-o"></i> check-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-child"></i> child</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle"></i> circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o"></i> circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o-notch"></i> circle-o-notch</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-thin"></i> circle-thin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clock-o"></i> clock-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clone"></i> clone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-close"></i> close <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud"></i> cloud</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud-download"></i> cloud-download</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud-upload"></i> cloud-upload</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-code"></i> code</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-code-fork"></i> code-fork</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-coffee"></i> coffee</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cog"></i> cog</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cogs"></i> cogs</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comment"></i> comment</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comment-o"></i> comment-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-commenting"></i> commenting</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-commenting-o"></i> commenting-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comments"></i> comments</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comments-o"></i> comments-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-compass"></i> compass</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-copyright"></i> copyright</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-creative-commons"></i> creative-commons</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card"></i> credit-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card-alt"></i> credit-card-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-crop"></i> crop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-crosshairs"></i> crosshairs</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cube"></i> cube</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cubes"></i> cubes</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cutlery"></i> cutlery</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dashboard"></i> dashboard <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-database"></i> database</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deaf"></i> deaf</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deafness"></i> deafness <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-desktop"></i> desktop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-diamond"></i> diamond</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dot-circle-o"></i> dot-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-download"></i> download</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drivers-license"></i> drivers-license <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drivers-license-o"></i> drivers-license-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-edit"></i> edit <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ellipsis-h"></i> ellipsis-h</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ellipsis-v"></i> ellipsis-v</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope"></i> envelope</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-o"></i> envelope-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-open"></i> envelope-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-open-o"></i> envelope-open-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-square"></i> envelope-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eraser"></i> eraser</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exchange"></i> exchange</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation"></i> exclamation</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation-circle"></i> exclamation-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation-triangle"></i> exclamation-triangle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-external-link"></i> external-link</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-external-link-square"></i> external-link-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eye"></i> eye</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eye-slash"></i> eye-slash</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eyedropper"></i> eyedropper</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fax"></i> fax</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-feed"></i> feed <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-female"></i> female</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fighter-jet"></i> fighter-jet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-archive-o"></i> file-archive-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-audio-o"></i> file-audio-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-code-o"></i> file-code-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-excel-o"></i> file-excel-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-image-o"></i> file-image-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-movie-o"></i> file-movie-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-pdf-o"></i> file-pdf-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-photo-o"></i> file-photo-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-picture-o"></i> file-picture-o <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-powerpoint-o"></i> file-powerpoint-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-sound-o"></i> file-sound-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-video-o"></i> file-video-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-word-o"></i> file-word-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-zip-o"></i> file-zip-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-film"></i> film</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-filter"></i> filter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fire"></i> fire</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fire-extinguisher"></i> fire-extinguisher</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag"></i> flag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag-checkered"></i> flag-checkered</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag-o"></i> flag-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flash"></i> flash <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flask"></i> flask</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder"></i> folder</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-o"></i> folder-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-open"></i> folder-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-open-o"></i> folder-open-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-frown-o"></i> frown-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-futbol-o"></i> futbol-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gamepad"></i> gamepad</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gavel"></i> gavel</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gear"></i> gear <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gears"></i> gears <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gift"></i> gift</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glass"></i> glass</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-globe"></i> globe</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-graduation-cap"></i> graduation-cap</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-group"></i> group <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-grab-o"></i> hand-grab-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-lizard-o"></i> hand-lizard-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-paper-o"></i> hand-paper-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-peace-o"></i> hand-peace-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-pointer-o"></i> hand-pointer-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-rock-o"></i> hand-rock-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-scissors-o"></i> hand-scissors-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-spock-o"></i> hand-spock-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-stop-o"></i> hand-stop-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-handshake-o"></i> handshake-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hard-of-hearing"></i> hard-of-hearing <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hashtag"></i> hashtag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hdd-o"></i> hdd-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-headphones"></i> headphones</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart"></i> heart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart-o"></i> heart-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heartbeat"></i> heartbeat</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-history"></i> history</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-home"></i> home</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hotel"></i> hotel <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass"></i> hourglass</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-1"></i> hourglass-1 <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-2"></i> hourglass-2 <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-3"></i> hourglass-3 <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-end"></i> hourglass-end</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-half"></i> hourglass-half</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-o"></i> hourglass-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-start"></i> hourglass-start</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-i-cursor"></i> i-cursor</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-badge"></i> id-badge</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-card"></i> id-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-id-card-o"></i> id-card-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-image"></i> image <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-inbox"></i> inbox</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-industry"></i> industry</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-info"></i> info</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-info-circle"></i> info-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-institution"></i> institution <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-key"></i> key</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-keyboard-o"></i> keyboard-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-language"></i> language</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-laptop"></i> laptop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-leaf"></i> leaf</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-legal"></i> legal <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lemon-o"></i> lemon-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-level-down"></i> level-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-level-up"></i> level-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-bouy"></i> life-bouy <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-buoy"></i> life-buoy <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-ring"></i> life-ring</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-saver"></i> life-saver <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lightbulb-o"></i> lightbulb-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-line-chart"></i> line-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-location-arrow"></i> location-arrow</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lock"></i> lock</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-low-vision"></i> low-vision</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-magic"></i> magic</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-magnet"></i> magnet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-forward"></i> mail-forward <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-reply"></i> mail-reply <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-reply-all"></i> mail-reply-all <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-male"></i> male</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map"></i> map</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-marker"></i> map-marker</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-o"></i> map-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-pin"></i> map-pin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-signs"></i> map-signs</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meh-o"></i> meh-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microchip"></i> microchip</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microphone"></i> microphone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microphone-slash"></i> microphone-slash</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus"></i> minus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-circle"></i> minus-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square"></i> minus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square-o"></i> minus-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mobile"></i> mobile</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mobile-phone"></i> mobile-phone <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-money"></i> money</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-moon-o"></i> moon-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mortar-board"></i> mortar-board <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-motorcycle"></i> motorcycle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mouse-pointer"></i> mouse-pointer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-music"></i> music</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-navicon"></i> navicon <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-newspaper-o"></i> newspaper-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-object-group"></i> object-group</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-object-ungroup"></i> object-ungroup</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paint-brush"></i> paint-brush</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paper-plane"></i> paper-plane</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paper-plane-o"></i> paper-plane-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paw"></i> paw</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil"></i> pencil</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil-square"></i> pencil-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil-square-o"></i> pencil-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-percent"></i> percent</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-phone"></i> phone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-phone-square"></i> phone-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-photo"></i> photo <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-picture-o"></i> picture-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pie-chart"></i> pie-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plane"></i> plane</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plug"></i> plug</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus"></i> plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-circle"></i> plus-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square"></i> plus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square-o"></i> plus-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-podcast"></i> podcast</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-power-off"></i> power-off</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-print"></i> print</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-puzzle-piece"></i> puzzle-piece</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-qrcode"></i> qrcode</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question"></i> question</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle"></i> question-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle-o"></i> question-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quote-left"></i> quote-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quote-right"></i> quote-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-random"></i> random</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-recycle"></i> recycle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-refresh"></i> refresh</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-registered"></i> registered</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-remove"></i> remove <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reorder"></i> reorder <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reply"></i> reply</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reply-all"></i> reply-all</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-retweet"></i> retweet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-road"></i> road</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rocket"></i> rocket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rss"></i> rss</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rss-square"></i> rss-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-s15"></i> s15 <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search"></i> search</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search-minus"></i> search-minus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search-plus"></i> search-plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-send"></i> send <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-send-o"></i> send-o <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-server"></i> server</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share"></i> share</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt"></i> share-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt-square"></i> share-alt-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-square"></i> share-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-square-o"></i> share-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shield"></i> shield</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ship"></i> ship</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-bag"></i> shopping-bag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-basket"></i> shopping-basket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-cart"></i> shopping-cart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shower"></i> shower</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-in"></i> sign-in</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-language"></i> sign-language</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-out"></i> sign-out</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signal"></i> signal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signing"></i> signing <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sitemap"></i> sitemap</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sliders"></i> sliders</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-smile-o"></i> smile-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snowflake-o"></i> snowflake-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-soccer-ball-o"></i> soccer-ball-o <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort"></i> sort</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-alpha-asc"></i> sort-alpha-asc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-alpha-desc"></i> sort-alpha-desc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-amount-asc"></i> sort-amount-asc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-amount-desc"></i> sort-amount-desc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-asc"></i> sort-asc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-desc"></i> sort-desc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-down"></i> sort-down <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-numeric-asc"></i> sort-numeric-asc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-numeric-desc"></i> sort-numeric-desc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-up"></i> sort-up <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-space-shuttle"></i> space-shuttle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spinner"></i> spinner</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spoon"></i> spoon</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square"></i> square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square-o"></i> square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star"></i> star</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half"></i> star-half</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-empty"></i> star-half-empty <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-full"></i> star-half-full <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-o"></i> star-half-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-o"></i> star-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sticky-note"></i> sticky-note</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sticky-note-o"></i> sticky-note-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-street-view"></i> street-view</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-suitcase"></i> suitcase</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sun-o"></i> sun-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-support"></i> support <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tablet"></i> tablet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tachometer"></i> tachometer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tag"></i> tag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tags"></i> tags</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tasks"></i> tasks</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-taxi"></i> taxi</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-television"></i> television</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-terminal"></i> terminal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer"></i> thermometer <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-0"></i> thermometer-0 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-1"></i> thermometer-1 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-2"></i> thermometer-2 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-3"></i> thermometer-3 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-4"></i> thermometer-4 <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-empty"></i> thermometer-empty</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-full"></i> thermometer-full</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-half"></i> thermometer-half</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-quarter"></i> thermometer-quarter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thermometer-three-quarters"></i> thermometer-three-quarters</a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumb-tack"></i> thumb-tack</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-down"></i> thumbs-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-down"></i> thumbs-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-up"></i> thumbs-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-up"></i> thumbs-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ticket"></i> ticket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times"></i> times</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-circle"></i> times-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-circle-o"></i> times-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-rectangle"></i> times-rectangle <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-rectangle-o"></i> times-rectangle-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tint"></i> tint</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-down"></i> toggle-down <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-left"></i> toggle-left <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-off"></i> toggle-off</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-on"></i> toggle-on</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-right"></i> toggle-right <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-up"></i> toggle-up <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trademark"></i> trademark</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trash"></i> trash</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trash-o"></i> trash-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tree"></i> tree</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trophy"></i> trophy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-truck"></i> truck</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tty"></i> tty</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tv"></i> tv <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-umbrella"></i> umbrella</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-universal-access"></i> universal-access</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-university"></i> university</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlock"></i> unlock</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlock-alt"></i> unlock-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unsorted"></i> unsorted <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-upload"></i> upload</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user"></i> user</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-circle"></i> user-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-circle-o"></i> user-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-o"></i> user-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-plus"></i> user-plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-secret"></i> user-secret</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-times"></i> user-times</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-users"></i> users</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vcard"></i> vcard <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vcard-o"></i> vcard-o <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-video-camera"></i> video-camera</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-control-phone"></i> volume-control-phone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-down"></i> volume-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-off"></i> volume-off</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-up"></i> volume-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-warning"></i> warning <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair"></i> wheelchair</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt"></i> wheelchair-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wifi"></i> wifi</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-close"></i> window-close</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-close-o"></i> window-close-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-maximize"></i> window-maximize</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-minimize"></i> window-minimize</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-window-restore"></i> window-restore</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wrench"></i> wrench</a></div>
            </div>
        </section>
        <section id="accessibility">
            <h2 class="page-header">Accessibility Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-american-sign-language-interpreting"></i>
                    american-sign-language...</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asl-interpreting"></i> asl-interpreting <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-assistive-listening-systems"></i> assistive-listening-systems</a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-audio-description"></i> audio-description</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-blind"></i> blind</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-braille"></i> braille</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc"></i> cc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deaf"></i> deaf</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deafness"></i> deafness <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hard-of-hearing"></i> hard-of-hearing <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-low-vision"></i> low-vision</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle-o"></i> question-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-language"></i> sign-language</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signing"></i> signing <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tty"></i> tty</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-universal-access"></i> universal-access</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-control-phone"></i> volume-control-phone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair"></i> wheelchair</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt"></i> wheelchair-alt</a></div>
            </div>
        </section>
        <section id="hand">
            <h2 class="page-header">Hand Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-grab-o"></i> hand-grab-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-lizard-o"></i> hand-lizard-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-down"></i> hand-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-left"></i> hand-o-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-right"></i> hand-o-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-up"></i> hand-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-paper-o"></i> hand-paper-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-peace-o"></i> hand-peace-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-pointer-o"></i> hand-pointer-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-rock-o"></i> hand-rock-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-scissors-o"></i> hand-scissors-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-spock-o"></i> hand-spock-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-stop-o"></i> hand-stop-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-down"></i> thumbs-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-down"></i> thumbs-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-up"></i> thumbs-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-up"></i> thumbs-up</a></div>
            </div>
        </section>
        <section id="transportation">
            <h2 class="page-header">Transportation Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ambulance"></i> ambulance</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-automobile"></i> automobile <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bicycle"></i> bicycle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bus"></i> bus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cab"></i> cab <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-car"></i> car</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fighter-jet"></i> fighter-jet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-motorcycle"></i> motorcycle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plane"></i> plane</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rocket"></i> rocket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ship"></i> ship</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-space-shuttle"></i> space-shuttle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-subway"></i> subway</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-taxi"></i> taxi</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-train"></i> train</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-truck"></i> truck</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair"></i> wheelchair</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt"></i> wheelchair-alt</a></div>
            </div>
        </section>
        <section id="gender">
            <h2 class="page-header">Gender Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-genderless"></i> genderless</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-intersex"></i> intersex <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars"></i> mars</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-double"></i> mars-double</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke"></i> mars-stroke</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke-h"></i> mars-stroke-h</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke-v"></i> mars-stroke-v</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mercury"></i> mercury</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-neuter"></i> neuter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-transgender"></i> transgender</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-transgender-alt"></i> transgender-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus"></i> venus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus-double"></i> venus-double</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus-mars"></i> venus-mars</a></div>
            </div>
        </section>
        <section id="file-type">
            <h2 class="page-header">File Type Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file"></i> file</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-archive-o"></i> file-archive-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-audio-o"></i> file-audio-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-code-o"></i> file-code-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-excel-o"></i> file-excel-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-image-o"></i> file-image-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-movie-o"></i> file-movie-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-o"></i> file-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-pdf-o"></i> file-pdf-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-photo-o"></i> file-photo-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-picture-o"></i> file-picture-o <span
                        class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-powerpoint-o"></i> file-powerpoint-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-sound-o"></i> file-sound-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text"></i> file-text</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text-o"></i> file-text-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-video-o"></i> file-video-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-word-o"></i> file-word-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-zip-o"></i> file-zip-o <span
                        class="text-muted">(alias)</span></a></div>
            </div>
        </section>
        <section id="spinner">
            <h2 class="page-header">Spinner Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o-notch"></i> circle-o-notch</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cog"></i> cog</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gear"></i> gear <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-refresh"></i> refresh</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spinner"></i> spinner</a></div>
            </div>
        </section>
        <section id="form-control">
            <h2 class="page-header">Form Control Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square"></i> check-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square-o"></i> check-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle"></i> circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o"></i> circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dot-circle-o"></i> dot-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square"></i> minus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square-o"></i> minus-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square"></i> plus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square-o"></i> plus-square-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square"></i> square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square-o"></i> square-o</a></div>
            </div>
        </section>
        <section id="payment">
            <h2 class="page-header">Payment Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-amex"></i> cc-amex</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-diners-club"></i> cc-diners-club</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-discover"></i> cc-discover</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-jcb"></i> cc-jcb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-mastercard"></i> cc-mastercard</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-paypal"></i> cc-paypal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-stripe"></i> cc-stripe</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-visa"></i> cc-visa</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card"></i> credit-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card-alt"></i> credit-card-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-wallet"></i> google-wallet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paypal"></i> paypal</a></div>
            </div>
        </section>
        <section id="chart">
            <h2 class="page-header">Chart Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-area-chart"></i> area-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart"></i> bar-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart-o"></i> bar-chart-o <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-line-chart"></i> line-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pie-chart"></i> pie-chart</a></div>
            </div>
        </section>
        <section id="currency">
            <h2 class="page-header">Currency Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitcoin"></i> bitcoin <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-btc"></i> btc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cny"></i> cny <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dollar"></i> dollar <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eur"></i> eur</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-euro"></i> euro <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gbp"></i> gbp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg"></i> gg</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg-circle"></i> gg-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ils"></i> ils</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-inr"></i> inr</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-jpy"></i> jpy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-krw"></i> krw</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-money"></i> money</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rmb"></i> rmb <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rouble"></i> rouble <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rub"></i> rub</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ruble"></i> ruble <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rupee"></i> rupee <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shekel"></i> shekel <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sheqel"></i> sheqel <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-try"></i> try</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-turkish-lira"></i> turkish-lira <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-usd"></i> usd</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-won"></i> won <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yen"></i> yen <span class="text-muted">(alias)</span></a></div>
            </div>
        </section>
        <section id="text-editor">
            <h2 class="page-header">Text Editor Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-center"></i> align-center</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-justify"></i> align-justify</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-left"></i> align-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-right"></i> align-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bold"></i> bold</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chain"></i> chain <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chain-broken"></i> chain-broken</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clipboard"></i> clipboard</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-columns"></i> columns</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-copy"></i> copy <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cut"></i> cut <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dedent"></i> dedent <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eraser"></i> eraser</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file"></i> file</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-o"></i> file-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text"></i> file-text</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text-o"></i> file-text-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-files-o"></i> files-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-floppy-o"></i> floppy-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-font"></i> font</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-header"></i> header</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-indent"></i> indent</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-italic"></i> italic</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-link"></i> link</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list"></i> list</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-alt"></i> list-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-ol"></i> list-ol</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-ul"></i> list-ul</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-outdent"></i> outdent</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paperclip"></i> paperclip</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paragraph"></i> paragraph</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paste"></i> paste <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-repeat"></i> repeat</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rotate-left"></i> rotate-left <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rotate-right"></i> rotate-right <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-save"></i> save <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-scissors"></i> scissors</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-strikethrough"></i> strikethrough</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-subscript"></i> subscript</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-superscript"></i> superscript</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-table"></i> table</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-text-height"></i> text-height</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-text-width"></i> text-width</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th"></i> th</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th-large"></i> th-large</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th-list"></i> th-list</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-underline"></i> underline</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-undo"></i> undo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlink"></i> unlink <span class="text-muted">(alias)</span></a>
                </div>
            </div>
        </section>
        <section id="directional">
            <h2 class="page-header">Directional Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-down"></i> angle-double-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-left"></i> angle-double-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-right"></i> angle-double-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-up"></i> angle-double-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-down"></i> angle-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-left"></i> angle-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-right"></i> angle-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-up"></i> angle-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-down"></i> arrow-circle-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-left"></i> arrow-circle-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-down"></i> arrow-circle-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-left"></i> arrow-circle-o-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-right"></i> arrow-circle-o-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-up"></i> arrow-circle-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-right"></i> arrow-circle-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-up"></i> arrow-circle-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-down"></i> arrow-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-left"></i> arrow-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-right"></i> arrow-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-up"></i> arrow-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows"></i> arrows</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-alt"></i> arrows-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-h"></i> arrows-h</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-v"></i> arrows-v</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-down"></i> caret-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-left"></i> caret-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-right"></i> caret-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-down"></i> caret-square-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-left"></i> caret-square-o-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-right"></i> caret-square-o-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-up"></i> caret-square-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-up"></i> caret-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-down"></i> chevron-circle-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-left"></i> chevron-circle-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-right"></i> chevron-circle-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-up"></i> chevron-circle-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-down"></i> chevron-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-left"></i> chevron-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-right"></i> chevron-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-up"></i> chevron-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exchange"></i> exchange</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-down"></i> hand-o-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-left"></i> hand-o-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-right"></i> hand-o-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-up"></i> hand-o-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-down"></i> long-arrow-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-left"></i> long-arrow-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-right"></i> long-arrow-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-up"></i> long-arrow-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-down"></i> toggle-down <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-left"></i> toggle-left <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-right"></i> toggle-right <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-up"></i> toggle-up <span class="text-muted">(alias)</span></a>
                </div>
            </div>
        </section>
        <section id="video-player">
            <h2 class="page-header">Video Player Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-alt"></i> arrows-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-backward"></i> backward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-compress"></i> compress</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eject"></i> eject</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-expand"></i> expand</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fast-backward"></i> fast-backward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fast-forward"></i> fast-forward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-forward"></i> forward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause"></i> pause</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause-circle"></i> pause-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause-circle-o"></i> pause-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play"></i> play</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play-circle"></i> play-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play-circle-o"></i> play-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-random"></i> random</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-step-backward"></i> step-backward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-step-forward"></i> step-forward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop"></i> stop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop-circle"></i> stop-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop-circle-o"></i> stop-circle-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-play"></i> youtube-play</a></div>
            </div>
        </section>
        <section id="brand">
            <h2 class="page-header">Brand Icons</h2>
            <div class="row fontawesome-icon-list margin-bottom-lg">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-500px"></i> 500px</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-adn"></i> adn</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-amazon"></i> amazon</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-android"></i> android</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angellist"></i> angellist</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-apple"></i> apple</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bandcamp"></i> bandcamp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-behance"></i> behance</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-behance-square"></i> behance-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitbucket"></i> bitbucket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitbucket-square"></i> bitbucket-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitcoin"></i> bitcoin <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-black-tie"></i> black-tie</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth"></i> bluetooth</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth-b"></i> bluetooth-b</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-btc"></i> btc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-buysellads"></i> buysellads</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-amex"></i> cc-amex</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-diners-club"></i> cc-diners-club</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-discover"></i> cc-discover</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-jcb"></i> cc-jcb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-mastercard"></i> cc-mastercard</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-paypal"></i> cc-paypal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-stripe"></i> cc-stripe</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-visa"></i> cc-visa</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chrome"></i> chrome</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-codepen"></i> codepen</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-codiepie"></i> codiepie</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-connectdevelop"></i> connectdevelop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-contao"></i> contao</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-css3"></i> css3</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dashcube"></i> dashcube</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-delicious"></i> delicious</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deviantart"></i> deviantart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-digg"></i> digg</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dribbble"></i> dribbble</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dropbox"></i> dropbox</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drupal"></i> drupal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-edge"></i> edge</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eercast"></i> eercast</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-empire"></i> empire</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envira"></i> envira</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-etsy"></i> etsy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-expeditedssl"></i> expeditedssl</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fa"></i> fa <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook"></i> facebook</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-f"></i> facebook-f <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-official"></i> facebook-official</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-square"></i> facebook-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-firefox"></i> firefox</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-first-order"></i> first-order</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flickr"></i> flickr</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-font-awesome"></i> font-awesome</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fonticons"></i> fonticons</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fort-awesome"></i> fort-awesome</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-forumbee"></i> forumbee</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-foursquare"></i> foursquare</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-free-code-camp"></i> free-code-camp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ge"></i> ge <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-get-pocket"></i> get-pocket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg"></i> gg</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg-circle"></i> gg-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-git"></i> git</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-git-square"></i> git-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github"></i> github</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github-alt"></i> github-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github-square"></i> github-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gitlab"></i> gitlab</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gittip"></i> gittip <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glide"></i> glide</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glide-g"></i> glide-g</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google"></i> google</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus"></i> google-plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-circle"></i> google-plus-circle <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-official"></i> google-plus-official</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-square"></i> google-plus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-wallet"></i> google-wallet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gratipay"></i> gratipay</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-grav"></i> grav</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hacker-news"></i> hacker-news</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-houzz"></i> houzz</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-html5"></i> html5</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-imdb"></i> imdb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-instagram"></i> instagram</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-internet-explorer"></i> internet-explorer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ioxhost"></i> ioxhost</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-joomla"></i> joomla</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-jsfiddle"></i> jsfiddle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lastfm"></i> lastfm</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lastfm-square"></i> lastfm-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-leanpub"></i> leanpub</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linkedin"></i> linkedin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linkedin-square"></i> linkedin-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linode"></i> linode</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linux"></i> linux</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-maxcdn"></i> maxcdn</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meanpath"></i> meanpath</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-medium"></i> medium</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meetup"></i> meetup</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mixcloud"></i> mixcloud</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-modx"></i> modx</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-odnoklassniki"></i> odnoklassniki</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-odnoklassniki-square"></i> odnoklassniki-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-opencart"></i> opencart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-openid"></i> openid</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-opera"></i> opera</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-optin-monster"></i> optin-monster</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pagelines"></i> pagelines</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paypal"></i> paypal</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper"></i> pied-piper</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper-alt"></i> pied-piper-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper-pp"></i> pied-piper-pp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest"></i> pinterest</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest-p"></i> pinterest-p</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest-square"></i> pinterest-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-product-hunt"></i> product-hunt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-qq"></i> qq</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quora"></i> quora</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ra"></i> ra <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ravelry"></i> ravelry</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rebel"></i> rebel</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit"></i> reddit</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit-alien"></i> reddit-alien</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit-square"></i> reddit-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-renren"></i> renren</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-resistance"></i> resistance <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-safari"></i> safari</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-scribd"></i> scribd</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sellsy"></i> sellsy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt"></i> share-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt-square"></i> share-alt-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shirtsinbulk"></i> shirtsinbulk</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-simplybuilt"></i> simplybuilt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-skyatlas"></i> skyatlas</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-skype"></i> skype</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-slack"></i> slack</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-slideshare"></i> slideshare</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat"></i> snapchat</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat-ghost"></i> snapchat-ghost</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat-square"></i> snapchat-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-soundcloud"></i> soundcloud</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spotify"></i> spotify</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stack-exchange"></i> stack-exchange</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stack-overflow"></i> stack-overflow</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-steam"></i> steam</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-steam-square"></i> steam-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stumbleupon"></i> stumbleupon</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stumbleupon-circle"></i> stumbleupon-circle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-superpowers"></i> superpowers</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-telegram"></i> telegram</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tencent-weibo"></i> tencent-weibo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-themeisle"></i> themeisle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trello"></i> trello</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tripadvisor"></i> tripadvisor</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tumblr"></i> tumblr</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tumblr-square"></i> tumblr-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitch"></i> twitch</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitter"></i> twitter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitter-square"></i> twitter-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-usb"></i> usb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viacoin"></i> viacoin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viadeo"></i> viadeo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viadeo-square"></i> viadeo-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vimeo"></i> vimeo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vimeo-square"></i> vimeo-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vine"></i> vine</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vk"></i> vk</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wechat"></i> wechat <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-weibo"></i> weibo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-weixin"></i> weixin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-whatsapp"></i> whatsapp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wikipedia-w"></i> wikipedia-w</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-windows"></i> windows</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wordpress"></i> wordpress</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpbeginner"></i> wpbeginner</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpexplorer"></i> wpexplorer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpforms"></i> wpforms</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-xing"></i> xing</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-xing-square"></i> xing-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-y-combinator"></i> y-combinator</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-y-combinator-square"></i> y-combinator-square <span
                        class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yahoo"></i> yahoo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yc"></i> yc <span class="text-muted">(alias)</span></a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yc-square"></i> yc-square <span class="text-muted">(alias)</span></a>
                </div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yelp"></i> yelp</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yoast"></i> yoast</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube"></i> youtube</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-play"></i> youtube-play</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-square"></i> youtube-square</a></div>
            </div>
        </section>
        <section id="medical">
            <h2 class="page-header">Medical Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ambulance"></i> ambulance</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-h-square"></i> h-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart"></i> heart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart-o"></i> heart-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heartbeat"></i> heartbeat</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hospital-o"></i> hospital-o</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-medkit"></i> medkit</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square"></i> plus-square</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stethoscope"></i> stethoscope</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-md"></i> user-md</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair"></i> wheelchair</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt"></i> wheelchair-alt</a></div>
            </div>
        </section>
        <section id="simple-line-icons">
            <h2 class="page-header">Simple Line Icons</h2>
            <div class="row fontawesome-icon-list">
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-user"></i> icon-user</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-user-female"></i> icon-user-female</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-users"></i> icon-users</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-user-follow"></i> icon-user-follow</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-user-following"></i> icon-user-following</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-user-unfollow"></i> icon-user-unfollow</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-trophy"></i> icon-trophy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-speedometer"></i> icon-speedometer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-youtube"></i> icon-social-youtube</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-twitter"></i> icon-social-twitter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-tumblr"></i> icon-social-tumblr</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-facebook"></i> icon-social-facebook</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-dropbox"></i> icon-social-dropbox</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-social-dribbble"></i> icon-social-dribbble</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-shield"></i> icon-shield</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-screen-tablet"></i> icon-screen-tablet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-screen-smartphone"></i> icon-screen-smartphone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-screen-desktop"></i> icon-screen-desktop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-plane"></i> icon-plane</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-notebook"></i> icon-notebook</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-moustache"></i> icon-moustache</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-mouse"></i> icon-mouse</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-magnet"></i> icon-magnet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-magic-wand"></i> icon-magic-wand</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-hourglass"></i> icon-hourglass</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-graduation"></i> icon-graduation</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-ghost"></i> icon-ghost</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-game-controller"></i> icon-game-controller</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-fire"></i> icon-fire</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-eyeglasses"></i> icon-eyeglasses</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-envelope-open"></i> icon-envelope-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-envelope-letter"></i> icon-envelope-letter</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-energy"></i> icon-energy</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-emoticon-smile"></i> icon-emoticon-smile</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-disc"></i> icon-disc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-cursor-move"></i> icon-cursor-move</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-crop"></i> icon-crop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-credit-card"></i> icon-credit-card</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-chemistry"></i> icon-chemistry</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bell"></i> icon-bell</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-badge"></i> icon-badge</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-anchor"></i> icon-anchor</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-action-redo"></i> icon-action-redo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-action-undo"></i> icon-action-undo</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bag"></i> icon-bag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-basket"></i> icon-basket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-basket-loaded"></i> icon-basket-loaded</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-book-open"></i> icon-book-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-briefcase"></i> icon-briefcase</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bubbles"></i> icon-bubbles</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-calculator"></i> icon-calculator</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-call-end"></i> icon-call-end</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-call-in"></i> icon-call-in</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-call-out"></i> icon-call-out</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-compass"></i> icon-compass</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-cup"></i> icon-cup</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-diamond"></i> icon-diamond</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-direction"></i> icon-direction</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-directions"></i> icon-directions</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-docs"></i> icon-docs</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-drawer"></i> icon-drawer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-drop"></i> icon-drop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-earphones"></i> icon-earphones</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-earphones-alt"></i> icon-earphones-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-feed"></i> icon-feed</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-film"></i> icon-film</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-folder-alt"></i> icon-folder-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-frame"></i> icon-frame</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-globe"></i> icon-globe</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-globe-alt"></i> icon-globe-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-handbag"></i> icon-handbag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-layers"></i> icon-layers</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-map"></i> icon-map</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-picture"></i> icon-picture</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-pin"></i> icon-pin</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-playlist"></i> icon-playlist</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-present"></i> icon-present</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-printer"></i> icon-printer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-puzzle"></i> icon-puzzle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-speech"></i> icon-speech</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-vector"></i> icon-vector</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-wallet"></i> icon-wallet</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-arrow-down"></i> icon-arrow-down</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-arrow-left"></i> icon-arrow-left</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-arrow-right"></i> icon-arrow-right</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-arrow-up"></i> icon-arrow-up</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bar-chart"></i> icon-bar-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bulb"></i> icon-bulb</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-calendar"></i> icon-calendar</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-end"></i> icon-control-end</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-forward"></i> icon-control-forward</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-pause"></i> icon-control-pause</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-play"></i> icon-control-play</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-rewind"></i> icon-control-rewind</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-control-start"></i> icon-control-start</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-cursor"></i> icon-cursor</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-dislike"></i> icon-dislike</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-equalizer"></i> icon-equalizer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-graph"></i> icon-graph</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-grid"></i> icon-grid</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-home"></i> icon-home</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-like"></i> icon-like</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-list"></i> icon-list</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-login"></i> icon-login</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-logout"></i> icon-logout</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-loop"></i> icon-loop</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-microphone"></i> icon-microphone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-music-tone"></i> icon-music-tone</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-music-tone-alt"></i> icon-music-tone-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-note"></i> icon-note</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-pencil"></i> icon-pencil</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-pie-chart"></i> icon-pie-chart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-question"></i> icon-question</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-rocket"></i> icon-rocket</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-share"></i> icon-share</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-share-alt"></i> icon-share-alt</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-shuffle"></i> icon-shuffle</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-size-actual"></i> icon-size-actual</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-size-fullscreen"></i> icon-size-fullscreen</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-support"></i> icon-support</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-tag"></i> icon-tag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-trash"></i> icon-trash</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-umbrella"></i> icon-umbrella</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-wrench"></i> icon-wrench</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-ban"></i> icon-ban</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-bubble"></i> icon-bubble</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-camcorder"></i> icon-camcorder</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-camera"></i> icon-camera</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-check"></i> icon-check</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-clock"></i> icon-clock</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-close"></i> icon-close</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-cloud-download"></i> icon-cloud-download</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-cloud-upload"></i> icon-cloud-upload</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-doc"></i> icon-doc</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-envelope"></i> icon-envelope</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-eye"></i> icon-eye</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-flag"></i> icon-flag</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-folder"></i> icon-folder</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-heart"></i> icon-heart</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-info"></i> icon-info</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-key"></i> icon-key</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-link"></i> icon-link</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-lock"></i> icon-lock</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-lock-open"></i> icon-lock-open</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-magnifier"></i> icon-magnifier</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-magnifier-add"></i> icon-magnifier-add</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-magnifier-remove"></i> icon-magnifier-remove</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-paper-clip"></i> icon-paper-clip</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-paper-plane"></i> icon-paper-plane</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-plus"></i> icon-plus</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-pointer"></i> icon-pointer</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-power"></i> icon-power</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-refresh"></i> icon-refresh</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-reload"></i> icon-reload</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-settings"></i> icon-settings</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-star"></i> icon-star</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-symbol-female"></i> icon-symbol-female</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-symbol-male"></i> icon-symbol-male</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-target"></i> icon-target</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-volume-1"></i> icon-volume-1</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-volume-2"></i> icon-volume-2</a></div>
                <div class="fa-hover col-md-3 col-sm-4"><a><i class="icon-volume-off"></i> icon-volume-off</a></div>
            </div>
        </section>

        <#--<section id="line-awesome-web-application">-->
            <#--<h2 class="page-header">Line Awesome - Web Application Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-adjust"></i> adjust</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-anchor"></i> anchor</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-archive"></i> archive</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-area-chart"></i> area-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows"></i> arrows</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-h"></i> arrows-h</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-v"></i> arrows-v</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-asterisk"></i> asterisk</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-at"></i> at</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-automobile"></i> automobile</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-balance-scale"></i> balance-scale</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ban"></i> ban</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bank"></i> bank</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bar-chart"></i> bar-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bar-chart-o"></i> bar-chart-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-barcode"></i> barcode</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bars"></i> bars</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-0"></i> battery-0</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-1"></i> battery-1</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-2"></i> battery-2</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-3"></i> battery-3</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-4"></i> battery-4</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-empty"></i> battery-empty</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-full"></i> battery-full</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-half"></i> battery-half</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-quarter"></i> battery-quarter</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-battery-three-quarters"></i> battery-three-quarters</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bed"></i> bed</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-beer"></i> beer</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bell"></i> bell</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bell-o"></i> bell-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bell-slash"></i> bell-slash</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bell-slash-o"></i> bell-slash-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bicycle"></i> bicycle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-binoculars"></i> binoculars</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-birthday-cake"></i> birthday-cake</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bolt"></i> bolt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bomb"></i> bomb</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-book"></i> book</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bookmark"></i> bookmark</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bookmark-o"></i> bookmark-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-briefcase"></i> briefcase</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bug"></i> bug</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-building"></i> building</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-building-o"></i> building-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bullhorn"></i> bullhorn</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bullseye"></i> bullseye</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bus"></i> bus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cab"></i> cab</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calculator"></i> calculator</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar"></i> calendar</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar-check-o"></i> calendar-check-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar-minus-o"></i> calendar-minus-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar-o"></i> calendar-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar-plus-o"></i> calendar-plus-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-calendar-times-o"></i> calendar-times-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-camera"></i> camera</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-camera-retro"></i> camera-retro</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-car"></i> car</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-down"></i> caret-square-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-left"></i> caret-square-o-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-right"></i> caret-square-o-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-up"></i> caret-square-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cart-arrow-down"></i> cart-arrow-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cart-plus"></i> cart-plus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc"></i> cc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-certificate"></i> certificate</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check"></i> check</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-circle"></i> check-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-circle-o"></i> check-circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-square"></i> check-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-square-o"></i> check-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-child"></i> child</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle"></i> circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle-o"></i> circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle-o-notch"></i> circle-o-notch</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle-thin"></i> circle-thin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-clock-o"></i> clock-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-clone"></i> clone</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-close"></i> close</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cloud"></i> cloud</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cloud-download"></i> cloud-download</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cloud-upload"></i> cloud-upload</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-code"></i> code</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-code-fork"></i> code-fork</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-coffee"></i> coffee</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cog"></i> cog</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cogs"></i> cogs</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-comment"></i> comment</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-comment-o"></i> comment-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-commenting"></i> commenting</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-commenting-o"></i> commenting-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-comments"></i> comments</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-comments-o"></i> comments-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-compass"></i> compass</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-copyright"></i> copyright</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-creative-commons"></i> creative-commons</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-credit-card"></i> credit-card</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-crop"></i> crop</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-crosshairs"></i> crosshairs</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cube"></i> cube</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cubes"></i> cubes</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cutlery"></i> cutlery</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dashboard"></i> dashboard</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-database"></i> database</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-desktop"></i> desktop</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-diamond"></i> diamond</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dot-circle-o"></i> dot-circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-download"></i> download</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-edit"></i> edit</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ellipsis-h"></i> ellipsis-h</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ellipsis-v"></i> ellipsis-v</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-envelope"></i> envelope</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-envelope-o"></i> envelope-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-envelope-square"></i> envelope-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eraser"></i> eraser</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-exchange"></i> exchange</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-exclamation"></i> exclamation</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-exclamation-circle"></i> exclamation-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-exclamation-triangle"></i> exclamation-triangle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-external-link"></i> external-link</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-external-link-square"></i> external-link-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eye"></i> eye</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eye-slash"></i> eye-slash</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eyedropper"></i> eyedropper</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fax"></i> fax</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-feed"></i> feed</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-female"></i> female</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fighter-jet"></i> fighter-jet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-archive-o"></i> file-archive-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-audio-o"></i> file-audio-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-code-o"></i> file-code-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-excel-o"></i> file-excel-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-image-o"></i> file-image-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-movie-o"></i> file-movie-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-pdf-o"></i> file-pdf-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-photo-o"></i> file-photo-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-picture-o"></i> file-picture-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-powerpoint-o"></i> file-powerpoint-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-sound-o"></i> file-sound-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-video-o"></i> file-video-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-word-o"></i> file-word-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-zip-o"></i> file-zip-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-film"></i> film</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-filter"></i> filter</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fire"></i> fire</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fire-extinguisher"></i> fire-extinguisher</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flag"></i> flag</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flag-checkered"></i> flag-checkered</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flag-o"></i> flag-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flash"></i> flash</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flask"></i> flask</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-folder"></i> folder</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-folder-o"></i> folder-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-folder-open"></i> folder-open</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-folder-open-o"></i> folder-open-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-frown-o"></i> frown-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-futbol-o"></i> futbol-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gamepad"></i> gamepad</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gavel"></i> gavel</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gear"></i> gear</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gears"></i> gears</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gift"></i> gift</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-glass"></i> glass</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-globe"></i> globe</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-graduation-cap"></i> graduation-cap</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-group"></i> group</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-grab-o"></i> hand-grab-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-lizard-o"></i> hand-lizard-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-paper-o"></i> hand-paper-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-peace-o"></i> hand-peace-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-pointer-o"></i> hand-pointer-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-rock-o"></i> hand-rock-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-scissors-o"></i> hand-scissors-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-spock-o"></i> hand-spock-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-stop-o"></i> hand-stop-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hdd-o"></i> hdd-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-headphones"></i> headphones</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heart"></i> heart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heart-o"></i> heart-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heartbeat"></i> heartbeat</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-history"></i> history</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-home"></i> home</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hotel"></i> hotel</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass"></i> hourglass</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-1"></i> hourglass-1</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-2"></i> hourglass-2</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-3"></i> hourglass-3</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-end"></i> hourglass-end</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-half"></i> hourglass-half</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-o"></i> hourglass-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hourglass-start"></i> hourglass-start</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-i-cursor"></i> i-cursor</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-image"></i> image</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-inbox"></i> inbox</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-industry"></i> industry</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-info"></i> info</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-info-circle"></i> info-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-institution"></i> institution</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-key"></i> key</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-keyboard-o"></i> keyboard-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-language"></i> language</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-laptop"></i> laptop</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-leaf"></i> leaf</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-legal"></i> legal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-lemon-o"></i> lemon-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-level-down"></i> level-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-level-up"></i> level-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-life-bouy"></i> life-bouy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-life-buoy"></i> life-buoy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-life-ring"></i> life-ring</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-life-saver"></i> life-saver</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-lightbulb-o"></i> lightbulb-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-line-chart"></i> line-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-location-arrow"></i> location-arrow</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-lock"></i> lock</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-magic"></i> magic</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-magnet"></i> magnet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mail-forward"></i> mail-forward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mail-reply"></i> mail-reply</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mail-reply-all"></i> mail-reply-all</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-male"></i> male</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-map"></i> map</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-map-marker"></i> map-marker</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-map-o"></i> map-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-map-pin"></i> map-pin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-map-signs"></i> map-signs</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-meh-o"></i> meh-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-microphone"></i> microphone</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-microphone-slash"></i> microphone-slash</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus"></i> minus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus-circle"></i> minus-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus-square"></i> minus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus-square-o"></i> minus-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mobile"></i> mobile</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mobile-phone"></i> mobile-phone</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-money"></i> money</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-moon-o"></i> moon-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mortar-board"></i> mortar-board</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-motorcycle"></i> motorcycle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mouse-pointer"></i> mouse-pointer</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-music"></i> music</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-navicon"></i> navicon</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-newspaper-o"></i> newspaper-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-object-group"></i> object-group</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-object-ungroup"></i> object-ungroup</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paint-brush"></i> paint-brush</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paper-plane"></i> paper-plane</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paper-plane-o"></i> paper-plane-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paw"></i> paw</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pencil"></i> pencil</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pencil-square"></i> pencil-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pencil-square-o"></i> pencil-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-phone"></i> phone</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-phone-square"></i> phone-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-photo"></i> photo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-picture-o"></i> picture-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pie-chart"></i> pie-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plane"></i> plane</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plug"></i> plug</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus"></i> plus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-circle"></i> plus-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-square"></i> plus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-square-o"></i> plus-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-power-off"></i> power-off</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-print"></i> print</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-puzzle-piece"></i> puzzle-piece</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-qrcode"></i> qrcode</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-question"></i> question</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-question-circle"></i> question-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-quote-left"></i> quote-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-quote-right"></i> quote-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-random"></i> random</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-recycle"></i> recycle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-refresh"></i> refresh</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-registered"></i> registered</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-remove"></i> remove</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-reorder"></i> reorder</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-reply"></i> reply</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-reply-all"></i> reply-all</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-retweet"></i> retweet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-road"></i> road</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rocket"></i> rocket</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rss"></i> rss</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rss-square"></i> rss-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-search"></i> search</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-search-minus"></i> search-minus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-search-plus"></i> search-plus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-send"></i> send</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-send-o"></i> send-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-server"></i> server</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share"></i> share</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-alt"></i> share-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-alt-square"></i> share-alt-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-square"></i> share-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-square-o"></i> share-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-shield"></i> shield</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ship"></i> ship</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-shopping-cart"></i> shopping-cart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sign-in"></i> sign-in</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sign-out"></i> sign-out</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-signal"></i> signal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sitemap"></i> sitemap</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sliders"></i> sliders</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-smile-o"></i> smile-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-soccer-ball-o"></i> soccer-ball-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort"></i> sort</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-alpha-asc"></i> sort-alpha-asc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-alpha-desc"></i> sort-alpha-desc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-amount-asc"></i> sort-amount-asc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-amount-desc"></i> sort-amount-desc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-asc"></i> sort-asc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-desc"></i> sort-desc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-down"></i> sort-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-numeric-asc"></i> sort-numeric-asc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-numeric-desc"></i> sort-numeric-desc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sort-up"></i> sort-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-space-shuttle"></i> space-shuttle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-spinner"></i> spinner</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-spoon"></i> spoon</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-square"></i> square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-square-o"></i> square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star"></i> star</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star-half"></i> star-half</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star-half-empty"></i> star-half-empty</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star-half-full"></i> star-half-full</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star-half-o"></i> star-half-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-star-o"></i> star-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sticky-note"></i> sticky-note</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sticky-note-o"></i> sticky-note-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-street-view"></i> street-view</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-suitcase"></i> suitcase</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sun-o"></i> sun-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-support"></i> support</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tablet"></i> tablet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tachometer"></i> tachometer</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tag"></i> tag</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tags"></i> tags</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tasks"></i> tasks</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-taxi"></i> taxi</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-television"></i> television</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-terminal"></i> terminal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumb-tack"></i> thumb-tack</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-down"></i> thumbs-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-o-down"></i> thumbs-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-o-up"></i> thumbs-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-up"></i> thumbs-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ticket"></i> ticket</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-times"></i> times</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-times-circle"></i> times-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-times-circle-o"></i> times-circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tint"></i> tint</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-down"></i> toggle-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-left"></i> toggle-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-off"></i> toggle-off</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-on"></i> toggle-on</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-right"></i> toggle-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-up"></i> toggle-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-trademark"></i> trademark</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-trash"></i> trash</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-trash-o"></i> trash-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tree"></i> tree</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-trophy"></i> trophy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-truck"></i> truck</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tty"></i> tty</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tv"></i> tv</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-umbrella"></i> umbrella</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-university"></i> university</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-unlock"></i> unlock</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-unlock-alt"></i> unlock-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-unsorted"></i> unsorted</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-upload"></i> upload</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-user"></i> user</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-user-plus"></i> user-plus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-user-secret"></i> user-secret</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-user-times"></i> user-times</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-users"></i> users</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-video-camera"></i> video-camera</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-volume-down"></i> volume-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-volume-off"></i> volume-off</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-volume-up"></i> volume-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-warning"></i> warning</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wheelchair"></i> wheelchair</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wifi"></i> wifi</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wrench"></i> wrench</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-hand">-->
            <#--<h2 class="page-header">Line Awesome - Hand Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-grab-o"></i> hand-grab-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-lizard-o"></i> hand-lizard-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-paper-o"></i> hand-paper-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-peace-o"></i> hand-peace-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-pointer-o"></i> hand-pointer-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-rock-o"></i> hand-rock-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-scissors-o"></i> hand-scissors-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-spock-o"></i> hand-spock-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-stop-o"></i> hand-stop-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-down"></i> thumbs-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-o-down"></i> thumbs-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-o-up"></i> thumbs-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-thumbs-up"></i> thumbs-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-down"></i> hand-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-left"></i> hand-o-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-right"></i> hand-o-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-up"></i> hand-o-up</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-transportation">-->
            <#--<h2 class="page-header">Line Awesome - Transportation Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-automobile"></i> automobile</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bicycle"></i> bicycle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bus"></i> bus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cab"></i> cab</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-car"></i> car</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fighter-jet"></i> fighter-jet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-motorcycle"></i> motorcycle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plane"></i> plane</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rocket"></i> rocket</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ship"></i> ship</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-space-shuttle"></i> space-shuttle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-taxi"></i> taxi</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-truck"></i> truck</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wheelchair"></i> wheelchair</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ambulance"></i> ambulance</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-subway"></i> subway</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-train"></i> train</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-gender">-->
            <#--<h2 class="page-header">Line Awesome - Gender Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-genderless"></i> genderless</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-intersex"></i> intersex</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mars"></i> mars</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mars-double"></i> mars-double</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mars-stroke"></i> mars-stroke</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mars-stroke-h"></i> mars-stroke-h</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mars-stroke-v"></i> mars-stroke-v</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-mercury"></i> mercury</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-neuter"></i> neuter</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-transgender"></i> transgender</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-transgender-alt"></i> transgender-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-venus"></i> venus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-venus-double"></i> venus-double</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-venus-mars"></i> venus-mars</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-file-type">-->
            <#--<h2 class="page-header">Line Awesome - File Type Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-archive-o"></i> file-archive-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-audio-o"></i> file-audio-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-code-o"></i> file-code-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-excel-o"></i> file-excel-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-image-o"></i> file-image-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-movie-o"></i> file-movie-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-pdf-o"></i> file-pdf-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-photo-o"></i> file-photo-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-picture-o"></i> file-picture-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-powerpoint-o"></i> file-powerpoint-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-sound-o"></i> file-sound-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-video-o"></i> file-video-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-word-o"></i> file-word-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-zip-o"></i> file-zip-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file"></i> file</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-o"></i> file-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-text"></i> file-text</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-text-o"></i> file-text-o</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-spinner">-->
            <#--<h2 class="page-header">Line Awesome - Spinner Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle-o-notch"></i> circle-o-notch</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cog"></i> cog</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gear"></i> gear</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-refresh"></i> refresh</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-spinner"></i> spinner</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-form-control">-->
            <#--<h2 class="page-header">Line Awesome - Form Control Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-square"></i> check-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-check-square-o"></i> check-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle"></i> circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-circle-o"></i> circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dot-circle-o"></i> dot-circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus-square"></i> minus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-minus-square-o"></i> minus-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-square"></i> plus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-square-o"></i> plus-square-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-square"></i> square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-square-o"></i> square-o</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-payment">-->
            <#--<h2 class="page-header">Line Awesome - Payment Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-credit-card"></i> credit-card</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-amex"></i> cc-amex</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-diners-club"></i> cc-diners-club</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-discover"></i> cc-discover</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-jcb"></i> cc-jcb</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-mastercard"></i> cc-mastercard</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-paypal"></i> cc-paypal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-stripe"></i> cc-stripe</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-visa"></i> cc-visa</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-google-wallet"></i> google-wallet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paypal"></i> paypal</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-chart">-->
            <#--<h2 class="page-header">Line Awesome - Chart Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-area-chart"></i> area-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bar-chart"></i> bar-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bar-chart-o"></i> bar-chart-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-line-chart"></i> line-chart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pie-chart"></i> pie-chart</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-currency">-->
            <#--<h2 class="page-header">Line Awesome - Currency Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-money"></i> money</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bitcoin"></i> bitcoin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-btc"></i> btc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cny"></i> cny</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dollar"></i> dollar</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eur"></i> eur</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-euro"></i> euro</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gbp"></i> gbp</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gg"></i> gg</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gg-circle"></i> gg-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ils"></i> ils</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-inr"></i> inr</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-jpy"></i> jpy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-krw"></i> krw</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rmb"></i> rmb</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rouble"></i> rouble</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rub"></i> rub</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ruble"></i> ruble</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rupee"></i> rupee</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-shekel"></i> shekel</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sheqel"></i> sheqel</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-try"></i> try</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-turkish-lira"></i> turkish-lira</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-usd"></i> usd</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-won"></i> won</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-yen"></i> yen</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-text-editor">-->
            <#--<h2 class="page-header">Line Awesome - Text Editor Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eraser"></i> eraser</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file"></i> file</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-o"></i> file-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-text"></i> file-text</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-file-text-o"></i> file-text-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-align-center"></i> align-center</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-align-justify"></i> align-justify</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-align-left"></i> align-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-align-right"></i> align-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bold"></i> bold</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chain"></i> chain</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chain-broken"></i> chain-broken</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-clipboard"></i> clipboard</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-columns"></i> columns</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-copy"></i> copy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cut"></i> cut</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dedent"></i> dedent</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-files-o"></i> files-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-floppy-o"></i> floppy-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-font"></i> font</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-header"></i> header</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-indent"></i> indent</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-italic"></i> italic</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-link"></i> link</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-list"></i> list</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-list-alt"></i> list-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-list-ol"></i> list-ol</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-list-ul"></i> list-ul</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-outdent"></i> outdent</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paperclip"></i> paperclip</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paragraph"></i> paragraph</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paste"></i> paste</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-repeat"></i> repeat</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rotate-left"></i> rotate-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rotate-right"></i> rotate-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-save"></i> save</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-scissors"></i> scissors</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-strikethrough"></i> strikethrough</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-subscript"></i> subscript</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-superscript"></i> superscript</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-table"></i> table</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-text-height"></i> text-height</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-text-width"></i> text-width</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-th"></i> th</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-th-large"></i> th-large</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-th-list"></i> th-list</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-underline"></i> underline</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-undo"></i> undo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-unlink"></i> unlink</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-directional">-->
            <#--<h2 class="page-header">Line Awesome - Directional Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows"></i> arrows</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-h"></i> arrows-h</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-v"></i> arrows-v</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-down"></i> caret-square-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-left"></i> caret-square-o-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-right"></i> caret-square-o-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-square-o-up"></i> caret-square-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-exchange"></i> exchange</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-down"></i> toggle-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-left"></i> toggle-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-right"></i> toggle-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-toggle-up"></i> toggle-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-down"></i> hand-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-left"></i> hand-o-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-right"></i> hand-o-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hand-o-up"></i> hand-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-double-down"></i> angle-double-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-double-left"></i> angle-double-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-double-right"></i> angle-double-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-double-up"></i> angle-double-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-down"></i> angle-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-left"></i> angle-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-right"></i> angle-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angle-up"></i> angle-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-down"></i> arrow-circle-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-left"></i> arrow-circle-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-o-down"></i> arrow-circle-o-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-o-left"></i> arrow-circle-o-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-o-right"></i> arrow-circle-o-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-o-up"></i> arrow-circle-o-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-right"></i> arrow-circle-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-circle-up"></i> arrow-circle-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-down"></i> arrow-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-left"></i> arrow-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-right"></i> arrow-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrow-up"></i> arrow-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-alt"></i> arrows-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-down"></i> caret-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-left"></i> caret-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-right"></i> caret-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-caret-up"></i> caret-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-circle-down"></i> chevron-circle-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-circle-left"></i> chevron-circle-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-circle-right"></i> chevron-circle-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-circle-up"></i> chevron-circle-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-down"></i> chevron-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-left"></i> chevron-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-right"></i> chevron-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chevron-up"></i> chevron-up</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-long-arrow-down"></i> long-arrow-down</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-long-arrow-left"></i> long-arrow-left</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-long-arrow-right"></i> long-arrow-right</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-long-arrow-up"></i> long-arrow-up</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-video-player">-->
            <#--<h2 class="page-header">Line Awesome - Video Player Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-random"></i> random</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-arrows-alt"></i> arrows-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-backward"></i> backward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-compress"></i> compress</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-eject"></i> eject</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-expand"></i> expand</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fast-backward"></i> fast-backward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fast-forward"></i> fast-forward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-forward"></i> forward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pause"></i> pause</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-play"></i> play</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-play-circle"></i> play-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-play-circle-o"></i> play-circle-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-step-backward"></i> step-backward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-step-forward"></i> step-forward</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stop"></i> stop</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-youtube-play"></i> youtube-play</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-brand">-->
            <#--<h2 class="page-header">Line Awesome - Brand Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-alt"></i> share-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-share-alt-square"></i> share-alt-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-amex"></i> cc-amex</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-diners-club"></i> cc-diners-club</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-discover"></i> cc-discover</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-jcb"></i> cc-jcb</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-mastercard"></i> cc-mastercard</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-paypal"></i> cc-paypal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-stripe"></i> cc-stripe</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-cc-visa"></i> cc-visa</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-google-wallet"></i> google-wallet</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-paypal"></i> paypal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bitcoin"></i> bitcoin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-btc"></i> btc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gg"></i> gg</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gg-circle"></i> gg-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-youtube-play"></i> youtube-play</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-500px"></i> 500px</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-adn"></i> adn</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-amazon"></i> amazon</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-android"></i> android</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-angellist"></i> angellist</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-apple"></i> apple</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-behance"></i> behance</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-behance-square"></i> behance-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bitbucket"></i> bitbucket</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-bitbucket-square"></i> bitbucket-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-black-tie"></i> black-tie</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-buysellads"></i> buysellads</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-chrome"></i> chrome</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-codepen"></i> codepen</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-connectdevelop"></i> connectdevelop</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-contao"></i> contao</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-css3"></i> css3</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dashcube"></i> dashcube</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-delicious"></i> delicious</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-deviantart"></i> deviantart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-digg"></i> digg</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dribbble"></i> dribbble</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-dropbox"></i> dropbox</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-drupal"></i> drupal</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-empire"></i> empire</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-expeditedssl"></i> expeditedssl</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-facebook"></i> facebook</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-facebook-f"></i> facebook-f</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-facebook-official"></i> facebook-official</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-facebook-square"></i> facebook-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-firefox"></i> firefox</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-flickr"></i> flickr</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-fonticons"></i> fonticons</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-forumbee"></i> forumbee</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-foursquare"></i> foursquare</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ge"></i> ge</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-get-pocket"></i> get-pocket</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-git"></i> git</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-git-square"></i> git-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-github"></i> github</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-github-alt"></i> github-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-github-square"></i> github-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gittip"></i> gittip</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-google"></i> google</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-google-plus"></i> google-plus</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-google-plus-square"></i> google-plus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-gratipay"></i> gratipay</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hacker-news"></i> hacker-news</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-houzz"></i> houzz</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-html5"></i> html5</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-instagram"></i> instagram</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-internet-explorer"></i> internet-explorer</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ioxhost"></i> ioxhost</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-joomla"></i> joomla</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-jsfiddle"></i> jsfiddle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-lastfm"></i> lastfm</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-lastfm-square"></i> lastfm-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-leanpub"></i> leanpub</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-linkedin"></i> linkedin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-linkedin-square"></i> linkedin-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-linux"></i> linux</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-maxcdn"></i> maxcdn</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-meanpath"></i> meanpath</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-medium"></i> medium</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-odnoklassniki"></i> odnoklassniki</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-odnoklassniki-square"></i> odnoklassniki-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-opencart"></i> opencart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-openid"></i> openid</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-opera"></i> opera</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-optin-monster"></i> optin-monster</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pagelines"></i> pagelines</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pied-piper"></i> pied-piper</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pied-piper-alt"></i> pied-piper-alt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pinterest"></i> pinterest</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pinterest-p"></i> pinterest-p</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-pinterest-square"></i> pinterest-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-qq"></i> qq</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ra"></i> ra</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-rebel"></i> rebel</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-reddit"></i> reddit</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-reddit-square"></i> reddit-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-renren"></i> renren</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-safari"></i> safari</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-sellsy"></i> sellsy</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-shirtsinbulk"></i> shirtsinbulk</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-simplybuilt"></i> simplybuilt</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-skyatlas"></i> skyatlas</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-skype"></i> skype</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-slack"></i> slack</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-slideshare"></i> slideshare</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-soundcloud"></i> soundcloud</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-spotify"></i> spotify</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stack-exchange"></i> stack-exchange</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stack-overflow"></i> stack-overflow</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-steam"></i> steam</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-steam-square"></i> steam-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stumbleupon"></i> stumbleupon</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stumbleupon-circle"></i> stumbleupon-circle</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tencent-weibo"></i> tencent-weibo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-trello"></i> trello</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tripadvisor"></i> tripadvisor</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tumblr"></i> tumblr</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-tumblr-square"></i> tumblr-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-twitch"></i> twitch</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-twitter"></i> twitter</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-twitter-square"></i> twitter-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-viacoin"></i> viacoin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-vimeo"></i> vimeo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-vimeo-square"></i> vimeo-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-vine"></i> vine</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-vk"></i> vk</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wechat"></i> wechat</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-weibo"></i> weibo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-weixin"></i> weixin</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-whatsapp"></i> whatsapp</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wikipedia-w"></i> wikipedia-w</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-windows"></i> windows</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wordpress"></i> wordpress</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-xing"></i> xing</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-xing-square"></i> xing-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-y-combinator"></i> y-combinator</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-y-combinator-square"></i> y-combinator-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-yahoo"></i> yahoo</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-yc"></i> yc</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-yc-square"></i> yc-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-yelp"></i> yelp</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-youtube"></i> youtube</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-youtube-square"></i> youtube-square</a></div>-->
            <#--</div>-->
        <#--</section>-->
        <#--<section id="line-awesome-medical">-->
            <#--<h2 class="page-header">Line Awesome - Medical Icons</h2>-->
            <#--<div class="row fontawesome-icon-list">-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heart"></i> heart</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heart-o"></i> heart-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-heartbeat"></i> heartbeat</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-plus-square"></i> plus-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-wheelchair"></i> wheelchair</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-ambulance"></i> ambulance</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-h-square"></i> h-square</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-hospital-o"></i> hospital-o</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-medkit"></i> medkit</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-stethoscope"></i> stethoscope</a></div>-->
                <#--<div class="fa-hover col-md-3 col-sm-4"><a><i class="la la-user-md"></i> user-md</a></div>-->
            <#--</div>-->
        <#--</section>-->
    </div>
</div>
<div style="height: 50px;"></div>
<script id="search-template" type="text/html">
    <section>
        <h2 class="page-header">搜索关键字 <code>{0}</code></h2>
        <div class="row fontawesome-icon-list">
            {1}
        </div>
    </section>
</script>
<script id="search-no-template" type="text/html">
    <section>
        <h2 class="page-header">搜索关键字 <code>{0}</code></h2>
        <div class="alert alert-danger text-lg" role="alert">
            <h3 class="margin-top margin-bottom-lg">
                <i class="fa fa-meh-o" aria-hidden="true"></i> 没有找到符合条件的图标!</h3>
        </div>
    </section>
</script>
<script src="${web.cdn('/jx.js')}"></script>
<script src="${web.cdn('/sys/js/icon.js')}</script>
</body>
</html>