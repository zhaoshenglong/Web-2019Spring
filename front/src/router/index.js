/* eslint-disable indent */
import Vue from 'vue'
import Router from 'vue-router'
import SignIn from '@/view/home/sign/SignIn'
import SignUp from '@/view/home/sign/SignUp'
import HomeSwiper from '@/components/home/HomeSwiper'
import StoreMain from '@/view/main/StoreMain'
import Home from '@/view/home/Home'
import BookStore from '@/view/BookStore'
import BookDetail from '@/view/detail/BookDetail'
import SettingProfile from '@/view/setting/SettingProfile'
import SettingAccount from '@/view/setting/SettingAccount'
import SettingAddress from '@/view/setting/SettingAddress'
import Cart from '@/view/cart/Cart'
import Order from '@/view/order/Order'
import AdminBooks from '@/view/admin/ManageBooks'
import AdminUsers from '@/view/admin/ManageUsers'
import AdminOrders from '@/view/admin/ManageOrders'
import Admin from '@/view/admin/Admin'
Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            component: Home,
            children: [{
                    path: '/',
                    name: 'Home',
                    component: HomeSwiper
                },
                {
                    path: '/signIn',
                    name: 'SignIn',
                    component: SignIn
                },
                {
                    path: '/signUp',
                    name: 'SignUp',
                    component: SignUp
                }
            ]
        },
        {
            path: '/books',
            component: BookStore,
            children: [{
                    path: '/books',
                    name: 'StorePage',
                    components: {
                        main: StoreMain
                    }
                },
                {
                    path: '/books/:userid',
                    name: 'StorePageSigned',
                    components: {
                        main: StoreMain
                    }
                },
                {
                    path: '/detail/:bookName',
                    name: 'BookDetail',
                    components: {
                        main: BookDetail
                    }
                },
                {
                    path: '/home/:userid/setting/Profile',
                    name: 'SettingProfile',
                    components: {
                        main: SettingProfile
                    }
                },
                {
                    path: '/home/:userid/setting/Address',
                    name: 'SettingAddress',
                    components: {
                        main: SettingAddress
                    }
                },
                {
                    path: '/home/:userid/setting/Account',
                    name: 'SettingAccount',
                    components: {
                        main: SettingAccount
                    }
                },
                {
                    path: '/home/:userid/order',
                    name: 'Order',
                    components: {
                        main: Order
                    }
                },
                {
                    path: '/home/:userid/cart',
                    name: 'Cart',
                    components: {
                        main: Cart
                    }
                }
            ]
        },
        {
            path: '/home/admin/',
            component: Admin,
            children: [{
                    path: 'books',
                    name: 'ManageBooks',
                    component: AdminBooks
                },
                {
                    path: 'users',
                    name: 'ManageUsers',
                    component: AdminUsers
                },
                {
                    path: 'orders',
                    name: 'ManageOrders',
                    component: AdminOrders
                }
            ]
        }
    ]
})