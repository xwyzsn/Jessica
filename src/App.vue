<template>
  <div id="q-app">
    <router-view v-if="isRouterAlive" />
        <q-ajax-bar
      ref="bar"
      position="top"
      color="accent"
      size="10px"
    />
  </div>
</template>
<script>
export default {
  name: 'App',
  provide (){
    return {
      reload:this.reload
    }
  },
  data(){
    return {
      isRouterAlive:true
    }
  },
  methods:{
    reload (){
      this.isRouterAlive = false
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  },
  components:{
  }
}
</script>
