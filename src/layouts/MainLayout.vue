<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="  text-white" style="background-color:pink">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="left = !left" />
        <q-toolbar-title style="margin-left:8%;">
          JESSICA'S PRIVATE ZONE
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="left" side="left" bordered width="150" >
      <q-scroll-area class="fit" >
        <q-list padding class="menu-list">
          <q-item clickable v-ripple to="/indexshow">
            <q-item-section avatar>
              <q-icon name="inbox" />
            </q-item-section>

            <q-item-section>
              首页
            </q-item-section>
          </q-item>

          <q-item  to="/learning" clickable v-ripple>
            <q-item-section avatar >
              <q-icon :name="outlinedEdit" />
            </q-item-section>

            <q-item-section>
              积分
            </q-item-section>
          </q-item>
          <q-item  to="/chart" clickable v-ripple>
            <q-item-section avatar >
              <q-icon :name="outlinedTimeline" />
            </q-item-section>

            <q-item-section>
              曲线
            </q-item-section>
          </q-item>
          <q-item to="/gift" clickable v-ripple>
            <q-item-section avatar>
              <q-icon :name="outlinedCardGiftcard" />
            </q-item-section>

            <q-item-section>
              礼物
            </q-item-section>
          </q-item>

          <q-item to="/word" clickable v-ripple>
            <q-item-section avatar>
              <q-icon :name="outlinedSortByAlpha" />
            </q-item-section>

            <q-item-section>
              单词
            </q-item-section>
          </q-item>
          <q-item to="/pic" clickable v-ripple>
            <q-item-section avatar>
              <q-icon :name="outlineInsertPhoto" />
            </q-item-section>

            <q-item-section>
              上传照片
            </q-item-section>
          </q-item>
          <q-item to="/g-list" clickable v-ripple>
            <q-item-section avatar>
              <q-icon :name="mdiHistory" />
            </q-item-section>

            <q-item-section>
              兑换记录
            </q-item-section>
          </q-item>
        </q-list>

      </q-scroll-area>

    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import {outlinedSortByAlpha,outlinedInsertPhoto,outlinedCardGiftcard,outlinedEdit,outlinedTimeline,} from "@quasar/extras/material-icons-outlined"
import {mdiHistory } from "@quasar/extras/mdi-v5"
export default {
  data () {
    return {
      left: false
    }
  },
  created() {
    this.outlinedSortByAlpha=outlinedSortByAlpha
    this.outlineInsertPhoto = outlinedInsertPhoto
    this.outlinedCardGiftcard=outlinedCardGiftcard
    this.outlinedEdit=outlinedEdit
    this.outlinedTimeline=outlinedTimeline
    this.mdiHistory=mdiHistory
  }
}
</script>
