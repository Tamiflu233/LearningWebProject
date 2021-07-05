<template>
  <div id="common-nav-bar">
    <el-menu
      background-color="#F4606C"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      width="100%"
      style="height: 70px"
    >
      <!-- 折叠按钮 -->
      <div
        class="collapse-btn"
        :class="{ 'is-hover': isHover }"
        @click="collapseChage"
        @mouseenter="mouseEnter"
        @mouseleave="mouseLeave"
      >
        <i class="el-icon-menu"></i>
      </div>
      <div class="logo">教学资源共享与答疑系统</div>
      <div class="header-right">
        <div class="header-user-con">
          <!-- 消息中心 -->
          <div class="btn-bell">
            <el-tooltip
              effect="dark"
              :content="message ? `有${message}条未读消息` : `消息中心`"
              placement="bottom"
            >
              <router-link to="/school">
                <i class="el-icon-bell"></i>
              </router-link>
            </el-tooltip>
            <span class="btn-bell-badge" v-if="message"></span>
          </div>
          <!-- 用户头像 -->
          <div class="user-avator">
            <img :src="avatarUrl" />
          </div>
          <!-- 用户名下拉菜单 -->
          <el-dropdown
            class="user-name"
            trigger="click"
            @command="handleCommand"
          >
            <span class="el-dropdown-link">
              {{ nickname }} <i class="el-icon-caret-bottom"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item divided command="changePWD"
                >修改密码</el-dropdown-item
              >
              <el-dropdown-item divided command="loginout"
                >退出登录</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </el-menu>
  </div>
</template>

<script>
import avatar from "assets/img/user_avatar.jpg";
import { mapState } from "vuex";
export default {
  name: "CommonNavBar",
  data() {
    return {
      message: 3,
      avatarUrl: avatar,
      username: "Tamiflu",
      isHover: false,
      collapse: true,
    };
  },
  computed: {
    ...mapState(["nickname"]),
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleCommand(command) {
      if (command == "loginout") {
        // this.$common.removeSessionStorage("token");
        // this.$router.push("/login");
        this.$message({
          type: "success",
          message: "成功退出登录!",
        });
        // 清除token并返回登录界面
        localStorage.removeItem("Authorization");
        this.$router.push("/login");
      } else if (command == "changePWD") {
        this.$message({
          type: "success",
          message: "前往修改密码!",
        });
      }
    },
    mouseEnter() {
      this.isHover = true;
    },
    mouseLeave() {
      this.isHover = false;
    },
    collapseChage() {
      // console.log("展开aside");
      this.collapse = !this.collapse;
      this.$bus.$emit("collapse", this.collapse);
    },
  },
};
</script>

<style scoped>
#common-nav-bar {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
  outline: none !important;
}
.is-hover {
  background-color: rgb(231, 107, 124);
}
#common-nav-bar .logo {
  float: left;
  width: 250px;
  line-height: 70px;
  outline: none !important;
}
.header-right {
  float: right;
  outline: none !important;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-bell {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #4cafec;
  color: rgb(255, 255, 255);
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}
</style>