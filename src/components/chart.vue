<template>
  <div>
    <div
      id="container"
      style="width: 80%;margin-left: 10%; height: auto; align: center; margin-top: 150px"
    ></div>
  </div>
</template>

<script>
import {Bar} from '@antv/g2plot'

export default {

  data () {
    return {
      chartdata:[],
      api_url:process.env.API_URL
    }
  },
  mounted(){
    this.$axios.get(
      this.api_url+"/api/study/chart"
    ).then((res)=>{
      let temp = this.group(res.data,'username');
      var data=[];
      Object.keys(temp).forEach((key)=>{
        let sum=0;
         temp[key].forEach((v)=>{
           sum+=v.score;
         })
        data.push({name:key.toString(),value:sum})

      })

      const bar =new Bar('container',{
        data,
        xField:'value',
        yField:'name',
        seriesField:'name',
        minBarWidth: 40,
        maxBarWidth: 40,
      })
      bar.render();
    })

  },
  methods:{
    group (arr, key) {
      let prop;
      return arr.reduce(function(rv, x) {
        prop = x[key];
        delete x[key];
        (rv[prop] = (rv[prop] || [])).push(x);
        return rv;
      }, {});

    }

  }
}
</script>

<style>
</style>
