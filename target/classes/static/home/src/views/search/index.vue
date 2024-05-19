<template>
  <div class="page_search search_index">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>



						  <list_result_search
				v-if="$check_action('/teacher_users/list', 'get')"
				:list="result_teacher_users_teachers_name"
				title="教师用户教师姓名"
				source_table="teacher_users"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/student_users/list', 'get')"
				:list="result_student_users_student_name"
				title="学生用户学生姓名"
				source_table="student_users"
			  ></list_result_search>
																	  <list_result_search
				v-if="$check_action('/student_users/list', 'get')"
				:list="result_student_users_professional_name"
				title="学生用户专业名称"
				source_table="student_users"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/learning_resource/list', 'get')"
				:list="result_learning_resource_teachers_name"
				title="学习课程教师姓名"
				source_table="learning_resource"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/learning_resource/list', 'get')"
				:list="result_learning_resource_resource_name"
				title="学习课程课程名称"
				source_table="learning_resource"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/assignment_information/list', 'get')"
				:list="result_assignment_information_teachers_name"
				title="作业信息教师姓名"
				source_table="assignment_information"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/assignment_information/list', 'get')"
				:list="result_assignment_information_assignment_title"
				title="作业信息作业标题"
				source_table="assignment_information"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/homework_submission/list', 'get')"
				:list="result_homework_submission_student_name"
				title="作业提交学生姓名"
				source_table="homework_submission"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/homework_submission/list', 'get')"
				:list="result_homework_submission_teachers_name"
				title="作业提交教师姓名"
				source_table="homework_submission"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/homework_correction/list', 'get')"
				:list="result_homework_correction_teachers_name"
				title="作业批改教师姓名"
				source_table="homework_correction"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/homework_correction/list', 'get')"
				:list="result_homework_correction_student_name"
				title="作业批改学生姓名"
				source_table="homework_correction"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/college_information/list', 'get')"
				:list="result_college_information_college_name"
				title="学院信息学院名称"
				source_table="college_information"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/professional_information/list', 'get')"
				:list="result_professional_information_professional_name"
				title="专业信息专业名称"
				source_table="professional_information"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/class_information/list', 'get')"
				:list="result_class_information_class_name"
				title="班级信息班级名称"
				source_table="class_information"
			  ></list_result_search>
						</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
						"result_teacher_users_teachers_name":[],
																		"result_student_users_student_name":[],
																	"result_student_users_professional_name":[],
															"result_learning_resource_teachers_name":[],
								"result_learning_resource_resource_name":[],
																								"result_assignment_information_teachers_name":[],
											"result_assignment_information_assignment_title":[],
															"result_homework_submission_student_name":[],
											"result_homework_submission_teachers_name":[],
																		"result_homework_correction_teachers_name":[],
											"result_homework_correction_student_name":[],
																		"result_college_information_college_name":[],
									"result_professional_information_professional_name":[],
									"result_class_information_class_name":[],
				};
  },
  methods: {

				/**
	 * 获取teachers_name
	 */
	get_teacher_users_teachers_name(){
		let url = "~/api/teacher_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_users_teachers_name = json.result.list;
			result_teacher_users_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_teacher_users_teachers_name = result_teacher_users_teachers_name
		 	}
		});
	},
																/**
	 * 获取student_name
	 */
	get_student_users_student_name(){
		let url = "~/api/student_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_users_student_name = json.result.list;
			result_student_users_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_users_student_name = result_student_users_student_name
		 	}
		});
	},
															/**
	 * 获取professional_name
	 */
	get_student_users_professional_name(){
		let url = "~/api/student_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "professional_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_users_professional_name = json.result.list;
			result_student_users_professional_name.map(o => o.title = o['professional_name'])
	  			this.result_student_users_professional_name = result_student_users_professional_name
		 	}
		});
	},
													/**
	 * 获取teachers_name
	 */
	get_learning_resource_teachers_name(){
		let url = "~/api/learning_resource/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_resource_teachers_name = json.result.list;
			result_learning_resource_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_learning_resource_teachers_name = result_learning_resource_teachers_name
		 	}
		});
	},
						/**
	 * 获取resource_name
	 */
	get_learning_resource_resource_name(){
		let url = "~/api/learning_resource/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "resource_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_resource_resource_name = json.result.list;
			result_learning_resource_resource_name.map(o => o.title = o['resource_name'])
	  			this.result_learning_resource_resource_name = result_learning_resource_resource_name
		 	}
		});
	},
																						/**
	 * 获取teachers_name
	 */
	get_assignment_information_teachers_name(){
		let url = "~/api/assignment_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_assignment_information_teachers_name = json.result.list;
			result_assignment_information_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_assignment_information_teachers_name = result_assignment_information_teachers_name
		 	}
		});
	},
									/**
	 * 获取assignment_title
	 */
	get_assignment_information_assignment_title(){
		let url = "~/api/assignment_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "assignment_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_assignment_information_assignment_title = json.result.list;
			result_assignment_information_assignment_title.map(o => o.title = o['assignment_title'])
	  			this.result_assignment_information_assignment_title = result_assignment_information_assignment_title
		 	}
		});
	},
													/**
	 * 获取student_name
	 */
	get_homework_submission_student_name(){
		let url = "~/api/homework_submission/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_homework_submission_student_name = json.result.list;
			result_homework_submission_student_name.map(o => o.title = o['student_name'])
	  			this.result_homework_submission_student_name = result_homework_submission_student_name
		 	}
		});
	},
									/**
	 * 获取teachers_name
	 */
	get_homework_submission_teachers_name(){
		let url = "~/api/homework_submission/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_homework_submission_teachers_name = json.result.list;
			result_homework_submission_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_homework_submission_teachers_name = result_homework_submission_teachers_name
		 	}
		});
	},
																/**
	 * 获取teachers_name
	 */
	get_homework_correction_teachers_name(){
		let url = "~/api/homework_correction/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "teachers_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_homework_correction_teachers_name = json.result.list;
			result_homework_correction_teachers_name.map(o => o.title = o['teachers_name'])
	  			this.result_homework_correction_teachers_name = result_homework_correction_teachers_name
		 	}
		});
	},
									/**
	 * 获取student_name
	 */
	get_homework_correction_student_name(){
		let url = "~/api/homework_correction/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_homework_correction_student_name = json.result.list;
			result_homework_correction_student_name.map(o => o.title = o['student_name'])
	  			this.result_homework_correction_student_name = result_homework_correction_student_name
		 	}
		});
	},
																/**
	 * 获取college_name
	 */
	get_college_information_college_name(){
		let url = "~/api/college_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "college_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_college_information_college_name = json.result.list;
			result_college_information_college_name.map(o => o.title = o['college_name'])
	  			this.result_college_information_college_name = result_college_information_college_name
		 	}
		});
	},
							/**
	 * 获取professional_name
	 */
	get_professional_information_professional_name(){
		let url = "~/api/professional_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "professional_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_professional_information_professional_name = json.result.list;
			result_professional_information_professional_name.map(o => o.title = o['professional_name'])
	  			this.result_professional_information_professional_name = result_professional_information_professional_name
		 	}
		});
	},
							/**
	 * 获取class_name
	 */
	get_class_information_class_name(){
		let url = "~/api/class_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_class_information_class_name = json.result.list;
			result_class_information_class_name.map(o => o.title = o['class_name'])
	  			this.result_class_information_class_name = result_class_information_class_name
		 	}
		});
	},
			
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
					this.get_teacher_users_teachers_name();
																	this.get_student_users_student_name();
																this.get_student_users_professional_name();
														this.get_learning_resource_teachers_name();
							this.get_learning_resource_resource_name();
																							this.get_assignment_information_teachers_name();
										this.get_assignment_information_assignment_title();
														this.get_homework_submission_student_name();
										this.get_homework_submission_teachers_name();
																	this.get_homework_correction_teachers_name();
										this.get_homework_correction_student_name();
																	this.get_college_information_college_name();
								this.get_professional_information_professional_name();
								this.get_class_information_class_name();
			  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
				  this.get_teacher_users_teachers_name();
																  this.get_student_users_student_name();
															  this.get_student_users_professional_name();
													  this.get_learning_resource_teachers_name();
						  this.get_learning_resource_resource_name();
																						  this.get_assignment_information_teachers_name();
									  this.get_assignment_information_assignment_title();
													  this.get_homework_submission_student_name();
									  this.get_homework_submission_teachers_name();
																  this.get_homework_correction_teachers_name();
									  this.get_homework_correction_student_name();
																  this.get_college_information_college_name();
							  this.get_professional_information_professional_name();
							  this.get_class_information_class_name();
				},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
