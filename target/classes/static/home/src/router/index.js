import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 媒体视频
	{
		path: '/user_center/index',
		name: 'user_center_index',
		component: () => import('../views/user_center/index.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},


	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue')
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue')
	},

	



	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	// 教师用户表格路由
	{
		path: '/teacher_users/table',
		name: '/teacher_users_table',
		component: () => import('../views/teacher_users/table.vue')
	},
	// 教师用户详情路由
	{
		path: '/teacher_users/view',
		name: '/teacher_users_view',
		component: () => import('../views/teacher_users/view.vue')
	},
	
	
		// 学生用户表格路由
	{
		path: '/student_users/table',
		name: '/student_users_table',
		component: () => import('../views/student_users/table.vue')
	},
	// 学生用户详情路由
	{
		path: '/student_users/view',
		name: '/student_users_view',
		component: () => import('../views/student_users/view.vue')
	},
	
	
		// 学习课程表格路由
	{
		path: '/learning_resource/table',
		name: '/learning_resource_table',
		component: () => import('../views/learning_resource/table.vue')
	},
	// 学习课程详情路由
	{
		path: '/learning_resource/view',
		name: '/learning_resource_view',
		component: () => import('../views/learning_resource/view.vue')
	},
	
		// 学习课程列表路由
	{
		path: '/learning_resource/list',
		name: '/learning_resource_list',
		component: () => import('../views/learning_resource/list.vue')
	},
	
		// 学习课程详情路由
	{
		path: '/learning_resource/details',
		name: '/learning_resource_details',
		component: () => import('../views/learning_resource/details.vue')
	},
		// 作业信息表格路由
	{
		path: '/assignment_information/table',
		name: '/assignment_information_table',
		component: () => import('../views/assignment_information/table.vue')
	},
	// 作业信息详情路由
	{
		path: '/assignment_information/view',
		name: '/assignment_information_view',
		component: () => import('../views/assignment_information/view.vue')
	},
	
		// 作业信息列表路由
	{
		path: '/assignment_information/list',
		name: '/assignment_information_list',
		component: () => import('../views/assignment_information/list.vue')
	},
	
		// 作业信息详情路由
	{
		path: '/assignment_information/details',
		name: '/assignment_information_details',
		component: () => import('../views/assignment_information/details.vue')
	},
		// 作业提交表格路由
	{
		path: '/homework_submission/table',
		name: '/homework_submission_table',
		component: () => import('../views/homework_submission/table.vue')
	},
	// 作业提交详情路由
	{
		path: '/homework_submission/view',
		name: '/homework_submission_view',
		component: () => import('../views/homework_submission/view.vue')
	},
		// 作业提交添加路由
	{
		path: '/homework_submission/edit',
		name: '/homework_submission_edit',
		component: () => import('../views/homework_submission/edit.vue')
	},
	
	
		// 作业批改表格路由
	{
		path: '/homework_correction/table',
		name: '/homework_correction_table',
		component: () => import('../views/homework_correction/table.vue')
	},
	// 作业批改详情路由
	{
		path: '/homework_correction/view',
		name: '/homework_correction_view',
		component: () => import('../views/homework_correction/view.vue')
	},
	
	
		// 学院信息表格路由
	{
		path: '/college_information/table',
		name: '/college_information_table',
		component: () => import('../views/college_information/table.vue')
	},
	// 学院信息详情路由
	{
		path: '/college_information/view',
		name: '/college_information_view',
		component: () => import('../views/college_information/view.vue')
	},
	
	
		// 专业信息表格路由
	{
		path: '/professional_information/table',
		name: '/professional_information_table',
		component: () => import('../views/professional_information/table.vue')
	},
	// 专业信息详情路由
	{
		path: '/professional_information/view',
		name: '/professional_information_view',
		component: () => import('../views/professional_information/view.vue')
	},
	
	
		// 班级信息表格路由
	{
		path: '/class_information/table',
		name: '/class_information_table',
		component: () => import('../views/class_information/table.vue')
	},
	// 班级信息详情路由
	{
		path: '/class_information/view',
		name: '/class_information_view',
		component: () => import('../views/class_information/view.vue')
	},
	
	
	
	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "高校专业课程学生作业管理系统-home";
	document.title = title;
	document.logo = "高校专业课程学生作业管理系统"
})

export default router
