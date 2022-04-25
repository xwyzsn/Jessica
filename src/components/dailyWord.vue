<template>
<div>
  <div v-if="id">
    <div class="row justify-center q-mt-md text-h5 title">
      Word of today({{date}}) &nbsp; <strong style="font-style: oblique"> {{word}}</strong>
    </div>
    <q-separator class="q-ma-md" />
    <blockquote class="otro-blockquote">
      <div class="row justify-center q-mt-md text-h6 title">Meaning of&nbsp;
        <strong style="font-style: oblique"> {{word}}</strong>
      </div>
      <div class="q-ml-md q-mr-md " style="white-space: pre-line;">
        {{mean}}
      </div>
    </blockquote>
    <q-separator class="q-ma-md" />
    <blockquote class="otro-blockquote" >
      <div class="row justify-center q-mt-md text-h6 title">
        <strong style="font-style: oblique">{{word}}</strong>
        &nbsp;in context</div>
      <div class="q-ml-md q-mr-md " style="white-space: pre-line;top: 0;">
        {{context}}
      </div>
    </blockquote>
    <q-separator class="q-ma-md" />
    <blockquote class="otro-blockquote" >
      <div class="row justify-center q-mt-md text-h6 title">did you know ?</div>
      <div class="row q-ml-md q-mr-md content-start" style="white-space: pre-line;">
        {{didyouknow}}
      </div>
    </blockquote>
    <q-separator class="q-ma-md" />
    <blockquote class="otro-blockquote">
      <div class="text-center q-ma-md">
        if you want check out this word in detail,I put the original link below.
        <br/>
        <a href="https://www.merriam-webster.com/word-of-the-day" target="_blank">link to {{word}}</a>
      </div>
    </blockquote>
  </div>

</div>

</template>

<script>

import {axios} from "boot/axios";

export default {
  name: "dailyWord",
  data(){

    return{
      mean:null,
      context:null,
      didyouknow:null,
      word:null,
      date:null,
      id:null
    }
  },
  mounted() {
    let url = process.env.API_URL;
    axios.get(url+'/api/study/daily_word').then(res=>{
      let data = res.data
      this.mean = data['mean']
      this.context = data['context']
      this.word = data['word']
      this.didyouknow = data['didyouknow']
      this.date = data['date']
      this.id = data['id']
    })
  }
}
</script>

<style scoped>
.title{
  font-weight: normal;
}

.blockquote {
  position: relative;
}
.blockquote p {
  font-family: "Utopia-italic";
  font-size: 15px;
  font-weight: 700px;
  text-align: center;
}

/*blockquote p::before {
    content: "\f095";
    font-family: FontAwesome;
   display: inline-block;
   padding-right: 6px;
   vertical-align: middle;
  font-size: 180px;
 }*/

.blockquote:before {
  position: absolute;
  font-family: 'FontAwesome';
  top: 0;

  content:"\f10d";
  font-size: 200px;
  color: rgba(0,0,0,0.1);

}

.blockquote::after {
  content: "";
  top: 20px;
  left: 50%;
  margin-left: -100px;
  position: absolute;
  border-bottom: 3px solid #bf0024;
  height: 3px;
  width: 200px;
}

.otro-blockquote{
  font-size: 10px;
  width:90%;
  margin:50px auto;
  font-family:Open Sans;
  font-style:italic;
  color: #555555;
  padding:1.2em 30px 1.2em 75px;
  border-left:8px solid #78C0A8 ;
  line-height:1.6;
  position: relative;
  background:#EDEDED;
}

.otro-blockquote::before{
  font-family:Arial;
  content: "\201C";
  color:#78C0A8;
  font-size:4em;
  position: absolute;
  left: 10px;
  top:-10px;
}

.otro-blockquote::after{
  content: '';
}

.otro-blockquote span{
  display:block;
  color:#333333;
  font-style: normal;
  font-weight: bold;
  margin-top:1em;
}
/* cyrillic-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWV0ewJER.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWVQewJER.woff2) format('woff2');
  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWVwewJER.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWVMewJER.woff2) format('woff2');
  unicode-range: U+0370-03FF;
}
/* hebrew */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWVIewJER.woff2) format('woff2');
  unicode-range: U+0590-05FF, U+200C-2010, U+20AA, U+25CC, U+FB1D-FB4F;
}
/* vietnamese */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWV8ewJER.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWV4ewJER.woff2) format('woff2');
  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Open Sans';
  font-style: italic;
  font-weight: 400;
  font-stretch: 100%;
  src: url(../assets/memQYaGs126MiZpBA-UFUIcVXSCEkx2cmqvXlWq8tWZ0Pw86hd0Rk8ZkWVAewA.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}
</style>
