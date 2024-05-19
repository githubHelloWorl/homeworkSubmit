<template>
	<div class="diy_list page_learning_resource" id="learning_resource_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">学习课程列表</span>
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
									<b-form-input size="sm" class="mr-sm-2" placeholder="课程名称搜索"
										v-model="query['resource_name']" />
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
					<div class="row diy_list_box">
						<div class="col">
							<div v-if="list_learning_resource.length != 0">
								<!-- 列表 -->
								<list_learning_resource :list="list_learning_resource" />
							</div>
							<div v-else class="center">
								暂无课程
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
import list_learning_resource from "@/components/diy/list_learning_resource.vue";
import diy_pager from "@/components/diy/diy_pager";
import mixin from "@/mixins/page.js";
import Cookies from "js-cookie"
import { getList } from "@/api/learning_resource/api"

export default {
	mixins: [mixin],
	components: {
		diy_pager,
		list_learning_resource,
	},
	data() {
		return {
			url_get_list: "~/api/learning_resource/get_list?like=0",

			// 查询条件
			query: {
				keyword: "",
				page: 1,
				size: 12,
				"teachers_name": "", // 教师姓名
				"resource_name": "", // 课程名称
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
				name: "课程名称正序",
				value: "resource_name asc",
			},
			{
				name: "课程名称倒序",
				value: "resource_name desc",
			},
			],
			list_learning_resource: [],
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
			this.query.resource_name = ""
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
		// 获取学习课程列表
		async get_learning_resource() {
			let user = JSON.parse(Cookies.get("user"))
			let param;
			if (Cookies.get("user") !== undefined) {
				if (user.user_group !== "" && (user.user_group === "学生用户" || user.user_group === "教师用户")) {
					param = {
						"userId": user.user_id
					}
				}
			}

			param = {
				...param,
				"page": 1,
				"size": 12,
			}
			getList(param).then((res) => {
				console.log(res);
				if (res.status === 200) {
					res.data.forEach((result) => {
						if (result.teachers_name === user.nickname && user.user_group === "教师用户") {
							this.list_learning_resource.push(result)
						}
						if (user.user_group === "学生用户") {
							this.list_learning_resource.push(result)
						}
					})
				}
			})

		},

	},
	computed: {
	},
	created() {
		this.get_learning_resource();
	}
}
</script>

<style>
.center {
	text-align: center;
	color: rgb(114, 122, 130);
}
</style>
