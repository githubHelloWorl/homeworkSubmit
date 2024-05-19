<template>
	<div class="diy_list page_assignment_information" id="assignment_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">作业信息列表</span>
					</div>
				</div>
				<div class="leis_box">
					<div class="iudis_box">

						<div class="row diy_list_search">
							<div class="col">
								<!-- 搜索栏 -->
								<div class="view Search">
									<span class="diy_list_search_title">关键字搜索：</span>
									<b-form-input size="sm" class="mr-sm-2" placeholder="教师姓名搜索"
										v-model="query['teachers_name']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="作业标题搜索"
										v-model="query['assignment_title']" />
									<b-button size="sm" @click="search()">
										<b-icon icon="search" />
									</b-button>
								</div>
								<!-- /搜索栏 -->
							</div>
						</div>
						<div class="diy_list_select_box">
							<span class="diy_list_select_title">下拉搜索：</span>
							<div class="diy_list_dropdown_box">
								<div class="col">
									<!-- 筛选 -->
									<div class="view sift">
										<!-- 排序 -->

										<b-dropdown text="排序" variant="outline-dark" left>
											<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)">
												{{ o.name }}
											</b-dropdown-item>
										</b-dropdown>

										<!--/排序 -->
									</div>
									<!-- /筛选 -->
								</div>
							</div>

						</div>
					</div>
					<div class="row diy_list_box ">
						<div class="col">
							<div v-if="List.length != 0">
								<!-- 列表 -->
								<list_assignment_information :list="List" />
							</div>
							<div v-else class="center">
								暂无作业
							</div>

						</div>
					</div>
				</div>
				<!-- 大盒子结尾 -->
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
						<b-pagination v-model="query.page" :total-rows="count" :per-page="query.size" @change="goToPage" />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import list_assignment_information from "@/components/diy/list_assignment_information.vue";
import diy_pager from "@/components/diy/diy_pager";
import mixin from "@/mixins/page.js";
import { getList } from "../../api/assignment_information/api";
import Cookies from "js-cookie"
import axios from "axios";
export default {
	mixins: [mixin],
	components: {
		diy_pager,
		list_assignment_information,
	},
	data() {
		return {
			url_get_list: "~/api/assignment_information/get_list?like=0",

			// 查询条件
			query: {
				keyword: "",
				page: 1,
				size: 12,
				"teachers_name": "", // 教师姓名
				"assignment_title": "", // 作业标题
			},
			// 排序内容
			list_sort: [{
				name: "创建时间从高到低",
				value: "create_time desc",
			},
			{
				name: "创建时间从低到高",
				value: "create_time asc",
			},
			{
				name: "更新时间从高到低",
				value: "update_time desc",
			},
			{
				name: "更新时间从低到高",
				value: "update_time asc",
			},
			{
				name: "教师姓名正序",
				value: "teachers_name asc",
			},
			{
				name: "教师姓名倒序",
				value: "teachers_name desc",
			},
			{
				name: "作业标题正序",
				value: "assignment_title asc",
			},
			{
				name: "作业标题倒序",
				value: "assignment_title desc",
			},
			],

			List: [],


		}
	},
	methods: {
		get_list_before(param) {
		},
		/**
		 * 筛选选择
		 */
		filter_set(o, key) {
			if (o == "全部") {
				this.query[key] = "";
			} else {
				this.query[key] = o;
			}
			this.search();
		},

		/**
		 * 排序
		 */
		set_sort(o) {
			this.query.orderby = o.value;
			this.search();
		},
		/**
* 筛选
*/
		/**
* 重置
*/
		reset() {
			this.query.teachers_name = ""
			this.query.assignment_title = ""
			this.search();
		},

		// 返回条数
		toSize(i) {
			this.query.size = i;
			this.first();
		},

		// 返回页数
		toPage(i) {
			this.query.page = i;
			this.first();
		},

		goToPage(v) {
			this.query.page = v;
			this.goToNew(v)
		},

		async getAssignmentList() {
			let user = JSON.parse(Cookies.get("user"))
			let query = {
				page : 1,
				size : 1000
			}
			if (user.user_group === "教师用户") {
				await getList(query).then((res) => {
          console.log("res = ")
					console.log(res);
					res.data.forEach((result) => {
						if (result.teachers_name == user.nickname && user.user_group === "教师用户" && result.status == 1) {
							this.List.push(result);
						}
					})
				})
			} else if (user.user_group === "学生用户") {
				await axios.get("http://localhost:5000/api/assignment_information/getStudentAss?userId=" + user.user_id).then(result => {
          console.log("result = ")
          console.log(result);
          result.data.forEach(res => {
						this.List.push(res)
					})
				})
			}

		}

	},
	computed: {
	},
	created() {
		this.getAssignmentList();
	}
}
</script>

<style>
.center {
	text-align: center;
	color: rgb(114, 122, 130);
}
</style>
