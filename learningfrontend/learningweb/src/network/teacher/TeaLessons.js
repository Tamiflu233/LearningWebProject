import {request} from '../request';

export function findLessonsByTeaId(pageNow,pageSize,teaId) {
  return request({
    url: '/lesson/findLessonsBySchoolAndTea',
    method: 'get',
    params: {
      pageNow,
      pageSize,
      schoolId: null,
      teaId
    },
    timeout: 5000
  })
}