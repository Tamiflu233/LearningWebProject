<template>
  <div id="teacher-account-manage">
    <el-row class="manip-header" type="flex">
      <el-col :span="15">
        <el-form
          :inline="true"
          :model="formInline"
          class="demo-form-inline"
          size="small"
        >
          <el-form-item label="用户名">
            <el-input
              v-model="formInline.username"
              clearable
              placeholder="请输入用户名"
            ></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input
              v-model="formInline.nickname"
              clearable
              placeholder="请输入昵称"
            ></el-input>
          </el-form-item>
          <el-form-item label="教师">
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
      </el-col>
      <el-col :span="9">
        <el-button @click="saveTeaAccount" type="primary" size="small" round
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

    <el-table :height="368" :data="tableData" style="width: 95%">
      <el-table-column type="selection" width="70"> </el-table-column>
      <el-table-column label="账号id" prop="uid" width="160" sortable>
      </el-table-column>
      <el-table-column label="用户名" prop="username" width="160">
      </el-table-column>
      <el-table-column
        label="密码"
        prop="password"
        width="160"
      ></el-table-column>
      <el-table-column
        label="昵称"
        prop="nickname"
        width="140"
      ></el-table-column>
      <el-table-column
        label="教师姓名"
        prop="teacher.name"
        width="170"
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
    <el-dialog title="教师账号信息" :visible.sync="dialogFormVisible">
      <el-form
        :model="form"
        ref="teaAccountForm"
        label-suffix=" : "
        :rules="rules"
      >
        <el-form-item
          label="用户名"
          prop="username"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="form.username"
            autocomplete="off"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="form.password"
            autocomplete="off"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="昵称"
          prop="nickname"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="form.nickname"
            autocomplete="off"
            placeholder="请输入昵称"
          ></el-input>
        </el-form-item>
        <el-form-item label="教师" prop="teaId" :label-width="formLabelWidth">
          <el-select
            v-model="form.teaId"
            filterable
            placeholder="请选择教师"
            clearable
            :disabled="isDisable"
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
  getAllTeaAccounts,
  createTeaAccount,
  deleteAccount,
  updateAccount,
} from "network/admin/Account";
import { findAllTeachers } from "network/admin/Teacher";
export default {
  name: "TeacherAccountManage",
  data() {
    return {
      tableData: [],
      formInline: {},
      form: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名!!", trigger: "blur" },
          {
            pattern: /^[_a-zA-Z0-9]+$/,
            message: "用户名只能是英文/数字/下划线!!",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请填写密码!!", trigger: "blur" },
          { min: 8, message: "密码长度至少8位!!", trigger: "blur" },
        ],
        nickname: [{ required: true, message: "请输入昵称!", trigger: "blur" }],
        teaId: [{ required: true, message: "请选择教师!!", trigger: "blur" }],
      },
      isUpdate: false,
      dialogFormVisible: false,
      formLabelWidth: "100px",
      teaOptions: [],
      total: 0,
      size: 5,
      pageNow: 1,
      isDisable: false,
    };
  },
  methods: {
    Reset() {
      this.form = {};
    },
    Submit() {
      this.$refs["teaAccountForm"].validate((valid) => {
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
    saveTeaAccount() {
      this.dialogFormVisible = true;
      this.form = {};
      this.isDisable = false;
      this.isUpdate = false;
    },
    findAllTableData(page, size) {
      page = page ? page : this.pageNow;
      size = size ? size : this.size;
      getAllTeaAccounts(page, size, this.formInline)
        .then((res) => {
          // console.log(res.code);
          if (res.code === 200) {
            this.tableData = res.data.list;
            this.total = res.data.total;
            this.tableData.forEach((item, index) => {
              this.tableData[index].teaId = item.teacher.teaId;
            });
            // console.log(this.tableData);
          }
        })
        .catch((err) => {
          this.$message.error("404错误！");
        });
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
      console.log(row);
    },
    handleDelete(index, row) {
      deleteAccount(row.uid)
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
    Search() {
      console.log(this.formInline);
      this.findAllTableData(this.pageNow, this.size);
    },
    saveOrUpdate() {
      if (this.isUpdate) {
        updateAccount(this.form)
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
            this.$message.error("404错误!请求更新教师账号信息失败！");
            this.form = {};
          });
      } else {
        createTeaAccount(this.form)
          .then((res) => {
            if (res.code === 200) {
              let msgs = res.msg.split("-");
              if (msgs[0] == "warn") {
                this.$message({
                  type: "warning",
                  message: msgs[1],
                });
              } else {
                this.$message({
                  type: "success",
                  message: msgs[1],
                });
              }
            } else {
              this.$message.error(res.code + "错误!" + res.msg);
            }
            this.form = {};
            this.findAllTableData(this.pageNow, this.size);
          })
          .catch((err) => {
            this.$message.error("404错误!请求添加教师账号信息失败！");
            this.form = {};
            this.findAllTableData(this.pageNow, this.size);
          });
      }
    },
    handleDeleteAll() {
      console.log("删除这些玩意");
    },
  },
  created() {
    this.findAllTableData();
    this.initTeaOptions();
  },
};
</script>

<style scoped>
</style>