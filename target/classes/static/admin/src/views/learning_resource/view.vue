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
							<!--v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set','teacher_users')) || (!form['learning_resource_id'] && $check_field('add','teacher_users'))" :disabled="disabledObj['teacher_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','teacher_users')">{{form['teacher_users']}}</div>-->
							<el-select
								v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'teacher_users')) || (!form['learning_resource_id'] && $check_field('add', 'teacher_users'))"
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
							v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'teachers_name')) || (!form['learning_resource_id'] && $check_field('add', 'teachers_name'))"
							:disabled="disabledObj['teachers_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'teachers_name')">{{ form['teachers_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'resource_name') || $check_field('add', 'resource_name') || $check_field('set', 'resource_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程名称" prop="resource_name">
						<el-input id="resource_name" v-model="form['resource_name']" placeholder="请输入课程名称"
							v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'resource_name')) || (!form['learning_resource_id'] && $check_field('add', 'resource_name'))"
							:disabled="disabledObj['resource_name_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'resource_name')">{{ form['resource_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'college_name') || $check_field('add', 'college_name') || $check_field('set', 'college_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="学院名称" prop="college_name">
						<el-select id="college_name" v-model="form['college_name']"
							v-if="user_group === '管理员' || (form['teacher_users_id'] && $check_field('set', 'college_name')) || (!form['teacher_users_id'] && $check_field('add', 'college_name'))">
							<el-option v-for="o in list_college_name" :key="o['college_name']" :label="o['college_name']"
								:value="o['college_name']">
							</el-option>
						</el-select>
						<div v-else-if="$check_field('get', 'college_name')">{{ form['college_name'] }}</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'professional_name') || $check_field('add', 'professional_name') || $check_field('set', 'professional_name')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="专业名称" prop="professional_name">
						<el-select id="professional_name" v-model="form['professional_name']"
							v-if="user_group === '管理员' || (form['teacher_users_id'] && $check_field('set', 'professional_name')) || (!form['teacher_users_id'] && $check_field('add', 'professional_name'))">
							<el-option v-for="o in list_professional_name" :key="o" :label="o" :value="o">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'resource_images') || $check_field('add', 'resource_images') || $check_field('set', 'resource_images')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程图片" prop="resource_images">
						<el-upload :disabled="disabledObj['resource_images_isDisabled']" id="resource_images"
							class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg" action=""
							:http-request="upload_resource_images" :show-file-list="false"
							v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'resource_images')) || (!form['learning_resource_id'] && $check_field('add', 'resource_images'))">
							<img v-if="form['resource_images']" :src="$fullUrl(form['resource_images'])" class="avatar">
							<i v-else class="el-icon-plus avatar-uploader-icon"></i>
						</el-upload>
						<el-image v-else-if="$check_field('get', 'resource_images')" style="width: 100px; height: 100px"
							:src="$fullUrl(form['resource_images'])"
							:preview-src-list="[$fullUrl(form['resource_images'])]">
							<div slot="error" class="image-slot">
								<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
							</div>
						</el-image>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'resource_document') || $check_field('add', 'resource_document') || $check_field('set', 'resource_document')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程文档" prop="resource_document">
						<div v-if="disabledObj['resource_document_isDisabled']">
							<div v-if="$check_field('get', 'resource_document')">
								<el-button type="primary"
									@click="$download($fullUrl(form['resource_document']), form['resource_document'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
						<div v-else>
							<el-upload
								v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'resource_document')) || (!form['learning_resource_id'] && $check_field('add', 'resource_document'))"
								class="upload-demo" drag action="" style="max-width: 300px;width: 100%;"
								:http-request="upload_resource_document" :limit="1" accept="">
								<i class="el-icon-upload"></i>
								<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
							</el-upload>
							<div v-else-if="$check_field('get', 'resource_document')">
								<el-button type="primary"
									@click="$download($fullUrl(form['resource_document']), form['resource_document'])">下载<i
										class="el-icon-download el-icon--right"></i></el-button>
							</div>
						</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'resource_video') || $check_field('add', 'resource_video') || $check_field('set', 'resource_video')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程视频" prop="resource_video">
						<el-upload
							v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'resource_video')) || (!form['learning_resource_id'] && $check_field('add', 'resource_video'))"
							class="upload-demo" drag action="" style="max-width: 300px;width: 100%;"
							:http-request="upload_resource_video" :limit="1" accept="video/ogg,video/mp4,video/webm">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get', 'resource_video')">
							<el-button type="primary" @click="download(form['resource_video'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</el-form-item>
				</el-col>
				<el-col
					v-if="user_group === '管理员' || $check_field('get', 'resource_content') || $check_field('add', 'resource_content') || $check_field('set', 'resource_content')"
					:xs="24" :sm="12" :lg="8" class="el_form_item_warp">
					<el-form-item label="课程内容" prop="resource_content">
						<el-input type="textarea" id="resource_content" v-model="form['resource_content']"
							placeholder="请输入课程内容"
							v-if="user_group === '管理员' || (form['learning_resource_id'] && $check_field('set', 'resource_content')) || (!form['learning_resource_id'] && $check_field('add', 'resource_content'))"
							:disabled="disabledObj['resource_content_isDisabled']"></el-input>
						<div v-else-if="$check_field('get', 'resource_content')">{{ form['resource_content'] }}</div>
					</el-form-item>
				</el-col>
			</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item
					v-if="$check_action('/learning_resource/view', 'set') || $check_action('/learning_resource/view', 'add') || $check_option('/learning_resource/table', 'examine')">
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
import { getList } from "../../api/professional_information/api";

export default {
	mixins: [mixin],
	data() {
		return {
			field: "learning_resource_id",
			url_add: "~/api/learning_resource/add?",
			url_set: "~/api/learning_resource/set?",
			url_get_obj: "~/api/learning_resource/get_obj?",
			url_upload: "~/api/learning_resource/upload?",

			query: {
				"learning_resource_id": 0,
			},

			form: {
				"teacher_users": 0, // 教师用户
				"teachers_name": '', // 教师姓名
				"resource_name": '', // 课程名称
				"college_name" : '', // 学院名称
				"professional_name": '', // 专业名称"
				"resource_images": '', // 课程图片
				"resource_document": '', // 课程文档
				"resource_video": '', // 课程视频
				"resource_content": '', // 课程内容
				"learning_resource_id": 0, // ID

			},
			disabledObj: {
				"teacher_users_isDisabled": false,
				"teachers_name_isDisabled": false,
				"resource_name_isDisabled": false,
				"resource_images_isDisabled": false,
				"resource_document_isDisabled": false,
				"resource_video_isDisabled": false,
				"resource_content_isDisabled": false,
			},
			// 用户列表
			list_user_teacher_users: [],
			// 用户组
			group_user_teacher_users: "",
			// 学院名称选项列表
			list_college_name: [""],
			// 专业名称选项列表
			list_professional_name: []
		}
	},
	methods: {
		/**
	 * 获取学院名称列表
	 */
	 async get_list_college_name() {
			var json = await this.$get("~/api/college_information/get_list?");
			if (json.result && json.result.list) {
				this.list_college_name = json.result.list;
			}
			else if (json.error) {
				console.error(json.error);
			}
		},

		async get_list_professional_name() {
			await getList().then((res) => {
				this.list_professional_name = res.data
			})
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
* 上传课程图片
* @param {Object} param 图片参数
*/
		upload_resource_images(param) {
			this.uploadFile(param.file, "resource_images");
		},


		/**
* 上传课程文档
* @param {Object} param 文件参数
*/
		upload_resource_document(param) {
			this.uploadFile(param.file, "resource_document");
		},


		/**
* 上传课程视频
* @param {Object} param 视频参数
*/
		upload_resource_video(param) {
			this.uploadFile(param.file, "resource_video");
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
				bl = this.$check_action('/learning_resource/table', 'add');
				console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/learning_resource/table', 'set');
				console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/learning_resource/view', 'add');
				console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
			}
			if (!bl) {
				bl = this.$check_action('/learning_resource/view', 'set');
				console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
			}
			if (!bl) {
				bl = this.$check_action('/learning_resource/view', 'get');
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
		this.get_list_user_teacher_users();
		this.get_group_user_teacher_users();
		this.get_list_college_name();
		this.get_list_professional_name();
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
