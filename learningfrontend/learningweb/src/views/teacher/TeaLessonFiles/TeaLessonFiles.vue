<template>
  <div id="tea-lesson-files">
    <el-row :gutter="18">
      <el-col :span="18"
        ><el-card class="box-card">
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
          ></file-item> </el-card
      ></el-col>
      <el-col :span="6"
        ><el-card class="upload-card">
          <el-upload
            name="multipartFiles"
            class="upload-demo"
            action="http://localhost:8989/api/file/upload/"
            :file-list="fileList"
            :on-success="uploadSuccess"
            :on-error="error"
            multiple
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div class="el-upload__tip" slot="tip">
              文件大小不超过100mb，多文件上传请压缩
            </div>
          </el-upload>
        </el-card></el-col
      >
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
import FileItem from "components/content/common/FileItem";
import { saveFileInfo } from "network/file/LessonFile.js";
export default {
  name: "TeaLessonFiles",
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
      this.findAllQuestions(this.pageNow, size);
    },
    findPage(page) {
      //处理分页的相关方法
      // console.log(page);
      this.pageNow = page;
      this.findAllQuestions(page, this.size);
    },

    uploadSuccess(response, file, fileList) {
      console.log(response);

      if (response.code == 200) {
        saveFileInfo(
          this.$store.state.lessonInfo.lessonId,
          this.$store.state.teaInfo.teaId,
          response.data[0].name,
          response.data[0].url,
          this.$store.state.teaInfo.name,
          response.data[0].size
        )
          .then((res) => {
            console.log(res);
            if (res.code === 200) {
            } else {
            }
          })
          .catch((err) => {
            console.log(err);
            this.$message.error("404错误！");
          });
      }else {
        this.$message.error(response.msg);
      }
    },
    error() {},
  },
  computed: {},
};
</script>

<style scoped>
#tea-lesson-files {
  padding: 20px;
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
.el-upload {
  width: 100%;
}
</style>