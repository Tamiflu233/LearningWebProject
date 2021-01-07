import {request} from '../request';

export function createStuAccount(accountInfo) {
  return request({
    url: '/account/createStuAccount',
    method: 'post',
    data: accountInfo,
    timeout: 5000
  })
}