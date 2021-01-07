<template>
  <div id="admin-aside-menu">
    <el-menu
      default-active="/school"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
      background-color="rgb(50, 65, 87)"
      text-color="rgb(191, 203, 217)"
      active-text-color="#409EFF"
      :router="true"
      :collapse="collapse"
    >
      <el-submenu class="el-submenu" index="1">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-school"></i>
          <span slot="title">学院管理</span>
        </template>
        <el-menu-item index="/admin/school">
          <span slot="title">学院信息管理</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu class="el-submenu" index="2">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-notebook-2"></i>
          <span slot="title">课程管理</span>
        </template>
        <el-menu-item index="/admin/lesson">
          <span slot="title">课程信息管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/teacherLesson">
          <span slot="title">授课信息管理</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu class="el-submenu" index="3">
        <template class="el-submenu-title" slot="title">
          <i class="el-icon-user-solid"></i>
          <span slot="title">教师管理</span>
        </template>
        <el-menu-item index="/admin/teacher">
          <span slot="title">教师信息管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/teacherAccount">
          <span slot="title">教师账号管理</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "AdminAsideMenu",
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
</style>

<style lang="css">
.el-submenu__title:hover {
  background-color: rgb(40, 52, 70) !important;
}
.el-menu-item:hover {
  background-color: rgb(40, 52, 70) !important;
}
.el-menu-item:focus,
.el-submenu__title:focus {
  background-color: transparent !important;
}
</style>