<template>
  <div>
    <el-image
      :src="'http://localhost:8080/image/'+userInfo.face"
      style="width: 200px; height: 200px"
      :fit="fit"
    />
    <div
      class="right"
      style="width:900px; height: 200px"
    >
      <div> 
        欢迎用户：<el-input
          v-model="userInfo.username"
          style="font-size:30px;font-family:'Microsoft YaHei'"
          readonly="ture"
        />
        国籍：<el-input
          v-model="userInfo.country"
          readonly="ture"
        />
        生日：<el-input
          v-model="userInfo.birth"
          readonly="ture"
        />
        签名：<el-input
          v-model="userInfo.signature"
          readonly="ture"
        />
      </div>
    </div>
  </div>  
</template>
<script>
import store from '@/store'
import request from "@/utils/request"
export default {
  data(){
    return{
      userId : "",
      userInfo: {},
      fit:"cover"
    }
  },
  created(){
    this.userId = this.$route.params.userId;
    this.reloadData(this.userId);
  },
  methods:{
    reloadData(id){
      let url = "user/getUserInfoById";
            request.request({url,method:"post",headers:{'Content-Type':'application/json'},data:id})
            .then( response =>{
                this.userInfo = response.data;
            });
    }
  }
}
</script>
<style scoped>
.right{
    float: right;
}
</style>