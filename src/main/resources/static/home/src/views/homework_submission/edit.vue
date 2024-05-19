<template>
	<div class="diy_edit page_homework_submission" id="homework_submission_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set', 'student_users') || $check_field('add', 'student_users') || $check_field('get', 'student_users')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								学生用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_users" :disabled="disabledObj['student_users_isDisabled']"
								v-model="form['student_users']"
								v-if="(form['student_users'] && $check_field('set', 'student_users')) || (!form['student_users'] && $check_field('add', 'student_users'))">
								<option v-for="o in list_user_student_users" :value="o['user_id']">
									{{ o['nickname'] + '-' + o['username'] }}
								</option>
							</select>
							<span v-else-if="$check_field('get', 'student_users')">{{ form['student_users'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set', 'student_name') || $check_field('add', 'student_name') || $check_field('get', 'student_name')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								学生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
								v-if="(form['student_name'] && $check_field('set', 'student_name')) || (!form['student_name'] && $check_field('add', 'student_name'))"
								:disabled="disabledObj['student_name_isDisabled']" />
							<span v-else-if="$check_field('get', 'student_name')">{{ form['${obj.name}'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set', 'teacher_users') || $check_field('add', 'teacher_users') || $check_field('get', 'teacher_users')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								教师用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_teacher_users" :disabled="disabledObj['teacher_users_isDisabled']"
								v-model="form['teacher_users']"
								v-if="(form['teacher_users'] && $check_field('set', 'teacher_users')) || (!form['teacher_users'] && $check_field('add', 'teacher_users'))">
								<option v-for="o in list_user_teacher_users" :value="o['user_id']">
									{{ o['nickname'] + '-' + o['username'] }}
								</option>
							</select>
							<span v-else-if="$check_field('get', 'teacher_users')">{{ form['teacher_users'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set', 'teachers_name') || $check_field('add', 'teachers_name') || $check_field('get', 'teachers_name')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								教师姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_teachers_name" v-model="form['teachers_name']" placeholder="请输入教师姓名"
								v-if="(form['teachers_name'] && $check_field('set', 'teachers_name')) || (!form['teachers_name'] && $check_field('add', 'teachers_name'))"
								:disabled="disabledObj['teachers_name_isDisabled']" />
							<span v-else-if="$check_field('get', 'teachers_name')">{{ form['${obj.name}'] }}</span>
						</div>
					</div>
					<div
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								课程名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_assignment_title" v-model="form['course_name']"
								placeholder="请输入课程名称"
								v-if="(form['course_name']) || (!form['course_name'] && $check_field('add', 'course_name'))"
								:disabled="disabledObj['assignment_title_isDisabled']" />
							<span v-else-if="$check_field('get', 'course_name')">{{ form['${obj.name}'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set', 'assignment_title') || $check_field('add', 'assignment_title') || $check_field('get', 'assignment_title')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								作业标题:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_assignment_title" v-model="form['assignment_title']"
								placeholder="请输入作业标题"
								v-if="(form['assignment_title'] && $check_field('set', 'assignment_title')) || (!form['assignment_title'] && $check_field('add', 'assignment_title'))"
								:disabled="disabledObj['assignment_title_isDisabled']" />
							<span v-else-if="$check_field('get', 'assignment_title')">{{ form['${obj.name}'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set', 'submission_content') || $check_field('add', 'submission_content') || $check_field('get', 'submission_content')"
						class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								提交内容:
							</span>
						</div>
						<!-- 富文本框 -->
						<!-- <el-input type="textarea" :rows="2" placeholder="请输入提交内容" v-model="form['submission_content']">
						</el-input> -->
						<!-- 文件 -->
						<input type="file" style="display: none;" id="form_file_submission_content"
							title="submission_content" @change="change_file($event.target.files, 'submission_content')" />
						<!-- 修改权限 -->
						<div class="diy_field diy_img"
							v-if="form['submission_content'] && $check_field('set', 'submission_content')">
							<label for="form_file_submission_content">
								<span>{{ form['submission_content'] }} </span>
								<a :href="$fullUrl(form['submission_content'])" target="_blank"
									style="color: rgb(64, 158, 255);">点击下载</a>
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img"
							v-else-if="!form['submission_content'] && $check_field('add', 'submission_content')">
							<label for="form_file_submission_content">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get', 'submission_content')">
							<span>{{ form['submission_content'] }} </span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import mixin from "@/mixins/page.js";
export default {
	mixins: [mixin],
	components: {},
	data() {
		return {
			url_get_obj: "~/api/homework_submission/get_obj?",
			url_add: "~/api/homework_submission/add?",
			url_set: "~/api/homework_submission/set?",

			// 登录权限
			oauth: {
				"signIn": true,
				"user_group": []
			},

			// 查询条件
			query: {
				"student_users": 0,
				"student_name": "",
				"teacher_users": 0,
				"teachers_name": "",
				"assignment_title": "",
				"submission_content": "",
				"homework_submission_id": 0,
			},

			obj: {
				"student_users": 0, // 学生用户
				"student_name": '', // 学生姓名
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"assignment_title": '', // 作业标题
				"submission_content": '', // 提交内容
				"homework_submission_id": 0,
			},

			// 表单字段
			form: {
				"student_users": 0, // 学生用户
				"student_name": '', // 学生姓名
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"assignment_title": '', // 作业标题
				"submission_content": '', // 提交内容
				"homework_submission_id": 0,
				"status" : 0,
				"course_name" : "",
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
			// 用户列表
			list_user_teacher_users: [],

			// ID字段
			field: "homework_submission_id",

		}
	},
	methods: {
		/**
		 * 提交前验证事件
		 * @param {Object} 请求参数
		 * @return {String} 验证成功返回null, 失败返回错误提示
		 */
		submit_check(param) {
			param.status = 0;
			console.log(param);
			return null;
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
		async get_user_session_student_users() {
			var _this = this;
			var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
			if (json.result && json.result.obj) {
				var source_table = json.result.obj.source_table;
				var user_id = _this.$store.state.user.user_id;
				if (user_id) {
					var url = "~/api/" + source_table + "/get_obj?"
					this.$get(url, { "user_id": _this.$store.state.user.user_id }, function (res) {
						if (res.result && res.result.obj) {
							var arr = []
							for (let key in res.result.obj) {
								arr.push(key)
							}
							var arrForm = []
							for (let key in _this.form) {
								arrForm.push(key)
							}
							_this.form["student_users"] = user_id
							_this.disabledObj['student_users' + '_isDisabled'] = true
							for (var i = 0; i < arr.length; i++) {
								if (arr[i] !== 'examine_state' && arr[i] !== 'examine_reply') {
									for (var j = 0; j < arrForm.length; j++) {
										if (arr[i] === arrForm[j]) {
											if (arr[i] !== "student_users") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						}
					});
				}
			}
			else if (json.error) {
				console.error(json.error);
			}
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
			}
			else if (json.error) {
				console.error(json.error);
			}
		},
		/**
		 * 修改文件
		 * @param { Object } files 上传文件对象
		 * @param { String } str 表单的属性名
		 */
		change_file(files, str) {
			var form = new FormData();
			form.append("file", files[0]);
			this.$post("~/api/homework_submission/upload?", form, (res) => {
				if (res.result) {
					this.form[str] = res.result.url;
				} else if (res.error) {
					this.$toast(res.error.message);
				}
			});
		},

		/**
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_before(param) {
			var form = $.db.get("form");
			// if (form) {
			//   delete(form.examine_state)
			//   delete(form.examine_reply)
			//   this.obj = $.push(this.obj ,form);
			// 	this.form = $.push(this.form ,form);
			// }
			// var arr = []
			// for (let key in form) {
			// 	arr.push(key)
			// }
			// for (var i=0;i<arr.length;i++){
			// 	this.disabledObj[arr[i] + '_isDisabled'] = true
			// }
			if (form) {
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				var arrForm = []
				for (let key in this.form) {
					arrForm.push(key)
				}
				for (var i = 0; i < arr.length; i++) {
					if (arr[i] !== 'examine_state' && arr[i] !== 'examine_reply') {
						for (var j = 0; j < arrForm.length; j++) {
							if (arrForm[j] === arr[i]) {
								this.form[arrForm[j]] = form[arr[i]]
								this.obj[arrForm[j]] = form[arr[i]]
								this.disabledObj[arrForm[j] + '_isDisabled'] = true
								break;
							}
						}
					}
				}
			}

			$.db.del("form");
			return param;
		},

		/**
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_after(json, func) {
			// var form = $.db.get("form");
			// var obj = Object.assign({} ,form ,this.obj);
			// if (obj) {
			//   delete(obj.examine_state)
			//   delete(obj.examine_reply)
			// 	this.obj = $.push(this.obj ,obj);
			// }
			// if (form) {
			//   delete(form.examine_state)
			//   delete(form.examine_reply)
			// 	this.form = $.push(this.form ,form);
			// }

			if (func) {
				func(json);
			}
		},

	},
	created() {
		this.get_user_session_student_users();
		this.get_list_user_student_users();
		this.get_list_user_teacher_users();
	},
}
</script>

<style></style>
