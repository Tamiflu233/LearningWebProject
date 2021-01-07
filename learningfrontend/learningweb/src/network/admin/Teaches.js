import {request} from '../request';

export function deleteTeachesInfo(lessonId,teaId) {
  return request({
    url: '/teaches/deleteTeachesInfo',
    method: 'get',
    params: {
      lessonId,
      teaId
    },
    timeout: 5000
  })
}

export function addTeachesInfo(teachesInfo) {
  return request({
    url: '/teaches/addTeachesInfo',
    method: 'post',
    data: teachesInfo,
    timeout: 5000
  })
}

export function findTeachesInfo(pageNow,pageSize,condition) {
  return request({
    url: '/lesson/findTeachesInfo',
    method: 'post',
    data: {
      pageNow,
      pageSize,
      condition
    },
    timeout: 5000
  })
}