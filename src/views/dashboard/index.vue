<template>
  <div>
    <ul
      v-for="s in scenicList"
      :key="s"
    >
      <el-input
        v-model="s.scenicName"
        readonly="true"
      />
      <br>
      <el-image 
        style="width: 400px; height: 400px"
        :src="s.imageUrl"
        :fit="fit"
      />
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

</style>

