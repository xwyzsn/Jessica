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
export default {
  data () {
    return {
      chartdata:[]
    }
  },
  mounted(){
    fetch(
      "http://120.77.174.209:8085/api/study/chart2"
    ).then(res=>res.json()).then((data)=>{
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
