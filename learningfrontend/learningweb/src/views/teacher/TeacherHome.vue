<template>
  <div id="teacher-home">
    <el-container>
      <el-header style="padding: 0" height="70px">
        <common-nav-bar></common-nav-bar>
      </el-header>
      <el-container>
        <el-aside :width="width"
          ><teacher-aside-menu></teacher-aside-menu
        ></el-aside>
        <el-main>
          <el-tabs
            v-model="activeName"
            type="border-card"
            @tab-click="handleClick"
          >
            <el-page-header @back="goBack"> </el-page-header>
            <el-tab-pane label="授课列表" name="tealessons">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="待答提问" name="neededquestions">
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="课程提问" name="tealessonquestions" disabled>
              <router-view></router-view>
            </el-tab-pane>
            <el-tab-pane label="提问详情" name="teaquestiondetail" disabled>
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
import TeacherAsideMenu from "components/content/teacher/TeacherAsideMenu";
import { mapState} from "vuex";
export default {
  name: "TeacherHome",
  data() {
    return {
      width: "251px",
    };
  },
  components: {
    CommonNavBar,
    TeacherAsideMenu,
  },
  computed: {
    ...mapState(["username", "nickname"]),
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
      this.$router.replace("/teacher/" + tab.name);
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
  created() {
  }
};
</script>

<style scoped>
</style>