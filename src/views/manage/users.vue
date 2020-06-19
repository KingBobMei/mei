<template>
  <div>
    <!-- <div class="right"> -->
    <el-select
      v-model="roleId"
      style="100px"
      class="right"
      placeholder="请选择角色筛选用户"
    >
      <el-option
        v-for="r in roleDtoList"
        :key="r.id"
        :label="r.roleName"
        :value="r.id"
      />
    </el-select>
    <!-- </div> -->
    <el-table :data="userList">
      <el-table-column
        prop="username"
        label="用户名"
        style="50px"
      />
      <el-table-column
        label="头像"
        style="100px"
      >
        <template slot-scope="scope">
          <el-image
            :src="scope.row.face"
            style="width: 100px; height: 100px"
            :fit="fit"
          />
        </template>
      </el-table-column>
      <el-table-column
        prop="location"
        label="所属国家"
        style="50px"
      />
      <el-table-column
        prop="roleName"
        label="所属角色"
        style="50px"
      />
      <el-table-column>
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="toQuery(scope.row.userId)"
          >
            查看主页
          </el-button>
          <el-button
            type="danger"
            size="small"
            @click="toDelete(scope.row.userId)"
          >
            屏蔽该用户
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import request from "@/utils/request"
export default {
    data(){
        return{
            userList:[],
            fit:"cover",
            roleDtoList:[],
            roleId:"-1"
        }
    },
    created(){
        this.reloadUser();
        this.reloadRole();
    },
    methods:{
        reloadUser(){
            let url = "user/userList";
            request.request({
                url,
                method: "post",
                headers: {'Content-Type':'application/json'},
                data: this.roleId
                })
                .then(response =>  {this.userList = response.data});
        },
        reloadRole(){
            let url = "admin/roleList";
            request.request({url,method:"get",headers:{'Content-Type':'application/json'}})
                    .then(response => { this.roleDtoList = response.data});
        },
        toQuery(userId){
          console.log("要去查看别人的id" + userId);
          this.$router.push({ name: 'profileIndex', params:{userId : userId}});
        }
    }
}
</script>
<style scoped>
.right{
    float: right;
}
</style>