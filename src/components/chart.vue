<template>
  <div>
    <div
      id="container"
      style="width: 80%;margin-left: 10%; height: auto; align: center; margin-top: 150px"
    ></div>
  </div>
</template>

<script>
import {Line} from '@antv/g2plot'
import axios from "axios";
export default {

  data () {
    return {
      chartdata:[],
      api_url:process.env.API_URL
    }
  },
  mounted(){
    axios.get(
      this.api_url+"/api/study/chart2"
    ).then((res)=>{
      var data =res.data
      const linePlot =new Line('container',{
        data,
        xField:'date',
        yField:'score',
      })
      linePlot.render();
    })

  }
}
</script>

<style>
</style>
