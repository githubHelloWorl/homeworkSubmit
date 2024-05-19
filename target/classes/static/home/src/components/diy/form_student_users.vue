<template>
	<div class="diy_form">
			<b-form-group v-if="$check_register_field('add','student_name','/student_users/view')" id="input-group-${idx}" label="学生姓名" label-for="input-${idx}">
			<b-form-input id="input-${idx}" v-model="form['student_name']" type="text" placeholder="学生姓名" trim></b-form-input>
		</b-form-group>
				<b-form-group v-if="$check_register_field('add','student_gender','/student_users/view')" id="input-group-${idx}" label="学生性别" label-for="input-${idx}">
			<b-form-select id="input-${idx}" v-model="form['student_gender']" :options="getDropList(list_student_gender)"></b-form-select>
		</b-form-group>
				<b-form-group v-if="$check_register_field('add','contact_information','/student_users/view')" id="input-group-${idx}" label="联系方式" label-for="input-${idx}">
			<b-form-input id="input-${idx}" v-model="form['contact_information']" type="text" placeholder="联系方式" trim></b-form-input>
		</b-form-group>
				<b-form-group v-if="$check_register_field('add','college_name','/student_users/view')" id="input-group-${idx}" label="学院名称" label-for="input-${idx}">
			<b-form-select id="input-${idx}" v-model="form['college_name']" :options="list_college_name"></b-form-select>
		</b-form-group>
				<b-form-group v-if="$check_register_field('add','professional_name','/student_users/view')" id="input-group-${idx}" label="专业名称" label-for="input-${idx}">
			<b-form-select id="input-${idx}" v-model="form['professional_name']" :options="list_professional_name"></b-form-select>
		</b-form-group>
				<b-form-group v-if="$check_register_field('add','class_name','/student_users/view')" id="input-group-${idx}" label="班级名称" label-for="input-${idx}">
			<b-form-select id="input-${idx}" v-model="form['class_name']" :options="list_class_name"></b-form-select>
		</b-form-group>
		</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: () => {
					return {

					}
				}
			}
		},
		data(){
			return {
				url_upload: "~/api/student_users/upload?",
				        list_student_gender: "男,女",
										        // 学院名称选项列表
        list_college_name: [],
						        // 专业名称选项列表
        list_professional_name: [],
						        // 班级名称选项列表
        list_class_name: [],
					}
		},
		methods: {
			// 下拉数据列表转换
			getDropList(e){
				let resultArr = [];
				if(e){
					let arr = e.split(",");
					arr.forEach(item => {
						resultArr.push({
							value: item,
							text: item
						})
					});
				}
				return resultArr;
			},
								      /**
       * 获取学院名称列表
       */
      async get_list_college_name() {
        let json = await this.$get("~/api/college_information/get_list?");
        if(json.result && json.result.list){
					this.list_college_name = [];
			json.result.list.forEach(item => {
				this.list_college_name.push({
					value: item.college_name,
					text: item.college_name
				})
			});
	            }
        else if(json.error){
          console.error(json.error);
        }
      },
            			      /**
       * 获取专业名称列表
       */
      async get_list_professional_name() {
        let json = await this.$get("~/api/professional_information/get_list?");
        if(json.result && json.result.list){
					this.list_professional_name = [];
			json.result.list.forEach(item => {
				this.list_professional_name.push({
					value: item.professional_name,
					text: item.professional_name
				})
			});
	            }
        else if(json.error){
          console.error(json.error);
        }
      },
            			      /**
       * 获取班级名称列表
       */
      async get_list_class_name() {
        let json = await this.$get("~/api/class_information/get_list?");
        if(json.result && json.result.list){
					this.list_class_name = [];
			json.result.list.forEach(item => {
				this.list_class_name.push({
					value: item.class_name,
					text: item.class_name
				})
			});
	            }
        else if(json.error){
          console.error(json.error);
        }
      },
            				/**
			 * 上传图片
			 * @param {Object} file 文件对象
			 * @param {key} 保存键名
			 */
			uploadFile(file, key = "img") {
				var _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', file[0]) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						// _this.form[key] = json.result.url;
						_this.$delete(_this.form,key);
						_this.$set(_this.form,key,json.result.url);
						console.log(_this.form[key])
					} else {
						_this.$toast('上传失败！');
					}
				});
			},
		},
    created() {
				      this.get_list_college_name();
		      this.get_list_professional_name();
		      this.get_list_class_name();
	    },
	mounted(){
							}
	}
</script>

<style>

.diy_fg.form-group {
    display: flex;
    justify-content: space-between;
}

.diy_in {
    width: 85%;
}
</style>
