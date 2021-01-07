import {request} from '../request';

export function findTeacherByUsername(username) {
  return request({
    url: '/teacher/findTeacherByUsername',
    method: 'get',
    params: {
      username
    },
    timeout: 5000
  })
}