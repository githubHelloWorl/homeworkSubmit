<template>
	<el-main class="bg edit_wrap comtable_e">
    <!-- TODO v-if="is_view()-->
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="true">
			<el-row class="row_ce">
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'teacher_users') || $check_field('add', 'teacher_users') || $check_field('set', 'teacher_users')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="教师用户" prop="teacher_users">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_teacher_users(form['teacher_users']) }}
							<!--<el-input id="business_name" v-model="form['teacher_users']" placeholder="请输入教师用户"-->
							<!--v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set','teacher_users')) || (!form['assignment_information_id'] && $check_field('add','teacher_users'))" :disabled="disabledObj['teacher_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','teacher_users')">{{form['teacher_users']}}</div>-->
							<el-select
								v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set', 'teacher_users')) || (!form['assignment_information_id'] && $check_field('add', 'teacher_users'))"
								id="teacher_users" v-model="form['teacher_users']"
								:disabled="disabledObj['teacher_users_isDisabled']">
								<el-option v-for="o in list_user_teacher_users" :key="o['username']"
									:label="o['nickname'] + '-' + o['username']" :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get', 'teacher_users')" id="teacher_users"
								v-model="form['teacher_users']" :disabled="true">
								<el-option v-for="o in list_user_teacher_users" :key="o['username']"
									:label="o['nickname'] + '-' + o['username']" :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="teacher_users" v-model="form['teacher_users']"
							:disabled="disabledObj['teacher_users_isDisabled']" @change="aaa">
              <!-- TODO aaa -->
							<el-option v-for="o in list_user_teacher_users" :key="o['username']"
								:label="o['nickname'] + '-' + o['username']" :value="o['user_id']" >
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'teachers_name') || $check_field('add', 'teachers_name') || $check_field('set', 'teachers_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="教师姓名" prop="teachers_name">
						<el-input id="teachers_name" v-model="form['teachers_name']" placeholder="请输入教师姓名"
							v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set', 'teachers_name')) || (!form['assignment_information_id'] && $check_field('add', 'teachers_name'))"
							:disabled="disabledObj['teachers_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'teachers_name')">{{ form['teachers_name'] }}</div>
					</el-form-item>
				</el-col>

        <el-col
            v-if="user_group === '管理员' || $check_field('get', 'teachers_name') || $check_field('add', 'teachers_name') || $check_field('set', 'teachers_name')"
            :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
          <el-form-item label="学院名称" prop="course_name">
            <el-select id="course_name" v-model="form['college_name']"
                       v-if="user_group != '学生用户' || (form['course_name'] && $check_field('set', 'course_name')) || (!form['homework_correction_id'] && $check_field('add', 'course_name'))" @change="bbb">
              <el-option v-for="o in college_name_list" :key="o" :label="o.college_name" :value="o">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

				<el-col
					v-if="user_group != '学生用户' || $check_field('get', 'course_name') || $check_field('add', 'course_name') || $check_field('set', 'course_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程名称" prop="course_name">
						<el-select id="course_name" v-model="form['course_name']"
							v-if="user_group != '学生用户' || (form['course_name'] && $check_field('set', 'course_name')) || (!form['homework_correction_id'] && $check_field('add', 'course_name'))" @click="console.log('bbb')">
							<el-option v-for="o in course_name_list" :key="o" :label="o.resource_name" :value="o.resource_name">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'assignment_title') || $check_field('add', 'assignment_title') || $check_field('set', 'assignment_title')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="作业标题" prop="assignment_title">
						<el-input id="assignment_title" v-model="form['assignment_title']" placeholder="请输入作业标题"
							v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set', 'assignment_title')) || (!form['assignment_information_id'] && $check_field('add', 'assignment_title'))"
							:disabled="disabledObj['assignment_title_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'assignment_title')">{{ form['assignment_title'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'course_cover') || $check_field('add', 'course_cover') || $check_field('set', 'course_cover')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程封面" prop="course_cover">
						<el-upload :disabled="disabledObj['course_cover_isDisabled']" id="course_cover"
							class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg" action=""
							:http-request="upload_course_cover" :show-file-list="false"
							v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set', 'course_cover')) || (!form['assignment_information_id'] && $check_field('add', 'course_cover'))">
							<img v-if="form['course_cover']" :src="$fullUrl(form['course_cover'])" class="avatar">
							<i v-else class="el-icon-plus avatar-uploader-icon"></i>
						</el-upload>
						<el-image v-else-if="$check_field('get', 'course_cover')" style="width: 100px; height: 100px"
							:src="$fullUrl(form['course_cover'])" :preview-src-list="[$fullUrl(form['course_cover'])]">
							<div slot="error" class="image-slot">
								<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
							</div>
						</el-image>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'assignment_content') || $check_field('add', 'assignment_content') || $check_field('set', 'assignment_content')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="作业内容" prop="assignment_content">
						<div v-if="disabledObj['assignment_content_isDisabled']">
							<div v-if="$check_field('get', 'assignment_content')">
								<el-button type="primary"
									@click="$download($fullUrl(form['assignment_content']), form['assignment_content'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
						<div v-else>
							<el-upload
								v-if="user_group === '管理员' || (form['assignment_information_id'] && $check_field('set', 'assignment_content')) || (!form['assignment_information_id'] && $check_field('add', 'assignment_content'))"
								class="upload-demo" drag action="" style="max-width: 300px;width: 100%;"
								:http-request="upload_assignment_content" :limit="1" accept="">
								<i class="el-icon-upload"></i>
								<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
							</el-upload>
							<div v-else-if="$check_field('get', 'assignment_content')">
								<el-button type="primary"
									@click="$download($fullUrl(form['assignment_content']), form['assignment_content'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
					</el-form-item>
				</el-col>
			</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item
					v-if="$check_action('/assignment_information/view', 'set') || $check_action('/assignment_information/view', 'add') || $check_option('/assignment_information/table', 'examine')">
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
				<el-form-item v-else>
					<el-button @click="cancel()">返回</el-button>
				</el-form-item>
			</el-col>
		</el-form>
	</el-main>
</template>

<script>
import mixin from "@/mixins/page.js";
import {getList, getListTestCollege, getListTestCourse, getTestCollege} from "../../api/learning_resource/api";

export default {
	mixins: [mixin],
	data() {
		return {
			field: "assignment_information_id",
			url_add: "~/api/assignment_information/add?",
			url_set: "~/api/assignment_information/set?",
			url_get_obj: "~/api/assignment_information/get_obj?",
			url_upload: "~/api/assignment_information/upload?",

			query: {
				"assignment_information_id": 0,
			},

			form: {
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"course_cover": '', // 课程封面
				"assignment_title": '', // 作业标题
				"assignment_content": '', // 作业内容
				"assignment_information_id": 0, // ID
				"course_name": '' , // 课程名称
        "college_name": ""  // 学院名称
			},
			disabledObj: {
				"teacher_users_isDisabled": false,
				"teachers_name_isDisabled": false,
				"course_cover_isDisabled": false,
				"assignment_title_isDisabled": false,
				"assignment_content_isDisabled": false,
			},


			// 用户列表
			list_user_teacher_users: [],
			// 用户组
			group_user_teacher_users: "",
			course_name_list: [],
      college_name_list: [],
		}
	},
	methods: {
    /**
     * TODO
     */
    async aaa() {
      console.log("this.form = ")
      console.log(this.form)

      // 得到学院
      await this.getCollegeNameList();

      // 得到教师的课程名称
      // await this.getCourseNameList();
      // console.log(this.form.teacher_users)
    },

    /**
     * 得到课程
     * @returns {Promise<void>}
     */
    async bbb() {
      // 得到学院
      await this.getCourseNameList();
    },

		/**
	 * 获取教师用户用户列表
	 */
		async get_list_user_teacher_users() {
			// if(this.user_group !== "管理员" && this.form["teacher_users"] === 0) {
			//     this.form["teacher_users"] = this.user.user_id;
			// }
			var json = await this.$get("~/api/user/get_list?user_group=教师用户");
			if (json.result && json.result.list) {
				this.list_user_teacher_users = json.result.list;
        console.log("this.list_user_teacher_users =")
        console.log(this.list_user_teacher_users)
			}
			else if (json.error) {
				console.error(json.error);
			}
		},
		/**
 * 获取教师用户用户组
 */
		async get_group_user_teacher_users() {
			this.form["teacher_users"] = this.user.user_id;
			var json = await this.$get("~/api/user_group/get_obj?name=教师用户");
			if (json.result && json.result.obj) {
				this.group_user_teacher_users = json.result.obj;
        // console.log("group_user_teacher_users = ")
        // console.log(this.group_user_teacher_users)
			}
			else if (json.error) {
				console.error(json.error);
			}

      // console.log("fdsafdsafdsafds =")
      // console.log(this.form)

      // TODO
      this.get_test_college();
		},
		get_user_session_teacher_users(id) {
			var _this = this;
			var user_id = { "user_id": id }
			var url = "~/api/" + _this.group_user_teacher_users.source_table + "/get_obj?"
			this.$get(url, user_id, function (res) {
				if (res.result && res.result.obj) {
					var arr = []
					for (let key in res.result.obj) {
						arr.push(key)
					}
					var arrForm = []
					for (let key in _this.form) {
						arrForm.push(key)
					}
					_this.form["teacher_users"] = id
					_this.disabledObj['teacher_users' + '_isDisabled'] = true
					for (var i = 0; i < arr.length; i++) {
						if (arr[i] !== 'examine_state' && arr[i] !== 'examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
								if (arr[i] === arrForm[j]) {
									if (arr[i] !== "teacher_users") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									} else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				}
			});
		},
		get_user_teacher_users(id) {
			var obj = this.list_user_teacher_users.getObj({ "user_id": id });
			var ret = "";
			if (obj) {
				if (obj.nickname) {
					ret = obj.nickname;
				}
				else {
					ret = obj.username;
				}
			}
			return ret;
		},


		/**
* 上传课程封面
* @param {Object} param 图片参数
*/
		upload_course_cover(param) {
			this.uploadFile(param.file, "course_cover");
		},
		/**
* 上传作业内容
* @param {Object} param 文件参数
*/
		upload_assignment_content(param) {
			this.uploadFile(param.file, "assignment_content");
		},
		/**
		 * 获取对象之前
		 * @param {Object} param
		 */
		get_obj_before(param) {
			var form = "";

			if (this.form && form) {
				Object.keys(this.form).forEach(key => {
					Object.keys(form).forEach(dbKey => {
						// if(dbKey === "charging_standard"){
						// 	this.form['charging_rules'] = form[dbKey];
						// 	this.disabledObj['charging_rules_isDisabled'] = true;
						// };
						if (key === dbKey) {
							this.disabledObj[key + '_isDisabled'] = true;
						}
					})
				})
			}
			$.db.del("form");

			return param;
		},

		/**
		 * 获取对象之后
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_after(json, func) {


		},

		/**
		 * 提交前验证事件
		 * @param {Object} 请求参数
		 * @return {String} 验证成功返回null, 失败返回错误提示
		 */
		submit_check(param) {
			return null;
		},

		is_view() {
			var bl = this.user_group == "管理员";

			if (!bl) {
				bl = this.$check_action('/assignment_information/table', 'add');
				console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/assignment_information/table', 'set');
				console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/assignment_information/view', 'add');
				console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/assignment_information/view', 'set');
				console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/assignment_information/view', 'get');
				console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
			}

			console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

			return bl;
		},
		/**
		 * 上传文件
		 * @param {Object} param
		 */
		uploadimg(param) {
			this.uploadFile(param.file, "avatar");
		},
		async getCourseNameList() {
      // TODO
			// await getList().then((result) => {
			// 	this.course_name_list = result.data
			// })
      await getListTestCourse(this.form.teacher_users, this.form.college_name).then((result) => {
        this.course_name_list = result.data.result.list
        console.log("this.course_name_list =")
        console.log(this.course_name_list)
      })
		},
    async getCollegeNameList() {
      // TODO
      // await getList().then((result) => {
      // 	this.course_name_list = result.data
      // })
      await getListTestCollege(this.form.teacher_users).then((result) => {
        this.college_name_list.splice(0);
        result.data.result.list.forEach((item,index)=>{
          console.log(item.college_name)
          if(item.college_name !== null && item.college_name !== undefined && item.college_name !== "" && !this.college_name_list.includes(item.college_name)){
            this.college_name_list.push(item.college_name);
          }
        });

        // this.college_name_list = result.data.result.list
        // console.log("this.college_name_list =")
        // console.log(this.college_name_list)
      })
    },

    // TODO
    async get_test_college() {
      console.log("this.form.teacher_users, this.form.course_name =")
      console.log(this.form)
      console.log(this.form.teacher_users, this.form.course_name)
      await getTestCollege(this.form.teacher_users, this.form.course_name).then((result) => {
        this.form.college_name = result.data.result.list.at(0).college_name;
        console.log("result.data.result.list.at(0).=")
        console.log(result.data.result.list.at(0).college_name)
      });
    }

	},
	created() {
		this.get_list_user_teacher_users();
		this.get_group_user_teacher_users();
    // console.log("list_user_teacher_users = ")
    // console.log(this.list_user_teacher_users)
		// this.getCourseNameList();
    // this.getCollegeNameList()
	}
}
</script>

<style>
.avatar-uploader .el-upload {
	border: 1px dashed #d9d9d9;
	border-radius: 6px;
	cursor: pointer;
	position: relative;
	overflow: hidden;
}

.avatar-uploader .el-upload:hover {
	border-color: #409EFF;
}

.avatar-uploader-icon {
	font-size: 28px;
	color: #8c939d;
	width: 178px;
	height: 178px;
	line-height: 178px;
	text-align: center;
}

.avatar {
	width: 178px;
	height: 178px;
	display: block;
}
</style>
