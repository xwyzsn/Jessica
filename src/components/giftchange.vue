<template>

  <div class="q-ma-md q-mt-lg" >

    <q-list bordered padding class="rounded-borders" style="max-width: 100%;width: 100%;">
      <q-item-label header>你的兑换记录</q-item-label>
      <q-item clickable v-ripple v-for="(item,index) in g_list" :key="item.id">
        <q-item-section avatar top>
          <q-avatar :icon="mdiGift" color="grey-1" text-color="pink-6" />
        </q-item-section>

        <q-item-section v-if="item.gift_finish=='t'" >
          <q-item-label lines="1" style="text-decoration: line-through;color: grey">{{item.name}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section v-else >
          <q-item-label lines="1"  >{{item.name}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section  v-if="item.gift_finish=='f'" >
          <q-checkbox v-model="not_finish[index]" label="未完成"></q-checkbox>
        </q-item-section>
        <q-item-section  v-else style="text-decoration: line-through;color: grey">
          <q-checkbox disable v-model="finish" label="已完成" ></q-checkbox>
        </q-item-section>
      </q-item>
      <div class="row justify-center q-mt-md" v-if="loading">
        <q-skeleton style="width: 60px;height: 36px"   class="q-mt-md"></q-skeleton>

      </div>
      <div class="row justify-center q-mt-md" v-else>
        <q-btn class="q-mt-md"  label="确认" color="blue-8" @click="gift_finish()"></q-btn>
      </div>
    </q-list>
    <q-separator class="q-ma-lg" />

    <q-list bordered padding class="rounded-borders" style="max-width: 100%;width: 100%;">
      <q-item-label header>等待你的兑换</q-item-label>
      <q-item clickable v-ripple v-for="(item) in my_g_list" :key="item.id">
        <q-item-section avatar top>
          <q-avatar :icon="mdiGift" color="grey-1" text-color="pink-6" />
        </q-item-section>

        <q-item-section v-if="item.gift_finish=='t'" >
          <q-item-label lines="1" style="text-decoration: line-through;color: grey">{{item.name}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section v-else >
          <q-item-label lines="1"  >{{item.name}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
        <q-item-section  v-if="item.gift_finish=='f'" >
          <q-checkbox disable  label="未完成"></q-checkbox>
        </q-item-section>
        <q-item-section  v-else style="text-decoration: line-through;color: grey">
          <q-checkbox disable v-model="finish" label="已完成" ></q-checkbox>
        </q-item-section>
      </q-item>
      <div class="row justify-center q-mt-md" v-if="loading">
        <q-skeleton style="width: 60px;height: 36px"   class="q-mt-md"></q-skeleton>

      </div>

    </q-list>


  </div>


</template>

<script>
import {mdiGift} from "@quasar/extras/mdi-v5"
export default {
  inject:['reload'],
  data(){
    return{
      base_url:process.env.API_URL,
      g_list:[],
      not_finish:[],
      finish:true,
      loading:true,
      my_g_list:[],
      my_not_finish:[]
    }
  },
  methods:{
    gift_finish(){

      for(var i=0;i<this.not_finish.length;i++){
        // console.log(this.not_finish[i])
        if(this.not_finish[i]==true){
          var formdata = new FormData()
          formdata.append('id',this.g_list[i].id)
          formdata.append('finish',"t")
          this.$axios({
            method:'PUT',
            url:this.base_url+'/api/study/finishgift',
            data:formdata,
            headers:{"Content-Type": "multipart/form-data" }
          })
          .then(()=>{this.reload()})


        }
      }


    }


  },

  mounted() {
    const getData = async ()=> {
     await this.$axios.get(this.base_url + '/api/study/giftlist')
        .then(res => {

          this.g_list = res.data.filter((item)=>{
            return item.username !==localStorage.getItem('username')
          })
          this.my_g_list  = res.data.filter((item)=>{
            return item.username === localStorage.getItem('username')
          })

          for (var i = 0; i < this.g_list.length; i++) {
            this.not_finish.push(false)
          }
          for (var i = 0; i < this.my_g_list.length; i++) {
            this.my_not_finish.push(false)
          }
          this.loading=!this.loading

        })
        .catch(err => console.log(err))
    }
    getData()
      },
  created() {
    this.mdiGift=mdiGift
  }
}
</script>

<style scoped>

</style>
