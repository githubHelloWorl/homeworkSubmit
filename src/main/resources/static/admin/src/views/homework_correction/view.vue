<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-row class="row_ce">
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'teacher_users') || $check_field('add', 'teacher_users') || $check_field('set', 'teacher_users')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="教师用户" prop="teacher_users">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_teacher_users(form['teacher_users']) }}
							<!--<el-input id="business_name" v-model="form['teacher_users']" placeholder="请输入教师用户"-->
							<!--v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set','teacher_users')) || (!form['homework_correction_id'] && $check_field('add','teacher_users'))" :disabled="disabledObj['teacher_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','teacher_users')">{{form['teacher_users']}}</div>-->
							<el-select
								v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'teacher_users')) || (!form['homework_correction_id'] && $check_field('add', 'teacher_users'))"
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
							:disabled="disabledObj['teacher_users_isDisabled']">
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
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'teachers_name')) || (!form['homework_correction_id'] && $check_field('add', 'teachers_name'))"
							:disabled="disabledObj['teachers_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'teachers_name')">{{ form['teachers_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'student_users') || $check_field('add', 'student_users') || $check_field('set', 'student_users')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="学生用户" prop="student_users">
						<el-select
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'student_users')) || (!form['homework_correction_id'] && $check_field('add', 'student_users'))"
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
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'student_name') || $check_field('add', 'student_name') || $check_field('set', 'student_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="学生姓名" prop="student_name">
						<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'student_name')) || (!form['homework_correction_id'] && $check_field('add', 'student_name'))"
							:disabled="disabledObj['student_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'student_name')">{{ form['student_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程名称" prop="course_name">
						<el-select id="course_name" v-model="form['course_name']">
							<el-option v-for="o in course_name_list" :key="o" :label="o" :value="o">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'assignment_title') || $check_field('add', 'assignment_title') || $check_field('set', 'assignment_title')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="作业标题" prop="assignment_title">
						<el-input id="assignment_title" v-model="form['assignment_title']" placeholder="请输入作业标题"
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'assignment_title')) || (!form['homework_correction_id'] && $check_field('add', 'assignment_title'))"
							:disabled="disabledObj['assignment_title_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'assignment_title')">{{ form['assignment_title'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'homework_grades') || $check_field('add', 'homework_grades') || $check_field('set', 'homework_grades')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="作业成绩" prop="homework_grades">
						<el-input-number id="homework_grades" v-model.number="form['homework_grades']"
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'homework_grades')) || (!form['homework_correction_id'] && $check_field('add', 'homework_grades'))"
							:disabled="disabledObj['homework_grades_isDisabled']"></el-input-number>
						<div v-else-if="$check_field('get', 'homework_grades')">{{ form['homework_grades'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'correction_content') || $check_field('add', 'correction_content') || $check_field('set', 'correction_content')"
					:xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
					<el-form-item label="批改内容" prop="correction_content">
						<quill-editor v-model.number="form['correction_content']"
							v-if="user_group === '管理员' || (form['homework_correction_id'] && $check_field('set', 'correction_content')) || (!form['homework_correction_id'] && $check_field('add', 'correction_content'))">
						</quill-editor>
						<div v-else-if="$check_field('get', 'correction_content')" v-html="form['correction_content']">
						</div>
					</el-form-item>
				</el-col>
			</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item
					v-if="$check_action('/homework_correction/view', 'set') || $check_action('/homework_correction/view', 'add') || $check_option('/homework_correction/table', 'examine')">
					<el-button type="primary" @click="submit();updateStatus()">提交</el-button>
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
import { getList } from '@/api/homework_correction/api.js'
import { updateSubmission } from '@/api/homework_submission/api.js'
import Cookies from 'js-cookie'

export default {
	mixins: [mixin],
	data() {
		return {
			field: "homework_correction_id",
			url_add: "~/api/homework_correction/add?",
			url_set: "~/api/homework_correction/set?",
			url_get_obj: "~/api/homework_correction/get_obj?",
			url_upload: "~/api/homework_correction/upload?",
			query: {
				"homework_correction_id": 0,
			},
			form: {
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"student_users": 0, // 学生用户
				"student_name": '', // 学生姓名
				"assignment_title": '', // 作业标题
				"homework_grades": 0, // 作业成绩
				"correction_content": '', // 批改内容
				"homework_correction_id": 0, // ID
				"course_name": ''
			},
			disabledObj: {
				"teacher_users_isDisabled": false,
				"teachers_name_isDisabled": false,
				"student_users_isDisabled": false,
				"student_name_isDisabled": false,
				"assignment_title_isDisabled": false,
				"homework_grades_isDisabled": false,
				"correction_content_isDisabled": false,
			},
			// 用户列表
			list_user_teacher_users: [],
			// 用户组
			group_user_teacher_users: "",
			// 用户列表
			list_user_student_users: [],

			course_name_list: [],
		}
	},
	methods: {
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
			}
			else if (json.error) {
				console.error(json.error);
			}
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
	 * 获取学生用户用户列表
	 */
		async get_list_user_student_users() {
			// if(this.user_group !== "管理员" && this.form["student_users"] === 0) {
			//     this.form["student_users"] = this.user.user_id;
			// }
			var json = await this.$get("~/api/user/get_list?user_group=学生用户");
			if (json.result && json.result.list) {
				this.list_user_student_users = json.result.list;
			}
			else if (json.error) {
				console.error(json.error);
			}
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
		 * 获取对象之前
		 * @param {Object} param
		 */
		get_obj_before(param) {
			var form = "";
			// 获取缓存数据附加
			form = $.db.get("form");
			$.push(this.form, form);

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
				bl = this.$check_action('/homework_correction/table', 'add');
				console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_correction/table', 'set');
				console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_correction/view', 'add');
				console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_correction/view', 'set');
				console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/homework_correction/view', 'get');
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
		async getNameList() {
			await getList().then((res) => {
				this.course_name_list = res.data;
			})
		},
		async updateStatus(){
			let ass = JSON.parse(Cookies.get('ass'));
			console.log(ass);
			await updateSubmission(ass.homework_submission_id).then((result) => {
				if (result.status === 200) {
					Cookies.remove("ass");
				}
			})
		}
	},
	created() {
		this.get_list_user_teacher_users();
		this.get_group_user_teacher_users();
		this.get_list_user_student_users();
		this.getNameList();
		console.log(this.form);
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
}
</style>
