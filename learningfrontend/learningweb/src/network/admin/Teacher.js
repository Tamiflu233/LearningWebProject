import {request} from '../request';


export function getAllTeachersByPage(page,size, conditions) {
  return request({
    url: '/teacher/findTeachers',
    method: 'post',
    data: {
      pageNow: page,
      pageSize: size,
      teacher: conditions
    },
    timeout: 5000
  })
}

export function findAllTeachers() {
  return request({
    url: '/teacher/findAllTeachers',
    method: 'get',
    timeout: 5000
  })
}

export function insertTeacher(teaInfo) {
  return request({
    url: '/teacher/insertTeaInfo',
    method: 'post',
    data: teaInfo,
    timeout: 5000
  })
}

export function deleteTeacher(teaId) {
  return request({
    url: '/teacher/deleteTeacherById',
    method: 'get',
    params: {
      teaId: teaId
    },
    timeout: 5000
  })
}

export function updateTeacher(teaInfo) {
  return request({
    url: '/teacher/updateTeaInfo',
    method: 'post',
    data: teaInfo,
    timeout: 5000
  })
}


