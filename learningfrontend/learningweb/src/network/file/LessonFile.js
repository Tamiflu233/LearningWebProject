import {request} from '../request';

export function download(path) {
  return request({
    url: '/file/download',
    method: 'post',
    data: {
      filename: path
    },
    responseType: 'blob',
    timeout: 5000
  })
}
export function getFiles(pageNow,pageSize,lessonId,teaId) {
  return request({
    url: '/file/getFiles',
    method: 'get',
    params: {
      pageNow,pageSize,lessonId,teaId
    },
    timeout: 5000
  })
}
export function saveFileInfo(lessonId,teaId,name,url,teaName,size) {
  return request({
    url: '/file/saveFileInfo',
    method: 'post',
    data: 
      {
        id:null,
        lessonId: lessonId,
        teaId: teaId,
        name: name,
        url: url,
        teaName: teaName,
        date: null,
        size: size
      }
    ,
    timeout: 5000
  })
}
export function deleteFile(id,path) {
  return request({
    url: '/file/deleteFile',
    method: 'post',
    data: {
      id: id,
      path: path
    },
    timeout: 5000
  })
}