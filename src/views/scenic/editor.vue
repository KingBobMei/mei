<template>
  <div>
    <el-form v-model="scenicDTO">
      <el-form-item label="景点名称">
        <input
          v-model="scenicDTO.scenicName"
          readonly="true"
        >
      </el-form-item>
      <el-form-item label="景点区域">
        <input v-model="scenicDTO.country">
      </el-form-item>
      <el-form-item label="景点标签">
        <input v-model="scenicDTO.tag">
      </el-form-item>
      <el-form-item label="景点主图">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/upload/picture"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img
            v-if="'http://localhost:8080/image/'+scenicDTO.imageUrl"
            :src="'http://localhost:8080/image/'+scenicDTO.imageUrl"
            class="avatar"
          >
          <i
            v-else
            class="el-icon-plus avatar-uploader-icon"
          />
        </el-upload>
      </el-form-item>
      <el-form-item label="景点介绍">
        <textarea
          v-model="scenicDTO.context"
          style="width: 800px; height: 100px"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="small"
          @click="toSave()"
        >
          保存
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import request from "@/utils/request"
export default {
    data(){
        return {
            id: '',
            scenicDTO:{},
        }
    },
    created(){
        this.id = this.$route.params.id;
        this.reloadData(this.id);
    },
    methods:{
        reloadData(id){
            let url = "scenic/getScenicById";
            request.request({url,method:"post",headers:{'Content-Type':'application/json'},data:id})
            .then( response =>{
                this.scenicDTO = response.data;
            });
        },
        toSave(){
            let url = "/scenic/updateScenic";
            request.request({
                url,
                method: "post",
                headers: {
                    'Content-Type':'application/json'
                },
                data: this.scenicDTO
            }).then(response =>{
                if(response.code === '2000'){
                    this.$message({
                        message: "更新成功",
                        type: "success"
                    })
                }
            })
        },
        handleAvatarSuccess(res) {
          this.scenicDTO.imageUrl = res.data;
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