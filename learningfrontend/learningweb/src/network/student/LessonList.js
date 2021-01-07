import {request} from '../request';

export function findLessonsBySchoolAndTea(page,size, schoolId,teaId) {
  return request({
    url: '/lesson/findLessonsBySchoolAndTea',
    method: 'get',
    params: {
      pageNow: page,
      pageSize: size,
      schoolId,
      teaId
    },
    timeout: 5000
  })
}