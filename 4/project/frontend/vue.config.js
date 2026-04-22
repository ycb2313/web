module.exports = {
  devServer: {
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/ssm-backend-1.0-SNAPSHOT',
        changeOrigin: true
      }
    }
  }
}