<template>
  <el-card id="file-item">
    <div class="file-name">
      文件名: {{ fileInfo.name }}
      <el-link
        class="download"
        type="primary"
        :underline="false"
        href="javascript:;"
        @click.native="downloadFile"
        ><i class="el-icon-view el-icon-download"></i
      ></el-link>
    </div>
    <div class="file-publisher">上传者: {{ fileInfo.teaName }}</div>
    <div class="file-size">文件大小: {{ fileInfo.size }}</div>
    <div class="file-date">上传日期: {{ fileInfo.date | formatDate }}</div>
  </el-card>
</template>

<script>
import { download } from "network/file/LessonFile.js";
import { formatDate } from "common/utils";
export default {
  name: "FileItem",
  components: {},
  data() {
    return {};
  },
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm"); //年-月-日 时分
    },
  },
  methods: {
    downloadFile() {
      download(this.fileInfo.url)
        .then((res) => {
          console.log(res);
          let blob = res;
          // 创建一个URL指向Blob，也就是Blob URL
          let url = null;
          try {
            url = window.URL.createObjectURL(blob);
          } catch (error) {
            url = blob;
          }
          console.log(url);
          // 创建<a>标签
          let el = document.createElement("a");
          el.href = url;
          // 指定下载的文件名
          el.download = this.fileInfo.name;
          el.hidden = true;
          document.body.appendChild(el);
          el.click();
          document.body.removeChild(el);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {},
  props: ["fileInfo"],
};
</script>

<style scoped>
#file-item {
  position: relative;
  font-size: 16px;
  height: 100px;
  margin-top: 8px;
  font-family: "Microsoft YaHei", -apple-system, BlinkMacSystemFont, "Segoe UI",
    "Helvetica Neue", Lato, Roboto, "PingFang SC", sans-serif;
}
.file-name {
  position: absolute;
  left: 15px;
  top: 15px;
}
.file-publisher {
  position: absolute;
  bottom: 15px;
  left: 15px;
}
.file-size {
  position: absolute;
  top: 15px;
  right: 15px;
}
.file-date {
  position: absolute;
  bottom: 15px;
  right: 15px;
}
.download {
  font-size: 20px;
}
</style>