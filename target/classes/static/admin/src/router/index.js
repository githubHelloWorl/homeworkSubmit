import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
    // 主页
    {
        path: '/',
        name: 'index',
        component: index,
        meta: {
            index: 0,
            title: '首页'
        }
    },

    // 登录
    {
        path: '/login',
        name: 'login',
        component: login,
        meta: {
            index: 0,
            title: '登录'
        }
    },

    
    // 忘记密码
    {
        path: '/forgot',
        name: "forgot",
        component: forgot,
        meta: {
            index: 0,
            title: '忘记密码'
        }
    },

    // 修改密码
    {
        path: '/user/password',
        name: "password",
        component: () => import("../views/user/password.vue"),
        meta: {
            index: 0,
            title: '修改密码'
        }
    },

    // 视频播放页
    {
        path: "/media/video",
        name: "video",
        component: () => import('../views/media/video.vue'),
        meta: {
            index: 0,
            title: "视频"
        }
    },

    // 音频播放页
    {
        path: "/media/audio",
        name: "audio",
        component: () => import('../views/media/audio.vue'),
        meta: {
            index: 0,
            title: "音频"
        }
    },

    
    
                // 轮播图路由
        {
            path: '/slides/table',
            name: 'slides_table',
            component: () => import('../views/slides/table.vue'),
            meta: {
                index: 0,
                title: '轮播图列表'
            }
        },
        {
            path: '/slides/view',
            name: 'slides_view',
            component: () => import('../views/slides/view.vue'),
            meta: {
                index: 0,
                title: '轮播图详情'
            }
        },
                            
    
    
            // 公告路由
        {
            path: '/notice/table',
            name: 'notice_table',
            component: () => import('../views/notice/table.vue'),
            meta: {
                index: 0,
                title: '通知公告列表'
            }
        },
        {
            path: '/notice/view',
            name: 'notice_view',
            component: () => import('../views/notice/view.vue'),
            meta: {
                index: 0,
                title: '通知公告详情'
            }
        },
                
            // 评论路由
        {
            path: '/comment/table',
            name: 'comment_table',
            component: () => import('../views/comment/table.vue'),
            meta: {
                index: 0,
                title: '评论列表'
            }
        },
        {
            path: '/comment/view',
            name: 'comment_view',
            component: () => import('../views/comment/view.vue'),
            meta: {
                index: 0,
                title: '评论详情'
            }
        },
        
            // 教师用户路由
        {
            path: '/teacher_users/table',
            name: 'teacher_users_table',
            component: () => import('../views/teacher_users/table.vue'),
            meta: {
                index: 0,
                title: '教师用户列表'
            }
        },
        {
            path: '/teacher_users/view',
            name: 'teacher_users_view',
            component: () => import('../views/teacher_users/view.vue'),
            meta: {
                index: 0,
                title: '教师用户详情'
            }
        },
            // 学生用户路由
        {
            path: '/student_users/table',
            name: 'student_users_table',
            component: () => import('../views/student_users/table.vue'),
            meta: {
                index: 0,
                title: '学生用户列表'
            }
        },
        {
            path: '/student_users/view',
            name: 'student_users_view',
            component: () => import('../views/student_users/view.vue'),
            meta: {
                index: 0,
                title: '学生用户详情'
            }
        },
            // 学习课程路由
        {
            path: '/learning_resource/table',
            name: 'learning_resource_table',
            component: () => import('../views/learning_resource/table.vue'),
            meta: {
                index: 0,
                title: '课程列表'
            }
        },
        {
            path: '/learning_resource/view',
            name: 'learning_resource_view',
            component: () => import('../views/learning_resource/view.vue'),
            meta: {
                index: 0,
                title: '课程详情'
            }
        },
            // 作业信息路由
        {
            path: '/assignment_information/table',
            name: 'assignment_information_table',
            component: () => import('../views/assignment_information/table.vue'),
            meta: {
                index: 0,
                title: '作业信息列表'
            }
        },
        {
            path: '/assignment_information/view',
            name: 'assignment_information_view',
            component: () => import('../views/assignment_information/view.vue'),
            meta: {
                index: 0,
                title: '作业信息详情'
            }
        },
            // 作业提交路由
        {
            path: '/homework_submission/table',
            name: 'homework_submission_table',
            component: () => import('../views/homework_submission/table.vue'),
            meta: {
                index: 0,
                title: '作业提交列表'
            }
        },
        {
            path: '/homework_submission/view',
            name: 'homework_submission_view',
            component: () => import('../views/homework_submission/view.vue'),
            meta: {
                index: 0,
                title: '作业提交详情'
            }
        },
            // 作业批改路由
        {
            path: '/homework_correction/table',
            name: 'homework_correction_table',
            component: () => import('../views/homework_correction/table.vue'),
            meta: {
                index: 0,
                title: '作业批改列表'
            }
        },
        {
            path: '/homework_correction/view',
            name: 'homework_correction_view',
            component: () => import('../views/homework_correction/view.vue'),
            meta: {
                index: 0,
                title: '作业批改详情'
            }
        },
            // 学院信息路由
        {
            path: '/college_information/table',
            name: 'college_information_table',
            component: () => import('../views/college_information/table.vue'),
            meta: {
                index: 0,
                title: '学院信息列表'
            }
        },
        {
            path: '/college_information/view',
            name: 'college_information_view',
            component: () => import('../views/college_information/view.vue'),
            meta: {
                index: 0,
                title: '学院信息详情'
            }
        },
            // 专业信息路由
        {
            path: '/professional_information/table',
            name: 'professional_information_table',
            component: () => import('../views/professional_information/table.vue'),
            meta: {
                index: 0,
                title: '专业信息列表'
            }
        },
        {
            path: '/professional_information/view',
            name: 'professional_information_view',
            component: () => import('../views/professional_information/view.vue'),
            meta: {
                index: 0,
                title: '专业信息详情'
            }
        },
            // 班级信息路由
        {
            path: '/class_information/table',
            name: 'class_information_table',
            component: () => import('../views/class_information/table.vue'),
            meta: {
                index: 0,
                title: '班级信息列表'
            }
        },
        {
            path: '/class_information/view',
            name: 'class_information_view',
            component: () => import('../views/class_information/view.vue'),
            meta: {
                index: 0,
                title: '班级信息详情'
            }
        },
    
    // 用户路由
    {
        path: '/user/table',
        name: 'user_table',
        component: () => import('../views/user/table.vue'),
        meta: {
            index: 0,
            title: '用户列表'
        }
    },
    {
        path: '/user/view',
        name: 'user_view',
        component: () => import('../views/user/view.vue'),
        meta: {
            index: 0,
            title: '用户详情'
        }
    },
    {
        path: '/user/info',
        name: 'user_info',
        component: () => import('../views/user/info.vue'),
        meta: {
            index: 0,
            title: '个人信息'
        }
    },
    // 用户组路由
    {
        path: '/user_group/table',
        name: 'user_group_table',
        component: () => import('../views/user_group/table.vue'),
        meta: {
            index: 0,
            title: '用户组列表'
        }
    },
    {
        path: '/user_group/view',
        name: 'user_group_view',
        component: () => import('../views/user_group/view.vue'),
        meta: {
            index: 0,
            title: '用户组详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    let token = to.query.token;
    if (token) {
        $.db.set("token", token, 120);
    }
    next();
})

router.afterEach((to, from, next) => {
    let title = "高校专业课程学生作业管理系统-admin";
    document.title = title;
})

export default router
