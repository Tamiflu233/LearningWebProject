<template>
  <div id="question-detail">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span>{{ "提问：" + question.title }}</span>
      </div>
      <el-container class="detail">
        <el-aside class="aside" width="200px">
          <div class="avatar"><img class="avatar-img" :src="avatarUrl" alt="" /></div>
          <div class="nickname">{{ question.nickname }}</div>
        </el-aside>
        <el-main class="main"
          ><div class="content">{{ question.content }}</div>
          <div class="date">
            <i class="el-icon-time"></i
            ><span>{{ question.publishDate | formatDate }}</span>
          </div></el-main
        >
      </el-container>
    </el-card>
    <el-card v-if="answer.aid !== null" class="box-card">
      <el-container class="detail">
        <el-aside width="200px" class="aside"
          ><div class="avatar"><img class="avatar-img" :src="avatarUrl" alt="" /></div>
          <div class="nickname">
            {{ question.teacher.name + "老师" }}
          </div></el-aside
        >
        <el-main class="main"
          ><div class="content">{{ answer.answerContent }}</div>
          <div class="date">
            <i class="el-icon-time"></i
            ><span>{{ answer.answerDate | formatDate }}</span>
          </div></el-main
        >
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
import avatar from "assets/img/user_avatar.jpg";
import { formatDate } from "common/utils";
export default {
  name: "QuestionDetail",
  data() {
    return {
      avatarUrl: avatar,
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
  methods: {},
};
</script>

<style scoped>
.title {
  font-size: 19px;
}

.nickname {
  margin-top: 10px;
  padding-left: 10px;
  color: #409EFF;
}
.box-card {
  width: 800px;
  margin: 30px auto;
  overflow: hidden;
}
.avatar {
  margin-top: 20px;
}
.avatar-img {
  width: 100px;
  height: 100px;
  border-radius: 5px;
}
.aside,.main {
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