import {createRouter, createWebHashHistory} from "vue-router";
import useLogin from "../stores/LoginStore"
import NProgress from 'nprogress'


const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/user/login/Index.vue'),
        meta: {
            // 标记该路由不需要登录
            noLogin: true
        }
    }, {
        //管理员登录界面
        path: '/manager',
        name: 'manager',
        component: () => import('../views/manager/login/Index.vue'),
        meta: {
            // 标记该路由不需要登录
            noLogin: true
        }
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/user/register/Index.vue'),
        meta: {
            // 标记该路由不需要登录
            noLogin: true
        }
    },

    {
        path: '/',
        name: 'main',
        component: () => import('../views/user/Index.vue'),
        redirect: '/home',
        children: [{
            path: '/stores',
            name: 'stores',
            component: () => import('../views/user/stores/Index.vue')
        }, {
            //首页
            path: '/home',
            name: 'home',
            component: () => import('../views/user/main/Index.vue')
        }, {
            //领养中心
            path: '/adoption',
            name: 'adoption',
            component: () => import('../views/user/adoption/Index.vue')
        }, {
            //宠物领养详细信息界面
            path: '/info',
            name: 'info',
            component: () => import('../views/user/adoption/Info/Index.vue')
        },{
            //宠物知识
            path: '/petKnowledge',
            name: 'petKnowledge',
            component: () => import('../views/user/petKnowledge/Index.vue')
        },{
            //宠物领养详细信息界面
            path: '/essay',
            name: 'essay',
            component: () => import('../views/user/petKnowledge/Essay.vue')
        },{
            //宠物转让界面
            path: '/transfer',
            name: 'transfer',
            component: () => import('../views/user/transfer/Index.vue')
        },{
            //宠物转让详情界面
            path: '/detailed',
            name: 'detailed',
            component: () => import('../views/user/transfer/Info/Index.vue')
        },]
    }, {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/manager/layout/Index.vue'),
        children: [{
            path: '/userDisplay',
            name: 'userDisplay',
            component: () => import('../views/manager/component/userDisplay/UserDisplay.vue')
        },]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

// 导航守卫
router.beforeEach((to, from) => {
    // 如果是目标路由不需要登录,则直接跳转
    NProgress.start()
    if (to.meta.noLogin) {
        return true;
    }
    const loginInfo = useLogin();
    if (loginInfo.isLogin()) {
        return true;
    } else {
        // 如果没有登录,则跳转到登录页面
        return {name: "login"};
    }
})
router.afterEach((to, from) => {
    NProgress.done()
})

export default router
