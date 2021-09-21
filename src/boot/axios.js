import { boot } from 'quasar/wrappers'
import axios  from "axios";
// "async" is optional;
// more info on params: s://v2.quasar.dev/quasar-cli/boot-files
axios.interceptors.request.use(config=>{
  if (localStorage.getItem('auth')){
    config.headers.Authorization=localStorage.getItem('auth')
  }
  return config
})
export default boot(async ({app}/* { app, router, ... } */) => {
  // something to do
    app.config.globalProperties.$axios = axios

})
export {axios}
