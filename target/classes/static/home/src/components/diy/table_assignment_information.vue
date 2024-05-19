<template>
  <el-main class="bg table_wrap">
    <el-form label-position="right" :model="query" class="form p_4" label-width="120">
      <el-row class="rows row1">
        <el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
          <el-form-item label="教师姓名">
            <el-input v-model="query.teachers_name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
          <el-form-item label="作业标题">
            <el-input v-model="query.assignment_title"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row class="rows row2">
        <el-col :xs="24" :sm="24" :lg="24" class="search_btn_wrap search_btns">
          <el-col :xs="24" :sm="10" :lg="8" class="search_btn_1 search_btn_wrap_1 btns">
            <el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
            <el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
            <el-button
              v-if="user_group == '管理员' || $check_action('/assignment_information/table', 'add') || $check_action('/assignment_information/view', 'add')"
              @click="$router.push('./view?')" class="add">添加</el-button>
            <el-button
              v-if="user_group == '管理员' || $check_action('/assignment_information/table', 'del') || $check_action('/assignment_information/view', 'del')"
              class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
          </el-col>

        </el-col>
      </el-row>
    </el-form>
    <el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%"
      id="dataTable">
      <el-table-column fixed type="selection" tooltip-effect="dark" width="55">
      </el-table-column>
      <el-table-column prop="teacher_users" @sort-change="$sortChange" label="教师用户"
        v-if="user_group == '管理员' || $check_field('get', 'teacher_users')" min-width="200">
        <template slot-scope="scope">
          {{ get_user_teacher_users(scope.row['teacher_users']) }}
        </template>
      </el-table-column>
      <el-table-column prop="teachers_name" @sort-change="$sortChange" label="教师姓名"
        v-if="user_group == '管理员' || $check_field('get', 'teachers_name')" min-width="200">
      </el-table-column>
      <el-table-column prop="course_name" @sort-change="$sortChange" label="课程名称"
        v-if="user_group != '学生' || $check_field('get', 'course_name')" min-width="200">
      </el-table-column>
      <el-table-column prop="course_cover" @sort-change="$sortChange" label="课程封面"
        v-if="user_group == '管理员' || $check_field('get', 'course_cover')" min-width="200">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 100px" :src="$fullUrl(scope.row['course_cover'])"
            :preview-src-list="[$fullUrl(scope.row['course_cover'])]">
            <div slot="error" class="image-slot">
              <img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="assignment_title" @sort-change="$sortChange" label="作业标题"
        v-if="user_group == '管理员' || $check_field('get', 'assignment_title')" min-width="200">
      </el-table-column>
      <el-table-column prop="assignment_content" @sort-change="$sortChange" label="作业内容"
        v-if="user_group == '管理员' || $check_field('get', 'assignment_content')" min-width="200">
        <template slot-scope="scope">
          <a :href="$fullUrl(scope.row['assignment_content'])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column prop="status" @sort-change="$sortChange" label="状态"
        v-if="user_group != '学生用户' || $check_field('get', 'status')" min-width="200">
        <template slot-scope="scope">
          {{ scope.row['status'] == 1 ? '已发布' : '未发布' }}
        </template>
      </el-table-column>
      <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
        <template slot-scope="scope">
          {{ $toTime(scope.row["create_time"], "yyyy-MM-dd hh:mm:ss") }}
        </template>
      </el-table-column>

      <el-table-column sortable prop="update_time" label="更新时间" min-width="200">
        <template slot-scope="scope">
          {{ $toTime(scope.row["update_time"], "yyyy-MM-dd hh:mm:ss") }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" min-width="120"
        v-if="user_group == '管理员' || $check_action('/assignment_information/table', 'set') || $check_action('/assignment_information/view', 'set') || $check_action('/assignment_information/view', 'get') || $check_action('/提交/table', 'add') || $check_action('/提交/view', 'add')">
        <template slot-scope="scope">
          <router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
            v-if="user_group == '管理员' || $check_action('/assignment_information/table', 'set') || $check_action('/assignment_information/view', 'set') || $check_action('/assignment_information/view', 'get')"
            :to="'./view?' + field + '=' + scope.row[field]" size="small">
            <span>详情</span>
          </router-link>
          <el-button class="el-button el-button--small is-plain el-button--success" @click="handlePublic(scope.row[field])">发布</el-button>
          <router-link v-if="user_group == '管理员' || $check_comment('/assignment_information/table')"
            class="el-button el-button--small is-plain el-button--primary"
            :to="'../comment/table?size=10&page=1&source_table=assignment_information&source_field=' + field + '&source_id=' + scope.row[field]"
            size="small">
            查看评论
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页器 -->
    <div class="mt text_center">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="query.page"
        :page-sizes="[7, 10, 30, 100]" :page-size="query.size" layout="total, sizes, prev, pager, next, jumper"
        :total="count">
      </el-pagination>
    </div>
    <!-- /分页器 -->


    <div class="modal_wrap" v-if="showModal">
      <div class="modal_box">
        <!-- <div class="modal_box_close" @click="closeModal">X</div> -->
        <p class="modal_box_title">重要提醒</p>
        <p class="modal_box_text">当前有数据达到预警值！</p>
        <div class="btn_box">
          <span @click="closeModal">取消</span>
          <span @click="closeModal">确定</span>
        </div>
      </div>
    </div>


  </el-main>
</template>
<script>
import mixin from "@/mixins/page.js";
import { toPublic,getList } from "../../api/assignment_information/api";
import Cookies from "js-cookie"
export default {
  mixins: [mixin],
  data() {
    return {
      // 弹框
      showModal: false,
      // 获取数据地址
      url_get_list: "~/api/assignment_information/get_list?like=0",
      url_del: "~/api/assignment_information/del?",

      // 字段ID
      field: "assignment_information_id",

      // 查询
      query: {
        "size": 7,
        "page": 1,
        "teachers_name": "",
        "assignment_title": "",
        "login_time": "",
        "create_time": "",
        "orderby": `create_time desc`
      },

      // 数据
      list: [],
      // 用户列表
      list_user_teacher_users: [],
    }
  },
  methods: {

    async handlePublic(id) {
      console.log(id);
      await toPublic(id).then((result) => {
        if (result.status === 200) {
          this.$message({
            message: "发布成功",
            type: "success"
          });
          this.list.forEach((res) => {
            if (res.assignment_information_id == id) {
              res.status = 1;
              ;
            }
          })
        }
      })
    },
    // 关闭弹框
    closeModal() {
      this.showModal = false;
    },
    get_list_before(param) {
      var user_group = this.$store.state.user.user_group;
      if (user_group != "管理员") {
        let sqlwhere = "(";
        if (user_group == "教师用户") {
          sqlwhere += "teacher_users = " + this.$store.state.user.user_id + " or ";
        }
        if (sqlwhere.length > 1) {
          sqlwhere = sqlwhere.substr(0, sqlwhere.length - 4);
          sqlwhere += ")";
          param["sqlwhere"] = sqlwhere;
        }
      }
      return param;
    },
    /**
* 获取教师用户用户列表
*/
    async get_list_user_teacher_users() {
      var json = await this.$get("~/api/user/get_list?user_group=教师用户");
      if (json.result && json.result.list) {
        this.list_user_teacher_users = json.result.list;
      }
      else if (json.error) {
        console.error(json.error);
      }
    },

    get_user_teacher_users(id) {
      var obj = this.list_user_teacher_users.getObj({ "user_id": id });
      var ret = "";
      if (obj) {
        ret = obj.nickname + "-" + obj.username;
        // if(obj.nickname){
        // 	ret = obj.nickname;
        // }
        // else{
        // 	ret = obj.username;
        // }
      }
      return ret;
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },

  },
  created() {
    this.get_list_user_teacher_users();
    console.log(this.list);
  }
}
</script>

<style type="text/css">
.bg {
  background: white;
}

.form.p_4 {
  padding: 1rem;
}

.form .el-input {
  width: initial;
}

.mt {
  margin-top: 1rem;
}

.text_center {
  text-align: center;
}

.float-right {
  float: right;
}


.modal_wrap {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 9999999999;
}

.modal_wrap .modal_box {
  width: 400px;
  height: 200px;
  background: url("../../assets/modal_bg.jpg") no-repeat center;
  background-size: cover;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-left: -200px;
  margin-top: -100px;
  border-radius: 10px;
}

.modal_wrap .modal_box .modal_box_close {
  font-size: 20px;
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

.modal_wrap .modal_box .modal_box_title {
  text-align: center;
  font-size: 18px;
  margin: 16px auto;
  color: #fff;
  border-bottom: 1px solid rgba(117, 116, 116, 0.5);
  padding-bottom: 16px;
  width: 356px;
}

.modal_wrap .modal_box .modal_box_text {
  text-align: center;
  font-size: 15px;
  color: #fff;
  margin-top: 25px;
}

.modal_wrap .modal_box .btn_box {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 42px;
}

.modal_wrap .modal_box .btn_box span {
  display: inline-block;
  width: 80px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border: 1px solid #ccc;
  font-size: 14px;
  cursor: pointer;
  color: #fff;
}

.modal_wrap .modal_box .btn_box span:nth-child(2) {
  background: #409EFF;
  color: #fff;
  border-color: #409EFF;
  margin-left: 15px;
}</style>
