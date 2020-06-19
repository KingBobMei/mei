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

      <el-form-item label="上传主图">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/upload/picture"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img
            v-if="imageUrl"
            :src="imageUrl"
            class="avatar"
          >
          <i
            v-else
            class="el-icon-plus avatar-uploader-icon"
          />
        </el-upload>
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
                context: "",
                imageUrl:""
            },
            tags: [],
            imageUrl:"",
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
                    this.scenic = "";
                    this.imageUrl = "";
                }
            })
        },
        handleAvatarSuccess(res) {
          this.scenic.imageUrl = res.data;
          this.imageUrl = 'http://localhost:8080/image/' + res.data;
        },
        beforeAvatarUpload(file) {
          const isPicture = (file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg');
          const isLt2M = file.size / 1024 / 1024 < 2;
          if (!isPicture) {
            this.$message.error('上传是图片的类型');
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
          }
          return isPicture && isLt2M;
        }
    }
    
}
</script>
<style scoped>
.avatar-uploader .el-upload {
        border: 1px dashed #409EFF;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #409EFF;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>