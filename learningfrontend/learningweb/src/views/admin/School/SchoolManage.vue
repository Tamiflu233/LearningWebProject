<template>
  <div id="school-manage" style="margin-left: 15px">
    <el-row class="manip-header" type="flex">
      <el-col :span="15">
        <el-form
          :inline="true"
          :model="formInline"
          class="demo-form-inline"
          size="small"
        >
          <el-form-item label="学院号">
            <el-input
              v-model="formInline.schoolId"
              clearable
              placeholder="请输入学院号"
              maxlength="8"
              show-word-limit
            ></el-input>
          </el-form-item>
          <el-form-item label="学院名称">
            <el-input
              v-model="formInline.schoolName"
              clearable
              placeholder="请输入学院名称"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="Search" icon="el-icon-search"
              >查询</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="9" style="padding-left: 20px">
        <el-button @click="saveSchoolInfo" type="primary" size="small" round
          >添加</el-button
        >
        <el-popconfirm
          confirm-button-text="好的"
          cancel-button-text="不用了"
          icon="el-icon-info"
          icon-color="red"
          title="确定要删除这些学院吗？"
          @confirm="handleDeleteAll"
        >
          <el-button
            size="small"
            type="danger"
            slot="reference"
            round
            style="margin-left: 10px"
            >批量删除</el-button
          >
        </el-popconfirm>
      </el-col>
    </el-row>
    <el-table :height="368" :data="tableData" style="width: 95%">
      <el-table-column type="selection" width="70"> </el-table-column>
      <el-table-column label="学院号" prop="schoolId" width="230" sortable>
      </el-table-column>
      <el-table-column label="学院名称" prop="schoolName" width="230">
      </el-table-column>
      <el-table-column
        label="学院简介"
        prop="info"
        width="350"
      ></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            style="margin-right: 10px"
            >编辑</el-button
          >

          <el-popconfirm
            confirm-button-text="好的"
            cancel-button-text="不用了"
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除当前学院吗？"
            @confirm="handleDelete(scope.$index, scope.row)"
          >
            <el-button size="mini" type="danger" slot="reference"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
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

    <el-dialog title="学院信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="schoolForm" label-suffix=" : " :rules="rules">
        <el-form-item
          label="学院号"
          prop="schoolId"
          :label-width="formLabelWidth"
          maxlength="10"
          show-word-limit
        >
          <el-input
            v-model="form.schoolId"
            autocomplete="off"
            placeholder="请输入学院号"
            :disabled="isDisable"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="学院名称"
          prop="schoolName"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="form.schoolName"
            autocomplete="off"
            placeholder="请输入学院名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            v-model="form.info"
            resize="none"
            :rows="8"
            placeholder="简介..."
          ></el-input>
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
  getAllSchoolsByPage,
  deleteSchoolById,
  insertSchool,
  updateSchool,
} from "network/admin/School.js";
export default {
  name: "SchoolManage",
  data() {
    return {
      tableData: [],
      search: "",
      isDisable: false,
      form: {},
      formInline: {
        schoolId: "",
        schoolName: "",
      },
      rules: {
        schoolId: [
          { required: true, message: "请输入学院号!!", trigger: "blur" },
          { max: 8, message: "长度不能超过8个数字!!", trigger: "blur" },
          {
            pattern: /^[0-9]+$/,
            message: "学院号只能是数字!!",
            trigger: "blur",
          },
        ],
        schoolName: [
          { required: true, message: "请填写学院名称!!", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z]+$/,
            message: "学院名称只能是中/英文!!",
            trigger: "blur",
          },
        ],
      },
      total: 0,
      size: 5,
      pageNow: 1,
      refresh: false,
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        schoolId: "",
        schoolName: "",
        info: "",
      },
      formLabelWidth: "100px",
      isUpdate: false,
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
    handleEdit(index, row) {
      // console.log(index, row);
      this.dialogFormVisible = true;
      this.isDisable = true;
      this.isUpdate = true;
      this.form = row;
    },
    handleDelete(index, row) {
      // console.log(index, row);
      deleteSchoolById(row.schoolId)
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
    handleDeleteAll() {
      console.log("删除这些玩意");
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
      getAllSchoolsByPage(page, size, this.formInline)
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
    saveSchoolInfo() {
      this.dialogFormVisible = true;
      this.form = {};
      this.isDisable = false;
      this.isUpdate = false;
    },
    Reset() {
      this.form = {};
    },
    saveOrUpdate() {
      if (this.isUpdate) {
        updateSchool(this.form)
          .then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: res.msg,
              });
              this.findAllTableData(this.pageNow, this.size);
            } else {
              this.$message.error(res.code + "错误!" + res.msg);
            }
            this.form = {};
          })
          .catch((err) => {
            this.$message.error("404错误!请求更新学院失败！");
            this.form = {};
          });
      } else {
        insertSchool(this.form)
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
            this.$message.error("404错误!请求插入学院失败！");
            this.form = {};
            this.findAllTableData(this.pageNow, this.size);
          });
      }
    },
    Submit() {
      // console.log("提交数据");
      this.$refs["schoolForm"].validate((valid) => {
        if (valid) {
          // 发送异步请求到Springboot项目
          this.saveOrUpdate();
          this.dialogFormVisible = false;
        } else {
          // console.log("error submit!!");
          this.$message.error("当前输入的数据不合法!!");
          return false;
        }
      });
    },
    Search() {
      // console.log("查找");
      this.findAllTableData(this.pageNow, this.size);
    },
  },
  created() {
    this.findAllTableData();
  },
};
</script>

<style scoped>
.manip-header {
  margin-left: 9px;
  height: 51px;
}
</style>