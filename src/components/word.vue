<template>
  <div >
    <br>

    <q-dialog persistent content-style="{ color: '#409EFF' }"
                v-model="dialog"  v-if="dialog">
      <div>
        <q-card>
          <q-card-section>
        <q-form @submit="confirm">
        <q-input label="输入身份" v-model="form.name" class="q-ma-md"></q-input>
        <q-btn type="submit" color="primary" label="确认"></q-btn>
        </q-form>
          </q-card-section>
          </q-card>
      </div>
    </q-dialog>


    <div class="row justify-center">
<!--      bind to num function is insert-->
    <q-input v-model="num" mask="###" label="输入今日的单词数目" class="q-ml-md"></q-input>
    <q-btn @click="insert" color="primary"  class="q-ml-md" label="确认"></q-btn>
    </div>
    <q-separator />
    <div class="row justify-center">
      <h6>每日曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container" >

      </div>
    </div>

    <div class="row justify-center">
      <h6>总计曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container2" >
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import {Line} from '@antv/g2plot'
import {Column} from "@antv/g2plot"
export default {

  inject:['reload']
  ,
  methods:{
    insert(){
      let nameInfo = window.localStorage.getItem('name');
      axios.post(this.api_url+'/api/study/wordpost',{name:nameInfo,number:this.num,date:this.date})
      .then(()=>{
        this.$q.notify({message:"successfully! you can reload the page to see the graph!",position:"center"});
        this.reload()
      });
      //TODO: 还没部署到网站上,reload刷新页面

    },
    cancel(){
      this.dialog=false;
    },
    confirm(){

      window.localStorage.setItem('name',this.form.name);
      this.dialog=false;
    }

  },
  data(){
    return{
      date:'',
      num:null,
      dialog:false,
      form:[
        {name:''},
      ],
      draw_data:'',
      api_url:process.env.API_URL
    }
  },
  mounted() {
    if(window.localStorage.getItem('name')){
      window.localStorage.getItem('name');
    }
    else{
      this.dialog=true;
    }
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    if(month<10){
      month="0"+month
    }
    if(day<10){
      day="0"+day
    }
    this.date=year+"-"+month+"-"+day;

    axios.get(this.api_url+"/api/study/word")
      .then(res=>{
        const data = res.data;
        const line = new Line('container',{
          data,
          xField:'date',
          yField:'number',
          seriesField:'name',
          autoFit:true

        })
        line.render();

      });
    axios.get(this.api_url+"/api/study/wordtotal")
      .then(res=>{
        const data = res.data;
        const column = new Column('container2',{
          data,
          xField:'name',
          yField:'total',

          autoFit:true,
          maxColumnWidth:80,
          color:"#409EFF",

        })
        column.render();
      })


  }

}
</script>

<style scoped>
</style>
