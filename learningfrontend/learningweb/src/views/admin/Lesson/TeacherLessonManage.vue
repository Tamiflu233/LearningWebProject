<template>
  <div id="teacher-lesson-manage">
    <!-- 查询表单 -->
    <div class="manip-header" style="margin-left: 10px">
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        size="small"
      >
        <el-form-item
          label="课程"
          prop="lessonId"
        >
          <el-select
            v-model="formInline.lessonId"
            filterable
            placeholder="请选择课程"
            clearable
          >
            <el-option
              v-for="item in lessonOptions"
              :key="item.lessonId"
              :label="item.lessonId + '-' + item.lessonName"
              :value="item.lessonId"
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

        <el-form-item>
          <el-button type="primary" @click="Search" icon="el-icon-search"
            >查询</el-button
          >
        </el-form-item>
      </el-form>
      <el-row>
        <el-col>
          <el-button @click="saveTeachesInfo" type="primary" size="small" round
            >添加</el-button
          >
        </el-col>
      </el-row>
    </div>
    <!-- 表格数据 -->
    <el-table :height="368" :data="tableData" style="width: 95%">
      <el-table-column type="selection" width="120"> </el-table-column>
      <el-table-column label="课程号" prop="lessonId" width="250" sortable>
      </el-table-column>
      <el-table-column label="课程名称" prop="lessonName" width="250">
      </el-table-column>
      <el-table-column label="授课老师" prop="listTeacher" width="680">
        <!-- 分行显示 -->
        <template slot-scope="scope">
          <el-tag
            type="primary"
            disable-transitions
            v-for="(item, index) in scope.row.listTeacher"
            :key="index"
            closable
            style="margin-left: 5px"
            @close="handleClose(scope.$index, scope.row, item)"
          >
            {{ item.name }}
          </el-tag>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
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

    <!-- 增改表单 -->
    <el-dialog title="授课信息" :visible.sync="dialogFormVisible">
      <el-form
        :model="form"
        ref="teachesInfoForm"
        label-suffix=" : "
        :rules="rules"
      >
        <el-form-item label="教师" prop="teaId" :label-width="formLabelWidth">
          <el-select
            v-model="form.teaId"
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
        <el-form-item
          label="课程"
          prop="lessonId"
          :label-width="formLabelWidth"
        >
          <el-select
            v-model="form.lessonId"
            filterable
            placeholder="请选择课程"
            clearable
          >
            <el-option
              v-for="item in lessonOptions"
              :key="item.lessonId"
              :label="item.lessonId + '-' + item.lessonName"
              :value="item.lessonId"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="Reset">重 置</el-button>
        <el-button type="primary" @click="Submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  deleteTeachesInfo,
  addTeachesInfo,
  findTeachesInfo,
} from "network/admin/Teaches";
import { findAllLessons } from "network/admin/Lesson";
import { findAllTeachers } from "network/admin/Teacher";
import { getAllSchools } from "network/admin/School";
export default {
  name: "TeacherLessonManage",
  data() {
    return {
      rules: {
        teaId: [{ required: true, message: "请选择教师!!", trigger: "blur" }],
        lessonId: [
          { required: true, message: "请选择课程!!", trigger: "blur" },
        ],
      },
      formLabelWidth: "100px",
      dialogFormVisible: false,
      tableData: [],
      total: 0,
      size: 5,
      pageNow: 1,
      formInline: {},
      form: {},
      schoolOptions: [],
      teaOptions: [],
      lessonOptions: [],
    };
  },
  methods: {
    save() {
      addTeachesInfo(this.form)
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
          this.findAllTableData(this.pageNow, this.size);
        })
        .catch((err) => {
          this.$message.error("404错误!请求添加授课信息失败！");
          this.form = {};
          this.findAllTableData(this.pageNow, this.size);
        });
    },
    Submit() {
      this.$refs["teachesInfoForm"].validate((valid) => {
        if (valid) {
          // 发送异步请求到Springboot项目
          this.save();
          this.dialogFormVisible = false;
        } else {
          // console.log("error submit!!");
          this.$message.error("当前输入的数据不合法!!");
          return false;
        }
      });
    },
    Reset() {
      this.form = {};
    },
    initLessonOptions() {
      findAllLessons()
        .then((res) => {
          if (res.code === 200) {
            this.lessonOptions = res.data;
          } else {
            this.$message.error("500错误！");
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
    saveTeachesInfo() {
      this.dialogFormVisible = true;
      this.form = {};
    },
    Search() {
      // console.log(this.formInline);
      this.findAllTableData(this.pageNow, this.size);
    },
    deleteTea(lessonId, teaId) {
      deleteTeachesInfo(lessonId, teaId)
        .then((res) => {
          this.$message({
            message: res.msg,
            type: "success",
          });
          // console.log(this.page);
          this.total -= 1;
          if (this.total % this.size === 0) {
            this.pageNow -= 1;
            this.pageNow = this.pageNow < 1 ? 1 : this.pageNow;
          }
          this.findAllTableData(this.page, this.size); //刷新数据
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
    handleClose(index, row, item) {
      row.listTeacher.splice(row.listTeacher.indexOf(item), 1);
      this.deleteTea(row.lessonId, item.teaId);
    },
    findSize(size) {
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
      // console.log("请求数据了");
      // if (this.total % this.size === 0) {
      //   this.page = this.page - 1;
      //   if(this.page < 1) {
      //     this.page = 1
      //   }
      // }
      findTeachesInfo(page, size, this.formInline)
        .then((res) => {
          // console.log(res.code);
          if (res.code === 200) {
            this.tableData = res.data.list;
            this.total = res.data.total;

            // console.log(this.tableData);
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
    },
  },
  created() {
    this.findAllTableData();
    this.initSchoolOptions();
    this.initTeaOptions();
    this.initLessonOptions();
  },
};
</script>

<style scoped>
</style>