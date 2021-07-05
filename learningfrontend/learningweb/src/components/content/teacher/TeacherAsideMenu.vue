<template>
  <div id="teacher-aside-menu">
    <el-menu
      default-active="/teacher/tealessons"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
      text-color="rgb(100,100,100)"
      active-text-color="#409EFF"
      :router="true"
      :collapse="collapse"
    >
      <el-submenu class="el-submenu" index="1">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-school"></i>
          <span slot="title">全部授课</span>
        </template>
        <el-menu-item index="tealessons">
          <span slot="title">授课列表</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu class="el-submenu" index="2">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-notebook-2"></i>
          <span slot="title">待答提问</span>
        </template>
        <el-menu-item index="neededquestions">
          <span slot="title">待答提问</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "TeacherAsideMenu",
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
.el-menu-vertical {
  height: calc(100vh - 71px);
}
.el-menu-vertical:not(.el-menu--collapse) {
  width: 250px;
  min-height: 400px;
}
.el-menu-vertical > ul {
  height: 100%;
}
.el-menu {
  height: 100%;
}
</style>

<style lang="css">
/* .el-submenu__title:hover {
  background-color: rgb(40, 52, 70) !important;
}
.el-menu-item:hover {
  background-color: rgb(40, 52, 70) !important;
} */
.el-menu-item:focus,
.el-submenu__title:focus {
  background-color: transparent !important;
}
</style>