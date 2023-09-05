import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '@/views/LoginView.vue' 
import Home from '@/views/HomeView.vue' 

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
