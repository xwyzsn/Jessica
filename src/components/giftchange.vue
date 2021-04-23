<template>

  <div class="q-ma-md" >

    <q-list bordered padding class="rounded-borders" style="max-width: 100%;width: 100%;">
      <q-item-label header>兑换记录</q-item-label>
      <q-item clickable v-ripple v-for="item in g_list" :key="item.id">
        <q-item-section avatar top>
          <q-avatar :icon="mdiGift" color="grey-1" text-color="pink-6" />
        </q-item-section>

        <q-item-section>
          <q-item-label lines="1">{{item.name}}</q-item-label>
          <q-item-label caption>{{item.date}}</q-item-label>
        </q-item-section>
      </q-item>
    </q-list>



  </div>


</template>

<script>
import axios from "axios";
import {mdiGift} from "@quasar/extras/mdi-v5"
export default {
  data(){
    return{
      base_url:process.env.API_URL,
      g_list:[]
    }
  },

 mounted() {
   axios.get(this.base_url+'/api/study/giftlist')
   .then(res=>{this.g_list=res.data
   for(var i =0;i<this.g_list.length;i++){
     if(this.g_list[i].score==-50){
       this.g_list[i]["name"]="火锅"
     }
     else if(this.g_list[i].score==-10){
       this.g_list[i]["name"]="奶茶"
     }
     else if(this.g_list[i].score==-100){
       this.g_list[i]["name"]="meeting"
     }
     else if(this.g_list[i].score==-150){
       this.g_list[i]["name"]="wish"
     }
     else {
       this.g_list[i]["name"] = "未知"
     }
   }
   })
   .catch(err=>console.log(err))
 },
  created() {
    this.mdiGift=mdiGift
  }
}
</script>

<style scoped>

</style>
