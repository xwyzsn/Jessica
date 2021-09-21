<template>
  <div>
    <div class="row ">
      <div class="col-3 q-ma-md">
        <q-img src="s://gitee.com/xwyzsn/Picture/raw/master/123456.jpg">
          <q-tooltip anchor="top middle" self="top middle"
                      transition-show="flip-right"
                      transition-hide="flip-left">10积分</q-tooltip>

        </q-img>
        <q-radio label="milktea" val="1" v-model="radio"></q-radio>
      </div>
      <div class="col-3 q-ma-md">
        <q-img src="s://gitee.com/xwyzsn/Picture/raw/master/hotpot.jpg" >
          <q-tooltip
            anchor="top middle" self="top middle"
            transition-show="flip-right"
            transition-hide="flip-left">50积分</q-tooltip>
        </q-img>
        <q-radio label="hotpot" val="2" v-model="radio"></q-radio>
      </div>
      <div class="col-3 q-mt-md">
        <q-img src="s://gitee.com/xwyzsn/Picture/raw/master/train.jpg">
          <q-tooltip           transition-show="flip-right"
                               transition-hide="flip-left"
                               anchor="top middle" self="top middle">100积分</q-tooltip>
        </q-img>
        <q-radio label="meeting" val="3" v-model="radio"></q-radio>
      </div>
    </div>
    <div class="row">
      <div class="col-3 q-ma-md">
        <q-img src="s://gitee.com/xwyzsn/Picture/raw/master/star.jpg">
          <q-tooltip
            anchor="top middle" self="top middle"
            transition-show="flip-right"
                               transition-hide="flip-left">150积分</q-tooltip>
        </q-img>
        <q-radio label="wish" val="4" v-model="radio"></q-radio>
      </div>
      <div class="col-3 q-ma-md">
        <q-img :ratio="2/2" src="s://gitee.com/xwyzsn/Picture/raw/master/image-20210421184556773.png">
          <q-tooltip
            anchor="top middle" self="top middle"
            transition-show="flip-right"
                               transition-hide="flip-left">5积分</q-tooltip>
        </q-img>
        <q-radio label="ice-cream" val="5" v-model="radio"></q-radio>
      </div>
    </div>
    <q-separator class="q-ma-md" />
    <div class="row justify-center">
      <div class="q-pa-md">
        <q-input
          v-model.number="num"
          type="number"
          filled
          style="max-width: 200px"
          hint="自由兑换区"
          label="输入消耗的积分数"
        />
      </div>
    </div>

    <div  class="row justify-center" >
<!--      function: confirm-->
      <q-btn color="primary " class="q-ma-lg" @click="confirm">确认</q-btn>
      <q-btn class="q-ma-lg "  color="white" @click="reset" text-color="black" >重置</q-btn>
    </div>

  </div>

</template>

<script>
import {date} from "../Utils/utils"
export default {
  name: "gift",

  data(){
    return{
      radio: '0',
      total:[],
      api_url:process.env.API_URL,
      num:undefined
    }
  },
  methods:{
    reset(){
      this.radio= '0';
      this.num=undefined
    },
    confirm(){
      //120.77.174.209


    const getData = async ()=>{
      this.$axios(this.api_url+"/api/study/chart").then(res=>this.total=res.data)

      let len = this.total.length;
      let sum = 0;
      let name = null;
      for(let i=0; i<len; i++){
        if(this.total[i]["username"]===localStorage.getItem('username'))
        sum+=this.total[i]["score"];
      }

      let m = 0;
      if(this.radio==="0" && this.num===undefined && this.num<=0){
        this.$q.notify({message:'啥都没选!',position:"center"})
      }
      else if (this.radio==="1" && sum>=10){
        this.$q.notify({message:"成功兑换一杯奶茶!"+new Date(),position:"center"})
        m=-10;
        name="奶茶"
      }
      else if(this.radio==="2" &&sum>=50){
        this.$q.notify({message:"成功兑换一次火锅"+new Date(),position:"center"})
        m=-50;
        name="火锅"
      }
      else if(this.radio==="3" &&sum>=100){
        this.$q.notify({message:"成功兑换一次meeting"+new Date(),position:"center"})
        m=-100;
        name="meeting"
      }
      else if(this.radio==="4" &&sum>=150){
        this.$q.notify({message:"成功兑换一次心愿"+new Date(),position:"center"})
        m=-150;
        name="wish"
      }
      else if(this.radio==="5"&&sum>=5){
        this.$q.notify({message:"成功兑换一次冰淇淋"+new Date(),position:"center"})
        m=-5
        name="冰淇淋"
      }
      else if(this.num&&this.num>=0 && sum>=this.num){
        this.$q.notify({message:"成功兑换消耗积分"+this.num+new Date(),position:"center"})
        m=-1*this.num;
        name = m+"积分"
      }
      else{
        this.$q.notify({message:"积分不够继续努力~",position:"center"})
      }
      let DATE = new Date();
      let now = date.formatDate(DATE,"YYYY-MM-DD")
      this.$axios.post(this.api_url+"/api/study/score",{date:now,score:m,gift_name:name,username:localStorage.getItem('username')})

      .catch(err=>{
        console.log(err)})


    }

      getData()

    }

  },
  mounted(){
    this.$axios( this.api_url+"/api/study/chart").then(res=>this.total = res.data)
  }
}
</script>

<style >

</style>
