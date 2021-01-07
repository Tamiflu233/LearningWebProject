import {request} from '../request';

export function checkLogin(account) {
  return request({
    url: '/login/checkLogin',
    method: 'post',
    data: account,
    timeout: 5000
  })
}

export function getNickname(username) {
  return request({
    url: '/login/getNickname',
    method: 'get',
    params: {username},
    timeout: 5000
  })
}