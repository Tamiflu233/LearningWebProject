<template>
  <div id="lesson-list">
    <div class="manip-header" style="margin-left: 10px">
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        size="small"
      >
        <el-form-item label="开课学院">
          <el-select
            v-model="formInline.schoolId"
            filterable
            placeholder="请选择学院"
            clearable
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
        <el-form-item label="教师" prop="teaId">
          <el-select
            v-model="formInline.teaId"
            filterable
            placeholder="请选择教师"
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
        <el-form-item>
          <el-button type="primary" @click="Search" icon="el-icon-search"
            >查询</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-row>
      <el-col :span="6" v-for="(item, index) in lessonList" :key="index">
        <el-card
          ref="card"
          :body-style="{ padding: '0px' }"
          style="margin: 10px 10px"
        >
          <img v-lazy="img.src" alt="" class="card-img" />
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
import { findAllTeachers } from "network/admin/Teacher";
import { getAllSchools } from "network/admin/School";
import { findLessonsBySchoolAndTea } from "network/student/LessonList";
import { mapMutations } from "vuex";
export default {
  name: "LessonList",
  data() {
    return {
      img: {
        preview:
          "https://cdn.jsdelivr.net/gh/Tamiflu233/cdn/img/cover3-min.jpg",
        src: "https://cdn.jsdelivr.net/gh/Tamiflu233/cdn/img/cover3.jpg",
      },
      formInline: {},
      schoolOptions: [],
      teaOptions: [],
      lessonList: [],
      total: 0,
      size: 8,
      pageNow: 1,
    };
  },
  methods: {
    ...mapMutations(["changeLessonInfo"]),
    SearchDetail(item) {
      this.changeLessonInfo({ lessonInfo: item });
      this.$router.push("lessonquestions");
    },
    Search() {
      this.findLessonsInfo(this.pageNow, this.pageSize);
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
    findLessonsInfo(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      findLessonsBySchoolAndTea(
        page,
        size,
        this.formInline.schoolId,
        this.formInline.teaId
      )
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
    initTeaOptions() {
      findAllTeachers()
        .then((res) => {
          if (res.code === 200) {
            this.teaOptions = res.data;
          } else {
            this.$message.error("500错误！");
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
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
  created() {
    this.findLessonsInfo();
    this.initSchoolOptions();
    this.initTeaOptions();
  },
};
</script>

<style scoped>
.card-img {
  width: 100%;
  display: block;
}
img[lazy="loading"] {
  display: block;
  width: 260px;
  height: 260px;
  margin: 0 auto;
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