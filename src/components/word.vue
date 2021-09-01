<template>
  <div >
    <br>

<!--    <q-dialog persistent content-style="{ color: '#409EFF' }"-->
<!--                v-model="dialog"  v-if="dialog">-->
<!--      <div>-->
<!--        <q-card>-->
<!--          <q-card-section>-->
<!--        <q-form @submit="confirm">-->
<!--        <q-input label="输入身份" v-model="form.name" class="q-ma-md"></q-input>-->
<!--        <q-btn type="submit" color="primary" label="确认"></q-btn>-->
<!--        </q-form>-->
<!--          </q-card-section>-->
<!--          </q-card>-->
<!--      </div>-->
<!--    </q-dialog>-->


    <div class="row justify-center">
<!--      bind to num function is insert-->
    <q-input v-model="num" mask="###" label="输入今日的单词数目" class="q-ml-md"></q-input>
    <q-btn @click="insert" color="primary"  class="q-ml-md" label="确认"></q-btn>
    </div>
    <q-separator />

    <div class="row justify-center">
      <h6>本月曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container-month" >
      </div>
    </div>
    <div class="row justify-center">
      <h6>月度曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container-each-month" >
      </div>
    </div>
    <div class="row justify-center">
      <h6>总计曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container2" >
      </div>
    </div>
    <div class="row justify-center">
      <h6>每日曲线</h6>
      <div class="q-ma-md" style="max-width:80%;width:100%" id="container" >

      </div>
    </div>
  </div>
</template>

<script>
import {Column, Line} from '@antv/g2plot'
import {date} from '../Utils/utils'

export default {

  inject:['reload']
  ,
  methods:{
    insert(){
      let nameInfo = localStorage.getItem('username');
      this.$axios.post(this.api_url+'/api/study/wordpost',{name:nameInfo,number:this.num,date:this.date})
      .then(()=>{
        this.$q.notify({message:"successfully! you can reload the page to see the graph!",position:"center"});
        this.reload()
      }).catch(e=>{console.log(e)})
    },
     sum(arr) {
  return arr.reduce(function(acr, cur){
    return acr + cur;
  });
  },



  },
  data(){
    return{
      date:'',
      num:null,
      // dialog:false,
      form:[
        {name:''},
      ],
      draw_data:'',
      api_url:process.env.API_URL,
      toMonth:[],
      pastMonth:[]
    }
  },
  created() {
    this.d=date
  },
  mounted() {
    this.form.name = localStorage.getItem('username');
    let now = new Date();

    this.date = date.formatDate(now,"YYYY-MM-DD");

    this.$axios.get(this.api_url + "/api/study/word")
      .then(res => {
        let data = res.data;

        const line = new Line('container', {
            data,
            xField: 'date',
            yField: 'number',
            seriesField: 'name',
            autoFit: true

          }
        )
        let t = Date.now()
        let toMon = this.d.formatDate(t, 'YYYY-MM')

        for (let i = 0; i < res.data.length; i++) {
          let tmp = this.d.formatDate(this.d.extractDate(data[i]["date"], 'YYYY-MM-DD'), 'YYYY-MM')
          if (tmp === toMon) {
            this.toMonth.push(data[i])
          }
          else if(tmp<toMon){
            // console.log({name:data[i]["name"],number:data[i]["number"]})
              this.pastMonth.push({name:data[i]["name"],number:data[i]["number"],month:tmp})
          }
        }
        const groupBy = (list, k,value,v2) =>
          list.reduce((result, item) => {
            result[item[k]] = [...(result[item[k]] || []),{ number:item[value],month:item[v2]}]
            return result
          }, {})


        // console.log(this.pastMonth)
        data = this.toMonth

        const line2 = new Line('container-month', {
            data,
            xField: 'date',
            yField: 'number',
            seriesField: 'name',
            autoFit: true
          }
        )

        data=[]
        this.pastMonth = groupBy(this.pastMonth,'month','number','name')
        for(let i in this.pastMonth){
          const group = (list, k,value) =>
            list.reduce((result, item) => {
              result[item[k]] = [...(result[item[k]] || []),item[value]]
              return result
            }, {})

          let temp = group(this.pastMonth[i],'month','number')
          for(let n in  temp){
            let tt = this.sum(temp[n])
            data.push({month:i,sum:tt,name:n})
          }

        }
        console.log(data)
        const line3 = new Line('container-each-month', {
            data,
            xField: 'month',
            yField: 'sum',
            seriesField: 'name',
            autoFit: true
          }
        )

        line.render();
        line2.render()
        line3.render()
      });
    axios.get(this.api_url + "/api/study/wordtotal")
      .then(res => {
        const data = res.data;
        const column = new Column('container2', {
          data,
          xField: 'name',
          yField: 'total',

          autoFit: true,
          maxColumnWidth: 80,
          color: "#409EFF",

        })
        column.render();
      })


  },


}
</script>

<style scoped>
</style>
