<template>
  <div id="student-aside-menu">
    <el-menu
      default-active="/school/lessonList"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
      text-color="rgb(100,100,100)"
      active-text-color="#409EFF"
      :router="true"
      :collapse="collapse"
      style="height: 100vh"
    >
      <el-submenu class="el-submenu" index="1">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-school"></i>
          <span slot="title">全部课程</span>
        </template>
        <el-menu-item index="lessonlist">
          <span slot="title">查看所有课程</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu class="el-submenu" index="2">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-notebook-2"></i>
          <span slot="title">全部留言</span>
        </template>
        <el-menu-item index="allquestions">
          <span slot="title">查看全部留言</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu class="el-submenu" index="3">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-user-solid"></i>
          <span slot="title">我的提问</span>
        </template>
        <el-menu-item index="myquestions">
          <span slot="title">查看我的提问</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "StudentAsideMenu",
  data() {
    return {
      collapse: false,
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
  created() {
    this.$bus.$on("collapse", (msg) => {
      this.collapse = msg;
    });
  },
};
</script>

<style scoped>
.el-menu-vertical:not(.el-menu--collapse) {
  width: 250px;
  min-height: 400px;
}
.el-menu-vertical > ul {
  height: 100%;
}
/* .el-menu {
  height: 100%;
} */
</style>

<style lang="css">
/* .el-submenu__title:hover {
  background-color: rgb(40, 52, 70) !important;
}
.el-menu-item:hover {
  background-color: rgb(40, 52, 70) !important;
} */
#student-aside-menu .el-menu-item:focus,
#student-aside-menu .el-submenu__title:focus {
  background-color: transparent !important;
}
#student-aside-menu .el-menu-vertical {
  border-right: none !important;
  height: 100% !important;
}
</style>