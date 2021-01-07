<template>
  <div id="register">
    <el-card class="register-form-layout">
      <el-form
        autocomplete="on"
        :model="registerForm"
        ref="registerForm"
        label-position="left"
        :rules="rules"
        label-suffix=" : "
      >
        <h2 class="register-title color-main">注册</h2>
        <el-form-item prop="nickname">
          <el-input
            clearable
            name="nickname"
            type="text"
            v-model="registerForm.nickname"
            autocomplete="off"
            placeholder="请输入昵称"
          >
            <i slot="prefix" class="el-input__icon el-icon-star-off"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            clearable
            name="username"
            type="text"
            v-model="registerForm.username"
            autocomplete="off"
            placeholder="请输入用户名"
          >
            <i slot="prefix" class="el-input__icon el-icon-user-solid"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            clearable
            name="password"
            v-model="registerForm.password"
            autocomplete="off"
            placeholder="请输入密码"
          >
            <i slot="prefix" class="el-input__icon el-icon-lock"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="checkpass">
          <el-input
            clearable
            name="checkpass"
            v-model="registerForm.checkpass"
            autocomplete="off"
            placeholder="确认密码"
          >
            <i slot="prefix" class="el-input__icon el-icon-check"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="stuId" maxlength="10" show-word-limit>
          <el-input
            clearable
            name="stuId"
            v-model="registerForm.stuId"
            autocomplete="off"
            placeholder="请输入学号"
          >
            <i slot="prefix" class="el-input__icon el-icon-document"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="name">
          <el-input
            clearable
            name="name"
            v-model="registerForm.name"
            autocomplete="off"
            placeholder="请输入姓名"
          >
            <i slot="prefix" class="el-input__icon el-icon-document"></i>
          </el-input>
        </el-form-item>

        <el-form-item label="所属学院" prop="schoolId">
          <el-select
            v-model="registerForm.schoolId"
            filterable
            placeholder="请选择学院"
            clearable
            style="float: right"
          >
            <el-option
              v-for="item in schoolOptions"
              :key="item.schoolId"
              :label="item.schoolId + '-' + item.schoolName"
              :value="item.schoolId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group
            v-model="registerForm.sex"
            size="small"
            style="float: right"
          >
            <el-radio-button label="男"></el-radio-button>
            <el-radio-button label="女"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item style="margin-bottom: 30px">
          <el-button
            style="width: 100%"
            type="primary"
            :loading="loading"
            @click.native.prevent="handleRegister"
            >注册</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getAllSchools } from "network/admin/School";
import { createStuAccount } from "network/login/Register";
export default {
  name: "Register",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      schoolOptions: {},
      registerForm: { sex: "男" },
      loading: false,
      rules: {
        nickname: [
          { required: true, message: "请输入昵称!!", trigger: "blur" },
        ],
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
        checkpass: [
          { required: true, message: "请再次输入密码!!", trigger: "blur" },
          { validator: validatePass2, trigger: "blur" },
        ],
        schoolId: [
          { required: true, message: "请选择所属学院!!", trigger: "blur" },
        ],
        stuId: [
          { required: true, message: "请输入学号!!", trigger: "blur" },
          {
            pattern: /^[0-9]+$/,
            message: "学号只能是数字!!",
            trigger: "blur",
          },
          { max: 10, message: "长度不能超过10个数字!!", trigger: "blur" },
        ],
        name: [
          { required: true, message: "请输入姓名!!", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z]+$/,
            message: "姓名只能是中/英文!!",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          createStuAccount(this.registerForm)
            .then((res) => {
              if (res.code == 200) {
                let msgs = res.msg.split("-");
                if (msgs[0] == "success") {
                  this.$message({
                    type: "success",
                    message: msgs[1],
                  });
                  this.registerForm = { sex: "男" };
                  this.$router.push('/login')
                } else {
                  this.$message({
                    type: "warning",
                    message: msgs[1],
                  });
                  this.registerForm = { sex: "男" };
                }
                
              } else {
                this.$message.error(res.msg);
                this.registerForm = { sex: "男" };
              }
            })
            .catch((err) => {});
        } else {
          // eslint-disable-next-line no-console
          this.$message.error("注册信息填写有误，请正确填写！");
          return false;
        }
      });
    },
    initSchoolOptions() {
      getAllSchools()
        .then((res) => {
          if (res.code === 200) {
            this.schoolOptions = res.data;
          } else {
            this.$message.error("500错误！");
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色
    document
      .querySelector("body")
      .setAttribute(
        "style",
        "background:url('https://static.zhihu.com/heifetz/assets/sign_bg.db29b0fb.png') "
      );
    next();
  },
  beforeRouteLeave(to, from, next) {
    // 去除背景色
    document.querySelector("body").setAttribute("style", "");
    next();
  },
  created() {
    this.initSchoolOptions();
  },
};
</script>

<style scoped>
.register-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 400px;
  margin: 90px auto;
  border-top: 10px solid #409eff;
}

.register-title {
  text-align: center;
}

.register-center-layout {
  background: #409eff;
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  margin-top: 200px;
}
</style>