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


    <div class="row">
<!--      bind to num function is insert-->
    <q-input v-model="num" mask="###" label="输入今日的单词数目" class="q-ml-md"></q-input>
    <q-btn @click="insert" color="primary"  class="q-ml-md" label="确认"></q-btn>
    </div>
    <q-separator />
    <div class="row">
      <h6>每日曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container" >

      </div>
    </div>

    <div class="row">
      <h6>总计曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container2" >
      </div>
    </div>


    <!--    <el-row>-->
    <!--      <h6>每日曲线</h6>-->
    <!--    <div style="margin-top:2%" id="container" >-->
    <!--    </div>-->
    <!--    </el-row>-->
    <!--    <el-row>-->
    <!--      <h6>总单词数</h6>-->
    <!--      <div id="container2">-->

    <!--      </div>-->
    <!--    </el-row>-->
  </div>
</template>

<script>
import axios from "axios";
import {Line} from '@antv/g2plot'
import {Column} from "@antv/g2plot"
export default {


  methods:{
    insert(){
      var nameInfo = window.localStorage.getItem('name');
      axios.post(this.api_url+'/api/study/wordpost',{name:nameInfo,number:this.num,date:this.date});
      this.$q.notify({message:"successfully! you can reload the page to see the graph!",position:"center"});
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
    var date=new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    if(month<10){
      month="0"+month
    }
    if(day<10){
      day="0"+day
    }
    this.date=year+"-"+month+"-"+day;

    axios.get(this.api_url+"/api/study/word")
      .then(res=>{
        var data = res.data
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
        var data = res.data
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
.button{

  /*margin-top: 50%;*/
  /*margin-bottom: 5px;*/
  /*margin-left: 5%;*/
}
</style>
