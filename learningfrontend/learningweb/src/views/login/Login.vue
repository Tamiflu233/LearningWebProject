<template>
  <div id="login">
    <el-card class="login-form-layout">
      <el-form
        autocomplete="on"
        :model="loginForm"
        ref="loginForm"
        label-position="left"
        :rules="rules"
        label-suffix=" : "
      >
        <div class="siteicon"></div>
        <h2 class="login-title color-main">教学资源共享与答疑系统</h2>
        <el-form-item prop="username">
          <el-input
            clearable
            name="username"
            type="text"
            v-model="loginForm.username"
            autocomplete="on"
            placeholder="请输入用户名"
          >
            <i slot="prefix" class="el-input__icon el-icon-user-solid"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            clearable
            name="password"
            :type="pwdType"
            @keyup.enter.native="handleLogin"
            v-model="loginForm.password"
            autocomplete="on"
            placeholder="请输入密码"
          >
            <i slot="prefix" class="el-input__icon el-icon-lock"></i>
            <i slot="suffix" class="el-icon-view" @click="showPwd"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="身份" style="margin-bottom: 10px">
          <el-radio-group
            v-model="loginForm.userType"
            size="small"
            style="margin-left: 50px"
          >
            <el-radio-button label="学生"></el-radio-button>
            <el-radio-button label="教师"></el-radio-button>
            <el-radio-button label="管理员"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <div class="register">
          <el-link class="register-link" type="primary" href="/register"
            >注册</el-link
          >
        </div>
        <el-form-item style="margin-bottom: 30px">
          <el-button
            style="width: 100%"
            type="primary"
            :loading="loading"
            @click.native.prevent="handleLogin"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { checkLogin, getNickname } from "network/login/Login";
import { mapMutations } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      userToken: "",
      loginForm: {
        username: "",
        password: "",
        userType: "学生",
      },
      loading: false,
      pwdType: "password",
      rules: {
        username: [
          { required: true, message: "请输入用户名!!", trigger: "blur" },
          {
            pattern: /^[_a-zA-Z0-9]+$/,
            message: "用户名只能是英文/数字/下划线!!",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请填写密码!!", trigger: "blur" },
          { min: 4, message: "密码长度至少4位!!", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    ...mapMutations(["changeLogin", "saveUsername", "saveNickname"]),
    showPwd() {
      if (this.pwdType === "password") {
        this.pwdType = "";
      } else {
        this.pwdType = "password";
      }
    },
    saveLoginNick() {
      getNickname(this.loginForm.username)
        .then((res) => {
          this.saveNickname({ nickname: res.data });
        })
        .catch((err) => {
          console.log("获取昵称失败！");
        });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          checkLogin(this.loginForm)
            .then((res) => {
              console.log(this.loginForm.userType);
              if (res.code == 200) {
                let msgs = res.msg.split("-");
                if (msgs[0] == "success") {
                  this.$message({
                    type: "success",
                    message: msgs[1],
                  });
                  // token加入vuex或localStorage
                  this.userToken = res.data;
                  // console.log("token:" + this.userToken);
                  this.changeLogin({ Authorization: this.userToken });
                  // 获取昵称
                  this.saveLoginNick();
                  this.saveUsername({ username: this.loginForm.username });
                  if (this.loginForm.userType === "学生") {
                    this.$router.push("/student");
                  } else if (this.loginForm.userType === "教师") {
                    this.$router.push("/teacher");
                  } else if (this.loginForm.userType === "管理员") {
                    this.$router.push("/admin");
                  }
                  this.loginForm = { userType: "学生" };
                } else {
                  this.$message({
                    type: "warning",
                    message: msgs[1],
                  });
                  this.loginForm = { userType: "学生" };
                }
              } else {
                this.$message.error(res.msg);
                this.loginForm = { userType: "学生" };
              }
            })
            .catch((err) => {
              this.$message.error("404 登录出错,请稍后再试！");
            });
        } else {
          // eslint-disable-next-line no-console
          this.$message.error("账号信息填写有误，请正确填写！");
          return false;
        }
      });
    },
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色
    document
      .querySelector("body")
      .setAttribute(
        "style",
        "background:url('https://cdn.jsDelivr.net/gh/Tamiflu233/AssetsRepo/img/bg5.jpg');background-size:cover;"
      );

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
@font-face {
  font-family: "Candyhome"; /* 字体名自定义即可 */
  src: url("https://cdn.jsDelivr.net/gh/Tamiflu233/cdn/fonts/Candy.ttf"); /* 字体文件路径 */
  font-display: swap;
}
.login-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 400px;
  margin: 150px auto;
  opacity: 0.9;
  border-top: 10px solid #409eff;
}
.siteicon {
  width: 96px;
  height: 96px;
  background: url("https://cdn.jsDelivr.net/gh/Tamiflu233/AssetsRepo/img/android-chrome-96x96.png");
  margin: 0 auto;
}
.login-title {
  font-family: "Candyhome", sans-serif;
  text-align: center;
}

.login-center-layout {
  background: #409eff;
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  margin-top: 200px;
}

.register {
  text-align: right;
  margin-bottom: 20px;
}
.register-link {
  margin-right: 10px;
  font-size: 16px;
}
</style>