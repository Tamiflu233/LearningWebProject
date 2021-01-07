<template>
  <div id="student-home">
    <el-container>
      <el-header style="padding: 0" height="70px">
        <common-nav-bar></common-nav-bar>
      </el-header>
      <el-container>
        <el-aside :width="width"
          ><student-aside-menu></student-aside-menu
        ></el-aside>
        <el-main>
          <el-tabs
            v-model="activeName"
            type="border-card"
            @tab-click="handleClick"
          >
            <el-page-header @back="goBack"> </el-page-header>
            <el-tab-pane label="查看所有课程" name="lessonlist">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="查看全部留言" name="allquestions">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="查看我的提问" name="myquestions">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="课程信息" name="lessonquestions" disabled>
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="提问详情" name="questiondetail" disabled>
              <router-view></router-view>
            </el-tab-pane>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CommonNavBar from "components/content/common/CommonNavBar";
import StudentAsideMenu from "components/content/student/StudentAsideMenu";
export default {
  name: "StudentHome",
  data() {
    return {
      width: "251px",
    };
  },
  components: {
    CommonNavBar,
    StudentAsideMenu,
  },
  computed: {
    activeName: {
      get() {
        this.pathName = this.$route.path;
        this.pathName = this.pathName.substring(9, this.pathName.length);
        return this.pathName;
      },
      set(value) {
        this.pathName = value;
      },
    },
  },
  methods: {
    handleClick(tab) {
      this.$router.replace("/student/" + tab.name);
    },
    goBack() {
      this.$router.go(-1);
    },
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色
    document
      .querySelector("body")
      .setAttribute("style", "background-color:rgb(242,244,247) ");

    next();
  },

  beforeRouteLeave(to, from, next) {
    // 去除背景色
    document.querySelector("body").setAttribute("style", "");

    next();
  },
};
</script>

<style scoped>
</style>