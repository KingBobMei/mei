<template>
  <div>
    <el-button
      type="primary"
      @click="showAddRole"
    >
      添加角色
    </el-button>
    <el-table :data="roleList">
      <el-table-column
        prop="roleName"
        label="角色名"
        style="150px"
      />
      <el-table-column
        prop="roleDesc"
        label="角色描述"
        style="150px"
      />
      <el-table-column
        prop="level"
        label="角色等级"
        style="150px"
      />
      <el-table-column
        prop="id"
        label="操作"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            plain=""
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="small"
            @click="toDelete(scope.row.id)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="visible">
      <el-form
        v-model="roleDTO"
        label-width="100px"
      >
        <el-form-item label="角色名">
          <el-input
            v-model="roleDTO.roleName"
            style="width:400px"
          />
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input
            v-model="roleDTO.roleDesc"
            style="width:400px"
          />
        </el-form-item>
        <el-form-item label="角色等级">
          <el-select
            v-model="roleDTO.level"
            style="width:100px"
          >
            <el-option
              v-for="l in levels"
              :key="l.value"
              :label="l.label"
              :value="l.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button
            type="info"
            plain
            @click="addRole"
          >
            添加
          </el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import request from "@/utils/request"
export default {
    data(){
        return{
            roleList:[],
            visible: false,
            roleDTO: {
                roleName: "",
                roleDesc: "",
                level: ""
            },
            levels:[
                {value:"1",label:"1"},
                {value:"2",label:"2"},
                {value:"3",label:"3"},
                {value:"4",label:"4"}
            ]
        }
    },
    created(){
        let url = "admin/roleList";
            request.request({url,method:"get",headers:{'Content-Type':'application/json'}})
            .then(response =>{
                this.roleList = response.data;
            });
    },
    methods:{
        reloadRole(){
            let url = "admin/roleList";
            request.request({url,method:"get",headers:{'Content-Type':'application/json'}})
            .then(response =>{
                this.roleList = response.data;
            });
        },
        showAddRole(){
            this.visible = true
        },
        addRole(){
            let url = "admin/addRole";
            request
                .request({
                url,
                method: "post",
                headers: {
                    'Content-Type':'application/json'
                },
                data: this.roleDTO
                })
                .then(response =>{
                if(response.message === 'success'){
                    this.$message({
                    message: "添加角色成功",
                    type: "success"
                    });
                    this.visible = false;
                    this.reloadRole();
                }
                });
        },
        toDelete(id){
            this.$confirm('是否删除该角色？','提示',{
                confirmButtonText:'确定',
                cancelButtonText:'取消',
                type: 'warning'
            }).then(() => {
                let url = "admin/deleteRole";
                request.request({url,method:"post",headers:{'Content-Type':'application/json'},data:id}).then(
                    response => {
                        if(response.code === '2000'){
                            this.$message({
                                message: '删除成功',
                                type: "success"
                            })
                            this.reloadRole();
                        }
                    }
                );
            })
            
        }
    }
}
</script>
<style scoped>

</style>