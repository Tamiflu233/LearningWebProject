import {
  Change_Login,
  Save_Username,
  Save_Nickname,
  Change_Lesson_Info,
  Save_Question,
  Save_Answer,
  Save_Tea_Info,
} from './mutation-types';
export default {
  // mutations唯一的目的就是修改state中状态
  // mutations中的每一个方法尽可能完成的事件比较单一
  [Change_Login](state, payload) {
    state.Authorization = payload.Authorization;
    localStorage.setItem('Authorization',payload.Authorization)
  },
  [Save_Username](state, payload) {
    state.username = payload.username;
  },
  [Save_Nickname](state, payload) {
    state.nickname = payload.nickname;
  },
  [Change_Lesson_Info](state, payload) {
    state.lessonInfo = payload.lessonInfo
  },
  [Save_Question](state, payload) {
    state.question = payload.question
  },
  [Save_Answer](state, payload) {
    state.answer = payload.answer
  },
  [Save_Tea_Info](state, payload) {
    state.teaInfo = payload.teaInfo
  }
}