import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from "vuex-persistedstate"

import mutations from './mutations';
import actions from './actions';
import getters from './getters';

// 1.安装插件
Vue.use(Vuex);

// 2.创建Store对象
const state = {
  Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
  username: '',
  nickname: '',
  lessonInfo: {},
  question: {},
  answer: {},
  teaInfo: {}
}

const store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  plugins: [createPersistedState()]
})

// 3.挂载到Vue实例上

export default store
