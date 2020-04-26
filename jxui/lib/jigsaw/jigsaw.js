/*
 * Copyright (c) XCI. All rights reserved.
 * Licensed under the Apache License, Version 2.0. See License.txt in the project root for license information.
 */

!function (n) {
    var e = {};

    function t(r) {
        if (e[r]) return e[r].exports;
        var i = e[r] = {i: r, l: !1, exports: {}};
        return n[r].call(i.exports, i, i.exports, t), i.l = !0, i.exports
    }

    t.m = n, t.c = e, t.d = function (n, e, r) {
        t.o(n, e) || Object.defineProperty(n, e, {enumerable: !0, get: r})
    }, t.r = function (n) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(n, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(n, "__esModule", {value: !0})
    }, t.t = function (n, e) {
        if (1 & e && (n = t(n)), 8 & e) return n;
        if (4 & e && "object" == typeof n && n && n.__esModule) return n;
        var r = Object.create(null);
        if (t.r(r), Object.defineProperty(r, "default", {
            enumerable: !0,
            value: n
        }), 2 & e && "string" != typeof n) for (var i in n) t.d(r, i, function (e) {
            return n[e]
        }.bind(null, i));
        return r
    }, t.n = function (n) {
        var e = n && n.__esModule ? function () {
            return n.default
        } : function () {
            return n
        };
        return t.d(e, "a", e), e
    }, t.o = function (n, e) {
        return Object.prototype.hasOwnProperty.call(n, e)
    }, t.p = "", t(t.s = 0)
}([function (n, e, t) {
    "use strict";
    t(1), t(6)
}, function (n, e, t) {
    var r = t(2);
    "string" == typeof r && (r = [[n.i, r, ""]]);
    var i = {hmr: !0, transform: void 0, insertInto: void 0};
    t(4)(r, i);
    r.locals && (n.exports = r.locals)
}, function (n, e, t) {
    (n.exports = t(3)(!1)).push([n.i, ".block {\n  position: absolute;\n  left: 0;\n  top: 0;\n}\n\n.sliderContainer {\n  position: relative;\n  text-align: center;\n  width: 310px;\n  height: 40px;\n  line-height: 40px;\n  margin-top: 15px;\n  background: #f7f9fa;\n  color: #45494c;\n  border: 1px solid #e4e7eb;\n}\n\n.sliderContainer_active .slider {\n  height: 38px;\n  top: -1px;\n  border: 1px solid #1991FA;\n}\n\n.sliderContainer_active .sliderMask {\n  height: 38px;\n  border-width: 1px;\n}\n\n.sliderContainer_success .slider {\n  height: 38px;\n  top: -1px;\n  border: 1px solid #52CCBA;\n  background-color: #52CCBA !important;\n}\n\n.sliderContainer_success .sliderMask {\n  height: 38px;\n  border: 1px solid #52CCBA;\n  background-color: #D2F4EF;\n}\n\n.sliderContainer_success .sliderIcon {\n  background-position: 0 0 !important;\n}\n\n.sliderContainer_fail .slider {\n  height: 38px;\n  top: -1px;\n  border: 1px solid #f57a7a;\n  background-color: #f57a7a !important;\n}\n\n.sliderContainer_fail .sliderMask {\n  height: 38px;\n  border: 1px solid #f57a7a;\n  background-color: #fce1e1;\n}\n\n.sliderContainer_fail .sliderIcon {\n  top: 14px;\n  background-position: 0 -82px !important;\n}\n.sliderContainer_active .sliderText, .sliderContainer_success .sliderText, .sliderContainer_fail .sliderText {\n  display: none;\n}\n\n.sliderMask {\n  position: absolute;\n  left: 0;\n  top: 0;\n  height: 40px;\n  border: 0 solid #1991FA;\n  background: #D1E9FE;\n}\n\n.slider {\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 40px;\n  height: 40px;\n  background: #fff;\n  box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  transition: background .2s linear;\n}\n\n.slider:hover {\n  background: #1991FA;\n}\n\n.slider:hover .sliderIcon {\n  background-position: 0 -13px;\n}\n\n.sliderIcon {\n  position: absolute;\n  top: 15px;\n  left: 13px;\n  width: 14px;\n  height: 12px;\n  background: url(http://cstaticdun.126.net//2.6.3/images/icon_light.f13cff3.png) 0 -26px;\n  background-size: 34px 471px;\n}\n\n.refreshIcon {\n  position: absolute;\n  right: 0;\n  top: 0;\n  width: 34px;\n  height: 34px;\n  cursor: pointer;\n  background: url(http://cstaticdun.126.net//2.6.3/images/icon_light.f13cff3.png) 0 -437px;\n  background-size: 34px 471px;\n}\n", ""])
}, function (n, e, t) {
    "use strict";
    n.exports = function (n) {
        var e = [];
        return e.toString = function () {
            return this.map(function (e) {
                var t = function (n, e) {
                    var t = n[1] || "", r = n[3];
                    if (!r) return t;
                    if (e && "function" == typeof btoa) {
                        var i = (s = r, "/*# sourceMappingURL=data:application/json;charset=utf-8;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(s)))) + " */"),
                            o = r.sources.map(function (n) {
                                return "/*# sourceURL=" + r.sourceRoot + n + " */"
                            });
                        return [t].concat(o).concat([i]).join("\n")
                    }
                    var s;
                    return [t].join("\n")
                }(e, n);
                return e[2] ? "@media " + e[2] + "{" + t + "}" : t
            }).join("")
        }, e.i = function (n, t) {
            "string" == typeof n && (n = [[null, n, ""]]);
            for (var r = {}, i = 0; i < this.length; i++) {
                var o = this[i][0];
                "number" == typeof o && (r[o] = !0)
            }
            for (i = 0; i < n.length; i++) {
                var s = n[i];
                "number" == typeof s[0] && r[s[0]] || (t && !s[2] ? s[2] = t : t && (s[2] = "(" + s[2] + ") and (" + t + ")"), e.push(s))
            }
        }, e
    }
}, function (n, e, t) {
    var r, i, o = {}, s = (r = function () {
        return window && document && document.all && !window.atob
    }, function () {
        return void 0 === i && (i = r.apply(this, arguments)), i
    }), a = function (n) {
        var e = {};
        return function (n, t) {
            if ("function" == typeof n) return n();
            if (void 0 === e[n]) {
                var r = function (n, e) {
                    return e ? e.querySelector(n) : document.querySelector(n)
                }.call(this, n, t);
                if (window.HTMLIFrameElement && r instanceof window.HTMLIFrameElement) try {
                    r = r.contentDocument.head
                } catch (n) {
                    r = null
                }
                e[n] = r
            }
            return e[n]
        }
    }(), c = null, l = 0, u = [], d = t(5);

    function f(n, e) {
        for (var t = 0; t < n.length; t++) {
            var r = n[t], i = o[r.id];
            if (i) {
                i.refs++;
                for (var s = 0; s < i.parts.length; s++) i.parts[s](r.parts[s]);
                for (; s < r.parts.length; s++) i.parts.push(m(r.parts[s], e))
            } else {
                var a = [];
                for (s = 0; s < r.parts.length; s++) a.push(m(r.parts[s], e));
                o[r.id] = {id: r.id, refs: 1, parts: a}
            }
        }
    }

    function p(n, e) {
        for (var t = [], r = {}, i = 0; i < n.length; i++) {
            var o = n[i], s = e.base ? o[0] + e.base : o[0], a = {css: o[1], media: o[2], sourceMap: o[3]};
            r[s] ? r[s].parts.push(a) : t.push(r[s] = {id: s, parts: [a]})
        }
        return t
    }

    function h(n, e) {
        var t = a(n.insertInto);
        if (!t) throw new Error("Couldn't find a style target. This probably means that the value for the 'insertInto' parameter is invalid.");
        var r = u[u.length - 1];
        if ("top" === n.insertAt) r ? r.nextSibling ? t.insertBefore(e, r.nextSibling) : t.appendChild(e) : t.insertBefore(e, t.firstChild), u.push(e); else if ("bottom" === n.insertAt) t.appendChild(e); else {
            if ("object" != typeof n.insertAt || !n.insertAt.before) throw new Error("[Style Loader]\n\n Invalid value for parameter 'insertAt' ('options.insertAt') found.\n Must be 'top', 'bottom', or Object.\n (https://github.com/webpack-contrib/style-loader#insertat)\n");
            var i = a(n.insertAt.before, t);
            t.insertBefore(e, i)
        }
    }

    function v(n) {
        if (null === n.parentNode) return !1;
        n.parentNode.removeChild(n);
        var e = u.indexOf(n);
        e >= 0 && u.splice(e, 1)
    }

    function b(n) {
        var e = document.createElement("style");
        if (void 0 === n.attrs.type && (n.attrs.type = "text/css"), void 0 === n.attrs.nonce) {
            var r = function () {
                0;
                return t.nc
            }();
            r && (n.attrs.nonce = r)
        }
        return g(e, n.attrs), h(n, e), e
    }

    function g(n, e) {
        Object.keys(e).forEach(function (t) {
            n.setAttribute(t, e[t])
        })
    }

    function m(n, e) {
        var t, r, i, o;
        if (e.transform && n.css) {
            if (!(o = "function" == typeof e.transform ? e.transform(n.css) : e.transform.default(n.css))) return function () {
            };
            n.css = o
        }
        if (e.singleton) {
            var s = l++;
            t = c || (c = b(e)), r = C.bind(null, t, s, !1), i = C.bind(null, t, s, !0)
        } else n.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (t = function (n) {
            var e = document.createElement("link");
            return void 0 === n.attrs.type && (n.attrs.type = "text/css"), n.attrs.rel = "stylesheet", g(e, n.attrs), h(n, e), e
        }(e), r = function (n, e, t) {
            var r = t.css, i = t.sourceMap, o = void 0 === e.convertToAbsoluteUrls && i;
            (e.convertToAbsoluteUrls || o) && (r = d(r));
            i && (r += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(i)))) + " */");
            var s = new Blob([r], {type: "text/css"}), a = n.href;
            n.href = URL.createObjectURL(s), a && URL.revokeObjectURL(a)
        }.bind(null, t, e), i = function () {
            v(t), t.href && URL.revokeObjectURL(t.href)
        }) : (t = b(e), r = function (n, e) {
            var t = e.css, r = e.media;
            r && n.setAttribute("media", r);
            if (n.styleSheet) n.styleSheet.cssText = t; else {
                for (; n.firstChild;) n.removeChild(n.firstChild);
                n.appendChild(document.createTextNode(t))
            }
        }.bind(null, t), i = function () {
            v(t)
        });
        return r(n), function (e) {
            if (e) {
                if (e.css === n.css && e.media === n.media && e.sourceMap === n.sourceMap) return;
                r(n = e)
            } else i()
        }
    }

    n.exports = function (n, e) {
        if ("undefined" != typeof DEBUG && DEBUG && "object" != typeof document) throw new Error("The style-loader cannot be used in a non-browser environment");
        (e = e || {}).attrs = "object" == typeof e.attrs ? e.attrs : {}, e.singleton || "boolean" == typeof e.singleton || (e.singleton = s()), e.insertInto || (e.insertInto = "head"), e.insertAt || (e.insertAt = "bottom");
        var t = p(n, e);
        return f(t, e), function (n) {
            for (var r = [], i = 0; i < t.length; i++) {
                var s = t[i];
                (a = o[s.id]).refs--, r.push(a)
            }
            n && f(p(n, e), e);
            for (i = 0; i < r.length; i++) {
                var a;
                if (0 === (a = r[i]).refs) {
                    for (var c = 0; c < a.parts.length; c++) a.parts[c]();
                    delete o[a.id]
                }
            }
        }
    };
    var x, y = (x = [], function (n, e) {
        return x[n] = e, x.filter(Boolean).join("\n")
    });

    function C(n, e, t, r) {
        var i = t ? "" : r.css;
        if (n.styleSheet) n.styleSheet.cssText = y(e, i); else {
            var o = document.createTextNode(i), s = n.childNodes;
            s[e] && n.removeChild(s[e]), s.length ? n.insertBefore(o, s[e]) : n.appendChild(o)
        }
    }
}, function (n, e, t) {
    "use strict";
    n.exports = function (n) {
        var e = "undefined" != typeof window && window.location;
        if (!e) throw new Error("fixUrls requires window.location");
        if (!n || "string" != typeof n) return n;
        var t = e.protocol + "//" + e.host, r = t + e.pathname.replace(/\/[^\/]*$/, "/");
        return n.replace(/url\s*\(((?:[^)(]|\((?:[^)(]+|\([^)(]*\))*\))*)\)/gi, function (n, e) {
            var i, o = e.trim().replace(/^"(.*)"$/, function (n, e) {
                return e
            }).replace(/^'(.*)'$/, function (n, e) {
                return e
            });
            return /^(#|data:|http:\/\/|https:\/\/|file:\/\/\/|\s*$)/i.test(o) ? n : (i = 0 === o.indexOf("//") ? o : 0 === o.indexOf("/") ? t + o : r + o.replace(/^\.\//, ""), "url(" + JSON.stringify(i) + ")")
        })
    }
}, function (n, e, t) {
    "use strict";
    var r = function () {
        function n(n, e) {
            for (var t = 0; t < e.length; t++) {
                var r = e[t];
                r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(n, r.key, r)
            }
        }

        return function (e, t, r) {
            return t && n(e.prototype, t), r && n(e, r), e
        }
    }();
    !function (n) {
        var e = 42, t = 9, i = 310, o = 155, s = Math.PI, a = e + 2 * t + 3;

        function c(n, e) {
            return Math.round(Math.random() * (e - n) + n)
        }

        function l(n, e) {
            var t = document.createElement(n);
            return t.className = e, t
        }

        function u(n, e) {
            n.classList.add(e)
        }

        function d() {
            return jx.libDir+'jigsaw/verify/' + c(1, 10) + ".jpg"
        }

        function f(n, r, i, o) {
            n.beginPath(), n.moveTo(r, i), n.arc(r + e / 2, i - t + 2, t, .72 * s, 2.26 * s), n.lineTo(r + e, i), n.arc(r + e + t - 2, i + e / 2, t, 1.21 * s, 2.78 * s), n.lineTo(r + e, i + e), n.lineTo(r, i + e), n.arc(r + t - 2, i + e / 2, t + .4, 2.76 * s, 1.24 * s, !0), n.lineTo(r, i), n.lineWidth = 2, n.fillStyle = "rgba(255, 255, 255, 0.7)", n.strokeStyle = "rgba(255, 255, 255, 0.7)", n.stroke(), n[o](), n.globalCompositeOperation = "overlay"
        }

        function p(n, e) {
            return n + e
        }

        function h(n) {
            return n * n
        }

        var v = function () {
            function n(e) {
                var t = e.el, r = e.onSuccess, i = e.onFail, o = e.onRefresh;
                !function (n, e) {
                    if (!(n instanceof e)) throw new TypeError("Cannot call a class as a function")
                }(this, n), t.style.position = t.style.position || "relative", this.el = t, this.onSuccess = r, this.onFail = i, this.onRefresh = o
            }

            return r(n, [{
                key: "init", value: function () {
                    return this.initDOM(), this.initImg(), this.bindEvents(), this
                }
            }, {
                key: "initDOM", value: function () {
                    var n = function (n, e) {
                            var t = l("canvas");
                            return t.width = n, t.height = e, t
                        }(i, o), e = n.cloneNode(!0), t = l("div", "sliderContainer"), r = l("div", "refreshIcon"), s = l("div", "sliderMask"),
                        a = l("div", "slider"), c = l("span", "sliderIcon"), u = l("span", "sliderText");
                    e.className = "block", u.innerHTML = "向右滑动填充拼图";
                    var d = this.el;
                    d.appendChild(n), d.appendChild(r), d.appendChild(e), a.appendChild(c), s.appendChild(a), t.appendChild(s), t.appendChild(u), d.appendChild(t), Object.assign(this, {
                        canvas: n,
                        block: e,
                        sliderContainer: t,
                        refreshIcon: r,
                        slider: a,
                        sliderMask: s,
                        sliderIcon: c,
                        text: u,
                        canvasCtx: n.getContext("2d"),
                        blockCtx: e.getContext("2d")
                    })
                }
            }, {
                key: "initImg", value: function () {
                    var n = this, e = function (n) {
                        var e = l("img");
                        return e.crossOrigin = "Anonymous", e.onload = n, e.onerror = function () {
                            e.src = d()
                        }, e.src = d(), e
                    }(function () {
                        n.draw(), n.canvasCtx.drawImage(e, 0, 0, i, o), n.blockCtx.drawImage(e, 0, 0, i, o);
                        var r = n.y - 2 * t - 1, s = n.blockCtx.getImageData(n.x - 3, r, a, a);
                        n.block.width = a, n.blockCtx.putImageData(s, 0, r)
                    });
                    this.img = e
                }
            }, {
                key: "draw", value: function () {
                    this.x = c(a + 10, i - (a + 10)), this.y = c(10 + 2 * t, o - (a + 10)), f(this.canvasCtx, this.x, this.y, "fill"), f(this.blockCtx, this.x, this.y, "clip")
                }
            }, {
                key: "clean", value: function () {
                    this.canvasCtx.clearRect(0, 0, i, o), this.blockCtx.clearRect(0, 0, i, o), this.block.width = i
                }
            }, {
                key: "bindEvents", value: function () {
                    var n = this;
                    this.el.onselectstart = function () {
                        return !1
                    }, this.refreshIcon.onclick = function () {
                        n.reset(), "function" == typeof n.onRefresh && n.onRefresh()
                    };
                    var e = void 0, t = void 0, r = [], o = !1, s = function (n) {
                        e = n.clientX || n.touches[0].clientX, t = n.clientY || n.touches[0].clientY, o = !0
                    }, a = function (s) {
                        if (!o) return !1;
                        var a = s.clientX || s.touches[0].clientX, c = s.clientY || s.touches[0].clientY, l = a - e, d = c - t;
                        if (l < 0 || l + 38 >= i) return !1;
                        n.slider.style.left = l + "px";
                        var f = 250 / 270 * l;
                        n.block.style.left = f + "px", u(n.sliderContainer, "sliderContainer_active"), n.sliderMask.style.width = l + "px", r.push(d)
                    }, c = function (t) {
                        if (!o) return !1;
                        var i, s;
                        if (o = !1, (t.clientX || t.changedTouches[0].clientX) == e) return !1;
                        i = n.sliderContainer, s = "sliderContainer_active", i.classList.remove(s), n.trail = r;
                        var a = n.verify(), c = a.spliced, l = a.verified;
                        c ? l ? (u(n.sliderContainer, "sliderContainer_success"), "function" == typeof n.onSuccess && n.onSuccess()) : (u(n.sliderContainer, "sliderContainer_fail"), n.text.innerHTML = "再试一次", n.reset()) : (u(n.sliderContainer, "sliderContainer_fail"), "function" == typeof n.onFail && n.onFail(), setTimeout(function () {
                            n.reset()
                        }, 1e3))
                    };
                    this.slider.addEventListener("mousedown", s), this.slider.addEventListener("touchstart", s), document.addEventListener("mousemove", a), document.addEventListener("touchmove", a), document.addEventListener("mouseup", c), document.addEventListener("touchend", c)
                }
            }, {
                key: "verify", value: function () {
                    var n = this.trail, e = n.reduce(p) / n.length, t = n.map(function (n) {
                        return n - e
                    }), r = Math.sqrt(t.map(h).reduce(p) / n.length), i = parseInt(this.block.style.left);
                    return {spliced: Math.abs(i - this.x) < 10, verified: 0 !== r}
                }
            }, {
                key: "reset", value: function () {
                    this.sliderContainer.className = "sliderContainer", this.slider.style.left = 0, this.block.style.left = 0, this.sliderMask.style.width = 0, this.clean(), this.img.src = d()
                }
            }]), n
        }();
        n.jigsaw = {
            init: function (n) {
                return new v(n).init()
            }
        }
    }(window)
}]);
