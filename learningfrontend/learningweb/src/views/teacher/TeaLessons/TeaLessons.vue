<template>
  <div id="tea-lessons">
    <el-row>
      <el-col :span="6" v-for="(item, index) in lessonList" :key="index">
        <el-card :body-style="{ padding: '0px' }" style="margin: 10px 10px">
          <img
            src="https://p.ananas.chaoxing.com/star3/270_160c/6ce77a10dd3268daa7ba6c93e5e76459.jpg"
            alt=""
            class="card-img"
          />
          <div style="padding: 14px">
            <span>{{ item.lessonName }}</span>
            <div class="bottom clearfix">
              <div class="lessonId">课程号: {{ item.lessonId }}</div>
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
          :page-sizes="[4, 8, 12, 16, 20]"
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
import { findLessonsByTeaId } from "network/teacher/TeaLessons";
import { findTeacherByUsername } from "network/teacher/TeacherHome";
import { mapMutations, mapState } from "vuex";
export default {
  name: "TeaLessons",
  data() {
    return {
      lessonList: [],
      total: 0,
      size: 8,
      pageNow: 1,
    };
  },
  computed: {
    ...mapState(["teaInfo","username"]),
  },
  methods: {
    ...mapMutations(["changeLessonInfo","saveTeaInfo"]),
    findLessonsInfo(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      findLessonsByTeaId(page, size, this.teaInfo.teaId)
        .then((res) => {
          if (res.code === 200) {
            this.lessonList = res.data.list;
            this.total = res.data.total;
            // console.log(this.lessonList);
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
    SearchDetail(item) {
      this.changeLessonInfo({ lessonInfo: item });
      this.$router.push("tealessonquestions");
    },
    findSize(size) {
      //处理每页显示记录发生变化的方法
      // console.log(size);
      this.size = size;
      this.findLessonsInfo(this.pageNow, size);
    },
    findPage(page) {
      //处理分页的相关方法
      // console.log(page);
      this.pageNow = page;
      this.findLessonsInfo(page, this.size);
    },
    
    initTeaInfo() {
      findTeacherByUsername(this.username).then(res => {
        this.saveTeaInfo({teaInfo: res.data})
        this.findLessonsInfo()
      }).catch(err => {
        console.log("获取教师信息失败");
      })
    },
    
  },
  created() {
    this.initTeaInfo()
  },
};
</script>

<style scoped>
.card-img {
  width: 100%;
  display: block;
}

.lessonId {
  font-size: 13px;
  color: #999;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>