import {request} from '../request';

export function findSchoolById(schoolId) {
  return request({
    url: '/school/findSchoolById',
    method: 'get',
    params: {
      schoolId
    },
    timeout: 5000
  })
}

export function findQuestions(pageNow,pageSize,lessonId,teaId,schoolId) {
  return request({
    url: '/question/findQuestions',
    method: 'get',
    params: {
      pageNow,
      pageSize,
      lessonId,
      teaId,
      schoolId
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