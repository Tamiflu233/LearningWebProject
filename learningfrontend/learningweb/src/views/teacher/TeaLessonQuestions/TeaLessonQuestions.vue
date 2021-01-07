<template>
  <div id="tea-lesson-questions">
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
        src="https://p.ananas.chaoxing.com/star3/270_160c/6ce77a10dd3268daa7ba6c93e5e76459.jpg"
        alt=""
      />
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
import {
  findSchoolById,
  findQuestions,
  deleteQuestion,
} from "network/teacher/TeaLessonQuestions";
import avatar from "assets/img/user_avatar.jpg";
import { mapState, mapMutations } from "vuex";
import { formatDate } from "common/utils";
export default {
  name: "TeaLessonQuestions",
  data() {
    return {
      avatarUrl: avatar,
      schoolName: "",
      questionList: [],
      total: 0,
      size: 5,
      pageNow: 1,
    };
  },
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm"); //年-月-日 时分
    },
  },
  computed: {
    ...mapState(["lessonInfo", "username", "nickname", "teaInfo"]),
  },
  methods: {
    ...mapMutations(["saveQuestion", "saveAnswer"]),
    SearchDetail(item) {
      // console.log(item);
      this.saveQuestion({ question: item });
      this.saveAnswer({ answer: item.answer });
      this.$router.push("teaquestiondetail");
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
      findQuestions(
        page,
        size,
        this.lessonInfo.lessonId,
        this.teaInfo.teaId,
        null
      )
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
  },
};
</script>

<style scoped>
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