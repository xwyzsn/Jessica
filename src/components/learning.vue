<template>
  <div class="about"  >
    <q-form class="q-ma-lg">
      <div style="width:100%;text-align:center" >
        <div class="q-ma-lg" style="margin-left:25%; max-width: 50%">
<!--          需要捆绑的类型是 num-->
        <q-input v-model="num" label="输入学时" mask="###">

        </q-input>
        </div>
        <div class="q-mt-lg">
<!--          确认积分：addscore 查看积分：open-->
        <q-btn color="primary" @click="addScore" label="确认积分"></q-btn>
          <q-btn @click="open" label="查看积分" class="q-ml-md"></q-btn>
        </div>
      </div>
    </q-form>

    <q-separator />
    <q-form  @submit="addToDo" >
      <div style="width:100%;text-align:center">
        <div class="q-pa-md" style="margin-left:25%; max-width: 50%">
          <strong style="text-align: center">输入待办事项</strong>
        </div>
        <div class="q-pa-md" style="margin-left:25%; max-width: 50%">
          <q-input filled v-model="value1">
            <template v-slot:append>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
                  <q-date v-model="value1" mask="YYYY-MM-DD">
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="Close" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
        <div class="q-pa-md" style="margin-left:25%; max-width: 50%">
          <q-input
            class="input-text  "
            filled

            label="输入待办事项"
            v-model="textarea"
            outlined
            autogrow
          />
        </div>
        <div class="q-pa-md" style="margin-left:25%; max-width: 50%">

          <q-btn label="Submit" style="display:block;margin:0 auto" type="submit"  color="primary"/>
        </div>
      </div>
    </q-form>
    <q-separator />
    <p style="text-align: center;" class="q-mt-sm"><strong>待办事项</strong></p>
    <div>
      <div class="row q-mt-md" v-for="(num,index) in this.todo.length" :key="index" v-bind="todo">
        <q-card :v-bind="todo" style="width: 70%;margin-left: 15%">
          <q-card-section>
            截 止 日 期:    {{ todo[index]["date"] }}
            <q-btn    flat color="primary" @click.prevent="finish(index)">finish</q-btn>
          </q-card-section>
          <q-card-section>
            <p style="text-align: center">
              {{todo[index]['text']}}
            </p>
          </q-card-section>

        </q-card>

      </div>
    </div>

  </div>
</template>
<script>
import axios from "axios";
export default {
  inject:['reload'],
  data() {
    return {
      textarea: "",
      todo: [],
      test: [],
      num: null,
      value1: '2021-04-06',
      goalflag: 1,
      score: 0,
      api_url:process.env.API_URL
    };
  },

  methods: {
    addToDo() {
      if (
        this.textarea == null ||
        this.value1 == null ||
        this.textarea == "" ||
        this.value1 == ""
      ) {
        this.$q.notify({message:"please! check your input infomation!",position:"center"});
      }

      else {
        axios
          .post(this.api_url+"/api/study/todo", {
            date: this.value1,
            text: this.textarea,
          })
          .then((res) => {
            console.log(res);
          });
        this.reload()
        this.$q.notify({message:"add successfully! refresh the page to check!",position:"center"});
      }
    },
    finish(index) {
      var key = this.todo[index]["id"];
      axios
        .delete(this.api_url+"/api/study/todo/" + key)
        .then((res) => console.log(res));
      this.$q.notify({message:"GOOD JOB !",position:"center"});
      this.reload()
    },
    open() {
      axios
        .get(this.api_url+"/api/study")
        .then((res) => (this.test = res.data));
      var len = this.test.length;
      var sum = 0;
      for (var i = 0; i < len; i++) {
        sum += this.test[i]["score"];
      }

      this.$q.notify({message:
        "your total score is " +
        sum +
        "\n" +
        "if didn't update the latest data ,please click button again!"
      ,position:"center"});
    },
    addScore() {
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      if(month<10){
        month="0"+month
      }
      if(day<10){
        day = "0" +day
      }
      var str = year + "-" + month + "-" + day;
      var formdata = this.$qs.stringify({ date: str, score: this.num });

      axios({
        url: this.api_url+"/api/study",
        method: "post",
        data: formdata,
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      });
      this.reload()

      // axios
      //   .post("http://120.77.174.209:8888/api/study", this.$qs.stringify({ date: str, score: this.num }),{headers:{'content-type':'application/x-www-form-urlencoded'}})
      //   .then((res) => {
      //     console.log(res);
      //   });
      this.$q.notify({message:
        "sucess to post data to Database you can check it maybe 10 seconds after!"
      ,position:"center"});
    },
  },
  mounted() {

    function compare(a,b){
      if(a.date>=b.date){
        return 1;
      }
      if(a.date<b.date){
        return -1;
      }
      return 0;
    }


    axios
      .get(this.api_url+"/api/study/todolist")
      .then((res2) => {

        this.todo = res2.data
        this.todo=this.todo.sort(compare)})
      .catch((e) => console.log(e));
    axios
      .get(this.api_url+"/api/study")
      .then((res) => (this.test = res.data));

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if(month<10){
      month="0"+month
    }
    if(day<10){
      day="0"+day
    }
    const str = year + "-" + month + "-" + day;
    this.value1=str

  },
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
.input-text {
  width: 100%;
  height: 50px;
}
</style>
