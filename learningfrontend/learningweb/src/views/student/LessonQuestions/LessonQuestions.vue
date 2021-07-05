<template>
  <div id="lesson-questions">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span>{{ "课程名称：" + lessonInfo.lessonName }}</span>
      </div>
      <div class="detail">
        <el-tag
          class="schoolId"
          type="primary"
          size="medium"
          style="display: inline-block"
        >
          {{ "开课学院:  " + schoolName }}
        </el-tag>
        <el-tag
          class="lessonId"
          type="success"
          size="medium"
          style="display: block"
        >
          {{ "课程号:  " + lessonInfo.lessonId }}
        </el-tag>
        <el-tag class="info" type="info" size="medium" style="display: block">
          {{ "课程简介:  " + lessonInfo.info }}
        </el-tag>
      </div>
      <img
        class="lesson-img"
        src="https://unpkg.zhimg.com/tamiflu233-assets@1.0.4/img/cover3.jpg"
        alt=""
      />
    </el-card>
    <!-- 发布提问表单 -->
    <el-card class="question-form clearfix" :body-style="{ padding: '0px' }">
      <el-form
        :model="questionForm"
        ref="questionForm"
        :rules="rules"
        class="clearfix"
      >
        <el-form-item prop="teaId" class="form-options">
          <el-select
            v-model="questionForm.teaId"
            filterable
            placeholder="请选择提问教师"
            clearable
          >
            <el-option
              v-for="item in teaOptions"
              :key="item.teaId"
              :label="item.teaId + '-' + item.name"
              :value="item.teaId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="title" class="form-title">
          <el-input
            v-model="questionForm.title"
            autocomplete="off"
            placeholder="请填写标题"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item class="form-input">
          <el-col :span="23">
            <el-input
              class="form-textarea"
              type="textarea"
              v-model="questionForm.content"
              prop="content"
              resize="none"
              :rows="8"
              placeholder="提问..."
            ></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div class="form-footer">
        <el-button @click="Reset">重 置</el-button>
        <el-button type="primary" @click="Submit">发 布</el-button>
      </div>
    </el-card>

    <!-- 提问列表(分页) -->
    <el-row style="margin-top: 10px">
      <el-col :span="24" v-for="(item, index) in questionList" :key="index">
        <el-card class="question-card" :body-style="{ padding: '0px' }">
          <div style="padding: 14px">
            <div class="question-title">{{ "提问: " + item.title }}</div>
            <el-tag
              class="not-answered"
              type="danger"
              v-if="item.isAnswered === null"
              >未解答</el-tag
            >
            <div class="bottom clearfix">
              <div class="nickname">
                <img class="user-avatar" :src="avatarUrl" alt="" />
                <span class="nicktext">{{ item.nickname }}</span>
              </div>
              <div class="publish-date">
                <i class="el-icon-time"></i>
                <span class="date">{{ item.publishDate | formatDate }}</span>
              </div>
              <el-button
                type="text"
                class="button"
                @click.native.prevent="SearchDetail(item)"
                >查看详情</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12">
        <!-- 分页组件 -->
        <el-pagination
          layout="total,  sizes,prev, pager, next, jumper"
          background
          :page-size="size"
          :current-page="pageNow"
          :page-sizes="[5, 10, 15, 20, 25]"
          :total="total"
          style="margin: 15px 0"
          @current-change="findPage"
          @size-change="findSize"
        >
        </el-pagination
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import avatar from "assets/img/user_avatar.jpg";
import { mapState, mapMutations } from "vuex";
import { formatDate } from "common/utils";
import {
  findSchoolById,
  findQuestions,
  updateQuestion,
  deleteQuestion,
  addQuestion,
  findTeachersByLesson,
} from "network/student/LessonQuestions";
export default {
  name: "LessonQuestions",
  data() {
    return {
      avatarUrl: avatar,
      schoolName: "",
      questionList: [],
      questionForm: {},
      total: 0,
      size: 5,
      pageNow: 1,
      rules: {
        teaId: [{ required: true, message: "请选择教师!!", trigger: "blur" }],
        title: [{ required: true, message: "请输入标题!!", trigger: "blur" }],
        content: [{ required: true, message: "请输入内容!!", trigger: "blur" }],
      },
      teaOptions: [],
    };
  },
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm"); //年-月-日 时分
    },
  },
  computed: {
    ...mapState(["lessonInfo", "username", "nickname"]),
  },
  methods: {
    ...mapMutations(["saveQuestion", "saveAnswer"]),
    SearchDetail(item) {
      // console.log(item);
      this.saveQuestion({ question: item });
      this.saveAnswer({ answer: item.answer });
      this.$router.push("questiondetail");
    },
    Reset() {
      this.questionForm = {};
    },
    save() {
      this.questionForm.lessonId = this.lessonInfo.lessonId;
      this.questionForm.username = this.username;
      this.questionForm.nickname = this.nickname;
      addQuestion(this.questionForm)
        .then((res) => {
          if (res.code === 200) {
            this.$message({
              type: "success",
              message: res.msg,
            });
          } else {
            this.$message.error(res.code + "错误!" + res.msg);
          }
          this.form = {};
          this.findAllQuestions(this.pageNow, this.size);
        })
        .catch((err) => {
          this.$message.error("404错误!请求发布提问失败！");
          this.form = {};
          this.findAllQuestions(this.pageNow, this.size);
        });
    },
    Submit() {
      this.$refs["questionForm"].validate((valid) => {
        if (valid) {
          // 发送异步请求到Springboot项目
          this.save();
        } else {
          // console.log("error submit!!");
          this.$message.error("当前输入的数据不合法!!");
          return false;
        }
        this.questionForm = {};
      });
    },
    findSize(size) {
      //处理每页显示记录发生变化的方法
      // console.log(size);
      this.size = size;
      this.findAllQuestions(this.pageNow, size);
    },
    findPage(page) {
      //处理分页的相关方法
      // console.log(page);
      this.pageNow = page;
      this.findAllQuestions(page, this.size);
    },
    initTeaOptions() {
      findTeachersByLesson(this.lessonInfo.lessonId)
        .then((res) => {
          this.teaOptions = res.data;
        })
        .catch((err) => {
          console.log("授课教师获取失败！");
        });
    },
    initSchoolName() {
      findSchoolById(this.lessonInfo.schoolId)
        .then((res) => {
          if (res.code == 200) {
            this.schoolName = res.data.schoolName;
          } else {
            this.schoolName = "未知";
          }
        })
        .catch((err) => {
          this.schoolName = "未知";
        });
    },
    findAllQuestions(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      findQuestions(page, size, this.lessonInfo.lessonId, null, null)
        .then((res) => {
          if (res.code === 200) {
            this.questionList = res.data.list;
            this.total = res.data.total;
            // console.log(this.lessonList);
            // console.log(this.questionList);
          } else {
            console.log("失败");
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
  },
  created() {
    this.initSchoolName();
    this.findAllQuestions();
    this.initTeaOptions();
  },
};
</script>

<style scoped>
.form-footer {
  position: absolute;
  bottom: 25px;
  right: 25px;
}
.form-title {
  width: 750px;
  margin-left: 25px;
}
.form-options {
  margin-left: 25px;
  margin-top: 20px;
}
.form-content {
  width: 750px;
  margin-left: 25px;
}
.form-textarea {
  width: 750px;
  margin-left: 25px;
  margin-bottom: 60px;
}
.question-form {
  width: 800px;
  margin: 10px auto;
  position: relative;
}
.publish-date {
  margin-bottom: 20px;
}
.question-title {
  font-size: 19px;
  font-style: bold;
  margin-bottom: 13px;
}
.nickname {
  /* vertical-align: middle; */
  height: 35px;
  position: relative;
  margin-bottom: 13px;
}
.nicktext {
  margin-left: 40px;
  line-height: 35px;
}
.user-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  position: absolute;
  bottom: 2px;
  left: 2px;
}
.not-answered {
  position: absolute;
  top: 15px;
  right: 15px;
}
.question-card {
  position: relative;
  width: 800px;
  margin: 2px auto;
}
.title {
  font-size: 24px;
  font-style: bold;
}
.lesson-img {
  position: absolute;
  width: 300px;
  right: 40px;
  bottom: 21px;
}
.schoolId {
  font-size: 15px;
  margin-bottom: 10px;
}
.lessonId {
  width: 150px;
  font-size: 15px;
  margin-bottom: 10px;
}
.info {
  width: 400px;
  height: 120px;
  font-size: 15px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 800px;
  margin: 0 auto;
  position: relative;
}
.button {
  position: absolute;
  right: 15px;
  bottom: 5px;
}
</style>