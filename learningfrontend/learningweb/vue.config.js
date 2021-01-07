module.exports = {
  // 对webpack.config.js进行配置,即configureWebpack
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets', //cli3/4 已经将src的别名设为@, cli2不可以用设置好的别名组成其他路径的别名,cli3/4 可以
        'common': '@/common',
        'components': '@/components',
        'network': '@/network',
        'views': '@/views',
      }
    }
  },
  lintOnSave: false,
}