<template>
  <div>

    <div class="row ">
      <div class="col-3 q-ma-md">
        <q-img src="https://gitee.com/xwyzsn/Picture/raw/master/123456.jpg"></q-img>
        <q-radio label="milktea" val="1" v-model="radio"></q-radio>
      </div>
      <div class="col-3 q-ma-md">
        <q-img src="https://gitee.com/xwyzsn/Picture/raw/master/hotpot.jpg"></q-img>
        <q-radio label="hotpot" val="2" v-model="radio"></q-radio>
      </div>
      <div class="col-3 q-ma-sm">
        <q-img src="https://gitee.com/xwyzsn/Picture/raw/master/train.jpg"></q-img>
        <q-radio label="meeting" val="3" v-model="radio"></q-radio>
      </div>
    </div>
    <div class="row">
      <div class="col-3 q-ma-md">
        <q-img src="https://gitee.com/xwyzsn/Picture/raw/master/star.jpg"></q-img>
        <q-radio label="wish" val="4" v-model="radio"></q-radio>
      </div>
    </div>
    <div style="text-align: center">
<!--      function: confirm-->
      <q-btn color="primary" @click="confirm">确认</q-btn>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "gift",

  data(){
    return{
      radio: '0',
      total:[],
      api_url:process.env.API_URL
    }
  },
  methods:{
    confirm(){
      //120.77.174.209
      fetch( api_url+"/api/study/chart").then(res=>res.json()).then(data=>this.total=data)
      var len = this.total.length;
      var sum = 0;
      for(var i=0;i<len;i++){
        sum+=this.total[i]["score"];
      }
      var m = 0
      console.log(this.radio)
      if(this.radio=="0"){
        this.$q.notify({message:'啥都没选!',position:"center"})
      }
      else if (this.radio=="1" && sum>=10){
        this.$q.notify({message:"成功兑换一杯奶茶!"+new Date(),position:"center"})
        m=-10;
      }
      else if(this.radio=="2" &&sum>=50){
        this.$q.notify({message:"成功兑换一次火锅"+new Date(),position:"center"})
        m=-50;
      }
      else if(this.radio=="3" &&sum>=100){
        this.$q.notify({message:"成功兑换一次meeting"+new Date(),position:"center"})
        m=-100;
      }
      else if(this.radio=="4" &&sum>=150){
        this.$q.notify({message:"成功兑换一次心愿"+new Date(),position:"center"})
        m=-150;
      }
      else{
        this.$q.notify({message:"积分不够继续努力~",position:"center"})
      }
      var DATE = new Date();
      var d = DATE.getDate();
      var mon = DATE.getMonth()+1;
      var y = DATE.getFullYear();
      var str = y+"-"+mon+"-"+d;
      var formdata = this.$qs.stringify({ date: str, score: m });
      axios({
        url:api_url+"/api/study",
        method:'post',
        data:formdata,
        headers:{
          'Content-Type':'application/x-www-form-urlencoded'
        }
      })
    }

  },
  mounted(){
    fetch( api_url+"/api/study/chart").then(res=>res.json()).then(data=>this.total=data);
  }
}
</script>

<style >
.el-row {
  line-height: 10px;
  margin-bottom: 20px;


}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
