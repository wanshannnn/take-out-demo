import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  // server: {
  //   host: '0.0.0.0',
  //   // public: '0.0.0.0:5173', // 本地的ip:端口号
  //   port: 5173,
  //   open: true,
  //   proxy: {
  //     '/api': {
  //       // 前缀替换成代理地址： 5173 -> 8080 后端tomcat服务器端口号
  //       target: 'http://localhost:8080/admin',
  //       ws: false,
  //       secure: false,
  //       changeOrigin: true,
  //       // /api去掉，变成空串，因为它只是一个标识而已，并不是路径
  //       rewrite: (path) => path.replace(/^\/api/, '')
  //     }
  //   }
  // }
})
