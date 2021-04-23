
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    redirect:'/indexShow',
    children: [
      { path: '/indexShow', component: () => import('components/indexShow.vue') },
      {path: '/learning',component:()=>import('components/learning.vue')},
      {path: '/gift',component:()=>import('components/gift.vue')},
      {path: '/chart',component:()=>import('components/chart.vue')},
      {path: '/word',component:()=>import('components/word.vue')},
      {path: '/pic',component:()=>import('components/upload.vue')},
      {path:'/g-list',component:()=>import('components/giftchange.vue')}
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
