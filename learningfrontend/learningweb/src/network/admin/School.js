import {request} from '../request';

export function getAllSchools() {
  return request({
    url: '/school/findAllSchools',
    method: 'get',
    timeout: 5000
  })
}

export function getAllSchoolsByPage(page,size, conditions) {
  return request({
    url: '/school/findSpecificSchools',
    method: 'post',
    data: {
      pageNow: page,
      pageSize: size,
      schools: conditions
    },
    timeout: 5000
  })
}

export function deleteSchoolById(schoolId) {
  return request({
    url: '/school/deleteSchoolById',
    method: 'get',
    params: {
      schoolId: schoolId
    },
    timeout: 5000
  })
}

export function insertSchool(school) {
  return request({
    url: '/school/insertSchool',
    method: 'post',
    data: school,
    timeout: 5000
  })
}

export function updateSchool(school) {
  return request({
    url: '/school/updateSchool',
    method: 'post',
    data: school,
    timeout: 5000
  })
}