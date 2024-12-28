import { createRouter, createWebHistory } from 'vue-router'
import { constantRoutes } from '@/router/routes.ts'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes,
  scrollBehavior() {
    return {
      left: 0,
      top: 0,
    }
  }
})

export default router
