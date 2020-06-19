<template>
  <div>
    <el-input
      v-model="sortCountry"
      placeholder="所属国家"
      style="width: 200px"
    />
    <el-input
      v-model="scenicCategory"
      placeholder="景点类型"
      style="width: 200px"
    />
    <el-input
      v-model="name"
      placeholder="景点名称"
      style="width: 200px"
    />
    <hr>
    <el-table :data="scenicListDto">
      <el-table-column
        prop="id"
        label="编号"
      />
      <el-table-column
        prop="scenicName"
        label="景点名称"
      />
      <el-table-column
        prop="country"
        label="所属国家"
      />
      <el-table-column
        prop="tag"
        label="所属类型"
      />
      <el-table-column>
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="showDetail(scope.row.id)"
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="small"
            @click="toDelete(scope.row.id)"
          >
            禁用
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
        return {
            sortCountry:"",
            scenicCategory:"",
            name:"",
            scenicListDto:[],
            
        }
    },
    created(){
        this.reloadData()
    },
    methods:{
        reloadData(){
            let url = "http://localhost:8080/scenic/list"
            request.post(url)
            .then(response => 
                this.scenicListDto = response.data
            )
        },
        showDetail(id){
          this.$router.push({ path: 'scenic/editor', query: id});
        }
    }
}
</script>