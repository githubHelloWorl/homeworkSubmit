<template>
	<div class="page_user my_home" id="user_address">
		<div class="warp">
			<div class="container">
				<div class="row justify-content-between">
					<div class="col-12 col-md-3">
						<div class="card_menu">
							<!-- 左侧边栏 -->
							<list_admin_menu_user></list_admin_menu_user>
						</div>
					</div>
					<div class="col-12 col-md-9">
						<div class="card_addres pl-2">
							<div class="warp">
								<div class="container-fluid">
									<el-row>
										<div>欢迎来到个人中心</div>
									</el-row>
									<el-row>
																<el-col v-if="user_group == '管理员' || $check_figure('/homework_correction/table')" :span="8">
											<div class="card chart">
														<newLineChart v-if="line_obj_homework_correction.values.length > 0" id="line_obj_homework_correction" :vm="line_obj_homework_correction" :title="'作业批改统计'">
												</newLineChart>
												<div v-if="!line_obj_homework_correction.values.length">作业批改没有符合条件的数据</div>
													</div>
										</el-col>
													</el-row>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_admin_menu_user from "@/components/diy/list_admin_menu_user.vue";
	import newLineChart from "@/components/charts/new_line_chart";
	export default {
		data() {
			return {
															line_obj_homework_correction:{
					names:[],
					xAxis: [],
					values:[]
				},
										};
		},
		mounted() {
		},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
														// 获取作业批改统计图
			async get_list_homework_correction() {
																								let group_by_value = "student_name";
				let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																		if (user_group=="教师用户"){
						sqlwhere+= "teacher_users = " + user_id + " or ";
					}
																																						if (user_group=="学生用户"){
						sqlwhere+= "student_users = " + user_id + " or ";
					}
																																																																						if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/homework_correction/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].student_name);
							}
							this.line_obj_homework_correction.names = name_list;
							this.get_list_homework_correction_sub("student_name",flag);
						}
				});
																							},
			async get_list_homework_correction_sub(v1,names_flag) {
																													let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																		if (user_group=="教师用户"){
						sqlwhere+= "teacher_users = " + user_id + " or ";
					}
																																						if (user_group=="学生用户"){
						sqlwhere+= "student_users = " + user_id + " or ";
					}
																																																																						if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/homework_correction/get_list?groupby=assignment_title",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(list[i].assignment_title);
											}
							this.line_obj_homework_correction.xAxis = xAxis_list;
							this.get_list_homework_correction_sub_sub(v1,"assignment_title",names_flag,flag);
						}
				});
																		},
			async get_list_homework_correction_sub_sub(v1,v2,names_flag,xAxis_flag) {
																																		let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																			if (user_group=="教师用户"){
							sqlwhere+= "teacher_users = " + user_id + " or ";
						}
																																							if (user_group=="学生用户"){
							sqlwhere+= "student_users = " + user_id + " or ";
						}
																																																																							if (sqlwhere.length>1){
							sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
							sqlwhere += ")";
							data.sqlwhere = sqlwhere;
						}
									}
								for (let i=0;i<this.line_obj_homework_correction.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_homework_correction.names.length;j++){
						data[v2] = this.line_obj_homework_correction.xAxis[i];
						data[v1] = this.line_obj_homework_correction.names[j];
						await this.$get(
								"~/api/homework_correction/sum?field=homework_grades",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_homework_correction.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_homework_correction.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_homework_correction.xAxis,true);
				}
													},
									},
		created() {
			setTimeout(() => {
									// 执行作业批改数据获取
			this.get_list_homework_correction();
							}, 1000);
		},
		components: {
			newLineChart,
			list_admin_menu_user
		},
	};
</script>

<style scoped>
	.container {
		min-height: 800px;
	}
</style>
