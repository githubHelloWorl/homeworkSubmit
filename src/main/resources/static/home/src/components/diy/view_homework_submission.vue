<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-row class="row_ce">
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'student_users') || $check_field('add', 'student_users') || $check_field('set', 'student_users')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="学生用户" prop="student_users">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_users(form['student_users']) }}
							<!--<el-input id="business_name" v-model="form['student_users']" placeholder="请输入学生用户"-->
							<!--v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set','student_users')) || (!form['homework_submission_id'] && $check_field('add','student_users'))" :disabled="disabledObj['student_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_users')">{{form['student_users']}}</div>-->
							<el-select
								v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'student_users')) || (!form['homework_submission_id'] && $check_field('add', 'student_users'))"
								id="student_users" v-model="form['student_users']"
								:disabled="disabledObj['student_users_isDisabled']">
								<el-option v-for="o in list_user_student_users" :key="o['username']"
									:label="o['nickname'] + '-' + o['username']" :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get', 'student_users')" id="student_users"
								v-model="form['student_users']" :disabled="true">
								<el-option v-for="o in list_user_student_users" :key="o['username']"
									:label="o['nickname'] + '-' + o['username']" :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_users" v-model="form['student_users']"
							:disabled="disabledObj['student_users_isDisabled']">
							<el-option v-for="o in list_user_student_users" :key="o['username']"
								:label="o['nickname'] + '-' + o['username']" :value="o['user_id']">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'student_name') || $check_field('add', 'student_name') || $check_field('set', 'student_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="学生姓名" prop="student_name">
						<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'student_name')) || (!form['homework_submission_id'] && $check_field('add', 'student_name'))"
							:disabled="disabledObj['student_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'student_name')">{{ form['student_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'teacher_users') || $check_field('add', 'teacher_users') || $check_field('set', 'teacher_users')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="教师用户" prop="teacher_users">
						<el-select
							v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'teacher_users')) || (!form['homework_submission_id'] && $check_field('add', 'teacher_users'))"
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
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'teachers_name') || $check_field('add', 'teachers_name') || $check_field('set', 'teachers_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="教师姓名" prop="teachers_name">
						<el-input id="teachers_name" v-model="form['teachers_name']" placeholder="请输入教师姓名"
							v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'teachers_name')) || (!form['homework_submission_id'] && $check_field('add', 'teachers_name'))"
							:disabled="disabledObj['teachers_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'teachers_name')">{{ form['teachers_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col v-if="form['course_name']"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程名称" prop="course_name">
						<div>{{ form['course_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'assignment_title') || $check_field('add', 'assignment_title') || $check_field('set', 'assignment_title')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="作业标题" prop="assignment_title">
						<el-input id="assignment_title" v-model="form['assignment_title']" placeholder="请输入作业标题"
							v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'assignment_title')) || (!form['homework_submission_id'] && $check_field('add', 'assignment_title'))"
							:disabled="disabledObj['assignment_title_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'assignment_title')">{{ form['assignment_title'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'submission_content') || $check_field('add', 'submission_content') || $check_field('set', 'submission_content')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="提交内容" prop="submission_content">
						<div v-if="disabledObj['submission_content_isDisabled']">
							<div v-if="$check_field('get', 'submission_content')">
								<el-button type="primary"
									@click="$download($fullUrl(form['submission_content']), form['submission_content'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
						<div v-else>
							<el-upload
								v-if="user_group === '管理员' || (form['homework_submission_id'] && $check_field('set', 'submission_content')) || (!form['homework_submission_id'] && $check_field('add', 'submission_content'))"
								class="upload-demo" drag action="" style="max-width: 300px;width: 100%;"
								:http-request="upload_submission_content" :limit="1" accept="">
								<i class="el-icon-upload"></i>
								<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
							</el-upload>
							<div v-else-if="$check_field('get', 'submission_content')">
								<el-button type="primary"
									@click="$download($fullUrl(form['submission_content']), form['submission_content'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
					</el-form-item>
				</el-col>
			</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item
					v-if="$check_action('/homework_submission/view', 'set') || $check_action('/homework_submission/view', 'add') || $check_option('/homework_submission/table', 'examine')">
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


export default {
	mixins: [mixin],
	data() {
		return {
			field: "homework_submission_id",
			url_add: "~/api/homework_submission/add?",
			url_set: "~/api/homework_submission/set?",
			url_get_obj: "~/api/homework_submission/get_obj?",
			url_upload: "~/api/homework_submission/upload?",
			query: {
				"homework_submission_id": 0,
			},
			form: {
				"student_users": 0, // 学生用户
				"student_name": '', // 学生姓名
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"assignment_title": '', // 作业标题
				"submission_content": '', // 提交内容
				"homework_submission_id": 0, // ID
				"couse_name" : '',

			},
			disabledObj: {
				"student_users_isDisabled": false,
				"student_name_isDisabled": false,
				"teacher_users_isDisabled": false,
				"teachers_name_isDisabled": false,
				"assignment_title_isDisabled": false,
				"submission_content_isDisabled": false,
			},
			// 用户列表
			list_user_student_users: [],
			// 用户组
			group_user_student_users: "",
			// 用户列表
			list_user_teacher_users: [],
			course_name_list : [],
		}
	},
	methods: {
		/**
	 * 获取学生用户用户列表
	 */
		async get_list_user_student_users() {
			var json = await this.$get("~/api/user/get_list?user_group=学生用户");
			if (json.result && json.result.list) {
				this.list_user_student_users = json.result.list;
			}
			else if (json.error) {
				console.error(json.error);
			}
		},
		/**
 * 获取学生用户用户组
 */
		async get_group_user_student_users() {
			this.form["student_users"] = this.$store.state.user.user_id;
			var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
			if (json.result && json.result.obj) {
				this.group_user_student_users = json.result.obj;
			}
			else if (json.error) {
				console.error(json.error);
			}
		},
		get_user_session_student_users(id) {
			var _this = this;
			var user_id = { "user_id": id }
			var url = "~/api/" + _this.group_user_student_users.source_table + "/get_obj?"
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
					_this.form["student_users"] = id
					_this.disabledObj['student_users' + '_isDisabled'] = true
					for (var i = 0; i < arr.length; i++) {
						if (arr[i] !== 'examine_state' && arr[i] !== 'examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
								if (arr[i] === arrForm[j]) {
									if (arr[i] !== "student_users") {
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
		get_user_student_users(id) {
			var obj = this.list_user_student_users.getObj({ "user_id": id });
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
* 上传提交内容
* @param {Object} param 文件参数
*/
		upload_submission_content(param) {
			this.uploadFile(param.file, "submission_content");
		},
		/**
		 * 获取对象之前
		 * @param {Object} param
		 */
		get_obj_before(param) {
			var form = "";
			// 获取缓存数据附加
			form = $.db.get("form");
			$.push(this.form, form);
			console.log(this.form);
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
				bl = this.$check_action('/homework_submission/table', 'add');
				console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_submission/table', 'set');
				console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_submission/view', 'add');
				console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_submission/view', 'set');
				console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_submission/view', 'get');
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

	},
	created() {
		this.get_list_user_student_users();
		this.get_group_user_student_users();
		this.get_list_user_teacher_users();
	},
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
}</style>
