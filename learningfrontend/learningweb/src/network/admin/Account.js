import {request} from '../request';


export function getAllTeaAccounts(page,size, conditions) {
  return request({
    url: '/account/findTeaAccounts',
    method: 'post',
    data: {
      pageNow: page,
      pageSize: size,
      teaId: conditions.teaId,
      accounts: conditions
    },
    timeout: 5000
  })
}

export function createTeaAccount(account) {
  return request({
    url: '/account/createTeaAccount',
    method: 'post',
    data: account,
    timeout: 5000
  })
}

export function deleteAccount(uid) {
  return request({
    url: '/account/deleteAccount',
    method: 'get',
    params: {
      uid: uid
    },
    timeout: 5000
  })
}

export function updateAccount(account) {
  return request({
    url: '/account/updateAccount',
    method: 'post',
    data: account,
    timeout: 5000
  })
}