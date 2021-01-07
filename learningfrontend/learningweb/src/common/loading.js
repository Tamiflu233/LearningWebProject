import { Loading } from 'element-ui';

let loadingCount = 0;
let loading;
// 加载动画
const startLoading = () => {
  loading = Loading.service({
    lock: true,
    text: 'Loading...',
    spinner:'el-icon-loading',
    background: 'transparent'
  });
};
// 结束加载动画
const endLoading = () => {
  loading.close();
};

export const showLoading = () => {
  if (loadingCount === 0) {
    startLoading();
  }
  loadingCount += 1;
};

export const hideLoading = () => {
  if (loadingCount <= 0) {
    return;
  }
  loadingCount -= 1;
  if (loadingCount === 0) {
    endLoading();
  }
};