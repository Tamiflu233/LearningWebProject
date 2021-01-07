import {request} from '../request';

export function addAnswer(answer) {
  return request({
    url: '/answer/addAnswer',
    method: 'post',
    data: answer,
    timeout: 5000
  })
}

export function updateAnswer(answer) {
  return request({
    url: '/answer/updateAnswer',
    method: 'post',
    data: answer,
    timeout: 5000
  })
}

export function deleteAnswer(aid,qid) {
  return request({
    url: '/answer/deleteAnswer',
    method: 'get',
    params: {
      aid,
      qid
    },
    timeout: 5000
  })
}

export function findAnswerByQid(qid) {
  return request({
    url: '/answer/findAnswerByQid',
    method: 'get',
    params: {
      qid
    },
    timeout: 5000
  })
}
export function deleteQuestion(qid) {
  return request({
    url: '/question/deleteQuestion',
    method: 'get',
    params: {
      qid
    },
    timeout: 5000
  })
}