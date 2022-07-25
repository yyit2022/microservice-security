import { createRouter, RouteRecordRaw, createWebHashHistory } from 'vue-router'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        component: () => import('@/view/index.vue')
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;