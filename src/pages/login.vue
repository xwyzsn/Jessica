<template>
  <div v-if="pics" class="fullscreen my-bg"  v-bind:style="{backgroundImage: 'url('+this.pic+')'}">
  <div class="   q-pa-md absolute-center " style="max-width: 100%;width: 100%;background-color: white;opacity: 0.9">
    <div class="row text-h5 justify-center q-pb-lg">
      登录
    </div>
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="name"
        label="用户名"
        hint="输入你的用户名"
        lazy-rules
        :rules="[ val => val && val.length > 0 || '用户名不能为空']"
      />

      <q-input
        filled
        type="password"
        v-model="password"
        label="密码"
        lazy-rules
        :rules="[
          val => val !== null && val !== '' || '输入密码'
        ]"
      />


      <div class="row justify-center q-ma-md">
        <div class="col-3">
        <q-btn label="登录" type="submit" color="primary"/>
        </div>
        <div class="col-3">
        <q-btn label="重置" type="reset"     class="q-ml-sm" />
        </div>
        </div>
    </q-form>

  </div>
  </div>
</template>
<script>
import axios from "axios";
import qs from 'qs'
export default {
  name:'login',
  data () {
    return {
      name: null,
      password: null,
      pics:[],
      pic_url:[],
      api_url:process.env.API_URL

    }
  },

  methods: {
    onSubmit () {
      let form = qs.stringify({username:this.name,password:this.password})

      axios({
        method:"post",
        data:form,
        url:this.api_url+"/login"
      }).then(res=>{
        if (res.data.code===400){
          this.$q.notify({message:'用户名或密码错误',icon: 'error',position:"center"})
        }
        else {
        localStorage.setItem('auth',res.headers.authorization.toString())
        localStorage.setItem('username',this.name)
        this.$router.push({name:'main'})
        }
      })

    },

    onReset () {
      this.name = null
      this.password = null
      this.accept = false
    }
  },
  mounted() {
    fetch('https://jessica.xwyzsn.site/proxy/HPImageArchive.aspx?format=js&idx=5&n=5&mkt=zh-CN')
      .then(res=>res.json())
      .then(data=>{
        this.pics=data
        let m = Math.floor(Math.random()*5);
        this.pic="https://cn.bing.com/"+this.pics.images[m].url
        // this.show=true
      })
    if(localStorage.getItem('auth')){
      axios.get(this.api_url+'/api/study/user',{
        headers:{
          "Authorization":localStorage.getItem('auth')
        }
      })
      .then(res=>{
        if(res.data === "OK"){
          this.$router.push({name:'main'})
        }
        else{
          localStorage.clear();
        }
      })
    }
  }
}
</script>

<style scoped>
.my-bg{
  z-index: -1;
  opacity: 1;
}
</style>
