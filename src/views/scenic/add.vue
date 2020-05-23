<template>
  <div>
    <el-form
      :model="scenic"
      label-width="100px"
    >
      <el-form-item label="景点名称">
        <el-input v-model="scenic.scenicName" />
      </el-form-item>
      <el-form-item label="所属国家">
        <el-select
          v-model="scenic.country"
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
      <el-form-item label="所属分类">
        <el-select
          v-model="scenic.tag"
          placeholder="请选择分类"
        >
          <el-option
            v-for="c in allCountry"
            :key="c.id"
            :value="c.id"
            :label="c.countryNameCn"
          />
        </el-select>
      </el-form-item>
            
      <el-form-item label="简介">
        <el-input
          v-model="scenic.context"
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="sublimt"
        >
          景点发布
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
    data(){
        return {
            allCountry:[],
            scenic: {
                scenicName: "",
                country: "",
                tag: "",
                context: ""
            },
            tags: [],
        }
    },
    created(){
        this.reloadData();
    },
    methods: {
        reloadData(){
            let url = "country/list";
            request.get(url)
            .then(response => {
                this.allCountry = response.data
            })
        },
        sublimt(){
            let url = "/scenic/add";
            request.request({
                url,
                method: "post",
                headers: {
                    'Content-Type':'application/json'
                },
                data: this.scenic
            }).then(response =>{
                if(response.code === '2000'){
                    this.$message({
                        message: "添加成功",
                        type: "success"
                    })
                    this.reloadData();
                }
            })
        }
    }
    
}
</script>
<style scoped>
</style>