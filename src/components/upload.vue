<template>

  <div class="q-pa-md">
    <q-stepper
      v-model="step"
      ref="stepper"
      color="primary"
      animated
      vertical
      keep-alive
      header-nav

    >
      <q-step
        :name="1"
        title="选择照片"
        icon="settings"
        :done="step > 1"
      >
        <q-uploader
          ref="uploader"
          :url=url
          label="可多选"
          multiple
          batch
          :headers="[{name: 'Authorization', value:auth}]"
          field-name="file"
          style="max-width: 50%;margin-left:25%;"
          class="q-mt-lg"
          :form-fields="additionalData"
          @start="showLoading"
          @finish="removeLoading"
        />

      </q-step>

      <q-step
        :name="2"
        title="添加标题"
        caption="默认为分组标题"
        icon="create_new_folder"
        :done="step > 2"
      >
        <q-input v-model="mytitle"   style="max-width: 50%;margin-left:25%;" label="输入相关标题" />
      </q-step>

      <q-step
        :name="4"
        title="添加描述信息"
        icon="add_comment"
      >
        <q-input v-model="description"   style="max-width: 50%;margin-left:25%;" label="输入相关描述" />
      </q-step>
      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn @click="moveNext" color="primary" v-if="step!=4" label="下一步" />
          <q-btn @click="finish" color="primary" v-else label="完成" />
          <q-btn v-if="step > 1" flat color="primary" @click="movePre" label="Back" class="q-ml-sm" />
        </q-stepper-navigation>
      </template>
    </q-stepper>

  </div>






</template>

<script>

export default {
  name: "upload",

  data () {
    return {
      step: 1,
      mytitle:'',
      description:'',
      date:'',
      url:'',
      api_url:process.env.API_URL,
      auth:'',
      timer:undefined
    }
  },
  created() {
    this.auth = localStorage.getItem('auth')
    },
  mounted() {

    var tmpDate=new Date()
    var day = tmpDate.getDate()
    var year = tmpDate.getFullYear()
    var month =tmpDate.getMonth()+1
    var hour = tmpDate.getHours()
    var min = tmpDate.getMinutes()
    if (month<10){
      month="0"+month
    }
    if(day<10){
      day ="0"+day
    }
    if(hour<10){
      hour="0"+hour
    }
    if(min<10){
      min="0"+min
    }
    this.date = year+"-"+month+"-"+day + " "+hour+":"+min
    this.url = this.api_url+'/api/study/upload'
  },
  methods:{
    additionalData(){
      //function called right before upload.use function but not static form data
      return [{name:'title',value:this.mytitle},
        {name:'description',value: this.description},
        {name:'uploadtime',value: this.date}
      ]
    },
    moveNext(){
      this.$refs.stepper.next()

    },
    movePre(){
      this.$refs.stepper.previous()
    }
    ,

    finish(){
        const UPLOAD =  ()=>{
           this.$refs.uploader.upload ()
        }
        UPLOAD()

    },
    showLoading () {
      this.$q.loading.show({
        message: '<span>正在上传图片</span><br/><span class="text-orange text-weight-bold">请稍后...</span>'
      })
    },
    removeLoading(){
      this.$q.loading.hide();
    }

  }
}
</script>

<style scoped>

</style>
