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

export function updateQuestion(question) {
  return request({
    url: '/question/updateQuestion',
    method: 'post',
    data: question,
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

export function addQuestion(question) {
  return request({
    url: '/question/addQuestion',
    method: 'post',
    data: question,
    timeout: 5000
  })
}

export function findTeachersByLesson(lessonId) {
  return request({
    url: 'teacher/findTeachersByLesson',
    method: 'get',
    params: {
      lessonId
    },
    timeout: 5000
  })
}