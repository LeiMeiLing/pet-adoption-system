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
        //个人中心主界面
        path: '/personalCenter',
        name: 'personalCenter',
        redirect:'/center',
        component: () => import('../views/user/personalCenter/Index.vue'),
        meta: {
            // 标记该路由不需要登录
            noLogin: true
        },
        //个人中心
        children: [{
            path: '/center',
            name: 'center',
            component: () => import('../views/user/personalCenter/Center.vue')
        },{//个人信息
            path: '/information',
            name: 'information',
            component: () => import('../views/user/personalCenter/Information.vue')
        },{//收货地址
            path: '/address',
            name: 'address',
            component: () => import('../views/user/personalCenter/Address.vue')
        },{//我的帖子
            path: '/invitation',
            name: 'invitation',
            component: () => import('../views/user/personalCenter/Invitation.vue')
        },{//领养申请
            path: '/application',
            name: 'application',
            component: () => import('../views/user/personalCenter/Application.vue')
        },{//转送宠物
            path: '/transfer',
            name: 'transfer',
            component: () => import('../views/user/personalCenter/Transfer.vue')
        },]
    },
    {
        path: '/',
        name: 'main',
        component: () => import('../views/user/Index.vue'),
        redirect: '/home',
        children: [{
            //商城界面
            path: '/stores',
            name: 'stores',
            component: () => import('../views/user/stores/Index.vue'),
        }, {
            //商品详情页
            path: '/storesDetail',
            name: 'storesDetail',
            component: () => import('../views/user/stores/storesDetail.vue'),
            props: { default: true, sidebar: false }
        }, {
            //首页
            path: '/home',
            name: 'home',
            component: () => import('../views/user/main/Index.vue')
        }, {
            //领养中心
            path: '/userAdoption',
            name: 'userAdoption',
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
            //宠物知识详细信息界面
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

        },{
            path:'/petInfo',
            name:"petInfo",
            component:()=>import('../views/manager/component/petManager/petManger.vue')
        },{
            path:'/adoption',
            name:"adoption",
            component:()=>import('../views/manager/component/AdoptionManger/AdoptionManger.vue')
        },{
            path:'/shopping',
            name:"shopping",
            component:()=>import('../views/manager/component/ShoppingManger/ShoppingManger.vue')
        },{
            path:'/comment',
            name:"comment",
            component:()=>import('../views/manager/component/CommentManger/CommentManger.vue')
        }


        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

//导航守卫
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
