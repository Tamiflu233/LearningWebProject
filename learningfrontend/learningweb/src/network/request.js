/* 封装axios(可能有很多axios实例要导出，故不用export default) */
import axios from 'axios'
import { showLoading, hideLoading } from 'common/loading';
export function request(config) {

  const instance = axios.create({
    baseURL: 'http://localhost:8989/api',
    timeout: 5000,
    headers: {
      'Access-Control-Allow-Headers': 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With, yourHeaderField'
    }
  })

  // 2.axios拦截器
  instance.interceptors.request.use(config => {
    // 将token放入请求头
    if (localStorage.getItem('Authorization')) {
      config.headers.Authorization = localStorage.getItem('Authorization');
    }
    // console.log(config.headers);
    // 开启加载动画
    showLoading();
    return config //必须返回，不然组件里request的时候then里面拿不到，就会去err报错
  }, err => {
    // console.log(err);
    return err
  })
  // 2.2 响应拦截
  instance.interceptors.response.use(res => {
    // 结束加载动画
    hideLoading();
    // console.log(res);
    return res.data //会拦截响应，所以要把data返回
  }, err => {
    hideLoading();
    // console.log(err);
    return err
  })

  //3. 发送真正的网络请求
  return instance(config)

}