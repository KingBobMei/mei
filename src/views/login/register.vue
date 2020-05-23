<template>
  <div>
    <el-form
      ref="form"
      :model="userInfo"
      label-width="100px"
    >
      <el-form-item label="用户名">
        <el-input
          v-model="userInfo.username"
          style="width: 500px"
        />
      </el-form-item>

      <el-form-item label="注册手机号">
        <el-input
          v-model="userInfo.phone"
          style="width: 500px"
        />
      </el-form-item>
      <el-form-item label="注册国家">
        <el-select
          v-model="userInfo.location"
          placeholder="请选择国家"
        >
          <el-option
            v-for="c in allCountry"
            :key="c.id"
            :value="c.id"
            :label="c.countryNameCn"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生日">
        <el-col :span="11">
          <el-date-picker
            v-model="userInfo.birth"
            type="date"
            placeholder="选择日期"
            style="width: 39%;"
            value-format="timestamp"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="个人签名">
        <el-input v-model="userInfo.signature" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input
          v-model="userInfo.password"
          show-password
          style="width: 500px"
        />
      </el-form-item>
      <el-form-item label="校验密码">
        <el-input
          v-model="repassword"
          style="width: 500px"
          placeholder="请再次输入密码"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="onSubmit"
        >
          立即创建
        </el-button>
        <el-button @click="callOf">
          取消
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      allCountry:[],
      userInfo: {
        username: "",
        phone: "",
        location: "",
        birth: "",
        signature: "",
        password: ""
      },
      repassword:""
    };
  },
  created() {
    this.reloadData();
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    getLong() {
      birth = new Date(birth).getTime;
      console.log(birth)
    },
    onSubmit() {
      let url = "user/register";
      request
        .request({
          url,
          method: "post",
          headers: {
            'Content-Type':'application/json'
          },
          data: this.userInfo
        })
        .then(response =>{
          if(response.message === 'success'){
            console.log('register success dispath login')
            this.$message({
              message: "注册成功",
              type: "success"
            });
            this.relogin();
          }
        });
    },
    relogin(){
      this.$router.push({path: '/login'})
    },
    reloadData(){
      let url = "country/list";
      request.get(url)
      .then(response => {
        this.allCountry = response.data
      })
    },
    callOf(form){
      this.userInfo = {};
    }
  }
};
</script>