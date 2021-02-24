<template>
  <div>
    <ul
      v-for="s in scenicList"
      :key="s"
    >
      <el-input
        v-model="s.scenicName"
        readonly="true"
        style="font-size:20px;font-family:'Microsoft YaHei'"
      />
      <br>
      <el-image 
        style="width: 400px; height: 400px"
        :src="s.imageUrl"
        :fit="fit"
      />
      <el-input
        v-model="s.context"
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 20}"
        label="介绍"
        readonly="true"
        style="width: 800px; height: 500px"
        class="right"
      />
      <br>
      更新时间：<el-input
        v-model="s.createTime"
        style="width: 200px;border:0px"
        readonly="true"
      />
      <br><br><br><br>
      <hr>
      <br>
    </ul>
  </div>
</template>

<script>
import request   from "@/utils/request";
export default {
  data(){
    return{
      scenicList:[],
      fit:"cover"
    }
  },
  created(){
    this.reloadData();
  },
  methods:{
    reloadData(){
      let url = 'scenic/getIndex';
            request.post(url)
            .then(response => 
                this.scenicList = response.data
            )
    }
  }
}
</script>
<style scoped>
.right{
    float: right;
}
</style>

