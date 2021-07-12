import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store';
import ElementUI from 'element-ui';
import { Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import VueLazyload from 'vue-lazyload'
Vue.config.productionTip = false

Vue.use(VueLazyload,{
  loading: 'https://cdn.jsdelivr.net/gh/Tamiflu233/cdn/img/loading.gif'
})
// 挂载到$message上
Vue.prototype.$message = Message
// 将事件总线$bus添加到Vue的原型中
Vue.prototype.$bus = new Vue()

// 注册element-ui
Vue.use(ElementUI)

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
