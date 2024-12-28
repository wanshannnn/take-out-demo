//任意路由
export const anyRoute = {
  path: '/:pathMatch(.*)*',
  redirect: '/404',
  name: 'Any',
}

//对外暴露配置路由(常量路由):全部用户都可以访问到的路由
export const constantRoutes = [
  {
    path: '/',
    component: () => import('../views/layout/index.vue'),
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('../views/dashboard/index.vue'),
      name: 'dashboard',
    },{
      path: 'statistics',
      component: () => import('../views/statistics/index.vue'),
      name: 'statistics',
    }, {
      path: 'order',
      component: () => import('../views/order/index.vue'),
      name: 'order',
    }, {
      path: 'category',
      component: () => import('../views/category/index.vue'),
      name: 'category',
    }, {
      path: 'category/add',
      name: 'category_add',
      component: () => import('../views/category/add.vue')
    }, {
      path: 'category/update',
      name: 'category_update',
      component: () => import('../views/category/update.vue')
    }, {
      path: 'dish',
      name: 'dish',
      component: () => import('../views/dish/index.vue')
    }, {
      path: 'dish/add',
      name: 'dish_add',
      component: () => import('../views/dish/add.vue')
    }, {
      path: 'setmeal',
      name: 'setmeal',
      component: () => import('../views/setmeal/index.vue')
    }, {
      path: 'setmeal/add',
      name: 'setmeal_add',
      component: () => import('../views/setmeal/add.vue')
    }, {
      path: 'employee',
      name: 'employee',
      component: () => import('../views/employee/index.vue')
    }, {
      path: 'employee/add',
      name: 'employee_add',
      component: () => import('../views/employee/add.vue')
    }, {
      path: 'employee/update',
      name: 'employee_update',
      component: () => import('../views/employee/update.vue')
    }]
  }, {
    //404
    path: '/404',
    component: () => import('@/views/404/index.vue'),
    name: '404',
  }, {
    path: '/login',
    name: 'login',
    // lazy loading
    component: () => import('../views/login/index.vue')
  }, {
    path: '/reg',
    name: 'reg',
    // lazy loading
    component: () => import('../views/reg/index.vue')
  },
  anyRoute
]