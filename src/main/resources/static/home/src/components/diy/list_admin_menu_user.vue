<template>
  <div class="list_menu diy_menu">
    <router-link
            to="/user_center/index"
            class="menu_item"
            :class="{ selected: this.$route.path=== '/user_center/index'}"
     >
      <span class="left_span">个人首页</span>
      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
    </router-link>
          <router-link
          to="/teacher_users/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/teacher_users/table'}"
          v-if="user_group == '管理员' || $check_action('/teacher_users/table','get')">
        <span class="left_span">教师用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/student_users/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/student_users/table'}"
          v-if="user_group == '管理员' || $check_action('/student_users/table','get')">
        <span class="left_span">学生用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/learning_resource/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/learning_resource/table'}"
          v-if="user_group == '管理员' || $check_action('/learning_resource/table','get')">
        <span class="left_span">学习课程</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/assignment_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/assignment_information/table'}"
          v-if="user_group == '管理员' || $check_action('/assignment_information/table','get')">
        <span class="left_span">作业信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/homework_submission/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/homework_submission/table'}"
          v-if="user_group == '管理员' || $check_action('/homework_submission/table','get')">
        <span class="left_span">作业提交</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/homework_correction/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/homework_correction/table'}"
          v-if="user_group == '管理员' || $check_action('/homework_correction/table','get')">
        <span class="left_span">作业批改</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/college_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/college_information/table'}"
          v-if="user_group == '管理员' || $check_action('/college_information/table','get')">
        <span class="left_span">学院信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/professional_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/professional_information/table'}"
          v-if="user_group == '管理员' || $check_action('/professional_information/table','get')">
        <span class="left_span">专业信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/class_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/class_information/table'}"
          v-if="user_group == '管理员' || $check_action('/class_information/table','get')">
        <span class="left_span">班级信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
    
  <router-link
              to="/user/collect"
              class="menu_item"
              :class="{ selected: this.$route.path=== '/collect/list'}"
              v-if="user_group == '管理员' || $check_action('/collect/list','get')">
        <span class="left_span">收藏</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
  </router-link>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";
export default {
  mixins: [mixin],
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  },

  components: {
  },
};
</script>

<style scoped>
.list_menu {
  flex: 0 0 25%;
  border-bottom: 1px solid #eee;
}
.menu_item {
  display: flex;
  justify-content: space-between;
  padding: 15px 20px;
  border: 1px solid #eee;
  border-bottom: none;
  background-color: #fff;
}
.menu_item.selected {
  background-color: #000;
}
.menu_item.selected span {
  color: #fff ;
}
.content {
  flex: 0 0 74%;
}
</style>
