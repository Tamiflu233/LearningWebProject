<template>
  <div id="admin">
    <el-container>
      <el-header style="padding: 0" height="70px">
        <admin-nav-bar></admin-nav-bar>
      </el-header>
      <el-container>
        <el-aside :width="width"
          ><admin-aside-menu></admin-aside-menu
        ></el-aside>
        <el-main class="homemain">
          <el-tabs
            v-model="activeName"
            type="border-card"
            @tab-click="handleClick"
          >
            <el-tab-pane label="学院信息管理" name="school">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="课程信息管理" name="lesson">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="授课信息管理" name="teacherLesson">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="教师信息管理" name="teacher">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="教师账号管理" name="teacherAccount">
              <router-view></router-view>
            </el-tab-pane>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import AdminNavBar from "components/content/admin/AdminNavBar";
import AdminAsideMenu from "components/content/admin/AdminAsideMenu";
export default {
  name: "Admin",
  components: {
    AdminNavBar,
    AdminAsideMenu,
  },
  data() {
    return {
      pathName: "school",
      width: "251px",
    };
  },
  computed: {
    activeName: {
      get() {
        this.pathName = this.$route.path;
        this.pathName = this.pathName.substring(7, this.pathName.length);
        return this.pathName;
      },
      set(value) {
        this.pathName = value;
      },
    },
  },
  methods: {
    handleClick(tab) {
      this.$router.replace("/admin/" + tab.name);
    },
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色
    document
      .querySelector("body")
      .setAttribute(
        "style",
        "background:url('https://cdn.jsDelivr.net/gh/Tamiflu233/cdn/img/geometric-leaves.png');"
      );

    next();
  },
};
</script>

<style scoped>
#admin .homemain {
  opacity: 0.9;
}
</style>