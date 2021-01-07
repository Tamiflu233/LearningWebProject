import {request} from '../request';

export function findAllLessons() {
  return request({
    url: '/lesson/findAllLessons',
    method: 'get',
    timeout: 5000
  })
}


export function findLessons(page,size, conditions) {
  return request({
    url: '/lesson/findLessons',
    method: 'post',
    data: {
      pageNow: page,
      pageSize: size,
      lessons: conditions
    },
    timeout: 5000
  })
}

export function updateLesson(lesson) {
  return request({
    url: '/lesson/updateLesson',
    method: 'post',
    data: lesson, 
    timeout: 5000
  })
}

export function insertLesson(lesson) {
  return request({
    url: '/lesson/insertLesson',
    method: 'post',
    data: lesson, 
    timeout: 5000
  })
}

export function deleteLessonById(lessonId) {
  return request({
    url: '/lesson/deleteLessonById',
    method: 'get',
    params: {
      lessonId: lessonId
    }, 
    timeout: 5000
  })
}