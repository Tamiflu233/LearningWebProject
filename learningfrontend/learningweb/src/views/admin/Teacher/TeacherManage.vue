<template>
  <div id="teacher-manage">
    <div class="manip-header" style="margin-left: 10px">
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        size="small"
      >
        <el-form-item label="教师号">
          <el-input
            v-model="formInline.teaId"
            clearable
            placeholder="请输入教师号"
            maxlength="10"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="formInline.sex" size="mini">
            <el-radio-button
              @click.native="clickitem('男')"
              label="男"
            ></el-radio-button>
            <el-radio-button
              @click.native="clickitem('女')"
              label="女"
            ></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input
            v-model="formInline.name"
            clearable
            placeholder="请输入姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="职称">
          <el-input
            v-model="formInline.profession"
            clearable
            placeholder="请输入职称"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="Search" icon="el-icon-search"
            >查询</el-button
          >
        </el-form-item>
      </el-form>
      <el-row>
        <el-col>
          <el-button @click="saveTeaInfo" type="primary" size="small" round
            >添加</el-button
          >
          <el-popconfirm
            confirm-button-text="好的"
            cancel-button-text="不用了"
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除这些教师吗？"
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
    </div>

    <el-table :height="368" :data="tableData" style="width: 95%">
      <el-table-column type="selection" width="70"> </el-table-column>
      <el-table-column label="教师号" prop="teaId" width="140" sortable>
      </el-table-column>
      <el-table-column label="性别" prop="sex" width="140"> </el-table-column>
      <el-table-column label="姓名" prop="name" width="140"></el-table-column>
      <el-table-column
        label="职称"
        prop="profession"
        width="140"
      ></el-table-column>
      <el-table-column label="简介" prop="info" width="320"></el-table-column>
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
            title="确定要删除当前教师吗？"
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

    <!-- 增改表单 -->
    <el-dialog title="教师信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="teaForm" label-suffix=" : " :rules="rules">
        <el-form-item
          label="教师号"
          prop="teaId"
          :label-width="formLabelWidth"
          maxlength="10"
          show-word-limit
        >
          <el-input
            v-model="form.teaId"
            autocomplete="off"
            placeholder="请输入教师号"
            :disabled="isDisable"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-radio-group v-model="form.sex" size="mini">
            <el-radio-button label="男"></el-radio-button>
            <el-radio-button label="女"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
          <el-input
            v-model="form.name"
            autocomplete="off"
            placeholder="请输入教师姓名"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="职称"
          prop="profession"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="form.profession"
            autocomplete="off"
            placeholder="请输入教师职称"
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
  getAllTeachersByPage,
  insertTeacher,
  deleteTeacher,
  updateTeacher,
} from "network/admin/Teacher";
export default {
  name: "TeacherManage",
  data() {
    return {
      dialogTableVisible: false,
      dialogFormVisible: false,
      isDisable: false,
      isUpdate: false,
      formLabelWidth: "100px",
      tableData: [],
      form: { sex: "男" },
      formInline: {
        sex: "男",
      },
      total: 0,
      size: 5,
      pageNow: 1,
      formInline: {
        teaId: "",
        sex: "",
        name: "",
        profession: "",
      },
      rules: {
        teaId: [
          { required: true, message: "请输入教师号!!", trigger: "blur" },
          { max: 10, message: "长度不能超过10个数字!!", trigger: "blur" },
          {
            pattern: /^[0-9]+$/,
            message: "教师号只能是数字!!",
            trigger: "blur",
          },
        ],
        name: [
          { required: true, message: "请填写教师姓名!!", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z]+$/,
            message: "姓名只能是中/英文!!",
            trigger: "blur",
          },
        ],
        profession: [
          { required: true, message: "请填写教师职称!!", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z]+$/,
            message: "职称只能是中/英文!!",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    clickitem(val) {
      if (this.flag === true) {
        this.formInline.sex === val
          ? (this.formInline.sex = "")
          : (this.formInline.sex = val);
      }
      this.flag = true;
      setTimeout(() => {
        this.flag = false;
      }, 300);
    },
    handleDeleteAll() {},
    saveOrUpdate() {
      if (this.isUpdate) {
        updateTeacher(this.form)
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
            this.form = { sex: "男" };
          })
          .catch((err) => {
            this.$message.error("404错误!请求更新教师信息失败！");
            this.form = { sex: "男" };
          });
      } else {
        insertTeacher(this.form)
          .then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: res.msg,
              });
            } else {
              this.$message.error(res.code + "错误!" + res.msg);
            }
            this.form = { sex: "男" };
            this.findAllTableData(this.pageNow, this.size);
          })
          .catch((err) => {
            this.$message.error("404错误!请求添加教师信息失败！");
            this.form = { sex: "男" };
            this.findAllTableData(this.pageNow, this.size);
          });
      }
    },
    Reset() {
      this.form = { sex: "男" };
    },
    saveTeaInfo() {
      this.dialogFormVisible = true;
      this.form = {};
      this.isDisable = false;
      this.isUpdate = false;
    },
    Search() {
      this.findAllTableData(this.pageNow, this.size);
    },
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
      this.dialogFormVisible = true;
      this.isDisable = true;
      this.isUpdate = true;
      this.form = row;
    },
    handleDelete(index, row) {
      deleteTeacher(row.teaId)
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
    findAllTableData(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      getAllTeachersByPage(page, size, this.formInline)
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
    Submit() {
      // console.log("提交数据");
      this.$refs["teaForm"].validate((valid) => {
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
  },
  created() {
    this.findAllTableData();
  },
};
</script>

<style scoped>
</style>