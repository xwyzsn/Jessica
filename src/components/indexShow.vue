<template>
  <div class="indexShow" >
        <span class="date q-ma-md">
            Today is the {{DiffDays()}} day we had been together!
        </span>
    <div class="row mobile-only " v-if="this.isloading==true" >
      <q-card v-for="i in this.numOfOnePage" :key="i" style="width: 80%;margin-left: 10%">
        <q-skeleton height="200px" square></q-skeleton>
        <q-item-label>
          <q-skeleton class="q-ml-sm" type="text" style="width: 50%;"></q-skeleton>
        </q-item-label>
        <q-item-label>
          <q-skeleton  class="q-ml-sm" type="text" style="width: 70%;"></q-skeleton>
        </q-item-label>
      </q-card>
    </div>
    <div class="row mobile-only" v-else>
      <q-card class="q-mt-sm" style="width: 80%;margin-left: 10%" v-for="(item,index) in this.picture.slice((this.current-1)*this.numOfOnePage,(this.current)*this.numOfOnePage)" :key="index">
        <q-card-section >
          <q-carousel
            swipeable
            animated
            v-model="slide[index]"
            infinite

          >
            <q-carousel-slide :name="index2+1" v-for="(i,index2) in item.path " :key="i">
              <q-img style="object-fit: cover;width:100%" :src="baseurl+i"></q-img>

            </q-carousel-slide>

          </q-carousel>
        </q-card-section>
        <q-separator />
        <q-card-section  >
          <div class="text-h6">{{item.group}}</div>
          <div class="text-subtitle2" style="color: grey">{{item.uploadtime}}</div>
          <div class="text-subtitle1">{{item.description}}</div>

        </q-card-section>


      </q-card>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination
        v-model="current"
        :max="total"
        :max-pages="2"
      >
      </q-pagination>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: 'indexShow',
  data(){
    return{
      date:new Date(),
      picture:[],
      isloading:true,
      slide:[],
      current:1,
      total:null,
      numOfOnePage:5,
      baseurl :'http://120.77.174.209:8080/pictures/'

    }
  },
  methods:{

    Time(){
      var  date =  new  Date();
      var  seperator1 =  "/" ;
      var  year = date.getFullYear();
      var  month = date.getMonth() + 1;
      var  strDate = date.getDate();

      if  (month >= 1 && month <= 9) {
        month =  "0"  + month;
      }
      if  (strDate >= 0 && strDate <= 9) {
        strDate =  "0"  + strDate;
      }
      var  currentdate = year + seperator1 + month + seperator1 + strDate;

      return  currentdate;

    },
    DiffDays(){
      var  aDate,  oDate1,  oDate2,  iDays
      var sDate1="2015/08/28";
      aDate  =  sDate1.split("/")
      oDate1  =  new  Date(aDate[1]  +  '/'  +  aDate[2]  +  '/'  +  aDate[0])    //转换为12-18-2002格式
      aDate  =  this.Time(this.date).split("/")
      oDate2  =  new  Date(aDate[1]  +  '/'  +  aDate[2]  +  '/'  +  aDate[0])
      iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
      return  iDays

    },



  },
  mounted(){
    //axios get picture wall information
    function compare(property){
      return function(obj1,obj2){
        var value1 = obj1[property];
        var value2 = obj2[property];
        return value1 - value2;     // 升序
      }
    }
    //TODO:picture should order by time,it can implemented in spring project

    const loadPictureInfo = async ()=>{
      await axios.get('http://120.77.174.209:8085/api/study/picture').then(res=>this.picture=res.data)
        .catch(err=>console.log(err))
      this.isloading=!this.isloading;
      this.picture.sort(compare("uploadtime"))
      this.picture.reverse()
      for (let i = 0; i<=this.picture.length; i++){
        this.slide.push(1)
      }
      this.total=this.picture.length/this.numOfOnePage +1

    }
    loadPictureInfo();
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
body{
  margin:0;
  padding:0;
}
.date{
  display: block;
  text-align: center;
  color: pink;

}
</style>
