<template>
  <div id="tea-question-detail">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span>{{ "提问：" + question.title }}</span>
        <el-button
          class="delete-question"
          type="danger"
          icon="el-icon-delete"
          @click.native.prevent="delQuestion"
          circle
        ></el-button>
      </div>
      
      <el-container class="detail">
        <el-aside class="aside" width="200px">
          <div class="avatar">
            <img class="avatar-img" :src="avatarUrl" alt="" />
          </div>
          <div class="nickname">{{ question.nickname }}</div>
        </el-aside>
        <el-main class="main">
          <div class="content">{{ question.content }}</div>
          <div class="date">
            <i class="el-icon-time"></i
            ><span>{{ question.publishDate | formatDate }}</span>
          </div></el-main
        >
      </el-container>
    </el-card>
    <el-button
      @click.native.prevent="clickAnswer"
      type="primary"
      class="answer-button"
      v-if="answer.aid == null"
      >回答</el-button
    >
    <!-- 回答 -->
    <el-card v-if="answer.aid !== null" class="box-card">
      <el-container class="detail">
        <el-aside width="200px" class="aside"
          ><div class="avatar">
            <img class="avatar-img" :src="avatarUrl" alt="" />
          </div>
          <div class="nickname">
            {{ question.teacher.name + "老师" }}
          </div></el-aside
        >
        <el-main class="main">
          <el-button
            class="edit"
            type="primary"
            icon="el-icon-edit"
            circle
            @click.native.prevent="handleUpdate"
          ></el-button>

          <el-button
            class="delete-answer"
            type="danger"
            icon="el-icon-delete"
            @click.native.prevent="handleDeleteAnswer"
            circle
          ></el-button>
          <div class="content">{{ answer.answerContent }}</div>
          <div class="date">
            <i class="el-icon-time"></i
            ><span>{{ answer.answerDate | formatDate }}</span>
          </div></el-main
        >
      </el-container>
    </el-card>

    <!-- 回答问题表单 -->
    <el-collapse-transition>
      <el-card
        v-show="isShowed"
        class="answer-form clearfix"
        :body-style="{ padding: '0px' }"
      >
        <el-form
          :model="answerForm"
          ref="answerForm"
          :rules="rules"
          class="clearfix"
        >
          <el-form-item class="form-input">
            <el-col :span="23">
              <el-input
                class="form-textarea"
                type="textarea"
                v-model="answerForm.answerContent"
                prop="content"
                resize="none"
                :rows="8"
                placeholder="解答..."
              ></el-input>
            </el-col>
          </el-form-item>
        </el-form>
        <div class="form-footer">
          <el-button @click="Reset">重 置</el-button>
          <el-button type="primary" @click="Submit">回 答</el-button>
        </div>
      </el-card>
    </el-collapse-transition>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import avatar from "assets/img/user_avatar.jpg";
import { formatDate } from "common/utils";
import {
  addAnswer,
  updateAnswer,
  deleteAnswer,
  findAnswerByQid,
  deleteQuestion,
} from "network/teacher/TeaQuestionDetail";
export default {
  name: "TeaQuestionDetail",
  data() {
    return {
      avatarUrl: avatar,
      answerForm: {},
      rules: {
        answerContent: [
          { required: true, message: "请输入内容!!", trigger: "blur" },
        ],
      },
      isShowed: false,
      isUpdate: false,
    };
  },
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm"); //年-月-日 时分
    },
  },
  computed: {
    ...mapState(["question", "answer"]),
  },
  methods: {
    ...mapMutations(["saveQuestion", "saveAnswer"]),
    delQuestion() {
      console.log("!!");
      deleteQuestion(this.question.qid).then((res) => {
          if (res.code === 200) {
            this.$message({
              type: "success",
              message: res.msg,
            });
            this.saveQuestion({question: null})
            this.$router.go(-1)
          } else {
            this.$message.error(res.code + "错误!" + res.msg);
          }
        })
        .catch((err) => {
          this.$message.error("404错误!请求删除问题失败！");
        });
    },
    handleDeleteQuestion() {},
    handleDeleteAnswer() {
      console.log("sdd");
      deleteAnswer(this.answer.aid, this.answer.qid)
        .then((res) => {
          if (res.code === 200) {
            this.$message({
              type: "success",
              message: res.msg,
            });
            this.findQuestion();
          } else {
            this.$message.error(res.code + "错误!" + res.msg);
          }
        })
        .catch((err) => {
          this.$message.error("404错误!请求删除回答失败！");
        });
    },
    handleUpdate() {
      this.isUpdate = true;
      this.isShowed = !this.isShowed;
    },
    clickAnswer() {
      this.isUpdate = false;
      this.isShowed = !this.isShowed;
    },
    findQuestion() {
      findAnswerByQid(this.question.qid)
        .then((res) => {
          if (res.code == 200) {
            if (res.data != null) this.saveAnswer({ answer: res.data });
            else this.saveAnswer({ answer: { aid: null } });
            this.isShowed = !this.isShowed;
          } else {
            this.saveAnswer({ answer: { aid: null } });
          }
        })
        .catch((err) => {
          console.log("获取回答失败！");
        });
    },
    saveOrUpdate() {
      if (!this.isUpdate) {
        this.answerForm.qid = this.question.qid;
        this.answerForm.lessonId = this.question.lessonId;
        this.answerForm.teaId = this.question.teaId;
        addAnswer(this.answerForm)
          .then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: res.msg,
              });
              this.findQuestion();
            } else {
              this.$message.error(res.code + "错误!" + res.msg);
            }
            this.answerForm = {};
          })
          .catch((err) => {
            this.$message.error("404错误!请求回答问题失败！");
            this.answerForm = {};
          });
      } else {
        this.answerForm.aid = this.answer.aid;
        updateAnswer(this.answerForm)
          .then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: res.msg,
              });
              this.findQuestion();
            } else {
              this.$message.error(res.code + "错误!" + res.msg);
            }
          })
          .catch((err) => {
            this.$message.error("404错误!修改请求回答失败！");
            this.answerForm = {};
          });
      }
    },
    Reset() {
      this.answerForm = {};
    },
    Submit() {
      this.$refs["answerForm"].validate((valid) => {
        if (valid) {
          // 发送异步请求到Springboot项目
          this.saveOrUpdate();
        } else {
          // console.log("error submit!!");
          this.$message.error("当前输入的数据不合法!!");
          return false;
        }
        this.questionForm = {};
      });
    },
  },
};
</script>

<style scoped>
.delete-question {
  position: absolute;
  right: 15px;
  top: 10px;
}
.delete-answer {
  position: absolute;
  right: 2px;
  top: 5px;
}
.edit {
  position: absolute;
  right: 47px;
  top: 5px;
}
.answer-button {
  margin-left: 1020px;
}
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
  margin-top: 30px;
}
.answer-form {
  width: 800px;
  margin: 10px auto;
  position: relative;
}
.title {
  font-size: 19px;
}

.nickname {
  margin-top: 10px;
  padding-left: 10px;
  color: #409eff;
}
.box-card {
  width: 800px;
  margin: 30px auto;
  overflow: hidden;
  position: relative;
}
.avatar {
  margin-top: 20px;
}
.avatar-img {
  width: 100px;
  height: 100px;
  border-radius: 5px;
}
.aside,
.main {
  height: 227px;
}
.main {
  position: relative;
}
.date {
  position: absolute;
  right: 10px;
  bottom: 6px;
}
</style>