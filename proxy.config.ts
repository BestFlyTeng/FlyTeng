export default {
  '/api': {
    target: 'http://localhost:3000',
    changeOrigin: true,
    rewrite: (path) => path.replace(/^\/api/, '')
  },
  // '/api': {
  //   target: 'https://sso-sit.newpearl.com',
  //   changeOrigin: true,
  //   ws: true
  // }
};