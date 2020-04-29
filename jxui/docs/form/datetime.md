# 日期时间控件

日期时间控件扩展自控件[`bootstrap-datetimepicker`](https://github.com/smalot/bootstrap-datetimepicker)。

!> [示例](demo/form/date.html)

## 使用方法

使用类`jxdatetime`标记的组件会自动引用依赖并初始化
```html
<!--组件模式-->
<div class='input-group date jxdatetime'>
    <input class="form-control" name="startDatetime1">
    <span class="input-group-addon">
        <i class="fa fa-calendar"></i>
    </span>
</div>

<!--文本框模式-->
<input name="startDatetime2" class="form-control jxdatetime">
```

## 默认值

```js
{
    autoclose: true,
    todayBtn: 'linked',
    todayHighlight: true,
    language: 'zh-CN',
    isInline: false,
    format:'yyyy-mm-dd hh:ii',
    fontAwesome: true
}
```

## Screenshots

### Decade year view

![Datetimepicker decade year view](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_decade.png)

This view allows to select the day in the selected month.

### Year view

![Datetimepicker year view](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_year.png)

This view allows to select the month in the selected year.

### Month view

![Datetimepicker month view](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_month.png)

This view allows to select the year in a range of 10 years.

### Day view

![Datetimepicker day view](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_day.png)

This view allows to select the hour in the selected day.

### Hour view

![Datetimepicker hour view](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_hour.png)

This view allows to select the preset of minutes in the selected hour.
The range of 5 minutes (by default) has been selected to restrict buttons quantity to an acceptable value, but it can be overrided by the <code>minuteStep</code> property.

### Day view - meridian

![Datetimepicker day view meridian](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_day_meridian.png)

Meridian is supported in both the day and hour views.
To use it, just enable the <code>showMeridian</code> property.

### Hour view - meridian

![Datetimepicker hour view meridian](https://raw.github.com/smalot/bootstrap-datetimepicker/master/screenshot/standard_hour_meridian.png)

## Example

Attached to a field with the format specified via options:

```html
<input type="text" value="2012-05-15 21:05" id="datetimepicker">
```
```javascript
$('#datetimepicker').datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
});
```

Attached to a field with the format specified via markup:

```html
<input type="text" value="2012-05-15 21:05" id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii">
```
```javascript
$('#datetimepicker').datetimepicker();
```

As component:

```html
<div class="input-append date" id="datetimepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
    <input size="16" type="text" value="12-02-2012" readonly>
    <span class="add-on"><i class="icon-th"></i></span>
</div>
```
```javascript
$('#datetimepicker').datetimepicker();
```

As inline datetimepicker:

```html
<div id="datetimepicker"></div>
```
```javascript
$('#datetimepicker').datetimepicker();
```

## 属性
All options that take a "Date" can handle a `Date` object; a String formatted according to the given `format`; or a timedelta relative to today, eg '-1d', '+6m +1y', etc, where valid units are 'd' (day), 'w' (week), 'm' (month), and 'y' (year).

You can also specify an ISO-8601 valid datetime, despite of the given `format` :
 * yyyy-mm-dd
 * yyyy-mm-dd hh:ii
 * yyyy-mm-ddThh:ii
 * yyyy-mm-dd hh:ii:ss
 * yyyy-mm-ddThh:ii:ssZ

### format

String.  Default: 'mm/dd/yyyy'

The date format, combination of p, P, h, hh, i, ii, s, ss, d, dd, m, mm, M, MM, yy, yyyy.

 * p : meridian in lower case ('am' or 'pm') - according to locale file
 * P : meridian in upper case ('AM' or 'PM') - according to locale file
 * s : seconds without leading zeros
 * ss : seconds, 2 digits with leading zeros
 * i : minutes without leading zeros
 * ii : minutes, 2 digits with leading zeros
 * h : hour without leading zeros - 24-hour format
 * hh : hour, 2 digits with leading zeros - 24-hour format
 * H : hour without leading zeros - 12-hour format
 * HH : hour, 2 digits with leading zeros - 12-hour format
 * d : day of the month without leading zeros
 * dd : day of the month, 2 digits with leading zeros
 * m : numeric representation of month without leading zeros
 * mm : numeric representation of the month, 2 digits with leading zeros
 * M : short textual representation of a month, three letters
 * MM : full textual representation of a month, such as January or March
 * yy : two digit representation of a year
 * yyyy : full numeric representation of a year, 4 digits
 * t : unix epoch timestamp
 * Z : abbreviated timezone name

### weekStart

Integer.  Default: 0

Day of the week start. 0 (Sunday) to 6 (Saturday)

### startDate

Date.  Default: Beginning of time

The earliest date that may be selected; all earlier dates will be disabled.

### endDate

Date.  Default: End of time

The latest date that may be selected; all later dates will be disabled.

### daysOfWeekDisabled

String, Array.  Default: '', []

Days of the week that should be disabled. Values are 0 (Sunday) to 6 (Saturday). Multiple values should be comma-separated. Example: disable weekends: `'0,6'` or `[0,6]`.

### autoclose

Boolean.  Default: false

Whether or not to close the datetimepicker immediately when a date is selected.

### startView

Number, String.  Default: 2, 'month'

The view that the datetimepicker should show when it is opened.
Accepts values of :
 * 0 or 'hour' for the hour view
 * 1 or 'day' for the day view
 * 2 or 'month' for month view (the default)
 * 3 or 'year' for the 12-month overview
 * 4 or 'decade' for the 10-year overview. Useful for date-of-birth datetimepickers.

### minView

Number, String. Default: 0, 'hour'

The lowest view that the datetimepicker should show.

### maxView

Number, String. Default: 4, 'decade'

The highest view that the datetimepicker should show.

### clearBtn

Boolean. Default: false

If true, displays a "Clear" button at the bottom of the datetimepicker to clear the current selection. Moreover, the datetimepicker will be closed if autoclose is set to true.

### todayBtn

Boolean, "linked".  Default: false

If true or "linked", displays a "Today" button at the bottom of the datetimepicker to select the current date.  If true, the "Today" button will only move the current date into view; if "linked", the current date will also be selected.

### todayHighlight

Boolean.  Default: false

If true, highlights the current date.

### keyboardNavigation

Boolean.  Default: true

Whether or not to allow date navigation by arrow keys.

### language

String.  Default: 'en'

The two-letter code of the language to use for month and day names.  These will also be used as the input's value (and subsequently sent to the server in the case of form submissions).  Currently ships with English ('en'), German ('de'), Brazilian ('br'), and Spanish ('es') translations, but others can be added (see I18N below).  If an unknown language code is given, English will be used.

### forceParse

Boolean.  Default: true

Whether or not to force parsing of the input value when the picker is closed.  That is, when an invalid date is left in the input field by the user, the picker will forcibly parse that value, and set the input's value to the new, valid date, conforming to the given `format`.

### bootcssVer

Number.

You can override the auto-detect functionality of the different bootstrap versions (e.g., 2 or 3) by using this option.

### minuteStep

Number.  Default: 5

The increment used to build the hour view. A button is created for each <code>minuteStep</code> minutes.

### pickerReferer : deprecated

String.  Default: 'default'

The referer element to place the picker for the component implementation. If you want to place the picker just under the input field, just specify <code>input</code>.

### pickerPosition

String. Default: 'bottom-right' (supported values are: 'bottom-right', 'bottom-left', 'top-right', 'top-left')

This option allows to place the picker just under the input field for the component implementation instead of the default position which is at the bottom right of the button.

### viewSelect

Number or String. Default: <code>same as minView</code> (supported values are: 'decade', 'year', 'month', 'day', 'hour')

With this option you can select the view from which the date will be selected. By default it's the last one, however you can choose the first one, so at each click the date will be updated.

### showMeridian

Boolean. Default: false

This option will enable meridian views for day and hour views.

### initialDate

Date or String. Default: new Date()

You can initialize the viewer with a date. By default it's now, so you can specify yesterday or today at midnight ...

### zIndex

Number. Default: undefined

zIndex value is being automatically calculated based on the DOM tree, where we seek the highest value. To skip this process you can set the value manually.

### timezone

String. Default: Clients current timezone abbreviated name

You can allow the viewer to display the date along with the given timezone. Note that this has to be used in conjunction with the `Z` format option. Example below: 


```javascript
$('#date-end').datetimepicker({
        format: 'yyyy-mm-dd hh:ii P Z'
        timezone: 'GMT'
    });
```

![](http://s32.postimg.org/55x4fud05/Screen_Shot_2016_05_17_at_5_43_34_PM.png)


### onRenderYear

This event is fired when a year is rendered inside the datepicker. Should return an array of classes to add to this element. Return ['disabled'] to disable the day from being selected.

```javascript
$('#date')
    .datetimepicker({
        onRenderYear: function(date) {
            //Disable picking dates from any year apart from 2015/2016
            if (date.getFullYear() < 2015 || date.getFullYear() > 2016)
                return ['disabled']
        }
    });
```

### onRenderMonth

This event is fired when a month is rendered inside the datepicker. Should return an array of classes to add to this element. Return ['disabled'] to disable the day from being selected.

```javascript
$('#date')
    .datetimepicker({
        onRenderMonth: function(date) {
            //Disable every other month in the year 2016
            if (date.getUTCMonth() % 2 === 0 && date.getUTCFullYear() === 2016)
                return ['disabled']
        }
    });
```

### onRenderDay

This event is fired when a day is rendered inside the datepicker. Should return an array of classes to add to this element. Return ['disabled'] to disable the day from being selected.

```javascript
$('#date')
    .datetimepicker({
        onRenderDay: function(date) {
            //Disable dates 18-24 of every month
            if (date.getDate() >= 18 && date.getDate() <= 24)
                return ['disabled'];
        }
    });
```

### onRenderHour

This event is fired when a hour is rendered inside the datepicker. Should return an array of classes to add to this element. Return ['disabled'] to disable the day from being selected.

```javascript
$('#date')
    .datetimepicker({
        onRenderHour: function(hour) {
            //Disable any time between 12:00 and 13:59
            if (date.getUTCHours() === 12 || date.getUTCHours() === 13)
                return ['disabled'];
        }
    });
```

### onRenderMinute

This event is fired when a minute is rendered inside the datepicker. Should return an array of classes to add to this element. Return ['disabled'] to disable the day from being selected.

```javascript
$('#date')
    .datetimepicker({
        onRenderMinute: function(minute) {
            //Disable all times between 30 past and 20 to every hour for workdays
            if (date.getDay() !== 0 && date.getDay() !== 6 && date.getUTCMinutes() >= 30 && date.getUTCMinutes() <= 40)
                return ['disabled'];
        }
    });
```

### fontAwesome

Boolean. Default: false

If true, [Font Awesome](http://fontawesome.io/) will be used.

## Markup

Format as component.

```html
<div class="input-append date" id="datetimepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
    <input class="span2" size="16" type="text" value="12-02-2012">
    <span class="add-on"><i class="icon-th"></i></span>
</div>
```

Format as component with reset button to clear the input field.

```html
<div class="input-append date" id="datetimepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
    <input class="span2" size="16" type="text" value="12-02-2012">
    <span class="add-on"><i class="icon-remove"></i></span>
    <span class="add-on"><i class="icon-th"></i></span>
</div>
```

## 方法
### .datetimepicker(options)

Initializes an datetimepicker.

### remove

Arguments: None

Remove the datetimepicker.  Removes attached events, internal attached objects, and
added HTML elements.

    $('#datetimepicker').datetimepicker('remove');

### show

Arguments: None

Show the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('show');
```

### hide

Arguments: None

Hide the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('hide');
```

### update

Arguments:

* currentDate (Date).

Update the datetimepicker with the specified date.

```javascript
$('#datetimepicker').datetimepicker('update', new Date());
```

Omit currentDate to update the datetimepicker with the current input value.

```javascript
$('#datetimepicker').datetimepicker('update');
```

### setStartDate

Arguments:

* startDate (String)

Sets a new lower date limit on the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('setStartDate', '2012-01-01');
```

Omit startDate (or provide an otherwise falsey value) to unset the limit.

```javascript
$('#datetimepicker').datetimepicker('setStartDate');
$('#datetimepicker').datetimepicker('setStartDate', null);
```

### setEndDate

Arguments:

* endDate (String)

Sets a new upper date limit on the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('setEndDate', '2012-12-31');
```

Omit endDate (or provide an otherwise falsey value) to unset the limit.

```javascript
$('#datetimepicker').datetimepicker('setEndDate');
$('#datetimepicker').datetimepicker('setEndDate', null);
```

### setDaysOfWeekDisabled

Arguments:

* daysOfWeekDisabled (String|Array)

Sets the days of week that should be disabled.

```javascript
$('#datetimepicker').datetimepicker('setDaysOfWeekDisabled', [0,6]);
```

Omit daysOfWeekDisabled (or provide an otherwise falsey value) to unset the disabled days.

```javascript
$('#datetimepicker').datetimepicker('setDaysOfWeekDisabled');
$('#datetimepicker').datetimepicker('setDaysOfWeekDisabled', null);
```

### setMinutesDisabled

Arguments:

* minutesDisabled (String|Array)

Sets the minutes that should be disabled.

```javascript
$('#datetimepicker').datetimepicker('setMinutesDisabled', [25,59]);
```

Omit minutesDisabled (or provide an otherwise falsey value) to unset the disabled minutes.

```javascript
$('#datetimepicker').datetimepicker('setMinutesDisabled');
$('#datetimepicker').datetimepicker('setMinutesDisabled', null);
```

### setHoursDisabled

Arguments:

* hoursDisabled (String|Array)

Sets the hours that should be disabled.

```javascript
$('#datetimepicker').datetimepicker('setHoursDisabled', [12,19]);
```

Omit hoursDisabled (or provide an otherwise falsey value) to unset the disabled hours.

```javascript
$('#datetimepicker').datetimepicker('setHoursDisabled');
$('#datetimepicker').datetimepicker('setHoursDisabled', null);
```

### setInitialDate

Arguments:

* setInitialDate (String)

Sets a new inital date on the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('setInitialDate', '2012-12-31');
```

Get the inital date on the datetimepicker.

```javascript
$('#datetimepicker').datetimepicker('getInitialDate');
```

## Events

Datetimepicker class exposes a few events for manipulating the dates.

### show

Fired when the date picker is displayed.

### hide

Fired when the date picker is hidden.

### changeDate

Fired when the date is changed.

```javascript
$('#date-end')
    .datetimepicker()
    .on('changeDate', function(ev){
        if (ev.date.valueOf() < date-start-display.valueOf()){
            ....
        }
    });
```

### changeYear

Fired when the *view* year is changed from decade view.

### changeMonth

Fired when the *view* month is changed from year view.

### outOfRange

Fired when you pick a date before the *startDate* or after the *endDate* or when you specify a date via the method *setDate* or *setUTCDate*..

### next:* / prev:*

Fired when you click the *next* or *previous* arrows. Supports all the differnt view modes ('year', 'month', 'day', 'hour'). For example allows you to bind a callback to 'next:month' or 'prev:month' action.

## Keyboard support

The datetimepicker includes some keyboard navigation:



### up, down, left, right arrow keys

By themselves, left/right will move backward/forward one day, up/down will move back/forward one week.

With the shift key, up/left will move backward one month, down/right will move forward one month.

With the ctrl key, up/left will move backward one year, down/right will move forward oone year.

Shift+ctrl behaves the same as ctrl -- that is, it does not change both month and year simultaneously, only the year.

### escape

The escape key can be used to hide and re-show the datetimepicker; this is necessary if the user wants to manually edit the value.

### enter

When the picker is visible, enter will simply hide it.  When the picker is not visible, enter will have normal effects -- submitting the current form, etc.

## Mouse Wheel View Navigation

In order to make this plugin easier to set different part of date time, mouse wheel has been used to navigate through different views. Scroll up your mouse wheel to navigate to the decade year view. Scroll down will lead to the minute view.

### Dependency

To enalbe this feature. [jQuery Mouse Wheel Plugin](https://github.com/brandonaaron/jquery-mousewheel) must be included before using this feature.

### Options

#### wheelViewModeNavigation

Boolean.  Default: false

Whether or not to enable navigating through different view mode using mouse wheel.

#### wheelViewModeNavigationInverseDirection

Boolean.  Default: false

Whether or not to reverse the direction of scrolling. Default is scroll up to the decade view.

#### wheelViewModeNavigationDelay

Integer.  Default: 100

Time delays between the next respond to the wheel command, it controls the speed between switching in different views. Unit is in million seconds.

#### About viewSelect option

The recommended value for viewSelect option is 4 when this feature is enable. That means you can easily update any the value in every view. This option value is applied in the demo page.

### Feature Demo

A simple [Demo](http://lyonlai.github.io/bootstrap-datetimepicker/demo.html) page is given to show it's simple idea.

## I18N

The plugin supports i18n for the month and weekday names and the `weekStart` option.  The default is English ('en'); other available translations are avilable in the `js/locales/` directory, simply include your desired locale after the plugin.  To add more languages, simply add a key to `$.fn.datetimepicker.dates`, before calling `.datetimepicker()`.  Example:

```javascript
$.fn.datetimepicker.dates['en'] = {
    days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
    daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
    months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
    today: "Today"
};

$.fn.datetimepicker.dates['zh-CN'] = {
    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
    months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    today: "今天",
    suffix: [],
    meridiem: ["上午", "下午"],
    monthTitleFormat:'yyyy年mm月',
    dayTitleFormat : 'yyyy年mm月dd日'
};
```

You can override the default date format in the language configuration with `format` attribute.
Example:

```javascript
$.fn.datetimepicker.dates['pt-BR'] = {
    format: 'dd/mm/yyyy'
};
```

Right-to-left languages may also include `rtl: true` to make the calendar display appropriately.

If your browser (or those of your users) is displaying characters wrong, chances are the browser is loading the javascript file with a non-unicode encoding.  Simply add `charset="UTF-8"` to your `script` tag:

```html
<script type="text/javascript" src="bootstrap-datetimepicker.de.js" charset="UTF-8"></script>
```