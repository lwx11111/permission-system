import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },

    // 配置前端服务地址和端口,默认端口是5173
    server: {
      host: '0.0.0.0',
      port: 8991,
      // 是否开启 https
      https: false,
    },
  }
})


