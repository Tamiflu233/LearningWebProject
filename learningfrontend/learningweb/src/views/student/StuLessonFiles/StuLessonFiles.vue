<template>
  <div id="stu-lesson-files">
    <el-card class="box-card">
      <div slot="header" class="clearfix title">
        <span>{{ $store.state.lessonInfo.lessonName }}课程资料</span>
      </div>
      <!-- <div v-for="o in 4" :key="o" class="text item">
        {{ "列表内容 " + o }}
      </div> -->
      <file-item
        v-for="(item, index) in fileList"
        :key="index"
        :fileInfo="item"
      ></file-item>
      <div class="empty-tip" v-if="fileList.length == 0">暂无资料😭~</div>
    </el-card>

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
import FileItem from "components/content/common/FileItem";
import { getFiles } from "network/file/LessonFile.js";
export default {
  name: "StuLessonFiles",
  components: {
    FileItem,
  },
  data() {
    return {
      total: 0,
      size: 5,
      pageNow: 1,
      experiment: {
        file: "",
      },
      fileList: [
        {
          name: "123.txt",
          teaName: "唐浩天",
          size: "30kb",
          date: "2022-07-05",
          url: "F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\123.txt",
        },
        {
          name: "自我介绍.pptx",
          teaName: "唐浩天",
          size: "50mb",
          date: "2022-07-05",
          url: "F:\\JavaWeb实验\\learningbackend\\learningweb\\target\\classes\\file\\自我介绍.pptx",
        },
      ],
    };
  },
  methods: {
    findSize(size) {
      //处理每页显示记录发生变化的方法
      // console.log(size);
      this.size = size;
      this.findAllTableData(this.pageNow, size);
    },
    findPage(page) {
      //处理分页的相关方法
      // console.log(page);
      this.pageNow = page;
      this.findAllTableData(page, this.size);
    },
    findAllTableData(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      getFiles(page, size, this.$store.state.lessonInfo.lessonId, null)
        .then((res) => {
          // console.log(res.code);
          console.log(res);
          if (res.code === 200) {
            this.fileList = res.data.list;
            this.total = res.data.total;

            // console.log(this.tableData);
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
  },
  computed: {},
  created() {
    this.findAllTableData();
  },
};
</script>

<style scoped>
#stu-lesson-files {
  padding: 20px;
}
.box-card {
  width: 80%;
  margin: 0 auto;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.title {
  font-size: 20px;
  text-align: center;
}
.empty-tip {
  margin-top: 42px;
  text-align: center;
  font-size: 18px;
  color: rgb(43, 147, 213);
}
.box-card{
  min-height: 200px;
}
</style>