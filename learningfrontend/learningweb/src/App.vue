<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import xx from "assets/js/love.js";
import nest from "assets/js/nest.js";

export default {
  name: "App",
  components: {},
};
</script>

<style>
@import "./assets/css/normalize.css";
/* 鼠标图标 */

* {
  cursor: url(https://cdn.jsdelivr.net/npm/kang-static/Hexo/img/default.cur),
    auto !important;
}
a,
button,
img {
  cursor: url(https://cdn.jsdelivr.net/npm/kang-static/Hexo/img/pointer.cur),
    default !important;
}
.el-link--inner {
  cursor: url(https://cdn.jsdelivr.net/npm/kang-static/Hexo/img/pointer.cur),
    default !important;
}
</style>
